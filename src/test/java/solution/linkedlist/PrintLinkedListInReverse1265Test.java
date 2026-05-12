package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import library.ImmutableListNode;
import org.junit.jupiter.api.Test;

public class PrintLinkedListInReverse1265Test {

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
}
