package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

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

    @Test
    public void testIntersectionIdentityIsReturnedNotEqualValueNode() {
        ListNode shared = new ListNode(42);
        ListNode a = new ListNode(1);
        a.next = shared;
        ListNode b = new ListNode(2);
        b.next = new ListNode(3);
        b.next.next = shared;

        assertSame(shared, test.getIntersectionNode(a, b));
    }

    @Test
    public void testIntersectionAtSecondNode() {
        ListNode shared = new ListNode(8);
        shared.next = new ListNode(9);
        ListNode a = new ListNode(1);
        a.next = shared;
        ListNode b = new ListNode(2);
        b.next = shared;

        assertSame(shared, test.getIntersectionNode(a, b));
    }

    @Test
    public void testOneListIsExactlyTheSharedTail() {
        ListNode shared = new ListNode(7);
        shared.next = new ListNode(8);
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next = shared;

        assertSame(shared, test.getIntersectionNode(a, shared));
    }

    @Test
    public void testSharedHeadWithDifferentReferences() {
        ListNode shared = new ListNode(11);
        shared.next = new ListNode(12);
        ListNode a = shared;
        ListNode b = shared;

        assertSame(shared, test.getIntersectionNode(a, b));
    }

    @Test
    public void testNoIntersectionWithEqualLengthsAndEqualValues() {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        ListNode b = new ListNode(1);
        b.next = new ListNode(2);

        assertNull(test.getIntersectionNode(a, b));
    }

    @Test
    public void testNoIntersectionWithEqualLengthDifferentValues() {
        ListNode a = new ListNode(-5);
        a.next = new ListNode(0);
        a.next.next = new ListNode(5);
        ListNode b = new ListNode(-5);
        b.next = new ListNode(0);
        b.next.next = new ListNode(5);

        assertNull(test.getIntersectionNode(a, b));
    }

    @Test
    public void testDistinctTailNodesWithSameValueDoNotIntersect() {
        ListNode a = new ListNode(1);
        a.next = new ListNode(99);
        ListNode b = new ListNode(2);
        b.next = new ListNode(99);

        assertNull(test.getIntersectionNode(a, b));
    }

    @Test
    public void testNegativeValuedIntersection() {
        ListNode shared = new ListNode(-10000);
        shared.next = new ListNode(-9999);
        ListNode a = new ListNode(0);
        a.next = shared;
        ListNode b = new ListNode(10000);
        b.next = shared;

        assertSame(shared, test.getIntersectionNode(a, b));
    }

    @Test
    public void testZeroValuedIntersection() {
        ListNode shared = new ListNode(0);
        ListNode a = new ListNode(-1);
        a.next = shared;
        ListNode b = new ListNode(1);
        b.next = new ListNode(2);
        b.next.next = shared;

        assertSame(shared, test.getIntersectionNode(a, b));
    }

    @Test
    public void testIntersectionAtTailPreservesSharedSuccessor() {
        ListNode shared = new ListNode(3);
        ListNode a = new ListNode(1);
        a.next = shared;
        ListNode b = new ListNode(2);
        b.next = shared;

        assertSame(shared, test.getIntersectionNode(a, b));
        assertNull(shared.next);
    }

    @Test
    public void testInputsRemainLinkedAfterLookup() {
        ListNode shared = new ListNode(8);
        shared.next = new ListNode(9);
        ListNode aPrefix = new ListNode(1);
        aPrefix.next = shared;
        ListNode bPrefix = new ListNode(2);
        bPrefix.next = new ListNode(3);
        bPrefix.next.next = shared;

        assertSame(shared, test.getIntersectionNode(aPrefix, bPrefix));
        assertSame(shared, aPrefix.next);
        assertSame(shared, bPrefix.next.next);
        assertEquals(9, shared.next.val);
    }

    @Test
    public void testReusableInstanceAfterIntersectionThenNoIntersection() {
        ListNode shared = new ListNode(4);
        ListNode a = new ListNode(1);
        a.next = shared;
        ListNode b = new ListNode(2);
        b.next = shared;
        assertSame(shared, test.getIntersectionNode(a, b));

        ListNode c = new ListNode(3);
        ListNode d = new ListNode(3);
        assertNull(test.getIntersectionNode(c, d));
    }
}
