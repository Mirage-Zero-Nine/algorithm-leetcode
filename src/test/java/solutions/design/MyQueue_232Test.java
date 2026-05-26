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
        for (int i = 0; i < 1000; i++) q.push(i);
        for (int i = 0; i < 1000; i++) assertEquals(i, q.pop());
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

        for (int i = 0; i < 1000; i++) {
            int op = rng.nextInt(3);
            if (op == 0 || ref.isEmpty()) {
                int val = rng.nextInt(2001) - 1000;
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
}
