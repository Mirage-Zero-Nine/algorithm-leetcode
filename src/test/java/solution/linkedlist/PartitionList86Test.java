package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class PartitionList86Test {

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
}
