package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Random;

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

    @Test
    public void testInsertFrontInsertLastOnNewDeque() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);
        assertTrue(deque.insertFront(1));
        assertEquals(1, deque.getFront());
        assertEquals(1, deque.getRear());
        assertTrue(deque.insertLast(2));
        assertEquals(1, deque.getFront());
        assertEquals(2, deque.getRear());
    }

    @Test
    public void testGetFrontGetRearOnEmptyReturnsNegativeOne() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);
        assertEquals(-1, deque.getFront());
        assertEquals(-1, deque.getRear());
        // also after insert+delete cycle
        deque.insertFront(5);
        deque.deleteFront();
        assertEquals(-1, deque.getFront());
        assertEquals(-1, deque.getRear());
    }

    @Test
    public void testDeleteFrontDeleteLastOnEmptyReturnsFalse() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);
        assertFalse(deque.deleteFront());
        assertFalse(deque.deleteLast());
        // fill and drain, then try again
        deque.insertLast(1);
        deque.insertLast(2);
        deque.deleteFront();
        deque.deleteLast();
        assertFalse(deque.deleteFront());
        assertFalse(deque.deleteLast());
    }

    @Test
    public void testCapacityOneInsertFrontThenInsertLastFails() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(1);
        assertTrue(deque.insertFront(42));
        assertTrue(deque.isFull());
        assertFalse(deque.insertLast(99));
        assertFalse(deque.insertFront(99));
        assertEquals(42, deque.getFront());
        assertEquals(42, deque.getRear());
    }

    @Test
    public void testIsEmptyIsFullBoundaries() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);
        assertTrue(deque.isEmpty());
        assertFalse(deque.isFull());

        deque.insertFront(1);
        assertFalse(deque.isEmpty());
        assertFalse(deque.isFull());

        deque.insertLast(2);
        assertFalse(deque.isEmpty());
        assertTrue(deque.isFull());

        deque.deleteLast();
        assertFalse(deque.isEmpty());
        assertFalse(deque.isFull());

        deque.deleteFront();
        assertTrue(deque.isEmpty());
        assertFalse(deque.isFull());
    }

    @Test
    public void testMixedFrontBackInsertionsAndDeletions() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(4);
        // insertFront: 3, 2, 1 -> deque: [1, 2, 3]
        deque.insertFront(3);
        deque.insertFront(2);
        deque.insertFront(1);
        assertEquals(1, deque.getFront());
        assertEquals(3, deque.getRear());

        // insertLast 4 -> [1, 2, 3, 4]
        assertTrue(deque.insertLast(4));
        assertTrue(deque.isFull());

        // deleteFront -> [2, 3, 4]
        assertTrue(deque.deleteFront());
        assertEquals(2, deque.getFront());

        // deleteLast -> [2, 3]
        assertTrue(deque.deleteLast());
        assertEquals(3, deque.getRear());

        // insertFront 0, insertLast 5 -> [0, 2, 3, 5]
        assertTrue(deque.insertFront(0));
        assertTrue(deque.insertLast(5));
        assertEquals(0, deque.getFront());
        assertEquals(5, deque.getRear());
        assertTrue(deque.isFull());
    }

    @Test
    public void testWrapAroundWithAlternatingOps() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);
        // Simulate wrap-around by repeatedly filling from front and deleting from back
        for (int i = 0; i < 20; i++) {
            assertTrue(deque.insertFront(i));
            assertEquals(i, deque.getFront());
            assertTrue(deque.deleteLast());
            assertTrue(deque.isEmpty());
        }
        // Now alternate insertLast/deleteFront
        for (int i = 0; i < 20; i++) {
            assertTrue(deque.insertLast(i));
            assertEquals(i, deque.getRear());
            assertTrue(deque.deleteFront());
            assertTrue(deque.isEmpty());
        }
    }

    @Test
    public void testStressRandomCrossCheckWithArrayDeque() {
        Random rng = new Random(42L);
        int capacity = 50;
        MyCircularDeque_641 deque = new MyCircularDeque_641(capacity);
        ArrayDeque<Integer> ref = new ArrayDeque<>();

        for (int i = 0; i < 10000; i++) {
            int op = rng.nextInt(6);
            int val = rng.nextInt(1000);
            switch (op) {
                case 0: // insertFront
                    if (ref.size() < capacity) {
                        assertTrue(deque.insertFront(val));
                        ref.addFirst(val);
                    } else {
                        assertFalse(deque.insertFront(val));
                    }
                    break;
                case 1: // insertLast
                    if (ref.size() < capacity) {
                        assertTrue(deque.insertLast(val));
                        ref.addLast(val);
                    } else {
                        assertFalse(deque.insertLast(val));
                    }
                    break;
                case 2: // deleteFront
                    if (ref.isEmpty()) {
                        assertFalse(deque.deleteFront());
                    } else {
                        assertTrue(deque.deleteFront());
                        ref.pollFirst();
                    }
                    break;
                case 3: // deleteLast
                    if (ref.isEmpty()) {
                        assertFalse(deque.deleteLast());
                    } else {
                        assertTrue(deque.deleteLast());
                        ref.pollLast();
                    }
                    break;
                case 4: // getFront
                    int expectedFront = ref.isEmpty() ? -1 : ref.peekFirst();
                    assertEquals(expectedFront, deque.getFront(), "getFront mismatch at op " + i);
                    break;
                case 5: // getRear
                    int expectedRear = ref.isEmpty() ? -1 : ref.peekLast();
                    assertEquals(expectedRear, deque.getRear(), "getRear mismatch at op " + i);
                    break;
            }
            // invariant checks
            assertEquals(ref.isEmpty(), deque.isEmpty(), "isEmpty mismatch at op " + i);
            assertEquals(ref.size() == capacity, deque.isFull(), "isFull mismatch at op " + i);
        }
    }
}
