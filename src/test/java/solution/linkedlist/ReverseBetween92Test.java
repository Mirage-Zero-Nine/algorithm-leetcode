package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReverseBetween92Test {

    private final ReverseBetween_92 test = new ReverseBetween_92();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5), 2, 4);
        assertEquals(1, result.val);
        assertEquals(4, result.next.val);
        assertEquals(2, result.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.reverseBetween(build(1), 1, 1).val);
        ListNode result = test.reverseBetween(build(1, 2, 3), 1, 3);
        assertEquals(3, result.val);
        assertEquals(1, result.next.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5, 6, 7), 2, 6);
        assertEquals(1, result.val);
        assertEquals(6, result.next.val);
        assertEquals(2, result.next.next.next.next.next.val);
    }
}
