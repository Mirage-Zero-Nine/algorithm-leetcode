package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/15 18:57
 * Created with IntelliJ IDEA
 */

public class MyCircularDeque_641Test {

    @Test
    public void test() {
        MyCircularDeque_641 test = new MyCircularDeque_641(4);
        assertTrue(test.insertFront(9));
        assertTrue(test.deleteLast());
        assertEquals(-1, test.getRear());
        assertEquals(-1, test.getFront());
        assertEquals(-1, test.getFront());
        assertFalse(test.deleteFront());
        assertTrue(test.insertFront(6));
        assertTrue(test.insertLast(5));
        assertTrue(test.insertFront(9));
        assertTrue(test.insertFront(6));
    }

    @Test
    public void testNewDequeIsEmpty() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);

        assertTrue(deque.isEmpty());
        assertFalse(deque.isFull());
        assertEquals(-1, deque.getFront());
        assertEquals(-1, deque.getRear());
    }

    @Test
    public void testInsertFrontUpdatesBothEndsWhenSingleElement() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);

        assertTrue(deque.insertFront(10));
        assertEquals(10, deque.getFront());
        assertEquals(10, deque.getRear());
    }

    @Test
    public void testInsertLastUpdatesBothEndsWhenSingleElement() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);

        assertTrue(deque.insertLast(20));
        assertEquals(20, deque.getFront());
        assertEquals(20, deque.getRear());
    }

    @Test
    public void testInsertFrontAndLastOrder() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);

        assertTrue(deque.insertLast(1));
        assertTrue(deque.insertLast(2));
        assertTrue(deque.insertFront(0));
        assertEquals(0, deque.getFront());
        assertEquals(2, deque.getRear());
    }

    @Test
    public void testFullDequeRejectsInsertions() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);

        assertTrue(deque.insertLast(1));
        assertTrue(deque.insertFront(2));
        assertTrue(deque.isFull());
        assertFalse(deque.insertFront(3));
        assertFalse(deque.insertLast(4));
    }

    @Test
    public void testDeleteFrontMovesFrontForward() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);
        deque.insertLast(1);
        deque.insertLast(2);
        deque.insertLast(3);

        assertTrue(deque.deleteFront());
        assertEquals(2, deque.getFront());
        assertEquals(3, deque.getRear());
    }

    @Test
    public void testDeleteLastMovesRearBackward() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);
        deque.insertLast(1);
        deque.insertLast(2);
        deque.insertLast(3);

        assertTrue(deque.deleteLast());
        assertEquals(1, deque.getFront());
        assertEquals(2, deque.getRear());
    }

    @Test
    public void testDeleteFromEmptyReturnsFalse() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(1);

        assertFalse(deque.deleteFront());
        assertFalse(deque.deleteLast());
    }

    @Test
    public void testCapacityCanBeReusedAfterDeletion() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);
        deque.insertLast(1);
        deque.insertLast(2);
        assertTrue(deque.deleteFront());

        assertTrue(deque.insertLast(3));
        assertEquals(2, deque.getFront());
        assertEquals(3, deque.getRear());
    }

    @Test
    public void testGiantAlternatingOperationSequence() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(100);
        for (int i = 0; i < 100; i++) {
            assertTrue(deque.insertLast(i));
        }
        assertTrue(deque.isFull());
        for (int i = 0; i < 50; i++) {
            assertTrue(deque.deleteFront());
            assertTrue(deque.insertLast(100 + i));
        }

        assertEquals(50, deque.getFront());
        assertEquals(149, deque.getRear());
        assertTrue(deque.isFull());
    }
}
