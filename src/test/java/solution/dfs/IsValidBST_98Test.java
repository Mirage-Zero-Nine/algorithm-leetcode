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

    // --- New tricky/edge/negative/large tests ---

    @Test
    public void testAllDuplicateValues() {
        // [2,2,2] -> false, strict inequality required
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        assertFalse(test.isValidBST(root));
    }

    @Test
    public void testRightChildEqualToRoot() {
        // right child equal to root -> false (strictly greater needed)
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(2);
        assertFalse(test.isValidBST(root));
    }

    @Test
    public void testLeftSubtreeRightChildViolatesRootBound() {
        // Subtle: left subtree's RIGHT child equals root -> false
        // Tree: root=5, left=3, left.right=5 (5 is not < 5)
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        assertFalse(test.isValidBST(root));
    }

    @Test
    public void testDeepLeftSkewedValid() {
        // 10 -> 9 -> 8 -> ... -> 1 (all left children, valid BST)
        TreeNode root = new TreeNode(10);
        TreeNode cur = root;
        for (int i = 9; i >= 1; i--) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testDeepRightSkewedValid() {
        // 1 -> 2 -> 3 -> ... -> 10 (all right children, valid BST)
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 10; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testIntMinRootWithRightSubtree() {
        // INT_MIN root, left null, right subtree valid
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(-1);
        root.right.right = new TreeNode(Integer.MAX_VALUE);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testRightSubtreeDeepNodeViolatesRoot() {
        // root=10, right=15, right.left=6 (6 < 10, violates root's lower bound)
        // Already tested in testInvalidDeepLeft but this confirms with different structure
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(6);
        assertFalse(test.isValidBST(root));
    }

    @Test
    public void testLeftSubtreeDeepRightNodeExceedsRoot() {
        // root=10, left=5, left.right=12 (12 > 10, violates root's upper bound)
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(12);
        assertFalse(test.isValidBST(root));
    }

    @Test
    public void testLargeBalancedValidBST() {
        // 1023 nodes (depth 10 balanced BST)
        TreeNode root = buildBalancedBST(1, 1023);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testNegativeValues() {
        // Valid BST with all negative values: [-5, -10, -1]
        TreeNode root = new TreeNode(-5);
        root.left = new TreeNode(-10);
        root.right = new TreeNode(-1);
        assertTrue(test.isValidBST(root));
    }
}
