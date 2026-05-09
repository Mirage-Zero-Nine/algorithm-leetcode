package library.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
