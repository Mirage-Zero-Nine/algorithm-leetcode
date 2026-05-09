package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReverseKGroup25Test {

    private final ReverseKGroup_25 test = new ReverseKGroup_25();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 2);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.reverseKGroup(build(1), 1).val);
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 3);
        assertEquals(3, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6), 3);
        assertEquals(3, result.val);
        assertEquals(6, result.next.next.next.val);
    }
}
