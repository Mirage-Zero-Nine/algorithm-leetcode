package playground;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement an in-memory ordered buffer for one event
 * stream containing a fixed positive number {@code n} of numbered events. Each
 * event has an integer sequence number from {@code 1} through {@code n} and a
 * non-null string value; the empty string is allowed. The buffer represents a
 * single stream and is not shared across streams.
 *
 * <p>Define an {@code Event(sequence, value)} type for one numbered event and
 * an {@code InsertResult(events, nextExpected, pendingCount)} type for the
 * result of an insertion. The API to implement is sketched below; choose the
 * concrete Java types and accessor names for the status operation.
 *
 * <pre>{@code
 * OrderedEventBuffer(int n)
 * InsertResult insert(int sequence, String value)
 * // status accessor: report nextExpected and pendingCount
 * }</pre>
 *
 * <p>An insertion returns all newly available events in sequence order. Events
 * after a gap remain pending until that gap is filled. A retry with the same
 * sequence and equal value returns no events and leaves the buffer unchanged,
 * including when that event was already returned. A retry with a different
 * value for an existing sequence is rejected atomically and leaves the buffer
 * unchanged. Decide and document the explicit error used for conflicting
 * duplicates. Invalid {@code n} or
 * sequence numbers should be rejected with {@link IllegalArgumentException};
 * a null value should be rejected with {@link NullPointerException}.
 *
 * <p>{@code nextExpected} is the first sequence number not yet returned. It
 * should be a {@code long} so a completed stream can report {@code n + 1} even
 * when {@code n == Integer.MAX_VALUE}. {@code pendingCount} is the number of
 * accepted events that have not yet been returned. The result from each
 * insertion includes every event made available by that insertion.
 *
 * <p>For example, with {@code n = 3}, inserting {@code (2, "b")} returns no
 * events, with {@code nextExpected = 1} and {@code pendingCount = 1}. Inserting
 * {@code (1, "a")} then returns events {@code (1, "a")} and {@code (2, "b")}
 * in that order, with {@code nextExpected = 3} and {@code pendingCount = 0}.
 * Inserting {@code (3, "c")} returns that event and reports
 * {@code nextExpected = 4}.
 *
 * <p>In tests, cover out-of-order arrivals and later gap filling, repeated
 * equal values before and after return, conflicting values, invalid numbers,
 * and state preservation after errors. Explain how much memory is retained for
 * pending events and duplicate history. Also explain why returning an event
 * does not mean downstream processing completed, and what additional design
 * would be needed to handle crashes and retries safely.
 */
public class OrderedEvent {
    private final String[] array;
    private int start;
    private int pending;

    /**
     * Initializes a buffer for exactly {@code n} numbered events.
     * <p>
     * A fixed array keeps each accepted value at its sequence number. When an
     * earlier missing event arrives, the buffer returns events in order until
     * the next number is missing. The stored values also let it check repeated
     * requests after events have been returned. The array uses O(n) slots from
     * construction onward, and accepted strings stay in memory for the buffer's
     * lifetime. {@code start} marks the next event to return. {@code pending}
     * counts received events still waiting to be returned.
     * <p>
     * Time complexity: O(n) to allocate and initialize the array.
     * <p>
     * Auxiliary space: O(n) array slots, plus retained event strings.
     *
     * @param n fixed positive number of events in this stream
     * @throws IllegalArgumentException if {@code n} is not positive
     */
    public OrderedEvent(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be larger than 0.");
        }

        array = new String[n];
        start = 0;
        pending = 0;
    }

    /**
     * Stores a new event, then returns available events starting with the next
     * number needed. It stops when an earlier event is missing. It checks a
     * repeated request before changing stored data, so the same value leaves
     * the buffer unchanged and a different value throws
     * {@link IllegalArgumentException}. Null values are rejected by {@link NonNull}.
     * Returning an event only means this buffer made it available; it does not
     * mean another part of a program finished processing it. Keeping data safe
     * across crashes would need saved state and a way to confirm processing.
     * <p>
     * Time complexity: O(k + 1) to return k events, or O(L) to compare a
     * repeated value of length L.
     * <p>
     * Auxiliary space: O(k) for the returned event list; retained storage is
     * already part of the buffer's O(n) history array and accepted values.
     *
     * @param sequence event sequence, from 1 through the configured capacity
     * @param value    event value; the empty string is allowed
     * @return newly consecutive events, next expected sequence, and pending count
     * @throws IllegalArgumentException if the sequence is outside the configured range,
     *                                  or an accepted sequence is retried with a different value
     * @throws NullPointerException     if value is null
     */
    public InsertResult insert(int sequence, @NonNull String value) {
        if (sequence > array.length || sequence <= 0) {
            throw new IllegalArgumentException("Sequence should be between 0 and n.");
        }
        String current = array[sequence - 1];
        if (current != null) {
            if (value.equals(current)) {
                // Check stored values first so a repeated request cannot change state.
                return new InsertResult(new ArrayList<>(), start + 1, pending);
            } else {
                throw new IllegalArgumentException("Invalid sequence value pair");
            }
        }

        // Store the value before checking whether this also makes later events ready.
        pending++;
        array[sequence - 1] = value;
        List<Event> events = new ArrayList<>();
        while (start < array.length && array[start] != null) {
            // Move forward so each event is returned once, and only in order.
            events.add(new Event(start + 1, array[start++]));
            pending--;
        }

        return new InsertResult(events, start + 1, pending);
    }

    /**
     * Result snapshot for one insertion; the event list contains only events newly returned then.
     */
    @AllArgsConstructor
    public static class InsertResult {
        final List<Event> events;
        final long nextExpected;
        final int pendingCount;
    }

    /**
     * One event emitted in sequence order.
     */
    @AllArgsConstructor
    public static class Event {
        final int sequenceNumber;
        final String value;
    }
}
