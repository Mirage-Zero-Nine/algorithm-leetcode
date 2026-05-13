package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsBalanced_110Test {

    private final IsBalanced_110 test = new IsBalanced_110();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertTrue(test.isBalanced(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isBalanced(null));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertFalse(test.isBalanced(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        assertTrue(test.isBalanced(root));
    }

    @Test
    public void testSingleNode() {
        assertTrue(test.isBalanced(new TreeNode(1)));
    }

    @Test
    public void testTwoNodesLeft() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertTrue(test.isBalanced(root));
    }

    @Test
    public void testTwoNodesRight() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertTrue(test.isBalanced(root));
    }

    @Test
    public void testUnbalancedRight() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertFalse(test.isBalanced(root));
    }

    @Test
    public void testDeepUnbalanced() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        assertFalse(test.isBalanced(root));
    }

    @Test
    public void testPerfectTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertTrue(test.isBalanced(root));
    }

    @Test
    public void testGiantBalancedTree() {
        // 15-node perfect tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8); root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10); root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12); root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14); root.right.right.right = new TreeNode(15);
        assertTrue(test.isBalanced(root));
    }
}
