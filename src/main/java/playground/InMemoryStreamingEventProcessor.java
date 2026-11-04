package playground;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Stores events and queries the events in a fixed, lazy sliding time window.
 *
 * <p>An event is active when its timestamp is in {@code (now - windowMillis,
 * now]}. The timestamp is event time, and callers should not add future
 * events. Cleanup runs at the start of each query, so adding an event does not
 * remove old events until a query is made. The stream is intended for one
 * thread, and its window length must be positive.</p>
 *
 * <p>The timestamp map groups entries in chronological order. Its list value
 * is needed because several events may have the same timestamp. The type map
 * provides fast type lookup and a {@link LinkedHashMap} keeps the order in
 * which matching entries were added. Each index points to the same
 * {@link EventEntry}, so removing an expired entry updates both indexes and
 * the cached count and sum.</p>
 *
 * <p>Adding one event takes expected {@code O(log(T + 1))} time, where {@code T} is
 * the number of timestamp buckets, plus expected constant-time hash-map work.
 * A query's cleanup takes {@code O((E + 1) log(T + 1) + E)}, where {@code E}
 * is the number of expired entries; the extra one covers locating the range
 * even when nothing expires. Filtering then adds {@code O(K)} for {@code K}
 * active matching entries. The indexes use {@code O(N)} auxiliary space for
 * {@code N} retained entries. Cleanup is lazy, so a query can pay for entries
 * added since the previous query.</p>
 *
 * @author BorisMirage
 * Time: 2026/10/27 15:11
 * Created with IntelliJ IDEA
 */

public class InMemoryStreamingEventProcessor {
    private final NavigableMap<Long, List<EventEntry>> byTimestamp;
    private final Map<String, LinkedHashMap<UUID, EventEntry>> byType;
    private int count;
    private long sum;
    private final long windowMillis;
    private final Clock clock;

    public InMemoryStreamingEventProcessor(long windowMillis) {
        this(windowMillis, Clock.systemUTC());
    }

    /**
     * Creates a stream with a positive window and an injected clock.
     * Injecting a clock lets tests move time without sleeping.
     *
     * @param windowMillis positive window length in milliseconds
     * @param clock source of the current time in milliseconds
     */
    public InMemoryStreamingEventProcessor(long windowMillis, @NonNull Clock clock) {
        byTimestamp = new TreeMap<>();
        byType = new HashMap<>();
        if (windowMillis <= 0) {
            throw new IllegalArgumentException("windowMillis should be a positive number.");
        }
        this.windowMillis = windowMillis;
        this.clock = clock;
    }

    /**
     * Adds one event. A fresh UUID identifies this occurrence, even when the
     * same {@link Event} object is added more than once. This updates both
     * indexes and the cached count and sum; the input event is not changed.
     * Expected time is {@code O(log(T + 1))} plus expected constant-time map work.
     */
    public void add(@NonNull Event event) {
        UUID eventUuid = UUID.randomUUID();
        EventEntry entry = new EventEntry(eventUuid, event);
        // A list is required because different events may share one timestamp.
        byTimestamp.computeIfAbsent(event.timestamp, _ -> new ArrayList<>()).add(entry);
        // LinkedHashMap preserves arrival order while UUID gives each add its own identity.
        byType.computeIfAbsent(event.type, _ -> new LinkedHashMap<>());
        byType.get(event.type).putLast(eventUuid, entry);
        count++;
        sum += event.value;
    }

    /**
     * Returns the number of active events after lazy cleanup. Cleanup uses the
     * injected clock and changes this stream's cached state.
     */
    public int count() {
        removeExpiredEvent();
        return count;
    }

    /**
     * Returns the active values' arithmetic mean, truncated toward zero as
     * Java integer division specifies, or {@code 0} when no event is active.
     * Cleanup and its {@code O((E + 1) log(T + 1) + E)} cost happen before
     * division.
     */
    public long average() {
        removeExpiredEvent();
        return (count == 0) ? 0 : (sum / count);
    }

    private void removeExpiredEvent() {
        long now = clock.millis();
        long cutoff = now - windowMillis;
        // The window is strict at its lower edge: timestamps equal to the
        // cutoff expire, while timestamps greater than it remain active.
        Map<Long, List<EventEntry>> expired = byTimestamp.headMap(cutoff, true);
        expired.values().forEach(entryList -> {
            entryList.forEach(eventEntry -> {
                if (byType.containsKey(eventEntry.event.type) && byType.get(eventEntry.event.type).containsKey(eventEntry.id)) {
                    byType.get(eventEntry.event.type).remove(eventEntry.id);
                    // Remove empty type groups so a later filter sees no stale key.
                    if (byType.get(eventEntry.event.type).isEmpty()) {
                        byType.remove(eventEntry.event.type);
                    }
                }
                count--;
                sum -= eventEntry.event.value;
            });
        });
        // headMap is a backed TreeMap view; clearing it removes the same
        // entries from the timestamp index after both indexes are reconciled.
        expired.clear();
    }

    /**
     * Returns active events whose type exactly equals {@code filter}, in
     * arrival order. The returned list is independent of the indexes and is
     * unmodifiable; cleanup may change this stream but cannot change a list
     * already returned. Filtering takes {@code O(K)} time for {@code K}
     * matching active events after cleanup. Cleanup itself costs
     * {@code O((E + 1) log(T + 1) + E)} for {@code E} expired entries.
     */
    public List<Event> filter(@NonNull String filter) {
        removeExpiredEvent();
        return byType.containsKey(filter) ?
                byType.get(filter).values().stream().map(e -> e.event).toList()
                : new ArrayList<>();
    }

    @AllArgsConstructor
    @Getter
    public static class Event {
        private final long timestamp;
        @NonNull
        private final String type;
        private final int value;
        private final long id;
    }

    @AllArgsConstructor
    @Getter
    private static class EventEntry {
        private final UUID id;
        @NonNull
        private final Event event;
    }
}
