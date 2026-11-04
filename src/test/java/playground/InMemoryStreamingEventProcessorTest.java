package playground;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryStreamingEventProcessorTest {
    private static final long NOW = 10_000L;

    @Test
    void rejectsZeroWindow() {
        assertThrows(IllegalArgumentException.class, () -> new InMemoryStreamingEventProcessor(0, clock(NOW)));
    }

    @Test
    void rejectsNegativeWindow() {
        assertThrows(IllegalArgumentException.class, () -> new InMemoryStreamingEventProcessor(-1, clock(NOW)));
    }

    @Test
    void rejectsNullClock() {
        assertThrows(NullPointerException.class, () -> new InMemoryStreamingEventProcessor(1, null));
    }

    @Test
    void startsEmpty() {
        InMemoryStreamingEventProcessor stream = stream(100);
        assertEquals(0, stream.count());
        assertEquals(0, stream.average());
        assertTrue(stream.filter("missing").isEmpty());
    }

    @Test
    void countsAndAveragesActiveEvents() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "cpu", 4, 1));
        stream.add(event(NOW, "io", 8, 2));
        assertEquals(2, stream.count());
        assertEquals(6, stream.average());
    }

    @Test
    void filtersExactTypeInArrivalOrder() {
        InMemoryStreamingEventProcessor stream = stream(100);
        EventValues.add(stream, event(9_950, "cpu", 1, 1));
        EventValues.add(stream, event(9_910, "cpu", 2, 2));
        EventValues.add(stream, event(9_980, "other", 3, 3));
        assertEquals(List.of(1L, 2L), ids(stream.filter("cpu")));
    }

    @Test
    void supportsDuplicateTimestamps() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "same", 1, 1));
        stream.add(event(NOW, "same", 2, 2));
        assertEquals(2, stream.count());
        assertEquals(List.of(1L, 2L), ids(stream.filter("same")));
    }

    @Test
    void addingSameEventObjectCreatesTwoOccurrences() {
        InMemoryStreamingEventProcessor stream = stream(100);
        InMemoryStreamingEventProcessor.Event event = event(NOW, "same", 7, 42);
        stream.add(event);
        stream.add(event);
        assertEquals(2, stream.count());
        assertEquals(List.of(42L, 42L), ids(stream.filter("same")));
    }

    @Test
    void outOfOrderTimestampsDoNotChangeArrivalOrder() {
        InMemoryStreamingEventProcessor stream = stream(1_000);
        stream.add(event(9_900, "x", 1, 1));
        stream.add(event(9_100, "x", 2, 2));
        stream.add(event(9_500, "x", 3, 3));
        assertEquals(List.of(1L, 2L, 3L), ids(stream.filter("x")));
    }

    @Test
    void cutoffTimestampIsExpired() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW - 100, "x", 1, 1));
        stream.add(event(NOW - 99, "x", 2, 2));
        assertEquals(1, stream.count());
        assertEquals(List.of(2L), ids(stream.filter("x")));
    }

    @Test
    void timestampOneAfterCutoffRemains() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW - 99, "x", 1, 1));
        assertEquals(1, stream.count());
    }

    @Test
    void addingDoesNotAdvanceTimeOrCleanUp() {
        MutableClock clock = clock(NOW);
        InMemoryStreamingEventProcessor stream = new InMemoryStreamingEventProcessor(100, clock);
        stream.add(event(NOW - 100, "x", 1, 1));
        clock.setMillis(NOW + 1);
        stream.add(event(NOW + 1, "x", 2, 2));
        assertEquals(1, stream.count());
    }

    @Test
    void queriesCleanUpExpiredEntries() {
        MutableClock clock = clock(NOW);
        InMemoryStreamingEventProcessor stream = new InMemoryStreamingEventProcessor(100, clock);
        stream.add(event(NOW, "x", 1, 1));
        clock.setMillis(NOW + 100);
        assertEquals(0, stream.count());
        assertEquals(0, stream.count());
        assertEquals(0, stream.average());
    }

    @Test
    void oldEventIsExpiredOnFirstQuery() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW - 101, "x", 9, 1));
        assertEquals(0, stream.count());
        assertTrue(stream.filter("x").isEmpty());
    }

    @Test
    void expiryUpdatesAllTypesAndAverage() {
        MutableClock clock = clock(NOW);
        InMemoryStreamingEventProcessor stream = new InMemoryStreamingEventProcessor(100, clock);
        stream.add(event(NOW - 100, "a", 10, 1));
        stream.add(event(NOW - 99, "b", 20, 2));
        clock.setMillis(NOW);
        assertEquals(1, stream.count());
        assertEquals(20, stream.average());
        assertTrue(stream.filter("a").isEmpty());
        assertEquals(List.of(2L), ids(stream.filter("b")));
    }

    @Test
    void expiredTypeGroupIsRemoved() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW - 100, "only", 1, 1));
        assertTrue(stream.filter("only").isEmpty());
        assertTrue(stream.filter("ONLY").isEmpty());
    }

    @Test
    void typeMatchIsCaseSensitive() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "Cpu", 1, 1));
        assertTrue(stream.filter("cpu").isEmpty());
        assertEquals(List.of(1L), ids(stream.filter("Cpu")));
    }

    @Test
    void positiveAverageTruncatesTowardZero() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "x", 5, 1));
        stream.add(event(NOW, "x", 4, 2));
        assertEquals(4, stream.average());
    }

    @Test
    void negativeAverageTruncatesTowardZero() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "x", -5, 1));
        stream.add(event(NOW, "x", -4, 2));
        assertEquals(-4, stream.average());
    }

    @Test
    void sumUsesLongArithmeticForExtremeValues() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "x", Integer.MAX_VALUE, 1));
        stream.add(event(NOW, "x", Integer.MAX_VALUE, 2));
        stream.add(event(NOW, "x", Integer.MIN_VALUE, 3));
        stream.add(event(NOW, "x", Integer.MIN_VALUE, 4));
        assertEquals(4, stream.count());
        assertEquals(0, stream.average());
    }

    @Test
    void filterResultIsIndependentAndUnmodifiable() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "x", 1, 1));
        List<InMemoryStreamingEventProcessor.Event> result = stream.filter("x");
        assertNotSame(result, stream.filter("x"));
        assertThrows(UnsupportedOperationException.class, result::clear);
        assertEquals(1, stream.count());
    }

    @Test
    void noMatchReturnsEmptyList() {
        InMemoryStreamingEventProcessor stream = stream(100);
        stream.add(event(NOW, "x", 1, 1));
        assertTrue(stream.filter("y").isEmpty());
    }

    @Test
    void repeatedQueriesDoNotDoubleSubtract() {
        MutableClock clock = clock(NOW);
        InMemoryStreamingEventProcessor stream = new InMemoryStreamingEventProcessor(10, clock);
        stream.add(event(NOW, "x", Integer.MAX_VALUE, 1));
        clock.setMillis(NOW + 10);
        assertEquals(0, stream.average());
        assertEquals(0, stream.average());
        assertEquals(0, stream.count());
    }

    @Test
    void defaultConstructorUsesSystemClock() {
        InMemoryStreamingEventProcessor stream = new InMemoryStreamingEventProcessor(60_000);
        long now = System.currentTimeMillis();
        stream.add(event(now, "x", 1, 1));
        assertEquals(1, stream.count());
    }

    @Test
    void handlesTenThousandEvents() {
        InMemoryStreamingEventProcessor stream = stream(100);
        long expectedSum = 0;
        for (int i = 0; i < 10_000; i++) {
            int value = i % 101 - 50;
            expectedSum += value;
            stream.add(event(NOW, "bulk", value, i));
        }
        assertEquals(10_000, stream.count());
        assertEquals(expectedSum / 10_000, stream.average());
        assertEquals(10_000, stream.filter("bulk").size());
    }

    @Test
    void rejectsNullEvent() {
        assertThrows(NullPointerException.class, () -> stream(100).add(null));
    }

    @Test
    void rejectsNullFilter() {
        assertThrows(NullPointerException.class, () -> stream(100).filter(null));
    }

    @Test
    void rejectsNullEventType() {
        assertThrows(NullPointerException.class, () -> new InMemoryStreamingEventProcessor.Event(NOW, null, 1, 1));
    }

    @Test
    void eventGettersExposeInputValues() {
        InMemoryStreamingEventProcessor.Event event = event(7, "x", -3, 9);
        assertEquals(7, event.getTimestamp());
        assertEquals("x", event.getType());
        assertEquals(-3, event.getValue());
        assertEquals(9, event.getId());
    }

    private static InMemoryStreamingEventProcessor stream(long window) {
        return new InMemoryStreamingEventProcessor(window, clock(NOW));
    }

    private static MutableClock clock(long millis) {
        return new MutableClock(millis, ZoneId.of("UTC"));
    }

    private static InMemoryStreamingEventProcessor.Event event(long timestamp, String type, int value, long id) {
        return new InMemoryStreamingEventProcessor.Event(timestamp, type, value, id);
    }

    private static List<Long> ids(List<InMemoryStreamingEventProcessor.Event> events) {
        return events.stream().map(InMemoryStreamingEventProcessor.Event::getId).toList();
    }

    private static final class EventValues {
        private static void add(InMemoryStreamingEventProcessor stream, InMemoryStreamingEventProcessor.Event event) {
            stream.add(event);
        }
    }

    private static final class MutableClock extends Clock {
        private long now;
        private final ZoneId zone;

        private MutableClock(long now, ZoneId zone) {
            this.now = now;
            this.zone = zone;
        }

        private void setMillis(long now) {
            this.now = now;
        }

        @Override
        public ZoneId getZone() {
            return zone;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return new MutableClock(now, zone);
        }

        @Override
        public Instant instant() {
            return Instant.ofEpochMilli(now);
        }
    }
}
