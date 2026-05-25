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

    @Test
    public void testSingleNodeNegativeLarge() {
        // Must include at least one node, even if negative
        assertEquals(-2147483648, new MaxPathSum_124().maxPathSum(new TreeNode(Integer.MIN_VALUE)));
    }

    @Test
    public void testAllNegativeDeeper() {
        // All negative: answer is the least negative (max single node)
        TreeNode root = new TreeNode(-5);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-8);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(-1);
        root.right.left = new TreeNode(-9);
        // Least negative is -1
        assertEquals(-1, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPathThroughRootBothSubtrees() {
        // Best path uses both subtrees through root: 3 + 5 + 4 = 12
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(-10);
        root.right.right = new TreeNode(-10);
        assertEquals(12, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPathEntirelyInLeftSubtree() {
        // Best path is entirely in left subtree: 8 + 10 + 9 = 27
        TreeNode root = new TreeNode(-50);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(9);
        root.right = new TreeNode(-50);
        assertEquals(27, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLongChainPositive() {
        // Chain of 20 nodes each with value 5, best path = 20 * 5 = 100
        TreeNode root = new TreeNode(5);
        TreeNode cur = root;
        for (int i = 1; i < 20; i++) {
            cur.right = new TreeNode(5);
            cur = cur.right;
        }
        assertEquals(100, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLongChainMixed() {
        // Chain: 1 -> -100 -> 50 -> 50 -> 50, best subpath = 50+50+50 = 150
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-100);
        root.left.left = new TreeNode(50);
        root.left.left.left = new TreeNode(50);
        root.left.left.left.left = new TreeNode(50);
        assertEquals(150, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPropertyResultGeqMaxNode() {
        // Property: result >= max single node value for various trees
        int[][] trees = {{1, 2, 3}, {-10, 9, 20, 15, 7}, {-1, -2, -3, -4, -5}};
        for (int[] vals : trees) {
            TreeNode root = buildFromArray(vals);
            int result = new MaxPathSum_124().maxPathSum(root);
            int maxNode = Integer.MIN_VALUE;
            for (int v : vals) maxNode = Math.max(maxNode, v);
            assert result >= maxNode : "Result " + result + " < max node " + maxNode;
        }
    }

    @Test
    public void testDiamondShapedTree() {
        //       1
        //      / \
        //     2   3
        //      \ /
        //       4  (not possible in binary tree, so simulate with separate subtrees)
        // Instead: test where both children have good paths
        //       1
        //      / \
        //    10   10
        //    /      \
        //   5        5
        // Best: 5+10+1+10+5 = 31
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(5);
        assertEquals(31, new MaxPathSum_124().maxPathSum(root));
    }

    private TreeNode buildFromArray(int[] vals) {
        if (vals.length == 0) return null;
        TreeNode[] nodes = new TreeNode[vals.length];
        for (int i = 0; i < vals.length; i++) nodes[i] = new TreeNode(vals[i]);
        for (int i = 0; i < vals.length; i++) {
            if (2 * i + 1 < vals.length) nodes[i].left = nodes[2 * i + 1];
            if (2 * i + 2 < vals.length) nodes[i].right = nodes[2 * i + 2];
        }
        return nodes[0];
    }
}
