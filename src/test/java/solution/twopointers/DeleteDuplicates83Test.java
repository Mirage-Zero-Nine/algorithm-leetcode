package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class DeleteDuplicates83Test {

    private final DeleteDuplicates_83 test = new DeleteDuplicates_83();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deleteDuplicates(null));
        assertEquals(1, test.deleteDuplicates(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2, 3, 3, 4, 5, 5));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }
}
