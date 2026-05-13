package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsValidBST_98Test {

    private final IsValidBST_98 test = new IsValidBST_98();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(6);
        assertFalse(test.isValidBST(root));
        assertTrue(test.isValidBST(null));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(4);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testSingleNode() {
        assertTrue(test.isValidBST(new TreeNode(0)));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        assertFalse(test.isValidBST(root));
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testInvalidDeepLeft() {
        // right subtree has a node smaller than root
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(15);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(20);
        assertFalse(test.isValidBST(root)); // 6 < 10 but in right subtree
    }

    @Test
    public void testIntegerMinMax() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testIntegerOverflowEdge() {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testGiantValidBST() {
        // Build a balanced BST with 63 nodes
        TreeNode root = buildBalancedBST(1, 63);
        assertTrue(test.isValidBST(root));
    }

    private TreeNode buildBalancedBST(int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedBST(lo, mid - 1);
        node.right = buildBalancedBST(mid + 1, hi);
        return node;
    }
}
