package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class MergeTwoSortedLists_21Test {

    private final MergeTwoSortedLists_21 test = new MergeTwoSortedLists_21();

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
        ListNode result = test.mergeTwoLists(build(1, 2, 4), build(1, 3, 4));
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        assertEquals(4, result.next.next.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeTwoLists(null, null));
        assertEquals(1, test.mergeTwoLists(build(1), null).val);
        assertEquals(1, test.mergeTwoLists(null, build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.mergeTwoLists(build(1, 3, 5, 7, 9), build(2, 4, 6, 8, 10));
        assertEquals(1, result.val);
        assertEquals(10, result.next.next.next.next.next.next.next.next.next.val);
    }

    @Test
    public void testBothSingleElement() {
        assertEquals("1,2,", toStr(test.mergeTwoLists(build(1), build(2))));
    }

    @Test
    public void testFirstListLonger() {
        assertEquals("1,2,3,4,5,", toStr(test.mergeTwoLists(build(1, 3, 5), build(2, 4))));
    }

    @Test
    public void testSecondListLonger() {
        assertEquals("1,2,3,4,5,", toStr(test.mergeTwoLists(build(2, 4), build(1, 3, 5))));
    }

    @Test
    public void testDuplicateValues() {
        assertEquals("1,1,1,1,", toStr(test.mergeTwoLists(build(1, 1), build(1, 1))));
    }

    @Test
    public void testNegativeValues() {
        assertEquals("-3,-2,-1,0,1,2,", toStr(test.mergeTwoLists(build(-3, -1, 1), build(-2, 0, 2))));
    }

    @Test
    public void testNoOverlap() {
        assertEquals("1,2,3,4,5,6,", toStr(test.mergeTwoLists(build(1, 2, 3), build(4, 5, 6))));
    }

    @Test
    public void testGiantCase() {
        int[] a = new int[500];
        int[] b = new int[500];
        for (int i = 0; i < 500; i++) { a[i] = i * 2; b[i] = i * 2 + 1; }
        ListNode result = test.mergeTwoLists(build(a), build(b));
        assertEquals(0, result.val);
        ListNode cur = result;
        while (cur.next != null) cur = cur.next;
        assertEquals(999, cur.val);
    }
}
