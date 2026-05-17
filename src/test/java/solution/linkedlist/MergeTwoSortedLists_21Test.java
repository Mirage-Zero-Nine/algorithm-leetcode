package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
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

    @Test
    public void testBothNull() {
        assertNull(test.mergeTwoLists(null, null));
    }

    @Test
    public void testOneNullOtherMultiple() {
        assertEquals("1,2,3,", toStr(test.mergeTwoLists(null, build(1, 2, 3))));
        assertEquals("4,5,6,", toStr(test.mergeTwoLists(build(4, 5, 6), null)));
    }

    @Test
    public void testFirstEntirelySmaller() {
        assertEquals("1,2,3,10,11,12,", toStr(test.mergeTwoLists(build(1, 2, 3), build(10, 11, 12))));
    }

    @Test
    public void testBothIdentical() {
        assertEquals("1,1,2,2,3,3,", toStr(test.mergeTwoLists(build(1, 2, 3), build(1, 2, 3))));
    }

    @Test
    public void testIntegerMinMaxValues() {
        ListNode result = test.mergeTwoLists(build(Integer.MIN_VALUE, 0), build(0, Integer.MAX_VALUE));
        assertEquals(Integer.MIN_VALUE + ",0,0," + Integer.MAX_VALUE + ",", toStr(result));
    }

    @Test
    public void testSingleElementVsLong() {
        assertEquals("0,1,2,3,4,5,", toStr(test.mergeTwoLists(build(0), build(1, 2, 3, 4, 5))));
        assertEquals("1,2,3,4,5,100,", toStr(test.mergeTwoLists(build(1, 2, 3, 4, 5), build(100))));
    }

    @Test
    public void testLargeListsSortedAndCorrectLength() {
        int[] a = new int[1000];
        int[] b = new int[1000];
        Random rng = new Random(42L);
        for (int i = 0; i < 1000; i++) { a[i] = rng.nextInt(100000); b[i] = rng.nextInt(100000); }
        Arrays.sort(a);
        Arrays.sort(b);
        ListNode result = test.mergeTwoLists(build(a), build(b));
        int count = 0;
        int prev = Integer.MIN_VALUE;
        ListNode cur = result;
        while (cur != null) {
            assertTrue(cur.val >= prev, "Result not sorted at position " + count);
            prev = cur.val;
            count++;
            cur = cur.next;
        }
        assertEquals(2000, count);
    }

    @Test
    public void testDuplicatesAcrossBoundaries() {
        assertEquals("1,2,2,3,3,4,", toStr(test.mergeTwoLists(build(1, 2, 3), build(2, 3, 4))));
    }
}
