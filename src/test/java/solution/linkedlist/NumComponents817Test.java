package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class NumComponents817Test {

    private final NumComponents_817 test = new NumComponents_817();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(2, test.numComponents(build(0, 1, 2, 3), new int[]{0, 1, 3}));
        assertEquals(2, test.numComponents(build(0, 1, 2, 3, 4), new int[]{0, 3, 1, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numComponents(build(0), new int[]{0}));
        assertEquals(0, test.numComponents(build(0, 1, 2), new int[]{3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.numComponents(build(0, 1, 2, 3, 4, 5, 6), new int[]{0, 1, 3, 5, 6}));
    }
}
