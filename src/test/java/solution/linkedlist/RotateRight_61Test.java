package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RotateRight_61Test {

    private final RotateRight_61 test = new RotateRight_61();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(4, test.rotateRight(build(1, 2, 3, 4, 5), 2).val);
        assertEquals(2, test.rotateRight(build(0, 1, 2), 4).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.rotateRight(null, 1));
        assertEquals(1, test.rotateRight(build(1), 5).val);
        assertEquals(1, test.rotateRight(build(1, 2), 0).val);
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.rotateRight(build(1, 2, 3, 4, 5, 6, 7), 3).val);
    }

    @Test
    public void testRotateByLength() {
        // rotating by length should return same list
        ListNode result = test.rotateRight(build(1, 2, 3), 3);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testRotateByMultipleOfLength() {
        ListNode result = test.rotateRight(build(1, 2, 3), 6);
        assertEquals(1, result.val);
    }

    @Test
    public void testRotateByOne() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4), 1);
        assertEquals(4, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testTwoElementsRotateOne() {
        ListNode result = test.rotateRight(build(1, 2), 1);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testKLargerThanLength() {
        ListNode result = test.rotateRight(build(1, 2, 3), 5);
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
        assertEquals(1, result.next.next.val);
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 7);
        assertEquals(4, result.val);
    }

    @Test
    public void testRotateTwo() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 2);
        assertEquals(4, result.val);
        assertEquals(5, result.next.val);
        assertEquals(1, result.next.next.val);
    }
}
