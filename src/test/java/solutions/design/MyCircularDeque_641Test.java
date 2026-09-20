package solutions.design;

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

    @Test
    public void testOfficialLeetCodeSequence() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(3);

        assertTrue(deque.insertLast(1));
        assertTrue(deque.insertLast(2));
        assertTrue(deque.insertFront(3));
        assertFalse(deque.insertFront(4));
        assertEquals(2, deque.getRear());
        assertTrue(deque.isFull());
        assertTrue(deque.deleteLast());
        assertTrue(deque.insertFront(4));
        assertEquals(4, deque.getFront());
    }

    @Test
    public void testCapacityOneCanBeReusedThroughBothEnds() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(1);

        for (int value = 0; value < 30; value++) {
            assertTrue(deque.insertFront(value));
            assertTrue(deque.isFull());
            assertEquals(value, deque.getFront());
            assertEquals(value, deque.getRear());
            assertFalse(deque.insertLast(value + 100));
            assertTrue(deque.deleteLast());
            assertTrue(deque.isEmpty());
            assertFalse(deque.deleteFront());

            assertTrue(deque.insertLast(-value));
            assertEquals(-value, deque.getFront());
            assertEquals(-value, deque.getRear());
            assertTrue(deque.deleteFront());
            assertTrue(deque.isEmpty());
        }
    }

    @Test
    public void testRepeatedMixedWraparoundAgainstIndependentDeque() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(4);
        ArrayDeque<Integer> expected = new ArrayDeque<>();
        int[][] operations = {
                {1, 10}, {1, 20}, {0, 5}, {0, 1}, {3, 0}, {1, 30},
                {2, 0}, {0, 40}, {1, 50}, {3, 0}, {2, 0}, {1, 60},
                {0, 70}, {3, 0}, {3, 0}, {2, 0}, {1, 80}, {0, 90},
                {1, 100}, {0, 110}, {2, 0}, {3, 0}, {2, 0}, {3, 0}
        };

        for (int index = 0; index < operations.length; index++) {
            int operation = operations[index][0];
            int value = operations[index][1];
            switch (operation) {
                case 0 -> {
                    boolean actual = deque.insertFront(value);
                    boolean model = expected.size() < 4;
                    assertEquals(model, actual, "insertFront at operation " + index);
                    if (model) {
                        expected.addFirst(value);
                    }
                }
                case 1 -> {
                    boolean actual = deque.insertLast(value);
                    boolean model = expected.size() < 4;
                    assertEquals(model, actual, "insertLast at operation " + index);
                    if (model) {
                        expected.addLast(value);
                    }
                }
                case 2 -> {
                    boolean actual = deque.deleteFront();
                    boolean model = !expected.isEmpty();
                    assertEquals(model, actual, "deleteFront at operation " + index);
                    if (model) {
                        expected.removeFirst();
                    }
                }
                case 3 -> {
                    boolean actual = deque.deleteLast();
                    boolean model = !expected.isEmpty();
                    assertEquals(model, actual, "deleteLast at operation " + index);
                    if (model) {
                        expected.removeLast();
                    }
                }
                default -> throw new AssertionError("unknown operation " + operation);
            }
            assertDequeMatches(deque, expected, 4, "operation " + index);
        }
    }

    @Test
    public void testDuplicateAndSignedBoundaryValuesRemainDistinct() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(6);
        int[] values = {Integer.MIN_VALUE, -1, -1, 0, 1000, Integer.MAX_VALUE};
        for (int value : values) {
            assertTrue(deque.insertLast(value));
        }
        assertTrue(deque.isFull());
        assertEquals(Integer.MIN_VALUE, deque.getFront());
        assertEquals(Integer.MAX_VALUE, deque.getRear());

        assertTrue(deque.deleteFront());
        assertEquals(-1, deque.getFront());
        assertTrue(deque.deleteLast());
        assertEquals(1000, deque.getRear());
        assertTrue(deque.insertFront(Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, deque.getFront());
        assertTrue(deque.insertLast(Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, deque.getRear());
        assertTrue(deque.isFull());
    }

    @Test
    public void testStoredNegativeOneIsNotTreatedAsEmpty() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(2);

        assertTrue(deque.insertFront(-1));
        assertFalse(deque.isEmpty());
        assertFalse(deque.isFull());
        assertEquals(-1, deque.getFront());
        assertEquals(-1, deque.getRear());
        assertTrue(deque.insertLast(7));
        assertTrue(deque.isFull());
        assertEquals(-1, deque.getFront());
        assertEquals(7, deque.getRear());
        assertTrue(deque.deleteFront());
        assertEquals(7, deque.getFront());
        assertEquals(7, deque.getRear());
        assertTrue(deque.deleteLast());
        assertTrue(deque.isEmpty());
        assertEquals(-1, deque.getFront());
        assertEquals(-1, deque.getRear());
    }

    @Test
    public void testIndependentInstancesDoNotShareNodesOrSize() {
        MyCircularDeque_641 first = new MyCircularDeque_641(2);
        MyCircularDeque_641 second = new MyCircularDeque_641(2);

        assertTrue(first.insertLast(1));
        assertTrue(first.insertLast(2));
        assertTrue(second.insertFront(9));
        assertEquals(1, first.getFront());
        assertEquals(2, first.getRear());
        assertTrue(first.isFull());
        assertEquals(9, second.getFront());
        assertEquals(9, second.getRear());
        assertFalse(second.isFull());

        assertTrue(first.deleteFront());
        assertEquals(2, first.getFront());
        assertEquals(9, second.getFront());
        assertTrue(second.insertLast(8));
        assertTrue(second.isFull());
    }

    @Test
    public void testMaximumCapacityAndTwoThousandCallBoundary() {
        MyCircularDeque_641 deque = new MyCircularDeque_641(1000);

        for (int value = 0; value < 1000; value++) {
            assertTrue(deque.insertLast(value));
        }
        assertTrue(deque.isFull());
        assertFalse(deque.insertFront(1000));
        assertFalse(deque.insertLast(1000));
        assertEquals(0, deque.getFront());
        assertEquals(999, deque.getRear());
        for (int value = 0; value < 1000; value++) {
            assertTrue(deque.deleteFront());
        }
        assertTrue(deque.isEmpty());
        assertFalse(deque.deleteFront());
        assertFalse(deque.deleteLast());
        assertEquals(-1, deque.getFront());
        assertEquals(-1, deque.getRear());
    }

    @Test
    public void testSeededOracleCoversEverySmallCapacityAndOperationType() {
        Random rng = new Random(641L);
        for (int capacity = 1; capacity <= 10; capacity++) {
            MyCircularDeque_641 deque = new MyCircularDeque_641(capacity);
            ArrayDeque<Integer> expected = new ArrayDeque<>();
            for (int operationIndex = 0; operationIndex < 200; operationIndex++) {
                int operation = rng.nextInt(8);
                int value = rng.nextInt(1001);
                switch (operation) {
                    case 0 -> {
                        boolean actual = deque.insertFront(value);
                        boolean model = expected.size() < capacity;
                        assertEquals(model, actual, "insertFront c=" + capacity + " op=" + operationIndex);
                        if (model) {
                            expected.addFirst(value);
                        }
                    }
                    case 1 -> {
                        boolean actual = deque.insertLast(value);
                        boolean model = expected.size() < capacity;
                        assertEquals(model, actual, "insertLast c=" + capacity + " op=" + operationIndex);
                        if (model) {
                            expected.addLast(value);
                        }
                    }
                    case 2 -> {
                        boolean actual = deque.deleteFront();
                        boolean model = !expected.isEmpty();
                        assertEquals(model, actual, "deleteFront c=" + capacity + " op=" + operationIndex);
                        if (model) {
                            expected.removeFirst();
                        }
                    }
                    case 3 -> {
                        boolean actual = deque.deleteLast();
                        boolean model = !expected.isEmpty();
                        assertEquals(model, actual, "deleteLast c=" + capacity + " op=" + operationIndex);
                        if (model) {
                            expected.removeLast();
                        }
                    }
                    case 4 -> assertEquals(expected.isEmpty() ? -1 : expected.peekFirst(), deque.getFront());
                    case 5 -> assertEquals(expected.isEmpty() ? -1 : expected.peekLast(), deque.getRear());
                    case 6 -> assertEquals(expected.isEmpty(), deque.isEmpty());
                    case 7 -> assertEquals(expected.size() == capacity, deque.isFull());
                    default -> throw new AssertionError("unreachable operation " + operation);
                }
                assertDequeMatches(deque, expected, capacity,
                        "capacity " + capacity + ", operation " + operationIndex);
            }
        }
    }

    private static void assertDequeMatches(MyCircularDeque_641 actual, ArrayDeque<Integer> expected,
                                           int capacity, String context) {
        assertEquals(expected.isEmpty(), actual.isEmpty(), "isEmpty mismatch at " + context);
        assertEquals(expected.size() == capacity, actual.isFull(), "isFull mismatch at " + context);
        assertEquals(expected.isEmpty() ? -1 : expected.peekFirst(), actual.getFront(),
                "front mismatch at " + context);
        assertEquals(expected.isEmpty() ? -1 : expected.peekLast(), actual.getRear(),
                "rear mismatch at " + context);
    }
}
