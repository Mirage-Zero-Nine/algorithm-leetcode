package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
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

    /** Builds a valid BST with distinct keys for generated contract cases. */
    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        TreeNode current = root;
        while (true) {
            if (value < current.val) {
                if (current.left == null) {
                    current.left = new TreeNode(value);
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TreeNode(value);
                    break;
                }
                current = current.right;
            }
        }
        return root;
    }

    /** Independent recursive in-order oracle used instead of mirroring the implementation's stack. */
    private List<Integer> recursiveInOrder(TreeNode node) {
        List<Integer> values = new ArrayList<>();
        collectInOrder(node, values);
        return values;
    }

    private void collectInOrder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        collectInOrder(node.left, values);
        values.add(node.val);
        collectInOrder(node.right, values);
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
    public void testOfficialExampleCompletesEveryOperationInOrder() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator_173 iter = new BSTIterator_173(root);
        assertTrue(iter.hasNext());
        assertEquals(3, iter.next());
        assertEquals(7, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(9, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(15, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(20, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testRootWithOnlyLeftChild() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertEquals(List.of(1, 2), consume(new BSTIterator_173(root)));
    }

    @Test
    public void testRootWithOnlyRightChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertEquals(List.of(1, 2), consume(new BSTIterator_173(root)));
    }

    @Test
    public void testMinimumAndMaximumContractValues() {
        TreeNode root = new TreeNode(1_000_000);
        root.left = new TreeNode(0);
        assertEquals(List.of(0, 1_000_000), consume(new BSTIterator_173(root)));
    }

    @Test
    public void testZeroValuedSingleton() {
        BSTIterator_173 iter = new BSTIterator_173(new TreeNode(0));
        assertTrue(iter.hasNext());
        assertEquals(0, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testRepeatedHasNextCallsDoNotAdvancePointer() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        BSTIterator_173 iter = new BSTIterator_173(root);
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals(2, iter.next());
    }

    @Test
    public void testRepeatedHasNextAfterExhaustionRemainsFalse() {
        BSTIterator_173 iter = new BSTIterator_173(new TreeNode(42));
        assertEquals(42, iter.next());
        assertFalse(iter.hasNext());
        assertFalse(iter.hasNext());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testFullTraversalUsesIndependentRecursiveOracle() {
        TreeNode root = null;
        int[] values = {50, 25, 75, 10, 40, 60, 90, 5, 15, 35, 45, 55, 65, 80, 100};
        for (int value : values) {
            root = insert(root, value);
        }
        List<Integer> expected = recursiveInOrder(root);
        assertEquals(expected, consume(new BSTIterator_173(root)));
    }

    @Test
    public void testIrregularValidTreeMatchesRecursiveOracle() {
        TreeNode root = new TreeNode(40);
        root.left = new TreeNode(20);
        root.left.right = new TreeNode(30);
        root.left.right.left = new TreeNode(25);
        root.right = new TreeNode(80);
        root.right.left = new TreeNode(60);
        root.right.left.right = new TreeNode(70);
        root.right.right = new TreeNode(100);
        assertEquals(recursiveInOrder(root), consume(new BSTIterator_173(root)));
    }

    @Test
    public void testAllSupportedValuesAreReturnedSorted() {
        int[] values = {0, 1, 2, 17, 99, 100_000, 500_000, 999_999, 1_000_000};
        TreeNode root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        assertEquals(Arrays.stream(values).sorted().boxed().toList(),
                consume(new BSTIterator_173(root)));
    }

    @Test
    public void testNegativeValuesRemainOrderedWhenImplementationSupportsThem() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(-5);
        root.right.left = new TreeNode(-7);
        root.right.right = new TreeNode(-1);
        assertEquals(List.of(-20, -10, -7, -5, -1),
                consume(new BSTIterator_173(root)));
    }

    @Test
    public void testSeparateIteratorsHaveIndependentPositions() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        BSTIterator_173 first = new BSTIterator_173(root);
        BSTIterator_173 second = new BSTIterator_173(root);

        assertEquals(1, first.next());
        assertEquals(1, second.next());
        assertEquals(2, first.next());
        assertEquals(List.of(2, 3, 4, 6), consume(second));
        assertEquals(List.of(3, 4, 6), consume(first));
    }

    @Test
    public void testIteratorStateDoesNotLeakAcrossNewTree() {
        BSTIterator_173 first = new BSTIterator_173(new TreeNode(8));
        assertEquals(8, first.next());
        assertFalse(first.hasNext());

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        BSTIterator_173 second = new BSTIterator_173(root);
        assertEquals(List.of(1, 2, 3), consume(second));
    }

    @Test
    public void testTraversalDoesNotMutateInputTopologyOrValues() {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(8);
        TreeNode leftRight = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.right = leftRight;

        assertEquals(List.of(3, 4, 5, 8), consume(new BSTIterator_173(root)));
        assertEquals(5, root.val);
        assertEquals(left, root.left);
        assertEquals(right, root.right);
        assertEquals(leftRight, root.left.right);
        assertNull(root.left.left);
        assertNull(root.right.left);
        assertNull(root.right.right);
    }

    @Test
    public void testAlternatingLeftAndRightBranches() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.left.right.left = new TreeNode(6);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);
        assertEquals(List.of(5, 6, 7, 10, 12, 13, 15), consume(new BSTIterator_173(root)));
    }

    @Test
    public void testLargeLeftSkewedTreeReturnsAscendingSequence() {
        TreeNode root = null;
        for (int value = 1; value <= 1000; value++) {
            TreeNode node = new TreeNode(value);
            node.left = root;
            root = node;
        }
        BSTIterator_173 iter = new BSTIterator_173(root);
        for (int expected = 1; expected <= 1000; expected++) {
            assertTrue(iter.hasNext());
            assertEquals(expected, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test
    public void testMaximumNodeBoundaryWithoutMaterializingExpectedList() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int value = 1; value < 100_000; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }

        BSTIterator_173 iter = new BSTIterator_173(root);
        for (int expected = 0; expected < 100_000; expected++) {
            assertEquals(expected, iter.next());
        }
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
