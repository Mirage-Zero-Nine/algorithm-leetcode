package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class SortList148Test {

    private final SortList_148 test = new SortList_148();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.sortList(build(4, 2, 1, 3));
        assertEquals(1, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortList(null));
        assertEquals(1, test.sortList(build(1)).val);
        ListNode result = test.sortList(build(-1, 5, 3, 4, 0));
        assertEquals(-1, result.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.sortList(build(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        assertEquals(1, result.val);
        assertEquals(10, result.next.next.next.next.next.next.next.next.next.val);
    }
}
