package library.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Tests for TreeDrawer.
 */
public class TreeDrawerTest {

    @Test
    public void testInvertTreeNull() {
        TreeNode result = TreeDrawer.invertTree(null);
        assertNull(result);
    }

    @Test
    public void testInvertTreeSingleNode() {
        TreeNode root = new TreeNode(1);
        TreeNode result = TreeDrawer.invertTree(root);
        assertNotNull(result);
        assertEquals(1, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testInvertTreeTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode result = TreeDrawer.invertTree(root);
        assertNotNull(result);
        assertEquals(1, result.val);
        assertEquals(2, result.right.val);
        assertNull(result.left);
    }

    @Test
    public void testInvertTreeFullTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeNode result = TreeDrawer.invertTree(root);
        assertNotNull(result);
        assertEquals(1, result.val);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertNull(result.left.left);
        assertNull(result.left.right);
        assertEquals(5, result.right.left.val);
        assertEquals(4, result.right.right.val);
    }

    @Test
    public void testInvertTreeComplexTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode result = TreeDrawer.invertTree(root);
        assertEquals(1, result.val);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(7, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(5, result.right.left.val);
        assertEquals(4, result.right.right.val);
    }

    @Test
    public void testInvertTreeDoubleInvert() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        TreeNode inverted = TreeDrawer.invertTree(root);
        TreeNode doubleInverted = TreeDrawer.invertTree(inverted);
        assertEquals(1, doubleInverted.val);
        assertEquals(2, doubleInverted.left.val);
        assertEquals(3, doubleInverted.right.val);
    }

    @Test void testInvertRightOnlyTree() {
        TreeNode root = new TreeNode(1); root.right = new TreeNode(2);
        TreeDrawer.invertTree(root);
        assertEquals(2, root.left.val); assertNull(root.right);
    }
    @Test void testInvertLeftOnlyDeepTree() {
        TreeNode root = new TreeNode(1); root.left = new TreeNode(2); root.left.left = new TreeNode(3);
        TreeDrawer.invertTree(root);
        assertEquals(2, root.right.val); assertEquals(3, root.right.right.val);
    }
    @Test void testInvertNegativeValues() {
        TreeNode root = new TreeNode(-1); root.left = new TreeNode(Integer.MIN_VALUE); root.right = new TreeNode(Integer.MAX_VALUE);
        TreeDrawer.invertTree(root);
        assertEquals(Integer.MAX_VALUE, root.left.val); assertEquals(Integer.MIN_VALUE, root.right.val);
    }
    @Test void testInvertDuplicateValuesStillSwapsNodes() {
        TreeNode left = new TreeNode(1), right = new TreeNode(1), root = new TreeNode(1);
        root.left = left; root.right = right;
        TreeDrawer.invertTree(root);
        org.junit.jupiter.api.Assertions.assertSame(right, root.left);
        org.junit.jupiter.api.Assertions.assertSame(left, root.right);
    }
    @Test void testInvertPreservesNodeCount() {
        TreeNode root = new TreeNode(1); root.left = new TreeNode(2); root.right = new TreeNode(3); root.left.right = new TreeNode(4);
        TreeDrawer.invertTree(root);
        assertEquals(4, count(root));
    }
    @Test void testInvertIsInPlace() { TreeNode root = new TreeNode(1); assertSame(root, TreeDrawer.invertTree(root)); }
    @Test void testDoubleInvertDeepTreeRestoresShape() {
        TreeNode root = new TreeNode(1); root.left = new TreeNode(2); root.left.right = new TreeNode(3);
        TreeDrawer.invertTree(root); TreeDrawer.invertTree(root);
        assertEquals(2, root.left.val); assertEquals(3, root.left.right.val);
    }
    @Test void testDrawNullDoesNotThrow() { org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> TreeDrawer.draw(null)); }
    @Test void testDrawSingleNodeDoesNotThrow() { org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> TreeDrawer.draw(new TreeNode(8))); }
    @Test void testDrawSparseTreeDoesNotThrow() { TreeNode root = new TreeNode(1); root.right = new TreeNode(3); assertDoesNotThrow(() -> TreeDrawer.draw(root)); }
    @Test void testInvertSeveralLevels() {
        TreeNode root = new TreeNode(1); root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.right.right = new TreeNode(7);
        TreeDrawer.invertTree(root);
        assertEquals(7, root.left.left.val); assertEquals(4, root.right.right.val);
    }
    private int count(TreeNode node) { return node == null ? 0 : 1 + count(node.left) + count(node.right); }
}
