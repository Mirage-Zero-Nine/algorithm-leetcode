package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BuildTree_106Test {

    private final BuildTree_106 test = new BuildTree_106();

    @Test
    public void testHappyCases() {
        TreeNode result = test.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        assertEquals(3, result.val);
        assertEquals(9, result.left.val);
        assertEquals(20, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.buildTree(new int[]{}, new int[]{}));
        assertEquals(1, test.buildTree(new int[]{1}, new int[]{1}).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.buildTree(new int[]{4, 2, 5, 1, 6, 3, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        assertEquals(1, result.val);
    }

    @Test
    public void testTwoNodesLeftChild() {
        // inorder: 2,1  postorder: 2,1 => root=1, left=2
        TreeNode result = test.buildTree(new int[]{2, 1}, new int[]{2, 1});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertNull(result.right);
    }

    @Test
    public void testTwoNodesRightChild() {
        // inorder: 1,2  postorder: 2,1 => root=1, right=2
        TreeNode result = test.buildTree(new int[]{1, 2}, new int[]{2, 1});
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testLeftSkewedTree() {
        // inorder: 4,3,2,1  postorder: 4,3,2,1
        TreeNode result = test.buildTree(new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 1});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.left.left.val);
        assertEquals(4, result.left.left.left.val);
    }

    @Test
    public void testRightSkewedTree() {
        // inorder: 1,2,3,4  postorder: 4,3,2,1
        TreeNode result = test.buildTree(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1});
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.right.val);
        assertEquals(4, result.right.right.right.val);
    }

    @Test
    public void testFullBinaryTreeVerifyLeaves() {
        // inorder: 4,2,5,1,6,3,7  postorder: 4,5,2,6,7,3,1
        TreeNode result = test.buildTree(new int[]{4, 2, 5, 1, 6, 3, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
        assertEquals(4, result.left.left.val);
        assertEquals(5, result.left.right.val);
        assertEquals(6, result.right.left.val);
        assertEquals(7, result.right.right.val);
    }

    @Test
    public void testThreeNodesRightHeavy() {
        // Tree: 1 -> right:2 -> right:3
        // inorder: 1,2,3  postorder: 3,2,1
        TreeNode result = test.buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1});
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.right.val);
    }

    @Test
    public void testNegativeValues() {
        // inorder: -3,-2,-1  postorder: -3,-2,-1 (left skewed)
        TreeNode result = test.buildTree(new int[]{-3, -2, -1}, new int[]{-3, -2, -1});
        assertEquals(-1, result.val);
        assertEquals(-2, result.left.val);
        assertEquals(-3, result.left.left.val);
    }

    @Test
    public void testGiantTree() {
        // Complete binary tree of 15 nodes
        int[] inorder = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15};
        int[] postorder = {8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1};
        TreeNode result = test.buildTree(inorder, postorder);
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
        assertEquals(4, result.left.left.val);
        assertEquals(5, result.left.right.val);
        assertEquals(8, result.left.left.left.val);
        assertEquals(15, result.right.right.right.val);
    }
}
