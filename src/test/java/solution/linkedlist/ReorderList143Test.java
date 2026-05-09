package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReorderList143Test {

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
}
