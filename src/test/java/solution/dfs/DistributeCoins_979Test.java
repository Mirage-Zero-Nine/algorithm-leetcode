package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DistributeCoins_979Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(2, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, new DistributeCoins_979().distributeCoins(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(3); root.right = new TreeNode(0);
        assertEquals(3, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testAllCoinsAtRoot() {
        // root=4, left=0, right=0, leftleft=0 -> 4 nodes, 4 coins all at root
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        assertEquals(4, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testAlreadyBalanced() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        assertEquals(0, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testCoinsAtLeaves() {
        // root=0, left=2, right=2 -> need to move 1 from each leaf to root
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        assertEquals(2, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testLeftSkewed() {
        // 1->0->3 (left chain): node vals [1,0,3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(3);
        assertEquals(3, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(0);
        root.right.right = new TreeNode(3);
        assertEquals(3, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testTwoNodesCoinsAtChild() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        assertEquals(1, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testNegativeCase() {
        // All coins at one deep leaf
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(4);
        assertEquals(6, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testGiantTree() {
        // Complete binary tree with 7 nodes, all coins at root
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        root.left.left = new TreeNode(0); root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(0);
        assertEquals(10, new DistributeCoins_979().distributeCoins(root));
    }
}
