package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InsertIntoBST_701Test {

    private final InsertIntoBST_701 test = new InsertIntoBST_701();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.insertIntoBST(root, 5);
        assertEquals(5, result.right.left.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.insertIntoBST(null, 1).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.insertIntoBST(root, 4);
        assertEquals(4, result.left.right.val);
    }

    @Test
    public void testInsertSmallest() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.insertIntoBST(root, 1);
        assertEquals(1, result.left.left.val);
    }

    @Test
    public void testInsertLargest() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.insertIntoBST(root, 10);
        assertEquals(10, result.right.right.val);
    }

    @Test
    public void testInsertIntoLeftSkewed() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        TreeNode result = test.insertIntoBST(root, 2);
        assertEquals(2, result.left.left.right.val);
    }

    @Test
    public void testInsertIntoRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        TreeNode result = test.insertIntoBST(root, 4);
        assertEquals(4, result.right.right.left.val);
    }

    @Test
    public void testInsertPreservesStructure() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        TreeNode result = test.insertIntoBST(root, 5);
        // original structure preserved
        assertEquals(4, result.val);
        assertEquals(2, result.left.val);
        assertEquals(7, result.right.val);
    }

    @Test
    public void testNegativeValue() {
        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(5);
        TreeNode result = test.insertIntoBST(root, -3);
        assertEquals(-3, result.left.val);
    }

    @Test
    public void testGiantTreeInsert() {
        // Build a balanced BST with 63 nodes (1..63), insert 64
        TreeNode root = buildBalancedBST(1, 63);
        TreeNode result = test.insertIntoBST(root, 64);
        // 64 should be at the rightmost position
        TreeNode cur = result;
        while (cur.right != null) cur = cur.right;
        assertEquals(64, cur.val);
    }

    @Test
    public void testInsertBetweenValues() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(15);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(7);
        TreeNode result = test.insertIntoBST(root, 6);
        assertEquals(6, result.left.right.left.val);
    }

    private TreeNode buildBalancedBST(int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedBST(lo, mid - 1);
        node.right = buildBalancedBST(mid + 1, hi);
        return node;
    }
}
