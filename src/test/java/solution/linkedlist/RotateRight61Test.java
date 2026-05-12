package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RotateRight61Test {

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
}
