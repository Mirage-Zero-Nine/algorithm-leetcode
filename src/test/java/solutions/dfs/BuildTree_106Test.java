package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Tests for reconstruction from inorder and postorder traversals. */
public class BuildTree_106Test {

    private final BuildTree_106 test = new BuildTree_106();

    @Test
    public void officialExample() {
        assertReconstructs(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }

    @Test
    public void officialSingletonNegativeValue() {
        assertReconstructs(new int[]{-1}, new int[]{-1});
    }

    @Test
    public void documentedEmptyPostorderReturnsNull() {
        assertNull(test.buildTree(new int[0], new int[0]));
    }

    @Test
    public void twoNodesWithOnlyLeftChild() {
        TreeNode root = test.buildTree(new int[]{2, 1}, new int[]{2, 1});
        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertNull(root.right);
    }

    @Test
    public void twoNodesWithOnlyRightChild() {
        TreeNode root = test.buildTree(new int[]{1, 2}, new int[]{2, 1});
        assertEquals(1, root.val);
        assertNull(root.left);
        assertEquals(2, root.right.val);
    }

    @Test
    public void threeNodesBalanced() {
        TreeNode root = test.buildTree(new int[]{1, 2, 3}, new int[]{1, 3, 2});
        assertEquals(2, root.val);
        assertEquals(1, root.left.val);
        assertEquals(3, root.right.val);
    }

    @Test
    public void threeNodesRightHeavy() {
        assertReconstructs(new int[]{1, 2, 3}, new int[]{3, 2, 1});
    }

    @Test
    public void threeNodesLeftHeavy() {
        assertReconstructs(new int[]{3, 2, 1}, new int[]{3, 2, 1});
    }

    @Test
    public void completeSevenNodeTreeHasEveryChildInRightPlace() {
        TreeNode root = test.buildTree(
                new int[]{4, 2, 5, 1, 6, 3, 7},
                new int[]{4, 5, 2, 6, 7, 3, 1});
        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertEquals(3, root.right.val);
        assertEquals(4, root.left.left.val);
        assertEquals(5, root.left.right.val);
        assertEquals(6, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    @Test
    public void sparseTreeWithAlternatingMissingChildren() {
        // 10 -> left 5 -> right 7 -> left 6, and 10 -> right 20 -> left 15.
        assertReconstructs(new int[]{5, 6, 7, 10, 15, 20},
                new int[]{6, 7, 5, 15, 20, 10});
    }

    @Test
    public void rootHasOnlyLeftSubtreeWithInternalRightBranches() {
        assertReconstructs(new int[]{1, 3, 2, 5, 4}, new int[]{1, 3, 5, 4, 2});
    }

    @Test
    public void rootHasOnlyRightSubtreeWithInternalLeftBranches() {
        assertReconstructs(new int[]{2, 1, 4, 3, 5}, new int[]{2, 3, 5, 4, 1});
    }

    @Test
    public void leftSkewedTree() {
        assertReconstructs(new int[]{6, 5, 4, 3, 2, 1}, new int[]{6, 5, 4, 3, 2, 1});
    }

    @Test
    public void rightSkewedTree() {
        assertReconstructs(new int[]{1, 2, 3, 4, 5, 6}, new int[]{6, 5, 4, 3, 2, 1});
    }

    @Test
    public void zigzagSkewedTree() {
        // 1 -> right 2 -> left 3 -> right 4 -> left 5.
        assertReconstructs(new int[]{1, 3, 5, 4, 2}, new int[]{1, 5, 3, 4, 2});
    }

    @Test
    public void negativeAndZeroValues() {
        assertReconstructs(new int[]{-8, -3, -5, 0, 4, 2},
                new int[]{-8, -5, -3, 4, 2, 0});
    }

    @Test
    public void fullJavaIntegerBoundariesAreDistinctValues() {
        assertReconstructs(new int[]{Integer.MIN_VALUE, -1, Integer.MAX_VALUE, 0},
                new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0, -1});
    }

    @Test
    public void valuesNeedNotBeSortedOrFormABst() {
        assertReconstructs(new int[]{42, -7, 100, 3, 0, 88},
                new int[]{42, 100, -7, 88, 0, 3});
    }

    @Test
    public void rootMayBeAtEitherInorderBoundary() {
        assertReconstructs(new int[]{-2, -1, 0, 1}, new int[]{-2, -1, 0, 1});
        assertReconstructs(new int[]{-2, -1, 0, 1}, new int[]{1, 0, -1, -2});
    }

    @Test
    public void inputArraysRemainUnchanged() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        int[] inorderCopy = inorder.clone();
        int[] postorderCopy = postorder.clone();

        assertReconstructs(inorder, postorder);
        assertArrayEquals(inorderCopy, inorder);
        assertArrayEquals(postorderCopy, postorder);
    }

    @Test
    public void repeatedCallsOnOneSolutionHaveNoStateLeak() {
        TreeNode first = test.buildTree(new int[]{2, 1, 3}, new int[]{2, 3, 1});
        TreeNode second = test.buildTree(new int[]{8, 4, 12}, new int[]{8, 12, 4});

        assertEquals(1, first.val);
        assertEquals(4, second.val);
        assertEquals(2, first.left.val);
        assertEquals(8, second.left.val);
    }

    @Test
    public void eachInvocationReturnsIndependentNodes() {
        int[] inorder = {1, 2, 3};
        int[] postorder = {1, 3, 2};
        TreeNode first = test.buildTree(inorder, postorder);
        first.left.val = 99;
        TreeNode second = test.buildTree(inorder, postorder);

        assertEquals(1, second.left.val);
        assertNotSame(first, second);
        assertNotSame(first.left, second.left);
    }

    @Test
    public void exhaustiveSmallGeneratedTreesHaveExactTraversals() {
        for (int seed = 0; seed < 40; seed++) {
            TreeNode source = generatedTree(1 + seed % 10, seed * 31L + 7);
            int[] inorder = traversal(source, Traversal.INORDER);
            int[] postorder = traversal(source, Traversal.POSTORDER);
            assertReconstructs(inorder, postorder);
        }
    }

    @Test
    public void deterministicGeneratedTreesUseIndependentTraversalOracle() {
        Random random = new Random(106_2026L);
        for (int trial = 0; trial < 80; trial++) {
            TreeNode source = generatedTree(1 + random.nextInt(40), random.nextLong());
            int[] inorder = traversal(source, Traversal.INORDER);
            int[] postorder = traversal(source, Traversal.POSTORDER);
            TreeNode result = test.buildTree(inorder.clone(), postorder.clone());
            assertArrayEquals(inorder, traversal(result, Traversal.INORDER));
            assertArrayEquals(postorder, traversal(result, Traversal.POSTORDER));
            assertEquals(inorder.length, countNodes(result));
        }
    }

    @Test
    public void maximumLegalBalancedTree() {
        TreeNode source = balancedTree(3_000);
        assertReconstructs(traversal(source, Traversal.INORDER), traversal(source, Traversal.POSTORDER));
    }

    @Test
    public void maximumLegalRightSkewedTree() {
        int[] inorder = new int[3_000];
        int[] postorder = new int[3_000];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = i - 1_500;
        }
        for (int i = 0; i < postorder.length; i++) {
            postorder[i] = inorder[inorder.length - 1 - i];
        }

        TreeNode result = test.buildTree(inorder, postorder);
        assertArrayEquals(inorder, traversal(result, Traversal.INORDER));
        assertArrayEquals(postorder, traversal(result, Traversal.POSTORDER));
        assertEquals(3_000, countNodes(result));
    }

