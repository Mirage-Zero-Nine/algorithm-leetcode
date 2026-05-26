package solutions.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReverseBetween_92Test {

    private final ReverseBetween_92 test = new ReverseBetween_92();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5), 2, 4);
        assertEquals(1, result.val);
        assertEquals(4, result.next.val);
        assertEquals(2, result.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.reverseBetween(build(1), 1, 1).val);
        ListNode result = test.reverseBetween(build(1, 2, 3), 1, 3);
        assertEquals(3, result.val);
        assertEquals(1, result.next.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5, 6, 7), 2, 6);
        assertEquals(1, result.val);
        assertEquals(6, result.next.val);
        assertEquals(2, result.next.next.next.next.next.val);
    }

    @Test
    public void testReverseFirstTwo() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5), 1, 2);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testReverseLastTwo() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5), 4, 5);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(5, result.next.next.next.val);
        assertEquals(4, result.next.next.next.next.val);
    }

    @Test
    public void testReverseMiddleTwo() {
        ListNode result = test.reverseBetween(build(1, 2, 3, 4, 5), 2, 3);
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
        assertEquals(2, result.next.next.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testTwoElementsReverse() {
        ListNode result = test.reverseBetween(build(1, 2), 1, 2);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testSamePosition() {
        ListNode result = test.reverseBetween(build(1, 2, 3), 2, 2);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.reverseBetween(build(-5, -3, -1, 0, 2), 2, 4);
        assertEquals(-5, result.val);
        assertEquals(0, result.next.val);
        assertEquals(-1, result.next.next.val);
        assertEquals(-3, result.next.next.next.val);
        assertEquals(2, result.next.next.next.next.val);
    }

    @Test
    public void testGiantCase() {
        int n = 500;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        ListNode result = test.reverseBetween(build(vals), 100, 400);
        assertEquals(1, result.val);
        // node at position 100 should now be 400
        ListNode cur = result;
        for (int i = 1; i < 100; i++) cur = cur.next;
        assertEquals(400, cur.val);
    }
}
