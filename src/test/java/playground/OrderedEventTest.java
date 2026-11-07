package playground;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderedEventTest {
    @Test
    void rejectsNonPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new OrderedEvent(0));
        assertThrows(IllegalArgumentException.class, () -> new OrderedEvent(-1));
    }

    @Test
    void emptyStringIsAValidSingleEventValue() {
        OrderedEvent buffer = new OrderedEvent(1);
        OrderedEvent.InsertResult result = buffer.insert(1, "");
        assertEvents(result.events, event(1, ""));
        assertStatus(result, 2, 0);
    }

    @Test
    void outOfOrderEventWaitsForGapAndThenDrainsTogether() {
        OrderedEvent buffer = new OrderedEvent(3);
        assertResult(buffer.insert(2, "b"), List.of(), 1, 1);
        assertResult(buffer.insert(1, "a"), List.of(event(1, "a"), event(2, "b")), 3, 0);
        assertResult(buffer.insert(3, "c"), List.of(event(3, "c")), 4, 0);
    }

    @Test
    void equalRetryWhilePendingDoesNotIncreaseCount() {
        OrderedEvent buffer = new OrderedEvent(4);
        assertResult(buffer.insert(3, "same"), List.of(), 1, 1);
        OrderedEvent.InsertResult retry = buffer.insert(3, new String("same"));
        assertResult(retry, List.of(), 1, 1);
    }

    @Test
    void equalRetryAfterReturnLeavesStateUnchangedAndHasFreshEmptyList() {
        OrderedEvent buffer = new OrderedEvent(1);
        buffer.insert(1, "done");
        OrderedEvent.InsertResult retry = buffer.insert(1, "done");
        assertResult(retry, List.of(), 2, 0);
        assertNotSame(buffer.insert(1, "done").events, retry.events);
    }

    @Test
    void conflictingPendingDuplicateIsRejectedWithoutChangingState() {
        OrderedEvent buffer = new OrderedEvent(3);
        buffer.insert(2, "original");
        assertThrows(IllegalArgumentException.class, () -> buffer.insert(2, "other"));
        assertResult(buffer.insert(1, "first"),
                List.of(event(1, "first"), event(2, "original")), 3, 0);
    }

    @Test
    void conflictingAlreadyReturnedDuplicateIsRejectedWithoutChangingState() {
        OrderedEvent buffer = new OrderedEvent(2);
        buffer.insert(1, "original");
        assertThrows(IllegalArgumentException.class, () -> buffer.insert(1, "other"));
        assertResult(buffer.insert(1, "original"), List.of(), 2, 0);
        assertResult(buffer.insert(2, "next"), List.of(event(2, "next")), 3, 0);
    }

    @Test
    void rejectsSequenceBelowRange() {
        OrderedEvent buffer = new OrderedEvent(2);
        assertThrows(IllegalArgumentException.class, () -> buffer.insert(0, "x"));
        assertResult(buffer.insert(1, "valid"), List.of(event(1, "valid")), 2, 0);
    }

    @Test
    void rejectsSequenceAboveRangeWithoutChangingState() {
        OrderedEvent buffer = new OrderedEvent(2);
        assertThrows(IllegalArgumentException.class, () -> buffer.insert(3, "x"));
        assertResult(buffer.insert(1, "valid"), List.of(event(1, "valid")), 2, 0);
    }

    @Test
    void rejectsNullValueWithoutChangingState() {
        OrderedEvent buffer = new OrderedEvent(2);
        assertThrows(NullPointerException.class, () -> buffer.insert(1, null));
        assertResult(buffer.insert(1, "valid"), List.of(event(1, "valid")), 2, 0);
    }

    @Test
    void multipleGapsKeepPendingCountAccurateAcrossDrains() {
        OrderedEvent buffer = new OrderedEvent(6);
        assertResult(buffer.insert(6, "f"), List.of(), 1, 1);
        assertResult(buffer.insert(3, "c"), List.of(), 1, 2);
        assertResult(buffer.insert(5, "e"), List.of(), 1, 3);
        assertResult(buffer.insert(4, "d"), List.of(), 1, 4);
        assertResult(buffer.insert(2, "b"), List.of(), 1, 5);
        assertResult(buffer.insert(1, "a"), List.of(event(1, "a"), event(2, "b"),
                event(3, "c"), event(4, "d"), event(5, "e"), event(6, "f")), 7, 0);
    }

    @Test
    void eachInsertionReturnsOnlyItsNewlyAvailableEvents() {
        OrderedEvent buffer = new OrderedEvent(4);
        OrderedEvent.InsertResult first = buffer.insert(1, "a");
        assertEvents(first.events, event(1, "a"));
        assertResult(buffer.insert(3, "c"), List.of(), 2, 1);
        assertResult(buffer.insert(2, "b"), List.of(event(2, "b"), event(3, "c")), 4, 0);
        assertResult(buffer.insert(4, "d"), List.of(event(4, "d")), 5, 0);
        assertEvents(first.events, event(1, "a"));
    }

    @Test
    void sequentialArrivalsReleaseOneAtATime() {
        OrderedEvent buffer = new OrderedEvent(8);
        for (int sequence = 1; sequence <= 8; sequence++) {
            assertResult(buffer.insert(sequence, "v" + sequence),
                    List.of(event(sequence, "v" + sequence)), sequence + 1L, 0);
        }
    }

    @Test
    void exhaustiveAndLargeChecksRunInKillableProcess() throws Exception {
        String classPath = System.getProperty("surefire.test.class.path",
                System.getProperty("java.class.path"));
        Path javaExecutable = Path.of(System.getProperty("java.home"), "bin", "java");
        Process process = new ProcessBuilder(javaExecutable.toString(), "-cp", classPath,
                ExhaustiveAndStressRunner.class.getName()).redirectErrorStream(true).start();
        if (!process.waitFor(12, java.util.concurrent.TimeUnit.SECONDS)) {
            process.destroyForcibly();
            process.waitFor();
            String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            throw new AssertionError("Isolated exhaustive/stress checks exceeded 12 seconds:\n" + output);
        }
        String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(0, process.exitValue(), output);
    }

    public static class ExhaustiveAndStressRunner {
        public static void main(String[] args) {
            for (int capacity = 1; capacity <= 3; capacity++) {
                enumerate(1, new ArrayList<>(), capacity, 5);
            }
            checkLargeOutOfOrderDrain();
        }

        private static void enumerate(int firstNotRun, List<String> history, int capacity, int maxLength) {
            if (firstNotRun > maxLength) return;
            // Check every operation prefix up to length five for capacities 1..3,
            // using all sequence/value pairs with values "a" and "b".
            for (int sequence = 1; sequence <= capacity; sequence++) {
                for (String value : List.of("a", "b")) {
                    List<String> nextHistory = new ArrayList<>(history);
                    nextHistory.add(sequence + ":" + value);
                    verifyPrefix(capacity, nextHistory);
                    enumerate(firstNotRun + 1, nextHistory, capacity, maxLength);
                }
            }
        }

        private static void verifyPrefix(int capacity, List<String> operations) {
            OrderedEvent actual = new OrderedEvent(capacity);
            String[] model = new String[capacity];
            int nextExpectedIndex = 0;
            for (String operation : operations) {
                int colon = operation.indexOf(':');
                int sequence = Integer.parseInt(operation.substring(0, colon));
                String value = operation.substring(colon + 1);
                int slot = sequence - 1;
                List<OrderedEvent.Event> expectedEvents = new ArrayList<>();
                if (model[slot] == null) {
                    model[slot] = value;
                } else if (!model[slot].equals(value)) {
                    assertThrows(IllegalArgumentException.class, () -> actual.insert(sequence, value),
                            "operations=" + operations);
                    continue;
                }
                // Recompute the full known prefix from sequence one, then report
                // only entries beyond the prefix returned on the previous call.
                int knownPrefixEnd = 0;
                while (knownPrefixEnd < capacity && model[knownPrefixEnd] != null) knownPrefixEnd++;
                for (int index = nextExpectedIndex; index < knownPrefixEnd; index++) {
                    expectedEvents.add(new OrderedEvent.Event(index + 1, model[index]));
                }
                nextExpectedIndex = Math.max(nextExpectedIndex, knownPrefixEnd);
                int pending = 0;
                for (int index = nextExpectedIndex; index < capacity; index++) {
                    if (model[index] != null) pending++;
                }
                OrderedEvent.InsertResult result = actual.insert(sequence, value);
                assertResult(result, expectedEvents, nextExpectedIndex + 1L, pending);
            }
        }

        private static void checkLargeOutOfOrderDrain() {
            int size = 100_000;
            OrderedEvent buffer = new OrderedEvent(size);
            for (int sequence = size; sequence > 1; sequence--) {
                OrderedEvent.InsertResult result = buffer.insert(sequence, "v" + sequence);
                assertEquals(0, result.events.size(), "out-of-order sequence " + sequence);
            }
            OrderedEvent.InsertResult drain = buffer.insert(1, "v1");
            assertEquals(size, drain.events.size());
            for (int index = 0; index < size; index++) {
                OrderedEvent.Event item = drain.events.get(index);
                assertEquals(index + 1, item.sequenceNumber);
                assertEquals("v" + (index + 1), item.value);
            }
            assertStatus(drain, size + 1L, 0);
        }
    }

    private static OrderedEvent.Event event(int sequence, String value) {
        return new OrderedEvent.Event(sequence, value);
    }

    private static void assertResult(OrderedEvent.InsertResult actual,
                                     List<OrderedEvent.Event> expectedEvents,
                                     long expectedNext, int expectedPending) {
        assertEvents(actual.events, expectedEvents.toArray(OrderedEvent.Event[]::new));
        assertStatus(actual, expectedNext, expectedPending);
    }

    private static void assertStatus(OrderedEvent.InsertResult actual, long next, int pending) {
        assertEquals(next, actual.nextExpected);
        assertEquals(pending, actual.pendingCount);
    }

    private static void assertEvents(List<OrderedEvent.Event> actual,
                                     OrderedEvent.Event... expected) {
        assertEquals(expected.length, actual.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].sequenceNumber, actual.get(i).sequenceNumber);
            assertEquals(expected[i].value, actual.get(i).value);
        }
    }
}
