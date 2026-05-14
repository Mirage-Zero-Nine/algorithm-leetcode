package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class SortList_148Test {

    private final SortList_148 test = new SortList_148();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    private String toStr(ListNode n) {
        StringBuilder sb = new StringBuilder();
        while (n != null) { sb.append(n.val).append(","); n = n.next; }
        return sb.toString();
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.sortList(build(4, 2, 1, 3));
        assertEquals(1, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortList(null));
        assertEquals(1, test.sortList(build(1)).val);
        ListNode result = test.sortList(build(-1, 5, 3, 4, 0));
        assertEquals(-1, result.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.sortList(build(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        assertEquals(1, result.val);
        assertEquals(10, result.next.next.next.next.next.next.next.next.next.val);
    }

    @Test
    public void testAlreadySorted() {
        assertEquals("1,2,3,4,5,", toStr(test.sortList(build(1, 2, 3, 4, 5))));
    }

    @Test
    public void testTwoElements() {
        assertEquals("1,2,", toStr(test.sortList(build(2, 1))));
    }

    @Test
    public void testDuplicates() {
        assertEquals("1,1,2,2,3,", toStr(test.sortList(build(3, 1, 2, 1, 2))));
    }

    @Test
    public void testAllSameValues() {
        assertEquals("5,5,5,5,", toStr(test.sortList(build(5, 5, 5, 5))));
    }

    @Test
    public void testNegativeValues() {
        assertEquals("-5,-3,-1,0,2,", toStr(test.sortList(build(0, -3, 2, -5, -1))));
    }

    @Test
    public void testReverseSorted() {
        assertEquals("1,2,3,4,5,6,7,8,", toStr(test.sortList(build(8, 7, 6, 5, 4, 3, 2, 1))));
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = 1000 - i;
        ListNode result = test.sortList(build(vals));
        assertEquals(1, result.val);
        // Traverse to last
        ListNode cur = result;
        while (cur.next != null) cur = cur.next;
        assertEquals(1000, cur.val);
    }
}
