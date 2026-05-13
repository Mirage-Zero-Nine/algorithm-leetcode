package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BtreeGameWinningMove_1145Test {

    private final BtreeGameWinningMove_1145 test = new BtreeGameWinningMove_1145();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertTrue(test.btreeGameWinningMove(root, 7, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.btreeGameWinningMove(new TreeNode(1), 1, 1));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertTrue(test.btreeGameWinningMove(root, 5, 1));
    }

    @Test
    public void testPlayerPicksRoot() {
        // Player 1 picks root(1), left subtree has 4 nodes, right has 2 => left > 7/2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6); root.left.left.right = new TreeNode(7);
        assertTrue(test.btreeGameWinningMove(root, 7, 1));
    }

    @Test
    public void testBalancedTreePickRoot() {
        // Balanced tree n=3, pick root => left=1, right=1, rest=0 => max=1, not > 1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertFalse(test.btreeGameWinningMove(root, 3, 1));
    }

    @Test
    public void testPickLeafNode() {
        // Pick leaf node 4: left=0, right=0, rest=6 => 6 > 7/2=3 => true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertTrue(test.btreeGameWinningMove(root, 7, 4));
    }

    @Test
    public void testLeftSkewedTree() {
        // 1->2->3->4->5, pick node 3: left=2, right=0, rest=2 => max=2, not > 2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(5);
        assertFalse(test.btreeGameWinningMove(root, 5, 3));
    }

    @Test
    public void testLeftSkewedPickNode2() {
        // 1->2->3->4->5, pick node 2: left=3, right=0, rest=1 => 3 > 2 => true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(5);
        assertTrue(test.btreeGameWinningMove(root, 5, 2));
    }

    @Test
    public void testRightHeavyTree() {
        // root(1), left(2), right(3)->right(4)->right(5)
        // pick 3: left=0, right=2, rest=2 => max=2, not > 2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertFalse(test.btreeGameWinningMove(root, 5, 3));
    }

    @Test
    public void testParentRegionWins() {
        // Pick a deep node where parent region dominates
        // 1->left:2->left:3, 1->right:4->right:5->right:6->right:7
        // pick 3: left=0, right=0, rest=6 => 6 > 3 => true
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        root.right.right.right.right = new TreeNode(7);
        assertTrue(test.btreeGameWinningMove(root, 7, 3));
    }

    @Test
    public void testGiantTree() {
        // Build a complete binary tree of 15 nodes, pick node at position with large subtree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8); root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10); root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12); root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14); root.right.right.right = new TreeNode(15);
        // pick node 2: left=4(nodes 4,8,9,10... wait let me count), left subtree of 2 has nodes 4,8,9 = 3 nodes
        // right subtree of 2 has nodes 5,10,11 = 3 nodes, rest = 15-3-3-1=8 => 8 > 7 => true
        assertTrue(test.btreeGameWinningMove(root, 15, 2));
    }
}
