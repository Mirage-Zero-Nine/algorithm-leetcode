package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BuildTree_105Test {

    private final BuildTree_105 test = new BuildTree_105();

    @Test
    public void testHappyCases() {
        TreeNode result = test.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        assertEquals(3, result.val);
        assertEquals(9, result.left.val);
        assertEquals(20, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.buildTree(null, null));
        assertEquals(1, test.buildTree(new int[]{1}, new int[]{1}).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.buildTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testEmptyArrays() {
        assertNull(test.buildTree(new int[]{}, new int[]{}));
    }

    @Test
    public void testMismatchedLengths() {
        assertNull(test.buildTree(new int[]{1, 2}, new int[]{1}));
    }

    @Test
    public void testTwoNodesLeftChild() {
        TreeNode result = test.buildTree(new int[]{1, 2}, new int[]{2, 1});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertNull(result.right);
    }

    @Test
    public void testTwoNodesRightChild() {
        TreeNode result = test.buildTree(new int[]{1, 2}, new int[]{1, 2});
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testLeftSkewedTree() {
        // preorder: 1,2,3,4  inorder: 4,3,2,1
        TreeNode result = test.buildTree(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.left.left.val);
        assertEquals(4, result.left.left.left.val);
        assertNull(result.right);
    }

    @Test
    public void testRightSkewedTree() {
        // preorder: 1,2,3,4  inorder: 1,2,3,4
        TreeNode result = test.buildTree(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4});
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.right.val);
        assertEquals(4, result.right.right.right.val);
    }

    @Test
    public void testFullBinaryTreeVerifyLeaves() {
        // Full tree: preorder 1,2,4,5,3,6,7 inorder 4,2,5,1,6,3,7
        TreeNode result = test.buildTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        assertEquals(4, result.left.left.val);
        assertEquals(5, result.left.right.val);
        assertEquals(6, result.right.left.val);
        assertEquals(7, result.right.right.val);
    }

    @Test
    public void testGiantTree() {
        // Build arrays for a complete binary tree of 15 nodes
        int[] preorder = {1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15};
        int[] inorder = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        TreeNode result = test.buildTree(preorder, inorder);
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
        assertEquals(8, result.left.left.left.val);
        assertEquals(15, result.right.right.right.val);
    }
}
