package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import library.ImmutableListNode;
import org.junit.jupiter.api.Test;

public class PrintLinkedListInReverse_1265Test {

    private final PrintLinkedListInReverse_1265 test = new PrintLinkedListInReverse_1265();

    static class TestNode implements ImmutableListNode {
        int val;
        TestNode next;
        List<Integer> printed;

        TestNode(int val, TestNode next, List<Integer> printed) {
            this.val = val;
            this.next = next;
            this.printed = printed;
        }

        @Override
        public void printValue() { printed.add(val); }

        @Override
        public ImmutableListNode getNext() { return next; }
    }

    private TestNode build(List<Integer> printed, int... vals) {
        TestNode head = null;
        for (int i = vals.length - 1; i >= 0; i--) {
            head = new TestNode(vals[i], head, printed);
        }
        return head;
    }

    @Test
    public void testHappyCases() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, 1, 2, 3));
        assertEquals(List.of(3, 2, 1), printed);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        test.printLinkedListInReverse(null); // should not throw
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, 5));
        assertEquals(List.of(5), printed);
    }

    @Test
    public void testLargeCase() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(List.of(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), printed);
    }

    @Test
    public void testTwoElements() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, 7, 8));
        assertEquals(List.of(8, 7), printed);
    }

    @Test
    public void testNegativeValues() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, -3, -2, -1));
        assertEquals(List.of(-1, -2, -3), printed);
    }

    @Test
    public void testDuplicateValues() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, 1, 1, 1, 1));
        assertEquals(List.of(1, 1, 1, 1), printed);
    }

    @Test
    public void testMixedValues() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, -5, 0, 5, 10));
        assertEquals(List.of(10, 5, 0, -5), printed);
    }

    @Test
    public void testOptimizeSpaceSingleNode() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverseOptimizeSpace(build(printed, 42));
        assertEquals(List.of(42), printed);
    }

    @Test
    public void testOptimizeSpaceMultipleNodes() {
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverseOptimizeSpace(build(printed, 1, 2, 3, 4, 5));
        assertEquals(List.of(5, 4, 3, 2, 1), printed);
    }

    @Test
    public void testGiantCase() {
        int size = 1000;
        int[] vals = new int[size];
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            vals[i] = i;
            expected.add(0, i);
        }
        List<Integer> printed = new ArrayList<>();
        test.printLinkedListInReverse(build(printed, vals));
        assertEquals(expected, printed);
    }
}
