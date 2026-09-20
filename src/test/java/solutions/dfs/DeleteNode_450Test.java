package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for both implementations of LeetCode 450, Delete Node in a BST.
 *
 * <p>Each case gives each approach an independent tree. Expectations come from the
 * sorted values in the input tree, not from agreement between the two approaches.
 * This is important because LeetCode accepts either the inorder successor or
 * predecessor arrangement when a node has two children.</p>
 */
public class DeleteNode_450Test {

    private final DeleteNode_450 solution = new DeleteNode_450();

    @Test
    void nullTreeIsUnchangedForBothApproaches() {
        assertBoth(null, 0);
    }

    @Test
    void deletingTheOnlyNodeReturnsNull() {
        assertBoth(bst(5), 5);
    }

    @Test
    void missingKeyInSingletonIsNoOpAndPreservesRootIdentity() {
        assertBoth(bst(5), 4);
    }

    @Test
    void deletingLeftLeaf() {
        assertBoth(bst(5, 3, 7), 3);
    }

    @Test
    void deletingRightLeaf() {
        assertBoth(bst(5, 3, 7), 7);
    }

    @Test
    void deletingNodeWithOnlyLeftChild() {
        assertBoth(bst(5, 3, 2, 7), 3);
    }

    @Test
    void deletingNodeWithOnlyRightChild() {
        assertBoth(bst(5, 3, 7, 8), 7);
    }

    @Test
    void deletingRootWithOnlyLeftChildReturnsThatChild() {
        assertBoth(bst(5, 3, 2), 5);
    }

    @Test
    void deletingRootWithOnlyRightChildReturnsThatChild() {
        assertBoth(bst(5, 7, 8), 5);
    }

    @Test
    void deletingRootWithImmediateSuccessor() {
        assertBoth(bst(5, 3, 7, 2, 4, 8), 5);
    }

    @Test
    void deletingRootWithDeepSuccessor() {
        assertBoth(bst(5, 2, 8, 1, 3, 6, 10, 7, 12), 5);
    }

    @Test
    void deletingInternalTwoChildNodeWithDeepSuccessor() {
        assertBoth(bst(10, 5, 15, 2, 7, 12, 20, 6, 8, 11, 13), 5);
    }

    @Test
    void successorMayHaveItsOwnRightChild() {
        // The successor of 5 is 6, and 6 has a right child that must remain reachable.
        assertBoth(bst(5, 2, 9, 1, 3, 6, 12, 7), 5);
    }

    @Test
    void missingKeyBelowEveryValueIsNoOp() {
        assertBoth(bst(5, 3, 7, 2, 4, 6, 8), Integer.MIN_VALUE);
    }

    @Test
    void missingKeyAboveEveryValueIsNoOp() {
        assertBoth(bst(5, 3, 7, 2, 4, 6, 8), Integer.MAX_VALUE);
    }

    @Test
    void missingKeyBetweenExistingValuesIsNoOp() {
        assertBoth(bst(10, 0, 20, -10, 5, 15, 30), 13);
    }

    @Test
    void negativeZeroAndPositiveValuesRemainOrdered() {
        assertBoth(bst(0, -10, 10, -20, -5, 5, 20), -10);
    }

    @Test
    void documentedValueBoundsAreHandled() {
        assertBoth(bst(0, -100_000, 100_000, -99_999, 99_999), -100_000);
        assertBoth(bst(0, -100_000, 100_000, -99_999, 99_999), 100_000);
    }

    @Test
    void deletingBalancedTreeRootPreservesEveryOtherValue() {
        assertBoth(buildBalanced(1, 15), 8);
    }

    @Test
    void deletingBalancedTreeInternalAndBoundaryLeaves() {
        assertBoth(buildBalanced(-31, 31), -31);
        assertBoth(buildBalanced(-31, 31), 31);
        assertBoth(buildBalanced(-31, 31), 0);
    }

    @Test
    void deletingFromRightSkewedTree() {
        assertBoth(rightSkewed(700), 350);
    }

    @Test
    void deletingFromLeftSkewedTree() {
        assertBoth(leftSkewed(700), -350);
    }

    @Test
    void maximumDocumentedNodeCountIsHandled() {
        assertBoth(buildBalanced(-5_000, 4_999), 0);
    }

    @Test
    void repeatedDeletesUseCurrentTreeAndDoNotLeakState() {
        int[] values = {50, 30, 70, 20, 40, 60, 80, 35, 65, 75};
        int[] keys = {20, 70, 50, 999, 35, 30};
        assertBothSequence(values, keys);
    }

    @Test
    void repeatedMissingDeletesPreserveTheSameRootAndNodes() {
        TreeNode root = bst(8, 4, 12, 2, 6, 10, 14);
        assertBothSequence(root, new int[] {-100, 0, 7, 13, 100});
    }

    @Test
    void independentCallsOnOneSolutionInstanceUseFreshTrees() {
        assertBoth(bst(4, 2, 6, 1, 3, 5, 7), 1);
        assertBoth(bst(-4, -2, 0, -3, -1, 1, 3), -4);
        assertBoth(bst(100, 50, 150), 150);
    }

