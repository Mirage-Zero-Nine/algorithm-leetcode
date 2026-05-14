package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxAncestorDiff_1026Test {

    private final MaxAncestorDiff_1026 test = new MaxAncestorDiff_1026();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3); root.right = new TreeNode(10);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(6);
        assertEquals(7, test.maxAncestorDiff(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.maxAncestorDiff(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); root.right.right = new TreeNode(0);
        root.right.right.left = new TreeNode(3);
        assertEquals(3, test.maxAncestorDiff(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        assertEquals(3, test.maxAncestorDiff(root));
    }

    @Test
    public void testIncreasingPath() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(10);
        assertEquals(9, test.maxAncestorDiff(root));
    }

    @Test
    public void testDecreasingPath() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        assertEquals(9, test.maxAncestorDiff(root));
    }

    @Test
    public void testSymmetricTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(9);
        assertEquals(4, test.maxAncestorDiff(root));
    }

    @Test
    public void testDeepLeftBranch() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(1);
        root.right = new TreeNode(12);
        assertEquals(9, test.maxAncestorDiff(root));
    }

    @Test
    public void testMaxDiffInRightSubtree() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(100);
        root.right.right = new TreeNode(3);
        assertEquals(98, test.maxAncestorDiff(root));
    }

    @Test
    public void testGiantTree() {
        // Build a chain: 0 -> 1 -> 2 -> ... -> 1000
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i <= 1000; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(1000, test.maxAncestorDiff(root));
    }
}
