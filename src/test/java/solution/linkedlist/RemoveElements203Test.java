package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveElements203Test {

    private final RemoveElements_203 test = new RemoveElements_203();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeElements(build(1, 2, 6, 3, 4, 5, 6), 6);
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeElements(null, 1));
        assertNull(test.removeElements(build(1, 1, 1), 1));
        assertEquals(1, test.removeElements(build(1), 2).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeElements(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5);
        assertEquals(1, result.val);
        assertEquals(6, result.next.next.next.next.val);
    }
}
