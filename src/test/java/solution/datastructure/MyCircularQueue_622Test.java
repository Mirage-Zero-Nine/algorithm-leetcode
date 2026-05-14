package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyCircularQueue_622Test {

    @Test
    public void testHappyCases() {
        MyCircularQueue_622 q = new MyCircularQueue_622(3);
        assertTrue(q.enQueue(1));
        assertTrue(q.enQueue(2));
        assertTrue(q.enQueue(3));
        assertFalse(q.enQueue(4));
        assertEquals(3, q.Rear());
        assertTrue(q.isFull());
        assertTrue(q.deQueue());
        assertTrue(q.enQueue(4));
        assertEquals(4, q.Rear());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyCircularQueue_622 q = new MyCircularQueue_622(1);
        assertTrue(q.isEmpty());
        assertEquals(-1, q.Front());
        assertEquals(-1, q.Rear());
        assertFalse(q.deQueue());
    }

    @Test
    public void testLargeCase() {
        MyCircularQueue_622 q = new MyCircularQueue_622(5);
        for (int i = 1; i <= 5; i++) assertTrue(q.enQueue(i));
        assertTrue(q.isFull());
        for (int i = 0; i < 5; i++) assertTrue(q.deQueue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testFrontAndRearAfterEnqueue() {
        MyCircularQueue_622 q = new MyCircularQueue_622(3);
        q.enQueue(10);
        assertEquals(10, q.Front());
        assertEquals(10, q.Rear());
        q.enQueue(20);
        assertEquals(10, q.Front());
        assertEquals(20, q.Rear());
    }

    @Test
    public void testWrapAround() {
        MyCircularQueue_622 q = new MyCircularQueue_622(3);
        q.enQueue(1);
        q.enQueue(2);
        q.enQueue(3);
        q.deQueue();
        q.enQueue(4);
        assertEquals(2, q.Front());
        assertEquals(4, q.Rear());
    }

    @Test
    public void testSizeOneQueue() {
        MyCircularQueue_622 q = new MyCircularQueue_622(1);
        assertTrue(q.enQueue(5));
        assertTrue(q.isFull());
        assertFalse(q.enQueue(6));
        assertEquals(5, q.Front());
        assertEquals(5, q.Rear());
        assertTrue(q.deQueue());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testDequeueOnEmpty() {
        MyCircularQueue_622 q = new MyCircularQueue_622(2);
        assertFalse(q.deQueue());
    }

    @Test
    public void testEnqueueOnFull() {
        MyCircularQueue_622 q = new MyCircularQueue_622(2);
        assertTrue(q.enQueue(1));
        assertTrue(q.enQueue(2));
        assertFalse(q.enQueue(3));
    }

    @Test
    public void testMultipleWrapArounds() {
        MyCircularQueue_622 q = new MyCircularQueue_622(3);
        for (int round = 0; round < 5; round++) {
            assertTrue(q.enQueue(round));
            assertEquals(round, q.Rear());
            assertTrue(q.deQueue());
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void testGiantCase() {
        MyCircularQueue_622 q = new MyCircularQueue_622(1000);
        for (int i = 0; i < 1000; i++) assertTrue(q.enQueue(i));
        assertTrue(q.isFull());
        assertEquals(0, q.Front());
        assertEquals(999, q.Rear());
        for (int i = 0; i < 1000; i++) assertTrue(q.deQueue());
        assertTrue(q.isEmpty());
        assertEquals(-1, q.Front());
    }
}
