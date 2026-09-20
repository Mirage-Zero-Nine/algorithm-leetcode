package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/** Tests the two implementations of LeetCode 776, including the in-place split contract. */
public class SplitBST_776Test {

    private final SplitBST_776 solution = new SplitBST_776();

    @FunctionalInterface
    private interface Splitter {
        TreeNode[] split(TreeNode root, int target);
    }

    @Test
    void emptyTreeReturnsTwoNullRoots() {
        assertBoth((TreeNode) null, 0);
    }

    @Test
    void singletonTargetBelowNode() {
        assertBoth(5, 4);
    }

    @Test
    void singletonTargetEqualsNode() {
        assertBoth(5, 5);
    }

    @Test
    void singletonTargetAboveNode() {
        assertBoth(5, 6);
    }

    @Test
    void twoNodesTargetBelowAll() {
        assertBoth(new int[]{2, 1}, 0);
    }

    @Test
    void twoNodesTargetBetweenValues() {
        assertBoth(new int[]{4, 2}, 3);
    }

    @Test
    void twoNodesTargetEqualsSmallest() {
        assertBoth(new int[]{2, 1}, 1);
    }

    @Test
    void twoNodesTargetEqualsLargest() {
        assertBoth(new int[]{1, 2}, 2);
    }

    @Test
    void balancedTreeTargetEqualsRoot() {
        assertBoth(new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15}, 8);
    }

    @Test
    void balancedTreeTargetEqualsInternalNode() {
        assertBoth(new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15}, 6);
    }

    @Test
    void balancedTreeTargetFallsBetweenNodes() {
        assertBoth(new int[]{50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 44, 56, 68, 81, 94}, 40);
    }

    @Test
    void balancedTreeTargetBelowAll() {
        assertBoth(new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15}, 0);
    }

    @Test
    void balancedTreeTargetAboveAll() {
        assertBoth(new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15}, 1000);
    }

    @Test
    void leftSkewedTreeSplitsAtAnInteriorValue() {
        assertBoth(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 4);
    }

    @Test
    void rightSkewedTreeSplitsAtAnInteriorValue() {
        assertBoth(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 4);
    }

    @Test
    void zigzagTreePreservesTheUnsplitSubtrees() {
        assertBoth(new int[]{8, 4, 6, 2, 3, 12, 10, 11, 14, 13}, 10);
    }

    @Test
    void valuesAtTheProblemBoundsArePartitionedCorrectly() {
        assertBoth(new int[]{0, 500, 1000, 250, 750, 125, 375, 625, 875}, 500);
    }

    @Test
    void targetAtMinimumAllowedValue() {
        assertBoth(new int[]{500, 250, 750, 0, 1000}, 0);
    }

    @Test
    void targetAtMaximumAllowedValue() {
        assertBoth(new int[]{500, 250, 750, 0, 1000}, 1000);
    }

    @Test
    void fiftyNodeBalancedTreeIsFullyAccountedFor() {
        assertBoth(balancedInsertionOrder(1, 50), 25);
    }

    @Test
    void fiftyNodeRightSkewedTreeIsFullyAccountedFor() {
        int[] insertionOrder = new int[50];
        for (int i = 0; i < insertionOrder.length; i++) {
            insertionOrder[i] = i + 1;
        }
        assertBoth(insertionOrder, 37);
    }

    @Test
    void allNodesRemainInLowerPartitionAtMaximumTarget() {
        assertBoth(new int[]{4, 2, 6, 1, 3, 5, 7}, Integer.MAX_VALUE);
    }

    @Test
    void allNodesRemainInUpperPartitionAtMinimumTarget() {
        assertBoth(new int[]{4, 2, 6, 1, 3, 5, 7}, Integer.MIN_VALUE);
    }

    @Test
    void negativeAndExtremeIntegerValuesRemainOrdered() {
        assertBoth(new int[]{0, Integer.MIN_VALUE, Integer.MAX_VALUE, -10, 10}, -10);
    }

    @Test
    void oneSolutionInstanceCanBeReusedWithFreshTrees() {
        assertSplit(buildBst(new int[]{4, 2, 6, 1, 3, 5, 7}), 3, solution::splitBST);
        assertSplit(buildBst(new int[]{10, 5, 15, 2, 7, 12, 20}), 13, solution::splitBST);
        assertSplit(buildBst(new int[]{4, 2, 6, 1, 3, 5, 7}), 3, solution::iterative);
        assertSplit(buildBst(new int[]{10, 5, 15, 2, 7, 12, 20}), 13, solution::iterative);
    }

    private void assertBoth(int[] insertionOrder, int target) {
        assertSplit(buildBst(insertionOrder), target, solution::splitBST);
        assertSplit(buildBst(insertionOrder), target, solution::iterative);
    }

    private void assertBoth(int value, int target) {
        assertBoth(new int[]{value}, target);
    }

    private void assertBoth(TreeNode root, int target) {
        assertSplit(root, target, solution::splitBST);
        // The null case has no mutable input, so the second implementation uses a fresh null root.
        assertSplit(null, target, solution::iterative);
    }

    /**
     * Checks the required value partition independently of either implementation. It also verifies
     * that splitting reuses exactly the input nodes and retains every original edge that remains
     * inside one partition.
     */
    private void assertSplit(TreeNode root, int target, Splitter splitter) {
        List<Integer> originalValues = inorder(root);
        List<TreeNode> originalNodes = new ArrayList<>();
        Map<TreeNode, TreeNode[]> originalEdges = new IdentityHashMap<>();
        collectOriginal(root, originalNodes, originalEdges);

        List<Integer> expectedLower = new ArrayList<>();
        List<Integer> expectedUpper = new ArrayList<>();
        for (int value : originalValues) {
            if (value <= target) {
                expectedLower.add(value);
            } else {
                expectedUpper.add(value);
            }
        }

        TreeNode[] result = splitter.split(root, target);
        assertNotNull(result);
        assertEquals(2, result.length);

        IdentityHashMap<TreeNode, Integer> sides = new IdentityHashMap<>();
        collectPartition(result[0], 0, sides);
        collectPartition(result[1], 1, sides);
        assertEquals(originalNodes.size(), sides.size(), "a node was lost or duplicated");
        for (TreeNode original : originalNodes) {
            assertTrue(sides.containsKey(original), "the original node was not returned");
        }

        assertEquals(expectedLower, inorder(result[0]));
        assertEquals(expectedUpper, inorder(result[1]));
        assertTrue(isStrictBst(result[0], Long.MIN_VALUE, Long.MAX_VALUE));
        assertTrue(isStrictBst(result[1], Long.MIN_VALUE, Long.MAX_VALUE));

        for (Map.Entry<TreeNode, TreeNode[]> entry : originalEdges.entrySet()) {
            TreeNode parent = entry.getKey();
            Integer parentSide = sides.get(parent);
            for (TreeNode child : entry.getValue()) {
                if (child != null && parentSide.equals(sides.get(child))) {
                    assertTrue(parent.left == child || parent.right == child,
                            "an edge inside a partition was unnecessarily changed");
                }
            }
        }
    }

    private void collectOriginal(TreeNode node, List<TreeNode> nodes,
                                 Map<TreeNode, TreeNode[]> edges) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        edges.put(node, new TreeNode[]{node.left, node.right});
        collectOriginal(node.left, nodes, edges);
        collectOriginal(node.right, nodes, edges);
    }

    private void collectPartition(TreeNode node, int side, IdentityHashMap<TreeNode, Integer> sides) {
        if (node == null) {
            return;
        }
        Integer previous = sides.put(node, side);
        if (previous != null) {
            throw new AssertionError("a result contains a cycle or duplicate node");
        }
        collectPartition(node.left, side, sides);
        collectPartition(node.right, side, sides);
    }

    private boolean isStrictBst(TreeNode node, long lowerExclusive, long upperExclusive) {
        if (node == null) {
            return true;
        }
        return node.val > lowerExclusive
                && node.val < upperExclusive
                && isStrictBst(node.left, lowerExclusive, node.val)
                && isStrictBst(node.right, node.val, upperExclusive);
    }

    private List<Integer> inorder(TreeNode node) {
        List<Integer> values = new ArrayList<>();
        inorder(node, values);
        return values;
    }

    private void inorder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    private TreeNode buildBst(int[] insertionOrder) {
        TreeNode root = null;
        for (int value : insertionOrder) {
            root = insert(root, value);
        }
        return root;
    }

    private TreeNode insert(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.val) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }

    private int[] balancedInsertionOrder(int low, int high) {
        List<Integer> values = new ArrayList<>();
        addBalancedOrder(low, high, values);
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    private void addBalancedOrder(int low, int high, List<Integer> values) {
        if (low > high) {
            return;
        }
        int middle = low + (high - low) / 2;
        values.add(middle);
        addBalancedOrder(low, middle - 1, values);
        addBalancedOrder(middle + 1, high, values);
    }
}
