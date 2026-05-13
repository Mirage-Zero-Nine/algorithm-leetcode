package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class SwapPairs_24Test {

    private final SwapPairs_24 test = new SwapPairs_24();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(4, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.swapPairs(null));
        assertEquals(1, test.swapPairs(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4, 5, 6));
        assertEquals(2, result.val);
        assertEquals(6, result.next.next.next.next.val);
    }

    @Test
    public void testThreeElements() {
        ListNode result = test.swapPairs(build(1, 2, 3));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testTwoElements() {
        ListNode result = test.swapPairs(build(1, 2));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testFiveElements() {
        ListNode result = test.swapPairs(build(5, 4, 3, 2, 1));
        assertEquals(4, result.val);
        assertEquals(5, result.next.val);
        assertEquals(2, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
        assertEquals(1, result.next.next.next.next.val);
    }

    @Test
    public void testSameValues() {
        ListNode result = test.swapPairs(build(1, 1, 1, 1));
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.swapPairs(build(-1, -2, -3, -4));
        assertEquals(-2, result.val);
        assertEquals(-1, result.next.val);
        assertEquals(-4, result.next.next.val);
        assertEquals(-3, result.next.next.next.val);
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(4, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testEightElements() {
        ListNode result = test.swapPairs(build(10, 20, 30, 40, 50, 60, 70, 80));
        assertEquals(20, result.val);
        assertEquals(10, result.next.val);
        assertEquals(40, result.next.next.val);
    }
}
