package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class MyLinkedList_707Test {

    @Test
    public void testHappyCases() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyLinkedList_707 list = new MyLinkedList_707();
        assertEquals(-1, list.get(0));
        list.addAtHead(1);
        list.deleteAtIndex(0);
        assertEquals(-1, list.get(0));
    }

    @Test
    public void testLargeCase() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int i = 0; i < 5; i++) list.addAtTail(i);
        assertEquals(0, list.get(0));
        assertEquals(4, list.get(4));
        list.deleteAtIndex(2);
        assertEquals(3, list.get(2));
    }

    @Test
    public void testGetInvalidIndex() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        assertEquals(-1, list.get(-1));
        assertEquals(-1, list.get(1));
        assertEquals(-1, list.get(100));
    }

    @Test
    public void testAddAtNegativeIndex() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(10);
        list.addAtIndex(-1, 5);
        // negative index inserts at head
        assertEquals(5, list.get(0));
        assertEquals(10, list.get(1));
    }

    @Test
    public void testAddAtIndexBeyondSize() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.addAtIndex(5, 99);
        // index > size, should not insert
        assertEquals(-1, list.get(1));
    }

    @Test
    public void testAddAtIndexEqualsSize() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.addAtIndex(1, 2);
        assertEquals(2, list.get(1));
    }

    @Test
    public void testDeleteInvalidIndex() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(1);
        list.deleteAtIndex(-1);
        list.deleteAtIndex(5);
        // list unchanged
        assertEquals(1, list.get(0));
    }

    @Test
    public void testDeleteHead() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(1);
        list.addAtTail(2);
        list.addAtTail(3);
        list.deleteAtIndex(0);
        assertEquals(2, list.get(0));
        assertEquals(3, list.get(1));
    }

    @Test
    public void testDeleteTail() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(1);
        list.addAtTail(2);
        list.addAtTail(3);
        list.deleteAtIndex(2);
        assertEquals(-1, list.get(2));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testGiantCase() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int i = 0; i < 1000; i++) list.addAtTail(i);
        assertEquals(0, list.get(0));
        assertEquals(999, list.get(999));
        assertEquals(500, list.get(500));
        for (int i = 0; i < 500; i++) list.deleteAtIndex(0);
        assertEquals(500, list.get(0));
    }

    @Test
    public void testEmptyListRejectsAllInvalidOperations() {
        MyLinkedList_707 list = new MyLinkedList_707();
        assertEquals(-1, list.get(-1));
        assertEquals(-1, list.get(0));
        assertEquals(-1, list.get(1));
        list.deleteAtIndex(-1);
        list.deleteAtIndex(0);
        list.deleteAtIndex(Integer.MAX_VALUE);
        list.addAtIndex(1, 7);
        assertEquals(-1, list.get(0));
        list.addAtIndex(-1, 8);
        assertEquals(8, list.get(0));
    }

    @Test
    public void testRepeatedHeadInsertionsReverseOrder() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int value = 0; value < 8; value++) {
            list.addAtHead(value);
        }
        assertContents(list, List.of(7, 6, 5, 4, 3, 2, 1, 0));
    }

    @Test
    public void testRepeatedTailInsertionsPreserveOrder() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int value = 0; value < 8; value++) {
            list.addAtTail(value);
        }
        assertContents(list, List.of(0, 1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    public void testInsertAtEveryValidPosition() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(10);
        list.addAtTail(30);
        list.addAtIndex(1, 20);
        list.addAtIndex(0, 0);
        list.addAtIndex(4, 40);
        assertContents(list, List.of(0, 10, 20, 30, 40));
    }

    @Test
    public void testAllNegativeIndicesPrepend() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtIndex(-1, 3);
        list.addAtIndex(-2, 2);
        list.addAtIndex(Integer.MIN_VALUE, 1);
        assertContents(list, List.of(1, 2, 3));
    }

    @Test
    public void testOutOfRangeInsertionsLeaveContentsUnchanged() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtTail(4);
        list.addAtTail(5);
        list.addAtIndex(3, 99);
        list.addAtIndex(100, 98);
        list.addAtIndex(Integer.MAX_VALUE, 97);
        assertContents(list, List.of(4, 5));
    }

    @Test
    public void testDeleteOnlyNodeAndRebuild() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtIndex(0, 42);
        assertEquals(42, list.get(0));
        list.deleteAtIndex(0);
        assertEquals(-1, list.get(0));
        list.addAtTail(11);
        list.addAtHead(10);
        assertContents(list, List.of(10, 11));
    }

    @Test
    public void testDeleteMiddleThenHeadAndTail() {
        MyLinkedList_707 list = new MyLinkedList_707();
        for (int value = 0; value < 5; value++) {
            list.addAtTail(value);
        }
        list.deleteAtIndex(2);
        list.deleteAtIndex(0);
        list.deleteAtIndex(2);
        assertContents(list, List.of(1, 3));
    }

    @Test
    public void testDuplicateAndDocumentedValueBoundaries() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(0);
        list.addAtTail(1000);
        list.addAtIndex(1, 0);
        list.addAtIndex(3, 1000);
        assertContents(list, List.of(0, 0, 1000, 1000));
    }

    @Test
    public void testRepeatedMixedCallsOnOneInstance() {
        MyLinkedList_707 list = new MyLinkedList_707();
        list.addAtHead(2);
        list.addAtIndex(0, 1);
        list.addAtTail(4);
        list.addAtIndex(2, 3);
        assertContents(list, List.of(1, 2, 3, 4));
        list.deleteAtIndex(1);
        list.addAtIndex(2, 9);
        list.deleteAtIndex(0);
        list.addAtHead(0);
        assertContents(list, List.of(0, 3, 9, 4));
    }

    @Test
    public void testInstancesAreIndependent() {
        MyLinkedList_707 first = new MyLinkedList_707();
        MyLinkedList_707 second = new MyLinkedList_707();
        first.addAtHead(1);
        second.addAtHead(2);
        first.addAtTail(3);
        second.deleteAtIndex(0);
        first.addAtIndex(1, 2);
        assertContents(first, List.of(1, 2, 3));
        assertEquals(-1, second.get(0));
        second.addAtIndex(0, 9);
        assertEquals(9, second.get(0));
        assertEquals(1, first.get(0));
    }

    @Test
    public void testSeededArrayListOracleAcrossStatefulOperations() {
        MyLinkedList_707 actual = new MyLinkedList_707();
        List<Integer> expected = new ArrayList<>();
        Random random = new Random(707_2026L);

        for (int call = 0; call < 1500; call++) {
            int operation = random.nextInt(5);
            if (operation == 0) {
                int value = random.nextInt(1001);
                actual.addAtHead(value);
                expected.add(0, value);
            } else if (operation == 1) {
                int value = random.nextInt(1001);
                actual.addAtTail(value);
                expected.add(value);
            } else if (operation == 2) {
                int index = randomIndex(random, expected.size());
                int value = random.nextInt(1001);
                actual.addAtIndex(index, value);
                if (index <= 0) {
                    expected.add(0, value);
                } else if (index <= expected.size()) {
                    expected.add(index, value);
                }
            } else if (operation == 3) {
                int index = randomIndex(random, expected.size());
                int expectedValue = index >= 0 && index < expected.size() ? expected.get(index) : -1;
                assertEquals(expectedValue, actual.get(index));
            } else {
                int index = randomIndex(random, expected.size());
                actual.deleteAtIndex(index);
                if (index >= 0 && index < expected.size()) {
                    expected.remove(index);
                }
            }
            assertContents(actual, expected);
        }
    }

    @Test
    public void testExactTwoThousandCallBudgetAgainstOracle() {
        MyLinkedList_707 actual = new MyLinkedList_707();
        List<Integer> expected = new ArrayList<>();

        for (int call = 0; call < 2000; call++) {
            switch (call % 5) {
                case 0 -> {
                    int value = call % 1001;
                    actual.addAtHead(value);
                    expected.add(0, value);
                }
                case 1 -> {
                    int value = (1000 - call % 1001 + 1001) % 1001;
                    actual.addAtTail(value);
                    expected.add(value);
                }
                case 2 -> {
                    int index = expected.size() / 2;
                    int value = (call * 3) % 1001;
                    actual.addAtIndex(index, value);
                    expected.add(index, value);
                }
                case 3 -> {
                    int index = expected.size();
                    actual.addAtIndex(index, 777);
                    expected.add(777);
                }
                case 4 -> {
                    int index = expected.size() - 1;
                    actual.deleteAtIndex(index);
                    expected.remove(index);
                }
                default -> throw new AssertionError("unreachable");
            }
            if (call % 200 == 199) {
                assertContents(actual, expected);
            }
        }
        assertContents(actual, expected);
    }

    private static int randomIndex(Random random, int size) {
        return random.nextInt(size + 7) - 3;
    }

    private static void assertContents(MyLinkedList_707 actual, List<Integer> expected) {
        for (int index = 0; index < expected.size(); index++) {
            assertEquals(expected.get(index), actual.get(index), "unexpected value at index " + index);
        }
        assertEquals(-1, actual.get(-1), "negative get index must be invalid");
        assertEquals(-1, actual.get(expected.size()), "get at length must be invalid");
        assertEquals(-1, actual.get(expected.size() + 1), "get beyond length must be invalid");
    }
}
