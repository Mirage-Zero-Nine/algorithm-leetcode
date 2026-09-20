package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class MyQueue_232Test {

    @Test
    public void testHappyCases() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1); q.push(2);
        assertEquals(1, q.peek());
        assertEquals(1, q.pop());
        assertFalse(q.empty());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyQueue_232 q = new MyQueue_232();
        assertTrue(q.empty());
        q.push(1);
        assertFalse(q.empty());
        assertEquals(1, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testLargeCase() {
        MyQueue_232 q = new MyQueue_232();
        for (int i = 1; i <= 5; i++) q.push(i);
        for (int i = 1; i <= 5; i++) assertEquals(i, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testPeekDoesNotRemove() {
        MyQueue_232 q = new MyQueue_232();
        q.push(42);
        assertEquals(42, q.peek());
        assertEquals(42, q.peek());
        assertFalse(q.empty());
    }

    @Test
    public void testInterleavedPushPop() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        q.push(2);
        assertEquals(1, q.pop());
        q.push(3);
        assertEquals(2, q.pop());
        assertEquals(3, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testSingleElement() {
        MyQueue_232 q = new MyQueue_232();
        q.push(99);
        assertEquals(99, q.peek());
        assertEquals(99, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testPushAfterAllPopped() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        q.pop();
        q.push(2);
        assertEquals(2, q.peek());
        assertEquals(2, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testFIFOOrder() {
        MyQueue_232 q = new MyQueue_232();
        q.push(10);
        q.push(20);
        q.push(30);
        assertEquals(10, q.pop());
        assertEquals(20, q.pop());
        assertEquals(30, q.pop());
    }

    @Test
    public void testMultiplePeeks() {
        MyQueue_232 q = new MyQueue_232();
        q.push(5);
        q.push(10);
        assertEquals(5, q.peek());
        q.pop();
        assertEquals(10, q.peek());
    }

    @Test
    public void testGiantCase() {
        MyQueue_232 q = new MyQueue_232();
        // LeetCode permits at most 100 calls, so use the exact documented limit.
        for (int i = 0; i < 50; i++) q.push(i);
        for (int i = 0; i < 50; i++) assertEquals(i, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testEmptyOnNewQueue() {
        MyQueue_232 q = new MyQueue_232();
        assertTrue(q.empty());
    }

    @Test
    public void testPushThenPeekReturnsFIFO() {
        MyQueue_232 q = new MyQueue_232();
        q.push(100);
        q.push(200);
        q.push(300);
        assertEquals(100, q.peek());
    }

    @Test
    public void testPushThenPopReturnsFIFO() {
        MyQueue_232 q = new MyQueue_232();
        q.push(7);
        q.push(8);
        assertEquals(7, q.pop());
    }

    @Test
    public void testPeekDoesNotModifyQueue() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        q.push(2);
        q.push(3);
        assertEquals(1, q.peek());
        assertEquals(1, q.peek());
        assertEquals(1, q.peek());
        assertEquals(1, q.pop());
        assertEquals(2, q.pop());
        assertEquals(3, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testMultiplePushesThenMultiplePopsFIFO() {
        MyQueue_232 q = new MyQueue_232();
        for (int i = 1; i <= 10; i++) q.push(i * 10);
        for (int i = 1; i <= 10; i++) assertEquals(i * 10, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testMixedPushPopPeekSequence() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        assertEquals(1, q.peek());
        q.push(2);
        q.push(3);
        assertEquals(1, q.pop());
        assertEquals(2, q.peek());
        q.push(4);
        assertEquals(2, q.pop());
        assertEquals(3, q.pop());
        assertEquals(4, q.peek());
        assertEquals(4, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testEmptyAfterAllPopped() {
        MyQueue_232 q = new MyQueue_232();
        q.push(5);
        q.push(6);
        q.push(7);
        assertFalse(q.empty());
        q.pop();
        q.pop();
        q.pop();
        assertTrue(q.empty());
    }

    @Test
    public void testNegativeValues() {
        MyQueue_232 q = new MyQueue_232();
        q.push(-1);
        q.push(-100);
        q.push(Integer.MIN_VALUE);
        assertEquals(-1, q.pop());
        assertEquals(-100, q.peek());
        assertEquals(-100, q.pop());
        assertEquals(Integer.MIN_VALUE, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testLargeStress1000OpsRandomCrossCheck() {
        Random rng = new Random(42L);
        MyQueue_232 q = new MyQueue_232();
        ArrayDeque<Integer> ref = new ArrayDeque<>();

        // Keep this stateful oracle within the problem's 100-call limit.
        for (int i = 0; i < 100; i++) {
            int op = rng.nextInt(3);
            if (op == 0 || ref.isEmpty()) {
                int val = rng.nextInt(9) + 1;
                q.push(val);
                ref.addLast(val);
            } else if (op == 1) {
                assertEquals(ref.peekFirst(), q.peek(), "peek mismatch at op " + i);
            } else {
                assertEquals(ref.pollFirst(), q.pop(), "pop mismatch at op " + i);
            }
            assertEquals(ref.isEmpty(), q.empty(), "empty mismatch at op " + i);
        }
    }

    @Test
    public void testOfficialExample() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        q.push(2);
        assertEquals(1, q.peek());
        assertEquals(1, q.pop());
        assertFalse(q.empty());
        assertEquals(2, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testLegalValueBoundsRemainFIFO() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        q.push(9);
        q.push(1);
        assertEquals(1, q.pop());
        assertEquals(9, q.peek());
        assertEquals(9, q.pop());
        assertEquals(1, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testDuplicateValuesAreNotCollapsed() {
        MyQueue_232 q = new MyQueue_232();
        for (int i = 0; i < 6; i++) q.push(7);
        for (int i = 0; i < 6; i++) {
            assertEquals(7, q.peek());
            assertEquals(7, q.pop());
        }
        assertTrue(q.empty());
    }

    @Test
    public void testSignedIntegerValuesSupportedByClass() {
        MyQueue_232 q = new MyQueue_232();
        int[] values = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
        for (int value : values) q.push(value);
        for (int value : values) assertEquals(value, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testRepeatedTransferFromFrontToBack() {
        MyQueue_232 q = new MyQueue_232();
        q.push(1);
        q.push(2);
        assertEquals(1, q.pop());
        q.push(3);
        q.push(4);
        assertEquals(2, q.peek());
        assertEquals(2, q.pop());
        q.push(5);
        assertEquals(3, q.pop());
        assertEquals(4, q.pop());
        assertEquals(5, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testPushAfterPeekForcesNoReordering() {
        MyQueue_232 q = new MyQueue_232();
        q.push(10);
        q.push(20);
        assertEquals(10, q.peek());
        q.push(30);
        assertEquals(10, q.pop());
        assertEquals(20, q.peek());
        assertEquals(20, q.pop());
        assertEquals(30, q.pop());
    }

    @Test
    public void testAlternatingSingleElementLifecycles() {
        MyQueue_232 q = new MyQueue_232();
        for (int value = -5; value <= 5; value++) {
            assertTrue(q.empty());
            q.push(value);
            assertFalse(q.empty());
            assertEquals(value, q.peek());
            assertEquals(value, q.pop());
        }
        assertTrue(q.empty());
    }

    @Test
    public void testEmptyChecksDoNotChangeState() {
        MyQueue_232 q = new MyQueue_232();
        assertTrue(q.empty());
        assertTrue(q.empty());
        q.push(4);
        assertFalse(q.empty());
        assertFalse(q.empty());
        assertEquals(4, q.pop());
        assertTrue(q.empty());
    }

    @Test
    public void testIndependentInstancesDoNotShareStacks() {
        MyQueue_232 first = new MyQueue_232();
        MyQueue_232 second = new MyQueue_232();
        first.push(1);
        second.push(2);
        first.push(3);
        assertEquals(1, first.pop());
        assertEquals(2, second.peek());
        assertEquals(3, first.peek());
        assertEquals(2, second.pop());
        assertTrue(second.empty());
        assertFalse(first.empty());
    }

    @Test
    public void testSeededOracleWithEarlyAndLateTransfers() {
        runSeededOracle(7L, 100);
    }

    @Test
    public void testSeededOracleWithDuplicateHeavyValues() {
        runSeededOracle(19L, 100);
    }

    @Test
    public void testSeededOracleWithLongMostlyPopPhases() {
        MyQueue_232 q = new MyQueue_232();
        ArrayDeque<Integer> ref = new ArrayDeque<>();
        int calls = 0;
        for (int phase = 0; phase < 10; phase++) {
            for (int offset = 0; offset < 4; offset++) {
                int value = phase * 4 + offset;
                q.push(value);
                ref.addLast(value);
                calls++;
                assertQueueMatches(q, ref, "push phase " + phase);
            }
            for (int peek = 0; peek < 2; peek++) {
                assertEquals(ref.peekFirst(), q.peek());
                calls++;
            }
            for (int offset = 0; offset < 4; offset++) {
                assertEquals(ref.removeFirst(), q.pop());
                calls++;
                assertQueueMatches(q, ref, "pop phase " + phase);
            }
        }
        assertEquals(100, calls);
        assertTrue(q.empty());
    }

    @Test
    public void testSeededOracleWithSignedImplementationValues() {
        MyQueue_232 q = new MyQueue_232();
        ArrayDeque<Integer> ref = new ArrayDeque<>();
        int[] values = {Integer.MIN_VALUE, Integer.MAX_VALUE, -42, 0, 42};
        for (int value : values) {
            q.push(value);
            ref.addLast(value);
            assertQueueMatches(q, ref, "signed push");
        }
        while (!ref.isEmpty()) {
            assertEquals(ref.peekFirst(), q.peek());
            assertEquals(ref.removeFirst(), q.pop());
            assertQueueMatches(q, ref, "signed pop");
        }
    }

    private void runSeededOracle(long seed, int calls) {
        Random rng = new Random(seed);
        MyQueue_232 q = new MyQueue_232();
        ArrayDeque<Integer> ref = new ArrayDeque<>();
        for (int i = 0; i < calls; i++) {
            int operation = rng.nextInt(3);
            if (ref.isEmpty() || operation == 0) {
                int value = rng.nextInt(9) + 1;
                q.push(value);
                ref.addLast(value);
            } else if (operation == 1) {
                assertEquals(ref.peekFirst(), q.peek(), "peek mismatch at call " + i);
            } else {
                assertEquals(ref.removeFirst(), q.pop(), "pop mismatch at call " + i);
            }
            assertQueueMatches(q, ref, "state at call " + i);
        }
    }

    private void assertQueueMatches(MyQueue_232 queue, ArrayDeque<Integer> reference, String context) {
        assertEquals(reference.isEmpty(), queue.empty(), context + ": empty");
        if (!reference.isEmpty()) {
            assertEquals(reference.peekFirst(), queue.peek(), context + ": front");
        }
    }
}
