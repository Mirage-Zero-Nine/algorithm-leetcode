package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class MergeKLists_23Test {

    private final MergeKLists_23 test = new MergeKLists_23();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.mergeKLists(new ListNode[]{build(1, 4, 5), build(1, 3, 4), build(2, 6)});
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeKLists(new ListNode[]{}));
        assertNull(test.mergeKLists(new ListNode[]{null}));
        assertEquals(1, test.mergeKLists(new ListNode[]{build(1)}).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.mergeKLists(new ListNode[]{build(1, 5, 9), build(2, 6, 10), build(3, 7, 11), build(4, 8, 12)});
        assertEquals(1, result.val);
        assertEquals(12, result.next.next.next.next.next.next.next.next.next.next.next.val);
    }

    @Test
    public void testTwoLists() {
        ListNode result = test.mergeKLists(new ListNode[]{build(1, 3, 5), build(2, 4, 6)});
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testSingleElementLists() {
        ListNode result = test.mergeKLists(new ListNode[]{build(3), build(1), build(2)});
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testWithNullsInArray() {
        ListNode result = test.mergeKLists(new ListNode[]{null, build(1, 3), null, build(2, 4)});
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testAllNulls() {
        assertNull(test.mergeKLists(new ListNode[]{null, null, null}));
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.mergeKLists(new ListNode[]{build(-5, -1, 3), build(-3, 0, 2)});
        assertEquals(-5, result.val);
        assertEquals(-3, result.next.val);
        assertEquals(-1, result.next.next.val);
    }

    @Test
    public void testDuplicateValues() {
        ListNode result = test.mergeKLists(new ListNode[]{build(1, 1, 1), build(1, 1, 1)});
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        // all 6 nodes should be 1
        ListNode cur = result;
        int count = 0;
        while (cur != null) { assertEquals(1, cur.val); cur = cur.next; count++; }
        assertEquals(6, count);
    }

    @Test
    public void testHeapVersion() {
        ListNode result = test.mergeKListsHeap(new ListNode[]{build(1, 4, 5), build(1, 3, 4), build(2, 6)});
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        assertEquals(2, result.next.next.val);
    }

    @Test
    public void testGiantCase() {
        int k = 50;
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            int[] vals = new int[20];
            for (int j = 0; j < 20; j++) vals[j] = i * 20 + j;
            lists[i] = build(vals);
        }
        ListNode result = test.mergeKLists(lists);
        assertEquals(0, result.val);
        // traverse to end
        ListNode cur = result;
        while (cur.next != null) cur = cur.next;
        assertEquals(999, cur.val);
    }
}
