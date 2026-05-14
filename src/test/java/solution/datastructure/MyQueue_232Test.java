package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
