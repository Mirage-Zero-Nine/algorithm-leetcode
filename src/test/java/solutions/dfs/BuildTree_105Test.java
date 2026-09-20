package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/** Tests reconstruction from preorder and inorder traversals. */
public class BuildTree_105Test {

    private final BuildTree_105 test = new BuildTree_105();

    @Test
    public void reconstructsOfficialExample() {
        assertTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    @Test
    public void reconstructsOfficialNegativeSingleton() {
        assertTree(new int[]{-1}, new int[]{-1});
    }

    @Test
    public void returnsNullForEmptyArrays() {
        assertNull(test.buildTree(new int[]{}, new int[]{}));
    }

    @Test
    public void returnsNullForBothNullArrays() {
        assertNull(test.buildTree(null, null));
    }

    @Test
    public void returnsNullForNullPreorder() {
        assertNull(test.buildTree(null, new int[]{1}));
    }

    @Test
    public void returnsNullForNullInorder() {
        assertNull(test.buildTree(new int[]{1}, null));
    }

    @Test
    public void returnsNullForMismatchedLengths() {
        assertNull(test.buildTree(new int[]{1, 2}, new int[]{1}));
    }

    @Test
    public void reconstructsTwoNodeLeftChild() {
        TreeNode root = assertTree(new int[]{1, 2}, new int[]{2, 1});
        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertNull(root.right);
    }

    @Test
    public void reconstructsTwoNodeRightChild() {
        TreeNode root = assertTree(new int[]{1, 2}, new int[]{1, 2});
        assertEquals(1, root.val);
        assertNull(root.left);
        assertEquals(2, root.right.val);
    }

    @Test
    public void reconstructsBalancedCompleteTree() {
        assertTree(
                new int[]{1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15},
                new int[]{8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15});
    }

    @Test
    public void reconstructsSparseTreeWithMissingChildren() {
        assertTree(
                new int[]{10, 5, 2, 7, 15, 12, 20, 18},
                new int[]{2, 5, 7, 10, 12, 15, 18, 20});
    }

    @Test
    public void reconstructsAllNegativeValues() {
        assertTree(new int[]{-1, -3, -4, -2, 0}, new int[]{-4, -3, -2, -1, 0});
    }

    @Test
    public void reconstructsIntegerAndProblemBounds() {
        assertTree(new int[]{0, -3000, Integer.MIN_VALUE, -2999, 3000, Integer.MAX_VALUE},
                new int[]{Integer.MIN_VALUE, -3000, -2999, 0, 3000, Integer.MAX_VALUE});
    }

    @Test
    public void doesNotMutateTraversalInputs() {
        int[] preorder = {8, 4, 2, 6, 12, 10, 14};
        int[] inorder = {2, 4, 6, 8, 10, 12, 14};
        int[] preorderCopy = preorder.clone();
        int[] inorderCopy = inorder.clone();

        assertTree(preorder, inorder);

        assertArrayEquals(preorderCopy, preorder);
        assertArrayEquals(inorderCopy, inorder);
    }

    @Test
    public void repeatedCallsOnSameInstanceHaveNoStateLeak() {
        BuildTree_105 solver = new BuildTree_105();
        TreeNode first = solver.buildTree(new int[]{1, 2, 3}, new int[]{2, 1, 3});
        TreeNode second = solver.buildTree(new int[]{9, 7, 11, 10}, new int[]{7, 9, 10, 11});

        assertArrayEquals(new int[]{1, 2, 3}, preorder(first));
        assertArrayEquals(new int[]{2, 1, 3}, inorder(first));
        assertArrayEquals(new int[]{9, 7, 11, 10}, preorder(second));
        assertArrayEquals(new int[]{7, 9, 10, 11}, inorder(second));
    }

    @Test
    public void separateResultsDoNotShareNodes() {
        int[] preorder = {4, 2, 1, 3, 6, 5, 7};
        int[] inorder = {1, 2, 3, 4, 5, 6, 7};
        TreeNode first = test.buildTree(preorder, inorder);
        TreeNode second = test.buildTree(preorder, inorder);

        assertNotSame(first, second);
        assertNotSame(first.left, second.left);
        assertNotSame(first.right, second.right);
        first.left.val = 999;
        assertEquals(4, second.val);
        assertEquals(2, second.left.val);
    }

    @Test
    public void reconstructsLeftSkewedTree() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] inorder = values.clone();
        reverse(inorder);
        assertTree(values, inorder);
    }

    @Test
    public void reconstructsRightSkewedTree() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        assertTree(values, values.clone());
    }

    @Test
    public void reconstructsAlternatingSingleChildTree() {
        TreeNode expected = new TreeNode(0);
        expected.left = new TreeNode(-1);
        expected.left.right = new TreeNode(2);
        expected.left.right.left = new TreeNode(-3);
        expected.left.right.left.right = new TreeNode(4);
        expected.left.right.left.right.left = new TreeNode(-5);
        assertSameTraversals(expected);
    }

    @Test
    public void reconstructsSparseTreeWithSignedValues() {
        TreeNode expected = new TreeNode(-10);
        expected.right = new TreeNode(-20);
        expected.right.left = new TreeNode(-30);
        expected.right.left.right = new TreeNode(40);
        expected.right.right = new TreeNode(50);
        assertSameTraversals(expected);
    }

    @Test
    public void reconstructsAllFiveNodeBinaryTreeShapes() {
        List<Shape> shapes = allShapes(5);
        assertEquals(42, shapes.size());
        for (Shape shape : shapes) {
            TreeNode expected = labelByInorder(shape, new int[]{-2, -1, 0, 1, 2});
            assertSameTraversals(expected);
        }
    }

    @Test
    public void exhaustivelyReconstructsAllFourNodeShapes() {
        List<Shape> shapes = allShapes(4);
        assertEquals(14, shapes.size());
        for (Shape shape : shapes) {
            TreeNode expected = labelByInorder(shape, new int[]{10, 20, 30, 40});
            assertSameTraversals(expected);
        }
    }

    @Test
    public void reconstructsSeededRandomUniqueTrees() {
        Random random = new Random(105_2026L);
        for (int trial = 0; trial < 200; trial++) {
            int size = 1 + random.nextInt(30);
            TreeNode expected = randomTree(random, size);
            assertSameTraversals(expected);
        }
    }

    @Test
    public void reconstructsSeededRandomLargerTrees() {
        Random random = new Random(0x105L);
        for (int trial = 0; trial < 20; trial++) {
            TreeNode expected = randomTree(random, 100 + random.nextInt(101));
            assertSameTraversals(expected);
        }
    }

    @Test
    public void reconstructsMaximumDepthLeftSkewedTree() {
        int size = 3000;
        int[] preorder = new int[size];
        int[] inorder = new int[size];
        for (int i = 0; i < size; i++) {
            preorder[i] = i - 1500;
            inorder[size - i - 1] = preorder[i];
        }
        assertTree(preorder, inorder);
    }

    @Test
    public void reconstructsMaximumDepthRightSkewedTree() {
        int size = 3000;
        int[] preorder = new int[size];
        int[] inorder = new int[size];
        for (int i = 0; i < size; i++) {
            preorder[i] = i - 1500;
            inorder[i] = preorder[i];
        }
        assertTree(preorder, inorder);
    }

    @Test
    public void reconstructsMaximumSizeRandomTreeWithinLeetCodeValueBounds() {
        Random random = new Random(105_3000L);
        TreeNode expected = randomTree(random, 3000);
        int[] preorder = preorder(expected);
        int[] inorder = inorder(expected);
        for (int value : preorder) {
            assertTrue(value >= -3000 && value <= 3000);
        }
        TreeNode actual = test.buildTree(preorder.clone(), inorder.clone());
        assertArrayEquals(preorder, preorder(actual));
        assertArrayEquals(inorder, inorder(actual));
    }

    @Test
    public void traversalHelpersConfirmExactStructureForMixedTree() {
        TreeNode expected = new TreeNode(5);
        expected.left = new TreeNode(1);
        expected.left.right = new TreeNode(4);
        expected.right = new TreeNode(9);
        expected.right.left = new TreeNode(6);
        expected.right.right = new TreeNode(12);
        expected.right.right.left = new TreeNode(10);
        assertSameTraversals(expected);
        TreeNode actual = test.buildTree(preorder(expected), inorder(expected));
        assertEquals(7, countNodes(actual));
    }

    private TreeNode assertTree(int[] expectedPreorder, int[] expectedInorder) {
        TreeNode actual = test.buildTree(expectedPreorder.clone(), expectedInorder.clone());
        assertArrayEquals(expectedPreorder, preorder(actual));
        assertArrayEquals(expectedInorder, inorder(actual));
        assertEquals(expectedPreorder.length, countNodes(actual));
        return actual;
    }

    private void assertSameTraversals(TreeNode expected) {
        assertTree(preorder(expected), inorder(expected));
    }

    /** Iterative traversal keeps the 3,000-node skew tests independent of Java call-stack depth. */
    private int[] preorder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) {
            return new int[0];
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            values.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    private int[] inorder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            values.add(current.val);
            current = current.right;
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    private int countNodes(TreeNode root) {
        return preorder(root).length;
    }

    private void reverse(int[] values) {
        for (int left = 0, right = values.length - 1; left < right; left++, right--) {
            int value = values[left];
            values[left] = values[right];
            values[right] = value;
        }
    }

    private TreeNode randomTree(Random random, int size) {
        int[] labels = new int[size];
        for (int i = 0; i < size; i++) {
            labels[i] = i - size / 2;
        }
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int value = labels[i];
            labels[i] = labels[j];
            labels[j] = value;
        }

        TreeNode root = null;
        List<TreeNode> nodes = new ArrayList<>();
        for (int label : labels) {
            TreeNode node = new TreeNode(label);
            if (root == null) {
                root = node;
            } else {
                TreeNode parent = nodes.get(random.nextInt(nodes.size()));
                while (parent.left != null && parent.right != null) {
                    parent = nodes.get(random.nextInt(nodes.size()));
                }
                if (parent.left == null && (parent.right != null || random.nextBoolean())) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
            nodes.add(node);
        }
        return root;
    }

    private List<Shape> allShapes(int size) {
        if (size == 0) {
            // A null entry is the empty subtree; a Shape instance is an actual node.
            return Collections.singletonList(null);
        }
        List<Shape> result = new ArrayList<>();
        for (int leftSize = 0; leftSize < size; leftSize++) {
            int rightSize = size - leftSize - 1;
            for (Shape left : allShapes(leftSize)) {
                for (Shape right : allShapes(rightSize)) {
                    result.add(new Shape(left, right));
                }
            }
        }
        return result;
    }

    private TreeNode labelByInorder(Shape shape, int[] labels) {
        int[] next = {0};
        return labelByInorder(shape, labels, next);
    }

    private TreeNode labelByInorder(Shape shape, int[] labels, int[] next) {
        if (shape == null) {
            return null;
        }
        TreeNode node = new TreeNode(0);
        node.left = labelByInorder(shape.left, labels, next);
        node.val = labels[next[0]++];
        node.right = labelByInorder(shape.right, labels, next);
        return node;
    }

    private static final class Shape {
        private final Shape left;
        private final Shape right;

        private Shape(Shape left, Shape right) {
            this.left = left;
            this.right = right;
        }
    }
}
