package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BSTIterator_173Test {
    private List<Integer> consume(BSTIterator_173 iter) {
        List<Integer> out = new ArrayList<>();
        while (iter.hasNext()) {
            out.add(iter.next());
        }
        return out;
    }

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

    @Test
    public void testNullRoot() {
        BSTIterator_173 iter = new BSTIterator_173(null);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertEquals(List.of(1, 2, 3, 4), consume(iter));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertEquals(List.of(1, 2, 3, 4), consume(iter));
    }

    @Test
    public void testBalancedTreeTraversal() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4); root.right = new TreeNode(10);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(9); root.right.right = new TreeNode(12);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertEquals(List.of(2, 4, 6, 8, 9, 10, 12), consume(iter));
    }

    @Test
    public void testHasNextBeforeAndAfterEachNext() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(2, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(3, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testTreeWithNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-3);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(-1);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertEquals(List.of(-3, -1, 0, 5), consume(iter));
    }

    @Test
    public void testGiantCase() {
        TreeNode root = null;
        for (int i = 1000; i >= 1; i--) {
            TreeNode node = new TreeNode(i);
            node.right = root;
            root = node;
        }
        BSTIterator_173 iter = new BSTIterator_173(root);
        List<Integer> out = consume(iter);
        assertEquals(1000, out.size());
        assertEquals(1, out.get(0));
        assertEquals(1000, out.get(999));
    }
}
