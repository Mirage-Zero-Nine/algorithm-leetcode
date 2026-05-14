package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class PlusOne_369Test {

    private final PlusOne_369 test = new PlusOne_369();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(3, test.plusOne(build(1, 2)).next.val);
        assertEquals(4, test.plusOne(build(1, 2, 3)).next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.plusOne(null));
        // 9 + 1 = 10
        ListNode result = test.plusOne(build(9));
        assertEquals(1, result.val);
        assertEquals(0, result.next.val);
    }

    @Test
    public void testLargeCase() {
        // 999 + 1 = 1000
        ListNode result = test.plusOne(build(9, 9, 9));
        assertEquals(1, result.val);
        assertEquals(0, result.next.val);
        assertEquals(0, result.next.next.val);
        assertEquals(0, result.next.next.next.val);
    }

    @Test
    public void testZero() {
        // 0 + 1 = 1
        ListNode result = test.plusOne(build(0));
        assertEquals(1, result.val);
    }

    @Test
    public void testNoCarry() {
        // 123 + 1 = 124
        ListNode result = test.plusOne(build(1, 2, 3));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(4, result.next.next.val);
    }

    @Test
    public void testMiddleNines() {
        // 199 + 1 = 200
        ListNode result = test.plusOne(build(1, 9, 9));
        assertEquals(2, result.val);
        assertEquals(0, result.next.val);
        assertEquals(0, result.next.next.val);
    }

    @Test
    public void testSingleDigitNoCarry() {
        // 5 + 1 = 6
        ListNode result = test.plusOne(build(5));
        assertEquals(6, result.val);
    }

    @Test
    public void testTrailingNines() {
        // 2999 + 1 = 3000
        ListNode result = test.plusOne(build(2, 9, 9, 9));
        assertEquals(3, result.val);
        assertEquals(0, result.next.val);
    }

    @Test
    public void testGiantCase() {
        // 9999999999 + 1 = 10000000000
        ListNode result = test.plusOne(build(9, 9, 9, 9, 9, 9, 9, 9, 9, 9));
        assertEquals(1, result.val);
        assertEquals(0, result.next.val);
    }

    @Test
    public void testLastDigitNine() {
        // 19 + 1 = 20
        ListNode result = test.plusOne(build(1, 9));
        assertEquals(2, result.val);
        assertEquals(0, result.next.val);
    }
}
