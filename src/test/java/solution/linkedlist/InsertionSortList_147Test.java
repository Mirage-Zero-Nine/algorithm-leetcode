package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class InsertionSortList_147Test {

    private final InsertionSortList_147 test = new InsertionSortList_147();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.insertionSortList(build(4, 2, 1, 3));
        assertEquals(1, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.insertionSortList(build(1)).val);
        ListNode result = test.insertionSortList(build(2, 1));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.insertionSortList(build(5, 4, 3, 2, 1));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testNullInput() {
        assertNull(test.insertionSortList(null));
    }

    @Test
    public void testAlreadySorted() {
        ListNode result = test.insertionSortList(build(1, 2, 3, 4, 5));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testDuplicates() {
        ListNode result = test.insertionSortList(build(3, 1, 3, 1));
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testAllSame() {
        ListNode result = test.insertionSortList(build(5, 5, 5));
        assertEquals(5, result.val);
        assertEquals(5, result.next.val);
        assertEquals(5, result.next.next.val);
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.insertionSortList(build(3, -1, 2, -5));
        assertEquals(-5, result.val);
        assertEquals(-1, result.next.val);
        assertEquals(2, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.insertionSortList(build(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2));
        assertEquals(-2, result.val);
        assertEquals(-1, result.next.val);
    }

    @Test
    public void testTwoElementsReversed() {
        ListNode result = test.insertionSortList(build(100, 1));
        assertEquals(1, result.val);
        assertEquals(100, result.next.val);
    }
}
