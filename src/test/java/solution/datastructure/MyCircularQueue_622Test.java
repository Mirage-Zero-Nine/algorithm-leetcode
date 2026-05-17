package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Random;

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

    @Test
    public void testIsEmptyOnNew() {
        MyCircularQueue_622 q = new MyCircularQueue_622(5);
        assertTrue(q.isEmpty());
        assertFalse(q.isFull());
    }

    @Test
    public void testFrontRearOnEmpty() {
        MyCircularQueue_622 q = new MyCircularQueue_622(3);
        assertEquals(-1, q.Front());
        assertEquals(-1, q.Rear());
        // also after enqueue then full dequeue
        q.enQueue(99);
        q.deQueue();
        assertEquals(-1, q.Front());
        assertEquals(-1, q.Rear());
    }

    @Test
    public void testEnQueueBeyondCapacity() {
        MyCircularQueue_622 q = new MyCircularQueue_622(2);
        assertTrue(q.enQueue(1));
        assertTrue(q.enQueue(2));
        assertFalse(q.enQueue(3));
        assertFalse(q.enQueue(4));
        // front/rear unchanged
        assertEquals(1, q.Front());
        assertEquals(2, q.Rear());
    }

    @Test
    public void testAlternatingEnqueueDequeueWrapsAround() {
        MyCircularQueue_622 q = new MyCircularQueue_622(3);
        // fill and drain alternately to force internal indices to wrap multiple times
        for (int i = 0; i < 20; i++) {
            assertTrue(q.enQueue(i));
            assertEquals(i, q.Front());
            assertEquals(i, q.Rear());
            assertTrue(q.deQueue());
        }
        assertTrue(q.isEmpty());
    }

    @Test
    public void testCapacityOneRepeatedOps() {
        MyCircularQueue_622 q = new MyCircularQueue_622(1);
        for (int i = 0; i < 10; i++) {
            assertTrue(q.isEmpty());
            assertFalse(q.deQueue());
            assertTrue(q.enQueue(i));
            assertTrue(q.isFull());
            assertFalse(q.enQueue(i + 100));
            assertEquals(i, q.Front());
            assertEquals(i, q.Rear());
            assertTrue(q.deQueue());
        }
    }

    @Test
    public void testFrontRearAfterVariousOps() {
        MyCircularQueue_622 q = new MyCircularQueue_622(4);
        q.enQueue(10);
        q.enQueue(20);
        q.enQueue(30);
        assertEquals(10, q.Front());
        assertEquals(30, q.Rear());
        q.deQueue(); // remove 10
        assertEquals(20, q.Front());
        assertEquals(30, q.Rear());
        q.enQueue(40);
        q.enQueue(50); // now full [20,30,40,50]
        assertEquals(20, q.Front());
        assertEquals(50, q.Rear());
        q.deQueue(); // remove 20
        q.deQueue(); // remove 30
        assertEquals(40, q.Front());
        assertEquals(50, q.Rear());
    }

    @Test
    public void testStressRandomCrossCheckWithArrayDeque() {
        int capacity = 50;
        MyCircularQueue_622 q = new MyCircularQueue_622(capacity);
        ArrayDeque<Integer> ref = new ArrayDeque<>();
        Random rng = new Random(42L);

        for (int i = 0; i < 10_000; i++) {
            int op = rng.nextInt(4);
            if (op == 0) { // enQueue
                int val = rng.nextInt(1000);
                if (ref.size() < capacity) {
                    assertTrue(q.enQueue(val));
                    ref.addLast(val);
                } else {
                    assertFalse(q.enQueue(val));
                }
            } else if (op == 1) { // deQueue
                if (ref.isEmpty()) {
                    assertFalse(q.deQueue());
                } else {
                    assertTrue(q.deQueue());
                    ref.pollFirst();
                }
            } else if (op == 2) { // Front
                if (ref.isEmpty()) {
                    assertEquals(-1, q.Front());
                } else {
                    assertEquals((int) ref.peekFirst(), q.Front());
                }
            } else { // Rear
                if (ref.isEmpty()) {
                    assertEquals(-1, q.Rear());
                } else {
                    assertEquals((int) ref.peekLast(), q.Rear());
                }
            }
            assertEquals(ref.isEmpty(), q.isEmpty());
            assertEquals(ref.size() == capacity, q.isFull());
        }
    }
}
