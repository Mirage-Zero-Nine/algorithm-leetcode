package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveZeroSumSublists1171Test {

    private final RemoveZeroSumSublists_1171 test = new RemoveZeroSumSublists_1171();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, -3, 3, 1));
        assertEquals(3, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeZeroSumSublists(build(1, -1)));
        assertEquals(1, test.removeZeroSumSublists(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, 3, -3, -2, 4));
        assertEquals(1, result.val);
        assertEquals(4, result.next.val);
    }
}
