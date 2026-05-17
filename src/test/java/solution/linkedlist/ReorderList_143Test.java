package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReorderList_143Test {

    private final ReorderList_143 test = new ReorderList_143();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode head = build(1, 2, 3, 4);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(4, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(3, head.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        ListNode head = build(1);
        test.reorderList(head);
        assertEquals(1, head.val);
    }

    @Test
    public void testLargeCase() {
        ListNode head = build(1, 2, 3, 4, 5);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(5, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(4, head.next.next.next.val);
        assertEquals(3, head.next.next.next.next.val);
    }

    @Test
    public void testNullInput() {
        assertDoesNotThrow(() -> test.reorderList(null));
    }

    @Test
    public void testTwoElements() {
        ListNode head = build(1, 2);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
    }

    @Test
    public void testThreeElements() {
        ListNode head = build(1, 2, 3);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(3, head.next.val);
        assertEquals(2, head.next.next.val);
    }

    @Test
    public void testSixElements() {
        ListNode head = build(1, 2, 3, 4, 5, 6);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(6, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(5, head.next.next.next.val);
        assertEquals(3, head.next.next.next.next.val);
        assertEquals(4, head.next.next.next.next.next.val);
    }

    @Test
    public void testSevenElements() {
        ListNode head = build(10, 20, 30, 40, 50, 60, 70);
        test.reorderList(head);
        assertEquals(10, head.val);
        assertEquals(70, head.next.val);
        assertEquals(20, head.next.next.val);
        assertEquals(60, head.next.next.next.val);
        assertEquals(30, head.next.next.next.next.val);
        assertEquals(50, head.next.next.next.next.next.val);
        assertEquals(40, head.next.next.next.next.next.next.val);
    }

    @Test
    public void testNegativeValues() {
        ListNode head = build(-1, -2, -3, -4);
        test.reorderList(head);
        assertEquals(-1, head.val);
        assertEquals(-4, head.next.val);
        assertEquals(-2, head.next.next.val);
        assertEquals(-3, head.next.next.next.val);
    }

    @Test
    public void testDuplicateValues() {
        ListNode head = build(1, 1, 1, 1, 1);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(1, head.next.val);
        assertEquals(1, head.next.next.val);
        assertEquals(1, head.next.next.next.val);
        assertEquals(1, head.next.next.next.next.val);
    }

    @Test
    public void testGiantCase() {
        int n = 500;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        ListNode head = build(vals);
        test.reorderList(head);
        assertEquals(1, head.val);
        assertEquals(500, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(499, head.next.next.next.val);
    }
}