    @Test
    void deterministicRandomTreeAndIndependentDeleteOracle() {
        Random random = new Random(450_2026L);
        Set<Integer> values = new HashSet<>();
        while (values.size() < 250) {
            values.add(random.nextInt(20_001) - 10_000);
        }
        int[] insertionOrder = values.stream().mapToInt(Integer::intValue).toArray();
        int[] keys = new int[100];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = random.nextInt(24_001) - 12_000;
        }
        assertBothSequence(insertionOrder, keys);
    }

    @Test
    void deletingEveryValueInAdversarialOrderEventuallyReturnsNull() {
        int[] values = {0, -10, 10, -20, -5, 5, 20, -30, -15, -1, 1, 15, 30};
        int[] deleteOrder = {0, 30, -30, 10, -10, 1, -1, 5, -5, 15, -15, 20, -20};
        assertBothSequence(values, deleteOrder);
    }

    @Test
    void inputTreeValuesAndTopologyAreNotChangedOutsideTheReturnedMutation() {
        TreeNode original = bst(10, 5, 15, 2, 7, 12, 20);
        List<Integer> before = inorder(original);
        assertBoth(original, 7);
        assertEquals(before, inorder(original));
        assertTrue(isValidBst(original));
    }

    private void assertBoth(TreeNode template, int key) {
        assertDeletion("recursive", template, key,
                (root, value) -> solution.deleteNode(root, value));
        assertDeletion("iterative", template, key,
                (root, value) -> solution.iterative(root, value));
    }

    private void assertDeletion(String approach, TreeNode template, int key,
                                BiFunction<TreeNode, Integer, TreeNode> delete) {
        if (template == null) {
            assertNull(delete.apply(null, key), approach);
            return;
        }

        TreeNode input = cloneTree(template);
        Map<Integer, TreeNode> beforeNodes = nodesByValue(input);
        List<Integer> expected = inorder(input);
        expected.remove(Integer.valueOf(key));

        TreeNode result = delete.apply(input, key);
        assertTreeMatches(approach, result, expected, beforeNodes, key);
        if (!beforeNodes.containsKey(key)) {
            assertSame(input, result, approach + " must no-op for a missing key");
        }
        assertTrue(isValidBst(template), approach + " must not mutate the independent template");
    }

    private void assertBothSequence(int[] insertionOrder, int[] keys) {
        assertBothSequence(bstFromInsertion(insertionOrder), keys);
    }

    private void assertBothSequence(TreeNode template, int[] keys) {
        assertSequence(template, keys, (root, key) -> solution.deleteNode(root, key));
        assertSequence(template, keys, (root, key) -> solution.iterative(root, key));
    }

    private void assertSequence(TreeNode template, int[] keys,
                                BiFunction<TreeNode, Integer, TreeNode> delete) {
        TreeNode root = cloneTree(template);
        Set<Integer> expected = new HashSet<>(inorder(root));
        for (int key : keys) {
            Map<Integer, TreeNode> beforeNodes = nodesByValue(root);
            boolean present = expected.remove(key);
            TreeNode previousRoot = root;
            root = delete.apply(root, key);
            assertTreeMatches("sequence", root, sorted(expected), beforeNodes, key);
            if (!present) {
                assertSame(previousRoot, root, "a missing key must preserve the current root");
            }
        }
    }

    private void assertTreeMatches(String approach, TreeNode root, List<Integer> expected,
                                   Map<Integer, TreeNode> beforeNodes, int deletedKey) {
        assertEquals(expected, inorder(root), approach + " returned wrong inorder values");
        assertTrue(isValidBst(root), approach + " returned an invalid BST");
        Map<Integer, TreeNode> afterNodes = nodesByValue(root);
        assertEquals(expected.size(), afterNodes.size(), approach + " returned wrong node count");
        assertFalse(afterNodes.containsKey(deletedKey), approach + " retained deleted key");
        for (int value : expected) {
            assertSame(beforeNodes.get(value), afterNodes.get(value),
                    approach + " must retain the node object for value " + value);
        }
    }

    private static TreeNode bst(int... values) {
        return bstFromInsertion(values);
    }

    private static TreeNode bstFromInsertion(int[] values) {
        TreeNode root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        TreeNode current = root;
        while (true) {
            if (value < current.val) {
                if (current.left == null) {
                    current.left = new TreeNode(value);
                    return root;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TreeNode(value);
                    return root;
                }
                current = current.right;
            }
        }
    }

    private static TreeNode buildBalanced(int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = low + (high - low) / 2;
        TreeNode root = new TreeNode(middle);
        root.left = buildBalanced(low, middle - 1);
        root.right = buildBalanced(middle + 1, high);
        return root;
    }

    private static TreeNode rightSkewed(int size) {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= size; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        return root;
    }

    private static TreeNode leftSkewed(int size) {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int value = -1; value >= -size; value--) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        return root;
    }

    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }

    private static List<Integer> inorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        return result;
    }

    private static Map<Integer, TreeNode> nodesByValue(TreeNode root) {
        Map<Integer, TreeNode> nodes = new HashMap<>();
        if (root == null) {
            return nodes;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Set<TreeNode> seen = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            assertTrue(seen.add(current), "tree contains a cycle or shared node");
            assertNull(nodes.put(current.val, current), "tree contains duplicate values");
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return nodes;
    }

    private static boolean isValidBst(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<BoundedNode> stack = new ArrayDeque<>();
        stack.push(new BoundedNode(root, Long.MIN_VALUE, Long.MAX_VALUE));
        while (!stack.isEmpty()) {
            BoundedNode bounded = stack.pop();
            TreeNode node = bounded.node();
            if (node.val <= bounded.lower() || node.val >= bounded.upper()) {
                return false;
            }
            if (node.right != null) {
                stack.push(new BoundedNode(node.right, node.val, bounded.upper()));
            }
            if (node.left != null) {
                stack.push(new BoundedNode(node.left, bounded.lower(), node.val));
            }
        }
        return true;
    }

    private static List<Integer> sorted(Set<Integer> values) {
        int[] array = values.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(array);
        List<Integer> result = new ArrayList<>(array.length);
        for (int value : array) {
            result.add(value);
        }
        return result;
    }

    private record BoundedNode(TreeNode node, long lower, long upper) {
    }
}
