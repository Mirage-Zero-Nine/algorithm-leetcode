package solutions.dfs;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Behavioral and boundary tests for {@link BalanceBST_1382}. */
public class BalanceBST_1382Test {

    private final BalanceBST_1382 solution = new BalanceBST_1382();

    @Test
    void nullInputReturnsNull() {
        assertNull(solution.balanceBST(null));
    }

    @Test
    void singletonPreservesValueAndCreatesIndependentNode() {
        TreeNode input = new TreeNode(1);
        TreeNode result = solution.balanceBST(input);
        assertEquals(List.of(1), inorder(result));
        assertNotSame(input, result);
        assertTrue(isBalanced(result));
    }

    @Test
    void officialRightSkewedExample() {
        TreeNode result = solution.balanceBST(rightSkewed(1, 2, 3, 4));
        assertValidResult(result, List.of(1, 2, 3, 4));
    }

    @Test
    void officialAlreadyBalancedExample() {
        TreeNode result = solution.balanceBST(node(2, new TreeNode(1), new TreeNode(3)));
        assertValidResult(result, List.of(1, 2, 3));
    }

    @Test
    void twoNodeRightSkewIsBalanced() {
        TreeNode result = solution.balanceBST(rightSkewed(1, 2));
        assertValidResult(result, List.of(1, 2));
    }

    @Test
    void twoNodeLeftSkewIsBalanced() {
        TreeNode result = solution.balanceBST(leftSkewed(2, 1));
        assertValidResult(result, List.of(1, 2));
    }

    @Test
    void threeNodeRightSkewUsesAllValues() {
        TreeNode result = solution.balanceBST(rightSkewed(1, 2, 3));
        assertValidResult(result, List.of(1, 2, 3));
        assertEquals(2, result.val);
    }

    @Test
    void threeNodeLeftSkewUsesAllValues() {
        TreeNode result = solution.balanceBST(leftSkewed(3, 2, 1));
        assertValidResult(result, List.of(1, 2, 3));
        assertEquals(2, result.val);
    }

