package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class DetectCycle142Test {

    private final DetectCycle_142 test = new DetectCycle_142();

    @Test
    public void testHappyCases() {
        ListNode n1 = new ListNode(3), n2 = new ListNode(2), n3 = new ListNode(0), n4 = new ListNode(-4);
        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n2;
        assertEquals(2, test.detectCycle(n1).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.detectCycle(null));
        ListNode n1 = new ListNode(1), n2 = new ListNode(2);
        n1.next = n2;
        assertNull(test.detectCycle(n1));
    }

    @Test
    public void testLargeCase() {
        ListNode n1 = new ListNode(1), n2 = new ListNode(2), n3 = new ListNode(3);
        n1.next = n2; n2.next = n3; n3.next = n1;
        assertEquals(1, test.detectCycle(n1).val);
    }
}
