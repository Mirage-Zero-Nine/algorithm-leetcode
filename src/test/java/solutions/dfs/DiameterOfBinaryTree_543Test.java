package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DiameterOfBinaryTree_543Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(3, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new DiameterOfBinaryTree_543().diameterOfBinaryTree(null));
        assertEquals(0, new DiameterOfBinaryTree_543().diameterOfBinaryTree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        assertEquals(4, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(1, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(3, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(3, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testDiameterNotThroughRoot() {
        // Diameter is in the left subtree, not passing through root
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        // diameter: 5->3->2->4->6 = 4
        assertEquals(4, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        // diameter: 4->2->1->3->6 or similar = 4
        assertEquals(4, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testDeepTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        // diameter: 4->3->2->1->5->6->7 = 6
        assertEquals(6, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testGiantCase() {
        // Build a chain of 100 nodes on left, 100 on right
        TreeNode root = new TreeNode(0);
        TreeNode curr = root;
        for (int i = 1; i <= 100; i++) {
            curr.left = new TreeNode(i);
            curr = curr.left;
        }
        curr = root;
        for (int i = 1; i <= 100; i++) {
            curr.right = new TreeNode(-i);
            curr = curr.right;
        }
        assertEquals(200, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertEquals(2, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }
}
