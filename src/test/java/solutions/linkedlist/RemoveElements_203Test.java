package solutions.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveElements_203Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {1, 2, 3, 4, 5, 8, 15, 32, 100, 1000})
    void removalMatchesFilteringAndPreservesRetainedNodes(int size) {
        for (int target = -4; target <= 4; target++) {
        java.util.List<ListNode> originals = new java.util.ArrayList<>();
        for (int i = 0; i < size; i++) originals.add(new ListNode(i % 7 - 3));
        for (int i = 1; i < size; i++) originals.get(i - 1).next = originals.get(i);
        java.util.List<ListNode> expected = new java.util.ArrayList<>(originals);
            final int value = target;
            expected.removeIf(node -> node.val == value);
            ListNode current = test.removeElements(originals.get(0), target);
        for (ListNode node : expected) {
            org.junit.jupiter.api.Assertions.assertSame(node, current);
            current = current.next;
        }
        org.junit.jupiter.api.Assertions.assertNull(current);
        for (int i = 0; i < size; i++) assertEquals(i % 7 - 3, originals.get(i).val);
        }
    }


    private final RemoveElements_203 test = new RemoveElements_203();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeElements(build(1, 2, 6, 3, 4, 5, 6), 6);
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeElements(null, 1));
        assertNull(test.removeElements(build(1, 1, 1), 1));
        assertEquals(1, test.removeElements(build(1), 2).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeElements(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5);
        assertEquals(1, result.val);
        assertEquals(6, result.next.next.next.next.val);
    }

    @Test
    public void testRemoveHead() {
        ListNode result = test.removeElements(build(1, 2, 3), 1);
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
    }

    @Test
    public void testRemoveTail() {
        ListNode result = test.removeElements(build(1, 2, 3), 3);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testRemoveConsecutive() {
        ListNode result = test.removeElements(build(1, 2, 2, 2, 3), 2);
        assertEquals(1, result.val);
        assertEquals(3, result.next.val);
    }

    @Test
    public void testNoMatch() {
        ListNode result = test.removeElements(build(1, 2, 3), 4);
        assertEquals(1, result.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testAllRemoved() {
        assertNull(test.removeElements(build(7, 7, 7, 7), 7));
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.removeElements(build(1, 1, 1, 2, 3, 1, 1, 4, 5, 1, 1, 1), 1);
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
        assertEquals(4, result.next.next.val);
        assertEquals(5, result.next.next.next.val);
    }

    @Test
    public void testSingleElementMatch() {
        assertNull(test.removeElements(build(5), 5));
    }
}
