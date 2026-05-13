package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class AddTwoNumbers_2Test {

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

    @Test
    public void testDifferentLengths() {
        // 99 + 1 = 100
        ListNode result = test.addTwoNumbers(build(9, 9), build(1));
        assertEquals(0, result.val);
        assertEquals(0, result.next.val);
        assertEquals(1, result.next.next.val);
    }

    @Test
    public void testOneNullInput() {
        ListNode result = test.addTwoNumbers(build(1, 2, 3), null);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testCarryPropagation() {
        // 999 + 1 = 1000
        ListNode result = test.addTwoNumbers(build(9, 9, 9), build(1));
        assertEquals(0, result.val);
        assertEquals(0, result.next.val);
        assertEquals(0, result.next.next.val);
        assertEquals(1, result.next.next.next.val);
    }

    @Test
    public void testNoCarry() {
        // 123 + 456 = 579
        ListNode result = test.addTwoNumbers(build(3, 2, 1), build(6, 5, 4));
        assertEquals(9, result.val);
        assertEquals(7, result.next.val);
        assertEquals(5, result.next.next.val);
    }

    @Test
    public void testSingleDigits() {
        // 5 + 5 = 10
        ListNode result = test.addTwoNumbers(build(5), build(5));
        assertEquals(0, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testRecursionVersion() {
        ListNode result = test.addTwoNumbersRecursion(build(2, 4, 3), build(5, 6, 4));
        assertEquals(7, result.val);
        assertEquals(0, result.next.val);
        assertEquals(8, result.next.next.val);
    }

    @Test
    public void testRecursionBothNull() {
        assertNull(test.addTwoNumbersRecursion(null, null));
    }

    @Test
    public void testGiantCase() {
        // Two 100-digit numbers all 9s: sum is 199...98
        int n = 100;
        int[] nines = new int[n];
        for (int i = 0; i < n; i++) nines[i] = 9;
        ListNode result = test.addTwoNumbers(build(nines), build(nines));
        assertEquals(8, result.val);
        assertEquals(9, result.next.val);
        // last node should be 1 (carry)
        ListNode cur = result;
        while (cur.next != null) cur = cur.next;
        assertEquals(1, cur.val);
    }
}
