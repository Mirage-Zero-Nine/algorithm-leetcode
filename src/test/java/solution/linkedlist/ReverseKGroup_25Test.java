package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReverseKGroup_25Test {

    private final ReverseKGroup_25 test = new ReverseKGroup_25();

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
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 2);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.reverseKGroup(build(1), 1).val);
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 3);
        assertEquals(3, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6), 3);
        assertEquals(3, result.val);
        assertEquals(6, result.next.next.next.val);
    }

    @Test
    public void testNullInput() {
        assertNull(test.reverseKGroup(null, 2));
    }

    @Test
    public void testKEqualsLength() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 5);
        assertEquals("5,4,3,2,1,", toStr(result));
    }

    @Test
    public void testKGreaterThanLength() {
        ListNode result = test.reverseKGroup(build(1, 2, 3), 5);
        assertEquals("1,2,3,", toStr(result));
    }

    @Test
    public void testKEqualsOne() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4), 1);
        assertEquals("1,2,3,4,", toStr(result));
    }

    @Test
    public void testKEqualsTwo() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4), 2);
        assertEquals("2,1,4,3,", toStr(result));
    }

    @Test
    public void testRemainder() {
        // 5 elements, k=2 -> last element stays
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 2);
        assertEquals("2,1,4,3,5,", toStr(result));
    }

    @Test
    public void testRecursionMethod() {
        ListNode result = test.reverseKGroupRecursion(build(1, 2, 3, 4, 5), 2);
        assertEquals("2,1,4,3,5,", toStr(result));
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = i + 1;
        ListNode result = test.reverseKGroup(build(vals), 10);
        // First element should be 10 (first group reversed)
        assertEquals(10, result.val);
    }
}
