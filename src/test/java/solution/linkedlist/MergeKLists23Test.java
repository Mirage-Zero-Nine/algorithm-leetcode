package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class MergeKLists23Test {

    private final MergeKLists_23 test = new MergeKLists_23();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.mergeKLists(new ListNode[]{build(1, 4, 5), build(1, 3, 4), build(2, 6)});
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeKLists(new ListNode[]{}));
        assertNull(test.mergeKLists(new ListNode[]{null}));
        assertEquals(1, test.mergeKLists(new ListNode[]{build(1)}).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.mergeKLists(new ListNode[]{build(1, 5, 9), build(2, 6, 10), build(3, 7, 11), build(4, 8, 12)});
        assertEquals(1, result.val);
        assertEquals(12, result.next.next.next.next.next.next.next.next.next.next.next.val);
    }
}
