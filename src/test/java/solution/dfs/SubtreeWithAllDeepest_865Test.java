package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SubtreeWithAllDeepest_865Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5); root.right = new TreeNode(1);
        root.left.left = new TreeNode(6); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7); root.left.right.right = new TreeNode(4);
        assertEquals(2, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(null));
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testSingleDeepestLeafOnLeft() {
        // Only one deepest leaf on left side - that leaf itself is the answer
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testSingleDeepestLeafOnRight() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testDeepestLeavesAtSameParent() {
        // Two deepest leaves share the same parent
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        assertEquals(2, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testAllLeavesAtSameDepth() {
        // Perfect binary tree - root is the LCA
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testDeepestLeavesOnBothSidesOfRoot() {
        // Deepest leaves on both sides of root
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testLinearTreeLeftOnly() {
        // Linear tree going left
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(4, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testGiantTree() {
        // Build a large balanced tree of depth 10
        TreeNode root = buildBalancedTree(1, 10);
        // In a perfect balanced tree, root is the LCA of all deepest leaves
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    private TreeNode buildBalancedTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildBalancedTree(val * 2, depth - 1);
        node.right = buildBalancedTree(val * 2 + 1, depth - 1);
        return node;
    }
}
