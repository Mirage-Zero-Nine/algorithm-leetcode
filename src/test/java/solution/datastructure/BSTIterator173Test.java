package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BSTIterator173Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3); root.right = new TreeNode(15);
        root.right.left = new TreeNode(9); root.right.right = new TreeNode(20);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertEquals(3, iter.next());
        assertEquals(7, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(9, iter.next());
    }

    @Test
    public void testEdgeCases() {
        BSTIterator_173 iter = new BSTIterator_173(new TreeNode(1));
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(4);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertEquals(1, iter.next());
        assertEquals(3, iter.next());
        assertEquals(4, iter.next());
        assertEquals(5, iter.next());
        assertEquals(7, iter.next());
        assertFalse(iter.hasNext());
    }
}
