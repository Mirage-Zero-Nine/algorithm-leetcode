package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxDepth_104Test {

    private final MaxDepth_104 test = new MaxDepth_104();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxDepth(null));
        assertEquals(1, test.maxDepth(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 10; i++) { cur.left = new TreeNode(i); cur = cur.left; }
        assertEquals(10, test.maxDepth(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testBalancedDepth3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, test.maxDepth(root));
    }

    @Test
    public void testLeftDeeperThanRight() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        assertEquals(4, test.maxDepth(root));
    }

    @Test
    public void testRightDeeperThanLeft() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        assertEquals(5, test.maxDepth(root));
    }

    @Test
    public void testZigZag() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        assertEquals(4, test.maxDepth(root));
    }

    @Test
    public void testGiantTree() {
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i <= 5000; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        assertEquals(5001, test.maxDepth(root));
    }

    @Test
    public void testLeftSkewedChain1000() {
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i < 1000; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(1000, test.maxDepth(root));
    }

    @Test
    public void testRightSkewedChain1000() {
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i < 1000; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        assertEquals(1000, test.maxDepth(root));
    }

    @Test
    public void testPerfectBinaryTreeDepth4() {
        // Level-order: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 -> depth 4
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        assertEquals(4, test.maxDepth(root));
    }

    @Test
    public void testTreeParserUnbalanced() {
        // Left subtree depth 3, right subtree depth 1
        TreeNode root = TreeParser.deserialize("1,2,3,4,null,null,null,5");
        assertEquals(4, test.maxDepth(root));
    }

    @Test
    public void testOnlyLeftAndRightChild() {
        // Root with exactly one left and one right child, no grandchildren
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(2, test.maxDepth(root));
    }

    @Test
    public void testMirrorInvariance() {
        // maxDepth should be the same after inverting (mirroring) the tree
        TreeNode root = TreeParser.deserialize("1,2,3,4,null,null,5,6");
        int originalDepth = test.maxDepth(root);
        invertTree(root);
        assertEquals(originalDepth, test.maxDepth(root));
    }

    @Test
    public void testLargeBalancedTreeViaParser() {
        // Build a complete binary tree with 1023 nodes (depth 10) as a string
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 1023; i++) {
            if (i > 1) sb.append(",");
            sb.append(i);
        }
        TreeNode root = TreeParser.deserialize(sb.toString());
        assertEquals(10, test.maxDepth(root));
    }

    @Test
    public void testDeeplyUnbalancedLeftVsRight() {
        // Left subtree depth 7, right subtree depth 2
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(100);
        root.right.left = new TreeNode(101);
        TreeNode cur = root;
        for (int i = 0; i < 6; i++) {
            cur.left = new TreeNode(i + 2);
            cur = cur.left;
        }
        assertEquals(7, test.maxDepth(root));
    }

    private void invertTree(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
    }
}
