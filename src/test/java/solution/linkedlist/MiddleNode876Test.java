package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class MiddleNode876Test {

    private final MiddleNode_876 test = new MiddleNode_876();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(3, test.middleNode(build(1, 2, 3, 4, 5)).val);
        assertEquals(4, test.middleNode(build(1, 2, 3, 4, 5, 6)).val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.middleNode(build(1)).val);
        assertEquals(2, test.middleNode(build(1, 2)).val);
    }

    @Test
    public void testLargeCase() {
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = i + 1;
        assertEquals(51, test.middleNode(build(vals)).val);
    }
}
