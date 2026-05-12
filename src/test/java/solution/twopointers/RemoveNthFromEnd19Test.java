package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveNthFromEnd19Test {

    private final RemoveNthFromEnd_19 test = new RemoveNthFromEnd_19();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3, 4, 5), 2);
        assertEquals(1, result.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeNthFromEnd(build(1), 1));
        assertEquals(2, test.removeNthFromEnd(build(1, 2), 2).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3, 4, 5, 6, 7), 3);
        assertEquals(1, result.val);
        assertEquals(6, result.next.next.next.next.val);
    }
}