    @Test
    public void maximumLegalLeftSkewedTree() {
        int[] inorder = new int[3_000];
        int[] postorder = new int[3_000];
        for (int i = 0; i < inorder.length; i++) {
            inorder[i] = 1_500 - i;
            postorder[i] = inorder[i];
        }

        TreeNode result = test.buildTree(inorder, postorder);
        assertArrayEquals(inorder, traversal(result, Traversal.INORDER));
        assertArrayEquals(postorder, traversal(result, Traversal.POSTORDER));
        assertEquals(3_000, countNodes(result));
    }

    @Test
    public void smallAllShapesWithUniqueLabelsRoundTrip() {
        for (int size = 1; size <= 7; size++) {
            TreeNode source = generatedTree(size, size * 1_001L);
            int[] inorder = traversal(source, Traversal.INORDER);
            int[] postorder = traversal(source, Traversal.POSTORDER);
            assertReconstructs(inorder, postorder);
        }
    }

    private void assertReconstructs(int[] inorder, int[] postorder) {
        int[] expectedInorder = inorder.clone();
        int[] expectedPostorder = postorder.clone();
        TreeNode result = test.buildTree(inorder, postorder);

        assertArrayEquals(expectedInorder, traversal(result, Traversal.INORDER));
        assertArrayEquals(expectedPostorder, traversal(result, Traversal.POSTORDER));
        assertEquals(inorder.length, countNodes(result));
        assertArrayEquals(expectedInorder, inorder);
        assertArrayEquals(expectedPostorder, postorder);
    }

    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int[] traversal(TreeNode root, Traversal order) {
        List<Integer> values = new ArrayList<>();
        if (order == Traversal.INORDER) {
            collectInorder(root, values);
        } else {
            collectPostorder(root, values);
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void collectInorder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        collectInorder(node.left, values);
        values.add(node.val);
        collectInorder(node.right, values);
    }

    private static void collectPostorder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        collectPostorder(node.left, values);
        collectPostorder(node.right, values);
        values.add(node.val);
    }

    private static TreeNode generatedTree(int size, long seed) {
        TreeNode root = null;
        Random random = new Random(seed);
        for (int i = 0; i < size; i++) {
            TreeNode node = new TreeNode(i * 17 - 1500);
            if (root == null) {
                root = node;
                continue;
            }
            TreeNode current = root;
            while (true) {
                boolean left = random.nextBoolean();
                if (left && current.left != null) {
                    current = current.left;
                } else if (!left && current.right != null) {
                    current = current.right;
                } else if (left) {
                    current.left = node;
                    break;
                } else {
                    current.right = node;
                    break;
                }
            }
        }
        return root;
    }

    private static TreeNode balancedTree(int size) {
        return balancedTree(0, size - 1);
    }

    private static TreeNode balancedTree(int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = low + (high - low) / 2;
        TreeNode root = new TreeNode(middle - 1_500);
        root.left = balancedTree(low, middle - 1);
        root.right = balancedTree(middle + 1, high);
        return root;
    }

    private enum Traversal {
        INORDER,
        POSTORDER
    }
}
