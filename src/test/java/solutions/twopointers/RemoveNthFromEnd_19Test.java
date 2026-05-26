package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveNthFromEnd_19Test {

    private final RemoveNthFromEnd_19 test = new RemoveNthFromEnd_19();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3, 4, 5), 2);
        assertEquals(1, result.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeNthFromEnd(build(1), 1));
        assertEquals(2, test.removeNthFromEnd(build(1, 2), 2).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3, 4, 5, 6, 7), 3);
        assertEquals(1, result.val);
        assertEquals(6, result.next.next.next.next.val);
    }

    @Test
    public void testRemoveLastNode() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3), 1);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testRemoveFirstNode() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3), 3);
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
    }

    @Test
    public void testRemoveMiddleNode() {
        ListNode result = test.removeNthFromEnd(build(1, 2, 3, 4, 5), 3);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(4, result.next.next.val);
    }

    @Test
    public void testTwoElementsRemoveLast() {
        ListNode result = test.removeNthFromEnd(build(1, 2), 1);
        assertEquals(1, result.val);
        assertNull(result.next);
    }

    @Test
    public void testTwoElementsRemoveFirst() {
        ListNode result = test.removeNthFromEnd(build(1, 2), 2);
        assertEquals(2, result.val);
        assertNull(result.next);
    }

    @Test
    public void testRemoveSecondFromEnd() {
        ListNode result = test.removeNthFromEnd(build(10, 20, 30, 40), 2);
        assertEquals(10, result.val);
        assertEquals(20, result.next.val);
        assertEquals(40, result.next.next.val);
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = i + 1;
        ListNode result = test.removeNthFromEnd(build(vals), 500);
        // removed 501st node (1000-500+1=501), so node after 500 should be 502
        ListNode cur = result;
        for (int i = 0; i < 499; i++) cur = cur.next;
        assertEquals(500, cur.val);
        assertEquals(502, cur.next.val);
    }
}
