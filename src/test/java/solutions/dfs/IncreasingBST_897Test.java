package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IncreasingBST_897Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(2, result.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new IncreasingBST_897().increasingBST(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(1, result.val);
        assertEquals(7, result.right.right.right.right.right.right.val);
    }

    @Test
    public void testNullInput() {
        assertNull(new IncreasingBST_897().increasingBST(null));
    }

    @Test
    public void testSingleNodeNoChildren() {
        TreeNode root = new TreeNode(42);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(42, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(1, result.val);
        assertEquals(3, result.right.val);
        assertEquals(5, result.right.right.val);
        assertNull(result.right.right.right);
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(1, result.val);
        assertEquals(3, result.right.val);
        assertEquals(5, result.right.right.val);
    }

    @Test
    public void testTwoNodesLeftChild() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(1, result.val);
        assertEquals(2, result.right.val);
        assertNull(result.left);
    }

    @Test
    public void testTwoNodesRightChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(1, result.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testAllLeftNullAfterTransform() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        TreeNode cur = result;
        while (cur != null) {
            assertNull(cur.left);
            cur = cur.right;
        }
    }

    @Test
    public void testGiantTree() {
        // Build a balanced BST with 15 nodes (depth 4)
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4); root.right = new TreeNode(12);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10); root.right.right = new TreeNode(14);
        root.left.left.left = new TreeNode(1); root.left.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(5); root.left.right.right = new TreeNode(7);
        root.right.left.left = new TreeNode(9); root.right.left.right = new TreeNode(11);
        root.right.right.left = new TreeNode(13); root.right.right.right = new TreeNode(15);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        TreeNode cur = result;
        for (int i = 1; i <= 15; i++) {
            assertEquals(i, cur.val);
            assertNull(cur.left);
            cur = cur.right;
        }
        assertNull(cur);
    }
}
