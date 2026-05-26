package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class ClosestValue_270Test {

    private final ClosestValue_270 test = new ClosestValue_270();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        assertEquals(4, test.closestValue(root, 3.714286));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.closestValue(new TreeNode(1), 4.428571));
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertEquals(2, test.closestValue(root, 2.0));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(4);
        assertEquals(4, test.closestValue(root, 4.1));
    }

    @Test
    public void testTargetBelowMinimum() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        assertEquals(2, test.closestValue(root, -100.0));
    }

    @Test
    public void testTargetAboveMaximum() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(20);
        root.right.right = new TreeNode(30);
        assertEquals(30, test.closestValue(root, 100.0));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-5);
        root.left = new TreeNode(-10);
        root.right = new TreeNode(-2);
        assertEquals(-2, test.closestValue(root, -1.6));
    }

    @Test
    public void testExactHitReturnsNode() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        assertEquals(3, test.closestValue(root, 3.0));
    }

    @Test
    public void testTieDistanceKeepsEarlierValueByTraversal() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        assertEquals(4, test.closestValue(root, 5.0));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(3, test.closestValue(root, 3.3));
    }

    @Test
    public void testGiantSkewedTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 150; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        assertEquals(149, test.closestValue(root, 149.2));
    }
}
