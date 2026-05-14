package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class DeleteDuplicates_82Test {

    private final DeleteDuplicates_82 test = new DeleteDuplicates_82();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.deleteDuplicates(build(1, 2, 3, 3, 4, 4, 5));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deleteDuplicates(null));
        assertNull(test.deleteDuplicates(build(1, 1, 1)));
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2, 3, 3, 4, 5, 5));
        assertEquals(2, result.val);
        assertEquals(4, result.next.val);
    }

    @Test
    public void testNoDuplicates() {
        ListNode result = test.deleteDuplicates(build(1, 2, 3, 4, 5));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testAllDuplicates() {
        assertNull(test.deleteDuplicates(build(1, 1, 2, 2, 3, 3)));
    }

    @Test
    public void testSingleElement() {
        ListNode result = test.deleteDuplicates(build(7));
        assertEquals(7, result.val);
        assertNull(result.next);
    }

    @Test
    public void testDuplicatesAtEnd() {
        ListNode result = test.deleteDuplicates(build(1, 2, 3, 3));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testDuplicatesAtStart() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2, 3));
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
    }

    @Test
    public void testTwoElementsDuplicate() {
        assertNull(test.deleteDuplicates(build(5, 5)));
    }

    @Test
    public void testTwoElementsDistinct() {
        ListNode result = test.deleteDuplicates(build(1, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testGiantCase() {
        // 1,1,2,2,...,499,499,500 -> only 500 remains
        int[] vals = new int[999];
        for (int i = 0; i < 499; i++) { vals[i * 2] = i + 1; vals[i * 2 + 1] = i + 1; }
        vals[998] = 500;
        ListNode result = test.deleteDuplicates(build(vals));
        assertEquals(500, result.val);
        assertNull(result.next);
    }
}
