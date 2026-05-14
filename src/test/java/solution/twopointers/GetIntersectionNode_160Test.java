package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class GetIntersectionNode_160Test {

    private final GetIntersectionNode_160 test = new GetIntersectionNode_160();

    @Test
    public void testHappyCases() {
        ListNode common = new ListNode(8);
        common.next = new ListNode(4);
        common.next.next = new ListNode(5);
        ListNode a = new ListNode(4); a.next = new ListNode(1); a.next.next = common;
        ListNode b = new ListNode(5); b.next = new ListNode(6); b.next.next = new ListNode(1); b.next.next.next = common;
        assertEquals(8, test.getIntersectionNode(a, b).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.getIntersectionNode(null, null));
        ListNode a = new ListNode(1), b = new ListNode(2);
        assertNull(test.getIntersectionNode(a, b));
    }

    @Test
    public void testLargeCase() {
        ListNode common = new ListNode(10);
        ListNode a = new ListNode(1); a.next = new ListNode(2); a.next.next = common;
        ListNode b = new ListNode(3); b.next = common;
        assertEquals(10, test.getIntersectionNode(a, b).val);
    }

    @Test
    public void testNullFirstList() {
        assertNull(test.getIntersectionNode(null, new ListNode(1)));
    }

    @Test
    public void testNullSecondList() {
        assertNull(test.getIntersectionNode(new ListNode(1), null));
    }

    @Test
    public void testSameHeadNode() {
        ListNode common = new ListNode(1);
        common.next = new ListNode(2);
        assertEquals(1, test.getIntersectionNode(common, common).val);
    }

    @Test
    public void testIntersectionAtLastNode() {
        ListNode common = new ListNode(99);
        ListNode a = new ListNode(1); a.next = new ListNode(2); a.next.next = common;
        ListNode b = new ListNode(3); b.next = new ListNode(4); b.next.next = common;
        assertEquals(99, test.getIntersectionNode(a, b).val);
    }

    @Test
    public void testNoIntersectionDifferentLengths() {
        ListNode a = new ListNode(1); a.next = new ListNode(2); a.next.next = new ListNode(3);
        ListNode b = new ListNode(4); b.next = new ListNode(5);
        assertNull(test.getIntersectionNode(a, b));
    }

    @Test
    public void testIntersectionWithLongTail() {
        ListNode common = new ListNode(7);
        common.next = new ListNode(8);
        common.next.next = new ListNode(9);
        ListNode a = new ListNode(1); a.next = common;
        ListNode b = new ListNode(2); b.next = new ListNode(3); b.next.next = new ListNode(4); b.next.next.next = common;
        assertEquals(7, test.getIntersectionNode(a, b).val);
    }

    @Test
    public void testGiantCase() {
        // Build two long lists that intersect at a node
        ListNode common = new ListNode(500);
        ListNode cur = common;
        for (int i = 501; i < 1000; i++) { cur.next = new ListNode(i); cur = cur.next; }
        ListNode a = new ListNode(0);
        cur = a;
        for (int i = 1; i < 200; i++) { cur.next = new ListNode(i); cur = cur.next; }
        cur.next = common;
        ListNode b = new ListNode(0);
        cur = b;
        for (int i = 1; i < 300; i++) { cur.next = new ListNode(i); cur = cur.next; }
        cur.next = common;
        assertEquals(500, test.getIntersectionNode(a, b).val);
    }
}
