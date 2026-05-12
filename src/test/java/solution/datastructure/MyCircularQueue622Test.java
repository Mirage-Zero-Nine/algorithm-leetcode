package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyCircularQueue622Test {

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
}
