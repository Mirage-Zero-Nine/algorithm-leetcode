package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class OddEvenList328Test {

    private final OddEvenList_328 test = new OddEvenList_328();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.oddEvenList(build(1, 2, 3, 4, 5));
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
        assertEquals(5, result.next.next.val);
        assertEquals(2, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.oddEvenList(null));
        assertEquals(1, test.oddEvenList(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.oddEvenList(build(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(1, result.val);
        assertEquals(7, result.next.next.next.val);
        assertEquals(2, result.next.next.next.next.val);
    }
}
