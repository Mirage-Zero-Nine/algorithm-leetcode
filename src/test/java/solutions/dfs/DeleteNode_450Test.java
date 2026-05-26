package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DeleteNode_450Test {

    private final DeleteNode_450 test = new DeleteNode_450();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        TreeNode result = test.deleteNode(root, 3);
        assertEquals(5, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deleteNode(null, 1));
        assertEquals(1, test.deleteNode(new TreeNode(1), 2).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.deleteNode(root, 5);
        assertEquals(7, result.val);
    }

    @Test
    public void testDeleteLeafNode() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.deleteNode(root, 7);
        assertEquals(5, result.val);
        assertNull(result.right);
    }

    @Test
    public void testDeleteNodeWithOnlyLeftChild() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        TreeNode result = test.deleteNode(root, 3);
        assertEquals(5, result.val);
        assertEquals(2, result.left.val);
    }

    @Test
    public void testDeleteNodeWithOnlyRightChild() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(7);
        root.right.right = new TreeNode(9);
        TreeNode result = test.deleteNode(root, 7);
        assertEquals(5, result.val);
        assertEquals(9, result.right.val);
    }

    @Test
    public void testDeleteRoot() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        TreeNode result = test.deleteNode(root, 5);
        assertEquals(7, result.val);
    }

    @Test
    public void testDeleteSingleNode() {
        TreeNode root = new TreeNode(1);
        assertNull(test.deleteNode(root, 1));
    }

    @Test
    public void testDeleteKeyNotInTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.deleteNode(root, 99);
        assertEquals(5, result.val);
        assertEquals(3, result.left.val);
        assertEquals(7, result.right.val);
    }

    @Test
    public void testGiantTreeDelete() {
        // Build a balanced BST with 31 nodes
        TreeNode root = buildBalancedBST(1, 31);
        assertEquals(16, root.val);
        TreeNode result = test.deleteNode(root, 16);
        // root deleted, tree should still be valid
        assertNull(findNode(result, 16));
    }

    private TreeNode buildBalancedBST(int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedBST(lo, mid - 1);
        node.right = buildBalancedBST(mid + 1, hi);
        return node;
    }

    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        return left != null ? left : findNode(root.right, val);
    }
}
