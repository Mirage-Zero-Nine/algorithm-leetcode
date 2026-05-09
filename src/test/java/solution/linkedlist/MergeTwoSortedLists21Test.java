package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class MergeTwoSortedLists21Test {

    private final MergeTwoSortedLists_21 test = new MergeTwoSortedLists_21();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.mergeTwoLists(build(1, 2, 4), build(1, 3, 4));
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        assertEquals(4, result.next.next.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeTwoLists(null, null));
        assertEquals(1, test.mergeTwoLists(build(1), null).val);
        assertEquals(1, test.mergeTwoLists(null, build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.mergeTwoLists(build(1, 3, 5, 7, 9), build(2, 4, 6, 8, 10));
        assertEquals(1, result.val);
        assertEquals(10, result.next.next.next.next.next.next.next.next.next.val);
    }
}
