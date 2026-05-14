package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SortedListToBST_109Test {

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

    @Test
    public void testTwoElements() {
        TreeNode result = test.sortedListToBST(build(1, 2));
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertNull(result.right);
    }

    @Test
    public void testThreeElements() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3));
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testFourElements() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3, 4));
        // mid of [1,2,3,4] with fast/slow: slow ends at index 2 -> val 3
        assertEquals(3, result.val);
    }

    @Test
    public void testNegativeValues() {
        TreeNode result = test.sortedListToBST(build(-5, -3, -1));
        assertEquals(-3, result.val);
        assertEquals(-5, result.left.val);
        assertEquals(-1, result.right.val);
    }

    @Test
    public void testAllSameValues() {
        TreeNode result = test.sortedListToBST(build(5, 5, 5, 5, 5));
        assertEquals(5, result.val);
    }

    @Test
    public void testBalancedProperty() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3, 4, 5, 6, 7));
        // Should be balanced: left subtree depth and right subtree depth differ by at most 1
        int leftDepth = depth(result.left);
        int rightDepth = depth(result.right);
        assertTrue(Math.abs(leftDepth - rightDepth) <= 1);
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = i;
        TreeNode result = test.sortedListToBST(build(vals));
        // Root should be the middle element
        assertEquals(500, result.val);
        // Tree should be balanced
        int leftDepth = depth(result.left);
        int rightDepth = depth(result.right);
        assertTrue(Math.abs(leftDepth - rightDepth) <= 1);
    }

    @Test
    public void testSixElements() {
        TreeNode result = test.sortedListToBST(build(10, 20, 30, 40, 50, 60));
        // mid of 6 elements: slow ends at index 3 -> val 40
        assertEquals(40, result.val);
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }
}
