package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxPathSum_124Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(6, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-3, new MaxPathSum_124().maxPathSum(new TreeNode(-3)));
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(42, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13); root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        assertEquals(48, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testSingleNode() {
        assertEquals(42, new MaxPathSum_124().maxPathSum(new TreeNode(42)));
    }

    @Test
    public void testAllNegative() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        // Best path is just -1 (the root alone)
        assertEquals(-1, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        // Best path: 3 + 2 + 1 = 6
        assertEquals(6, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(3);
        // Best path: 10 + 5 + 3 = 18
        assertEquals(18, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPathNotThroughRoot() {
        TreeNode root = new TreeNode(-100);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(6);
        // Best path: 5 + 10 + 6 = 21, doesn't go through root
        assertEquals(21, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testMixedValues() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);
        // Best path: 2 alone (since -1 would reduce it)
        assertEquals(2, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testZeroValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(0, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testGiantTree() {
        // Build a perfect binary tree of depth 10 with all values = 1
        TreeNode root = buildPerfectTree(1, 10);
        // The max path goes through the root: sum of left chain + root + sum of right chain
        // Each chain from root to leaf has 10 nodes, so path = 10 + 10 - 1 = 19
        assertEquals(19, new MaxPathSum_124().maxPathSum(root));
    }

    private TreeNode buildPerfectTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildPerfectTree(val, depth - 1);
        node.right = buildPerfectTree(val, depth - 1);
        return node;
    }
}
