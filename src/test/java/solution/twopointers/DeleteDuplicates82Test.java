package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class DeleteDuplicates82Test {

    private final DeleteDuplicates_82 test = new DeleteDuplicates_82();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.deleteDuplicates(build(1, 2, 3, 3, 4, 4, 5));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deleteDuplicates(null));
        assertNull(test.deleteDuplicates(build(1, 1, 1)));
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2, 3, 3, 4, 5, 5));
        assertEquals(2, result.val);
        assertEquals(4, result.next.val);
    }
}
