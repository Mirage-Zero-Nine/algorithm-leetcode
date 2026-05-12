package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class AddTwoNumbers2Test {

    private final AddTwoNumbers_2 test = new AddTwoNumbers_2();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        // 342 + 465 = 807
        ListNode result = test.addTwoNumbers(build(2, 4, 3), build(5, 6, 4));
        assertEquals(7, result.val);
        assertEquals(0, result.next.val);
        assertEquals(8, result.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.addTwoNumbers(null, null));
        // 0 + 0 = 0
        assertEquals(0, test.addTwoNumbers(build(0), build(0)).val);
    }

    @Test
    public void testLargeCase() {
        // 9999999 + 9999999 = 19999998
        ListNode result = test.addTwoNumbers(build(9, 9, 9, 9, 9, 9, 9), build(9, 9, 9, 9));
        assertEquals(8, result.val);
        assertEquals(1, result.next.next.next.next.next.next.next.val);
    }
}
