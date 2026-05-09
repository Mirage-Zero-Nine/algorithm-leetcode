package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SortedListToBST109Test {

    private final SortedListToBST_109 test = new SortedListToBST_109();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        TreeNode result = test.sortedListToBST(build(-10, -3, 0, 5, 9));
        assertEquals(0, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortedListToBST(null));
        assertEquals(1, test.sortedListToBST(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3, 4, 5, 6, 7));
        assertEquals(4, result.val);
    }
}