    @Test
    void alreadyPerfectlyBalancedTreeRemainsValid() {
        TreeNode input = node(4,
                node(2, new TreeNode(1), new TreeNode(3)),
                node(6, new TreeNode(5), new TreeNode(7)));
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(1, 2, 3, 4, 5, 6, 7));
        assertEquals(4, result.val);
    }

    @Test
    void unevenSevenNodeTreeIsRebalanced() {
        TreeNode input = node(8,
                node(3, new TreeNode(1), node(6, new TreeNode(4), null)),
                node(10, null, new TreeNode(14)));
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(1, 3, 4, 6, 8, 10, 14));
    }

    @Test
    void zigzagBstIsRebalanced() {
        TreeNode input = node(8,
                node(4, node(2, null, new TreeNode(3)), new TreeNode(6)),
                node(12, new TreeNode(10), node(14, null, new TreeNode(15))));
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(2, 3, 4, 6, 8, 10, 12, 14, 15));
    }

    @Test
    void negativeAndPositiveValuesAreSupportedByClass() {
        TreeNode input = node(0,
                node(-10, new TreeNode(-20), new TreeNode(-5)),
                node(10, new TreeNode(5), new TreeNode(20)));
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(-20, -10, -5, 0, 5, 10, 20));
    }

    @Test
    void integerBoundaryValuesDoNotOverflowOrderingChecks() {
        TreeNode input = node(0, new TreeNode(Integer.MIN_VALUE), new TreeNode(Integer.MAX_VALUE));
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(Integer.MIN_VALUE, 0, Integer.MAX_VALUE));
    }

    @Test
    void minimumAndMaximumOfficialValuesArePreserved() {
        TreeNode input = node(50_000,
                node(1, null, new TreeNode(25_000)),
                node(75_000, new TreeNode(60_000), new TreeNode(100_000)));
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(1, 25_000, 50_000, 60_000, 75_000, 100_000));
    }

    @Test
    void resultHasNoNodeAliasingWithInput() {
        TreeNode input = node(4,
                node(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(5));
        Set<TreeNode> inputNodes = identityNodes(input);
        TreeNode result = solution.balanceBST(input);
        for (TreeNode resultNode : identityNodes(result)) {
            assertFalse(inputNodes.contains(resultNode), "result must not reuse an input node");
        }
    }

    @Test
    void inputTreeIsNotMutated() {
        TreeNode input = node(5,
                node(2, new TreeNode(1), new TreeNode(3)),
                node(8, new TreeNode(7), new TreeNode(9)));
        List<Integer> before = preorderWithNulls(input);
        solution.balanceBST(input);
        assertEquals(before, preorderWithNulls(input));
    }

    @Test
    void resultContainsExactlyOneNodeForEachInputValue() {
        TreeNode result = solution.balanceBST(rightSkewed(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertEquals(9, identityNodes(result).size());
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), inorder(result));
    }

    @Test
    void repeatedCallsReturnFreshIndependentTrees() {
        TreeNode input = rightSkewed(1, 2, 3, 4, 5);
        TreeNode first = solution.balanceBST(input);
        TreeNode second = solution.balanceBST(input);
        first.val = 99;
        assertNotSame(first, second);
        assertEquals(List.of(1, 2, 3, 4, 5), inorder(second));
        assertEquals(List.of(1, 2, 3, 4, 5), inorder(input));
    }

    @Test
    void repeatedCallsWithDifferentRootsDoNotLeakState() {
        TreeNode first = solution.balanceBST(rightSkewed(1, 2, 3));
        TreeNode second = solution.balanceBST(leftSkewed(9, 8, 7, 6));
        assertValidResult(first, List.of(1, 2, 3));
        assertValidResult(second, List.of(6, 7, 8, 9));
    }

    @Test
    void irregularInsertionOrderStillProducesValidBst() {
        int[] insertionOrder = {10, 5, 15, 2, 7, 12, 20, 1, 3, 6, 8, 11, 13, 18, 25};
        TreeNode input = null;
        for (int value : insertionOrder) {
            input = insert(input, value);
        }
        TreeNode result = solution.balanceBST(input);
        assertValidResult(result, List.of(1, 2, 3, 5, 6, 7, 8, 10, 11, 12, 13, 15, 18, 20, 25));
    }

    @Test
    void everySmallRightSkewSizeIsBalanced() {
        for (int size = 1; size <= 63; size++) {
            TreeNode result = solution.balanceBST(rightSkewedRange(size));
            assertValidResult(result, range(1, size));
        }
    }

    @Test
    void everySmallLeftSkewSizeIsBalanced() {
        for (int size = 1; size <= 63; size++) {
            TreeNode result = solution.balanceBST(leftSkewedRange(size));
            assertValidResult(result, range(1, size));
        }
    }

    @Test
    void balancedInputAtOfficialMaximumNodeCount() {
        TreeNode result = solution.balanceBST(buildBalancedRange(1, 10_000));
        assertValidResult(result, range(1, 10_000));
    }

    @Test
    void rightSkewedInputAtOfficialMaximumNodeCount() {
        TreeNode result = solution.balanceBST(rightSkewedRange(10_000));
        assertValidResult(result, range(1, 10_000));
    }

    @Test
    void outputHeightIsNearMinimumForNonPowerOfTwoSize() {
        TreeNode result = solution.balanceBST(rightSkewedRange(127));
        assertEquals(7, height(result));
        assertValidResult(result, range(1, 127));
    }

    @Test
    void resultRootMayBeAnyMedianButMustKeepAllPartitionsValid() {
        TreeNode result = solution.balanceBST(rightSkewedRange(10));
        assertValidResult(result, range(1, 10));
        assertTrue(result.val >= 1 && result.val <= 10);
    }

    private void assertValidResult(TreeNode result, List<Integer> expectedInorder) {
        assertNotNull(result);
        assertEquals(expectedInorder, inorder(result));
        assertTrue(isStrictBst(result));
        assertTrue(isBalanced(result));
        assertEquals(expectedInorder.size(), identityNodes(result).size());
    }

    private TreeNode node(int value, TreeNode left, TreeNode right) {
        TreeNode root = new TreeNode(value);
        root.left = left;
        root.right = right;
        return root;
    }

    private TreeNode rightSkewed(int... values) {
        if (values.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        TreeNode current = root;
        for (int i = 1; i < values.length; i++) {
            current.right = new TreeNode(values[i]);
            current = current.right;
        }
        return root;
    }

    private TreeNode leftSkewed(int... values) {
        if (values.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        TreeNode current = root;
        for (int i = 1; i < values.length; i++) {
            current.left = new TreeNode(values[i]);
            current = current.left;
        }
        return root;
    }

    private TreeNode rightSkewedRange(int size) {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= size; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        return root;
    }

    private TreeNode leftSkewedRange(int size) {
        TreeNode root = new TreeNode(size);
        TreeNode current = root;
        for (int value = size - 1; value >= 1; value--) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        return root;
    }

    private TreeNode buildBalancedRange(int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = low + (high - low) / 2;
        return node(middle, buildBalancedRange(low, middle - 1), buildBalancedRange(middle + 1, high));
    }

    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    private List<Integer> range(int low, int high) {
        List<Integer> values = new ArrayList<>(high - low + 1);
        for (int value = low; value <= high; value++) {
            values.add(value);
        }
        return values;
    }

    private List<Integer> inorder(TreeNode root) {
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
        return values;
    }

    private boolean isStrictBst(TreeNode root) {
        List<Integer> values = inorder(root);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i - 1) >= values.get(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBalanced(TreeNode root) {
        return balancedHeight(root) >= 0;
    }

    private int balancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);
        if (leftHeight < 0 || rightHeight < 0 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private Set<TreeNode> identityNodes(TreeNode root) {
        Set<TreeNode> nodes = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (!nodes.add(current)) {
                continue;
            }
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }
        return nodes;
    }

    private List<Integer> preorderWithNulls(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        List<TreeNode> stack = new ArrayList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.remove(stack.size() - 1);
            if (current == null) {
                values.add(null);
                continue;
            }
            values.add(current.val);
            stack.add(current.right);
            stack.add(current.left);
        }
        return values;
    }
}
