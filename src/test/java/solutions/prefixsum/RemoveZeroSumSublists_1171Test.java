package solutions.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveZeroSumSublists_1171Test {

    private final RemoveZeroSumSublists_1171 test = new RemoveZeroSumSublists_1171();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, -3, 3, 1));
        assertEquals(3, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeZeroSumSublists(build(1, -1)));
        assertEquals(1, test.removeZeroSumSublists(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, 3, -3, -2, 4));
        assertEquals(1, result.val);
        assertEquals(4, result.next.val);
    }

    @Test
    public void testAllZeroSum() {
        assertNull(test.removeZeroSumSublists(build(1, 2, 3, -6)));
    }

    @Test
    public void testNoRemoval() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, 3));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testMultipleZeroSumGroups() {
        // [1, -1, 2, -2, 3] -> [3]
        ListNode result = test.removeZeroSumSublists(build(1, -1, 2, -2, 3));
        assertEquals(3, result.val);
        assertNull(result.next);
    }

    @Test
    public void testZeroValueNode() {
        // [0] sums to 0
        assertNull(test.removeZeroSumSublists(build(0)));
    }

    @Test
    public void testZeroInMiddle() {
        // [1, 0, 2] -> 0 alone sums to 0, removed
        ListNode result = test.removeZeroSumSublists(build(1, 0, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testNegativeOnly() {
        // [-1, -2, -3] no consecutive sum to 0
        ListNode result = test.removeZeroSumSublists(build(-1, -2, -3));
        assertEquals(-1, result.val);
        assertEquals(-2, result.next.val);
        assertEquals(-3, result.next.next.val);
    }

    @Test
    public void testComplexCase() {
        // [1, 2, -2, -1, 5] -> 2 + (-2) = 0 removed -> [1, -1, 5] -> 1 + (-1) = 0 removed -> [5]
        ListNode result = test.removeZeroSumSublists(build(1, 2, -2, -1, 5));
        assertEquals(5, result.val);
        assertNull(result.next);
    }

    @Test
    public void testGiantCase() {
        // Build a list of 500 elements that all cancel: [1, -1, 1, -1, ..., 1, -1, 99]
        int n = 501;
        int[] vals = new int[n];
        for (int i = 0; i < n - 1; i++) vals[i] = (i % 2 == 0) ? 1 : -1;
        vals[n - 1] = 99;
        ListNode result = test.removeZeroSumSublists(build(vals));
        assertEquals(99, result.val);
        assertNull(result.next);
    }
}
