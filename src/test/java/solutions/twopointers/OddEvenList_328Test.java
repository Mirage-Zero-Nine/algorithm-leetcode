package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class OddEvenList_328Test {

    private final OddEvenList_328 test = new OddEvenList_328();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.oddEvenList(build(1, 2, 3, 4, 5));
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
        assertEquals(5, result.next.next.val);
        assertEquals(2, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.oddEvenList(null));
        assertEquals(1, test.oddEvenList(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.oddEvenList(build(1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(1, result.val);
        assertEquals(7, result.next.next.next.val);
        assertEquals(2, result.next.next.next.next.val);
    }

    @Test
    public void testTwoElements() {
        ListNode result = test.oddEvenList(build(1, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testThreeElements() {
        ListNode result = test.oddEvenList(build(1, 2, 3));
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
        assertEquals(2, result.next.next.val);
    }

    @Test
    public void testFourElements() {
        ListNode result = test.oddEvenList(build(1, 2, 3, 4));
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
        assertEquals(2, result.next.next.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testSixElements() {
        ListNode result = test.oddEvenList(build(2, 1, 3, 5, 6, 4));
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
        assertEquals(6, result.next.next.val);
        assertEquals(1, result.next.next.next.val);
        assertEquals(5, result.next.next.next.next.val);
        assertEquals(4, result.next.next.next.next.next.val);
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.oddEvenList(build(-1, -2, -3, -4, -5));
        assertEquals(-1, result.val);
        assertEquals(-3, result.next.val);
        assertEquals(-5, result.next.next.val);
        assertEquals(-2, result.next.next.next.val);
        assertEquals(-4, result.next.next.next.next.val);
    }

    @Test
    public void testDuplicateValues() {
        ListNode result = test.oddEvenList(build(1, 1, 1, 1, 1));
        ListNode cur = result;
        int count = 0;
        while (cur != null) { assertEquals(1, cur.val); cur = cur.next; count++; }
        assertEquals(5, count);
    }

    @Test
    public void testGiantCase() {
        int n = 1000;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        ListNode result = test.oddEvenList(build(vals));
        // First element is 1 (odd position), second is 3, ...
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
        // After 500 odd-position nodes, even nodes start with 2
        ListNode cur = result;
        for (int i = 1; i < 500; i++) cur = cur.next;
        assertEquals(999, cur.val);
        assertEquals(2, cur.next.val);
    }
}
