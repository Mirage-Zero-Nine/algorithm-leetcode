package playground;

import lombok.NonNull;

import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

/**
 * Design an in-memory booking service for one event with numbered seats.
 *
 * <p>There are {@code seatCount} seats, numbered from 1 through
 * {@code seatCount}. Reserving for a customer immediately assigns the lowest
 * free seat and creates a globally unique booking ID. A customer may own more
 * than one active booking, and a customer may reserve again after a cancellation.
 * When every seat is booked, reservation returns {@link Optional#empty()}.
 *
 * <p>Cancellation succeeds only for an active booking owned by the supplied
 * customer. It must not change state for an unknown ID, an already cancelled
 * booking, or a booking owned by someone else. Booking IDs are never reused,
 * even when their seat becomes free. Booking lookup includes cancelled history
 * and returns an immutable snapshot whose {@code active} field reflects the
 * state at lookup time. The free-seat count reports current availability.
 *
 * <p>Reject a non-positive seat count and blank customer IDs with
 * {@link IllegalArgumentException}; reject null customer IDs with
 * {@link NullPointerException}. A non-positive booking ID is treated like an
 * unknown ID by lookup and cancellation. Methods are intended for sequential
 * use; decide and document any concurrency policy if you extend the exercise.
 *
 * <p>The core invariant is that each seat is either free or belongs to exactly
 * one active booking, never both. Keep cancelled booking history available for
 * lookup, while ensuring it does not count as active or occupy a seat. Choose
 * the state representation and algorithms that maintain this invariant.
 *
 * <p>Keep the required scope to one event, immediate booking, cancellation,
 * booking lookup, and free-seat count. As an optional follow-up, design
 * temporary seat holds with expiration and explain how holds interact with
 * booking IDs, cancellations, and the same seat-ownership invariant.
 */
public class BookingService {

    public record Booking(long bookingId, String customerId, int seatNumber, boolean active) {
    }

    public enum CancelResult {
        CANCELLED,
        UNKNOWN_BOOKING,
        ALREADY_CANCELLED,
        WRONG_CUSTOMER
    }

    private final Queue<Integer> pq;
    private final Map<Long, Booking> map;
    // IDs remain unique across service instances and after cancellation; seat reuse never reuses history IDs.
    private static final AtomicLong BOOKING_ID = new AtomicLong(System.currentTimeMillis());

    /**
     * Creates a service for one event with seats numbered from 1 through {@code seatCount}.
     *
     * <p>A minimum-priority queue keeps every currently free seat ordered, so a reservation can take
     * the lowest number and a cancellation can return a seat for later reuse. A map records the latest
     * immutable booking snapshot by ID, including cancelled bookings, so ownership checks and history
     * lookup do not depend on which seat is currently occupied. These structures keep active bookings
     * and free seats disjoint while making the free count available from the queue size.
     *
     * <p>Time complexity: {@code O(seatCount)} initialization.
     * <p>Auxiliary space: {@code O(seatCount)} for the free seats; booking history grows with reservations.
     *
     * @param seatCount positive number of seats at the event
     * @throws IllegalArgumentException if {@code seatCount} is not positive
     */
    public BookingService(int seatCount) {
        if (seatCount <= 0) {
            throw new IllegalArgumentException("Seat count should be larger than 0");
        }
        pq = new PriorityQueue<>(IntStream.rangeClosed(1, seatCount).boxed().toList());
        map = new ConcurrentHashMap<>();
    }

    /**
     * Reserves the lowest-numbered free seat and stores a new active booking snapshot.
     * A monotonic ID source distinguishes every successful reservation, including reservations after
     * a cancellation has made the same seat available again. If the queue is empty, no booking is
     * recorded and the result is empty. The customer string is checked before state changes.
     *
     * <p>Time complexity: {@code O(log seatCount)} for the queue removal and map insertion.
     * <p>Auxiliary space: {@code O(1)} beyond the newly retained booking record.
     *
     * @param customerId non-null, non-blank customer identifier
     * @return an active booking snapshot, or empty when no seat is free
     * @throws IllegalArgumentException if {@code customerId} is blank
     * @throws NullPointerException     if {@code customerId} is null
     */
    public Optional<Booking> reserve(@NonNull String customerId) {
        if (customerId.isBlank()) {
            throw new IllegalArgumentException("customerId must not be blank");
        }
        if (pq.isEmpty()) {
            return Optional.empty();
        }
        long bookingId = BOOKING_ID.incrementAndGet();
        // Only successful reservations consume an ID or remove a seat from the free pool.
        Optional<Booking> output = Optional.of(new Booking(bookingId, customerId, pq.poll(), true));
        output.ifPresent(booking -> map.put(bookingId, booking));
        return output;
    }

    /**
     * Cancels an active booking only when its recorded customer matches {@code customerId}.
     * Unknown IDs, wrong owners, and already-cancelled records leave service state unchanged. A
     * successful cancellation replaces the map value with an inactive immutable snapshot and returns
     * its seat to the free-seat queue; this preserves history without making the old snapshot mutable.
     * The customer string is checked before any booking state changes.
     *
     * <p>Time complexity: {@code O(log seatCount)} on success; {@code O(1)} map work otherwise.
     * <p>Auxiliary space: {@code O(1)} beyond the replacement snapshot on success.
     *
     * @param bookingId  ID to cancel; non-positive values are treated as unknown
     * @param customerId non-null, non-blank customer identifier
     * @return the specific cancellation outcome
     * @throws IllegalArgumentException if {@code customerId} is blank
     * @throws NullPointerException     if {@code customerId} is null
     */
    public CancelResult cancel(long bookingId, @NonNull String customerId) {
        if (customerId.isBlank()) {
            throw new IllegalArgumentException("customerId must not be blank");
        }
        Booking booking = map.get(bookingId);

        if (booking == null) {
            return CancelResult.UNKNOWN_BOOKING;
        }

        if (!customerId.equals(booking.customerId)) {
            return CancelResult.WRONG_CUSTOMER;
        }

        if (!booking.active) {
            return CancelResult.ALREADY_CANCELLED;
        }

        // Replacing the value retains the cancelled history and leaves previously returned records unchanged.
        map.put(bookingId, new Booking(
                booking.bookingId,
                customerId,
                booking.seatNumber,
                false
        ));

        pq.add(booking.seatNumber);

        return CancelResult.CANCELLED;
    }

    /**
     * Returns the latest immutable snapshot for an ID, including cancelled bookings.
     * Unknown and non-positive IDs return empty, while a previously returned snapshot remains unchanged.
     *
     * <p>Time complexity: {@code O(1)} expected map lookup.
     * <p>Auxiliary space: {@code O(1)}.
     *
     * @param bookingId ID to look up
     * @return the booking snapshot, whether active or cancelled, if known
     */
    public Optional<Booking> findBooking(long bookingId) {
        return Optional.ofNullable(map.get(bookingId));
    }

    /**
     * Returns how many seats are currently available.
     *
     * <p>The free-seat queue contains each available seat exactly once, so its size is the count.
     *
     * <p>Time complexity: {@code O(1)}.
     * <p>Auxiliary space: {@code O(1)}.
     *
     * @return number of unoccupied seats
     */
    public int freeSeatCount() {
        return pq.size();
    }
}
