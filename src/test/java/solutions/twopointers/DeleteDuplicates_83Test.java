package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class DeleteDuplicates_83Test {

    private final DeleteDuplicates_83 test = new DeleteDuplicates_83();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deleteDuplicates(null));
        assertEquals(1, test.deleteDuplicates(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.deleteDuplicates(build(1, 1, 2, 3, 3, 4, 5, 5));
        assertEquals(1, result.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testNoDuplicates() {
        ListNode result = test.deleteDuplicates(build(1, 2, 3));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testAllSame() {
        ListNode result = test.deleteDuplicates(build(5, 5, 5, 5));
        assertEquals(5, result.val);
        assertNull(result.next);
    }

    @Test
    public void testTwoElementsSame() {
        ListNode result = test.deleteDuplicates(build(3, 3));
        assertEquals(3, result.val);
        assertNull(result.next);
    }

    @Test
    public void testTwoElementsDifferent() {
        ListNode result = test.deleteDuplicates(build(1, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testDuplicatesAtEnd() {
        ListNode result = test.deleteDuplicates(build(1, 2, 3, 3, 3));
        assertEquals(1, result.val);
        assertEquals(3, result.next.next.val);
        assertNull(result.next.next.next);
    }

    @Test
    public void testDuplicatesAtStart() {
        ListNode result = test.deleteDuplicates(build(1, 1, 1, 2, 3));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testGiantCase() {
        // 1,1,2,2,...,500,500 -> 1,2,...,500
        int[] vals = new int[1000];
        for (int i = 0; i < 500; i++) { vals[i * 2] = i + 1; vals[i * 2 + 1] = i + 1; }
        ListNode result = test.deleteDuplicates(build(vals));
        ListNode cur = result;
        for (int i = 1; i <= 500; i++) {
            assertEquals(i, cur.val);
            cur = cur.next;
        }
        assertNull(cur);
    }
}
