package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class HasCycle141Test {

    private final HasCycle_141 test = new HasCycle_141();

    @Test
    public void testHappyCases() {
        ListNode n1 = new ListNode(3), n2 = new ListNode(2), n3 = new ListNode(0), n4 = new ListNode(-4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2;
        assertTrue(test.hasCycle(n1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.hasCycle(null));
        assertFalse(test.hasCycle(new ListNode(1)));
        ListNode n1 = new ListNode(1), n2 = new ListNode(2);
        n1.next = n2;
        assertFalse(test.hasCycle(n1));
    }

    @Test
    public void testLargeCase() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3);
        n1.next = n2; n2.next = n3; n3.next = n1;
        assertTrue(test.hasCycle(n1));
    }
}
