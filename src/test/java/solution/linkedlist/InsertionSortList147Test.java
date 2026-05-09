package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class InsertionSortList147Test {

    private final InsertionSortList_147 test = new InsertionSortList_147();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.insertionSortList(build(4, 2, 1, 3));
        assertEquals(1, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.insertionSortList(build(1)).val);
        ListNode result = test.insertionSortList(build(2, 1));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.insertionSortList(build(5, 4, 3, 2, 1));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }
}
