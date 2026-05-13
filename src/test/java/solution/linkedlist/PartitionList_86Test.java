package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class PartitionList_86Test {

    private final PartitionList_86 test = new PartitionList_86();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.partition(build(1, 4, 3, 2, 5, 2), 3);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(2, result.next.next.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.partition(build(1), 2).val);
        ListNode result = test.partition(build(2, 1), 2);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.partition(build(5, 4, 3, 2, 1), 3);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(5, result.next.next.val);
    }

    @Test
    public void testAllLessThanX() {
        ListNode result = test.partition(build(1, 2, 3), 5);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testAllGreaterOrEqual() {
        ListNode result = test.partition(build(5, 6, 7), 3);
        assertEquals(5, result.val);
        assertEquals(6, result.next.val);
        assertEquals(7, result.next.next.val);
    }

    @Test
    public void testNullInput() {
        assertNull(test.partition(null, 3));
    }

    @Test
    public void testPartitionAtBoundary() {
        ListNode result = test.partition(build(3, 1, 2), 3);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testDuplicateValues() {
        ListNode result = test.partition(build(3, 3, 1, 1), 2);
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.partition(build(9, 1, 8, 2, 7, 3, 6, 4, 5, 0), 5);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(4, result.next.next.next.val);
        assertEquals(0, result.next.next.next.next.val);
    }

    @Test
    public void testXEqualsElement() {
        ListNode result = test.partition(build(1, 4, 2, 4, 3), 4);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
        assertEquals(4, result.next.next.next.val);
    }
}
