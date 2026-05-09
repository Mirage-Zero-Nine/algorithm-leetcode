package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class GetIntersectionNode160Test {

    private final GetIntersectionNode_160 test = new GetIntersectionNode_160();

    @Test
    public void testHappyCases() {
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);
        ListNode a = new ListNode(4); a.next = new ListNode(1); a.next.next = common;
        ListNode b = new ListNode(5); b.next = new ListNode(6); b.next.next = new ListNode(1); b.next.next.next = common;
        assertEquals(8, test.getIntersectionNode(a, b).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.getIntersectionNode(null, null));
        ListNode a = new ListNode(1), b = new ListNode(2);
        assertNull(test.getIntersectionNode(a, b));
    }

    @Test
    public void testLargeCase() {
        ListNode common = new ListNode(10);
        ListNode a = new ListNode(1); a.next = new ListNode(2); a.next.next = common;
        ListNode b = new ListNode(3); b.next = common;
        assertEquals(10, test.getIntersectionNode(a, b).val);
    }
}
