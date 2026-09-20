package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BoundaryOfBinaryTree_545Test {

    private final BoundaryOfBinaryTree_545 test = new BoundaryOfBinaryTree_545();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3); root.right.left = new TreeNode(2);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.boundaryOfBinaryTree(null));
        assertEquals(List.of(1), test.boundaryOfBinaryTree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(List.of(1, 2, 4, 5, 6, 7, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testOnlyLeftSubtree() {
        // root has only left subtree: 1 -> 2 -> 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testOnlyRightSubtree() {
        // root has only right subtree: 1 -> 2 -> 3
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(1, 3, 2), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testLeftBoundaryGoesRight() {
        // Left boundary goes right when no left child: 1 -> left:2(no left child) -> 2.right=4(leaf)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 4, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testRightBoundaryGoesLeft() {
        // Right boundary goes left when no right child
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        assertEquals(List.of(1, 2, 5, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testCompleteTreeDepth3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8); root.left.left.right = new TreeNode(9);
        root.right.right.left = new TreeNode(10); root.right.right.right = new TreeNode(11);
        assertEquals(List.of(1, 2, 4, 8, 9, 5, 6, 10, 11, 7, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testRootWithTwoLeaves() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testZigzagLeftBoundary() {
        // Left boundary zigzags: 1 -> left:2 -> 2.right:4 -> 4.left:5(leaf)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(5);
        assertEquals(List.of(1, 2, 4, 5, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testGiantTree() {
        // Build a deep left-skewed tree with right leaf at bottom
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 20; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        current.right = new TreeNode(99);
        // Boundary: root(0), left boundary (1..20), leaves (99), no right boundary
        List<Integer> result = test.boundaryOfBinaryTree(root);
        assertEquals(0, result.get(0));
        assertEquals(99, result.get(result.size() - 1));
        assertEquals(22, result.size()); // root + 20 left boundary nodes + 1 leaf
    }

    @Test
    public void testSingletonWithNegativeValue() {
        assertBoundary(new TreeNode(-7), List.of(-7));
    }

    @Test
    public void testExtremeSignedValues() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.left.right = new TreeNode(-1);
        root.right = new TreeNode(Integer.MIN_VALUE + 1);
        root.right.left = new TreeNode(Integer.MAX_VALUE - 1);

        assertBoundary(root, List.of(
                Integer.MIN_VALUE, Integer.MAX_VALUE, -1, Integer.MAX_VALUE - 1,
                Integer.MIN_VALUE + 1));
    }

    @Test
    public void testLeavesRemainLeftToRightAcrossSparseLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.left.right = new TreeNode(10);
        root.right.right = new TreeNode(6);

        assertBoundary(root, List.of(1, 2, 4, 8, 9, 10, 6, 3));
    }

    @Test
    public void testLeftBoundaryChoosesLeftChildAtEveryFork() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);

        assertBoundary(root, List.of(1, 2, 4, 8, 9, 5, 3));
    }

    @Test
    public void testLeftBoundaryFallsBackToRightAtMultipleLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(8);
        root.left.right.right.left = new TreeNode(9);
        root.right = new TreeNode(3);

        assertBoundary(root, List.of(1, 2, 4, 8, 9, 3));
    }

    @Test
    public void testRightBoundaryChoosesRightChildAtEveryFork() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(11);
        root.right.right.left = new TreeNode(10);
        root.right.left = new TreeNode(6);

        assertBoundary(root, List.of(1, 2, 6, 10, 11, 7, 3));
    }

    @Test
    public void testRightBoundaryFallsBackToLeftAtMultipleLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(10);
        root.right.left.left.left = new TreeNode(11);

        assertBoundary(root, List.of(1, 2, 11, 10, 6, 3));
    }

    @Test
    public void testRightHeavyTreeHasLeavesBeforeReversedRightBoundary() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.left.left = new TreeNode(4);
        root.right.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);

        assertBoundary(root, List.of(1, 4, 5, 7, 6, 2));
    }

    @Test
    public void testLeftHeavyTreeHasLeavesBeforeReversedLeftBoundary() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(10);
        root.right = new TreeNode(3);

        assertBoundary(root, List.of(1, 2, 4, 8, 9, 10, 3));
    }

    @Test
    public void testOnlyLeftLeafDoesNotAppearAsBothBoundaryAndLeaf() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);

        assertBoundary(root, List.of(4, 2));
    }

    @Test
    public void testOnlyRightLeafDoesNotAppearAsBothBoundaryAndLeaf() {
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(6);

        assertBoundary(root, List.of(4, 6));
    }

    @Test
    public void testDuplicateValuesRepresentDistinctBoundaryNodes() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(5);

        assertBoundary(root, List.of(5, 5, 5, 5, 5, 5, 5));
    }

    @Test
    public void testAllInternalNodesOnBothSidesAndAllLeaves() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(50);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(70);

        assertBoundary(root, List.of(10, 20, 40, 50, 60, 70, 30));
    }

    @Test
    public void testBoundaryOrderingInAnAsymmetricBalancedTree() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(-2);
        root.left.right = new TreeNode(-3);
        root.left.right.left = new TreeNode(-4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.left = new TreeNode(4);

        assertBoundary(root, List.of(0, -1, -2, -4, 2, 4, 3, 1));
    }

    @Test
    public void testMaximumValidDepthLeftChain() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 10_000; i++) {
            TreeNode next = new TreeNode(i);
            current.left = next;
            current = next;
        }

        List<Integer> result = test.boundaryOfBinaryTree(root);
        assertEquals(10_001, result.size());
        for (int i = 0; i <= 10_000; i++) {
            assertEquals(i, result.get(i));
        }
    }

    @Test
    public void testMaximumNodeCountCompleteShape() {
        TreeNode root = completeTree(10_000);

        List<Integer> expected = boundaryOracle(root);
        assertEquals(expected, test.boundaryOfBinaryTree(root));
        assertEquals(10_000, countNodes(root));
    }

    @Test
    public void testInputTreeStructureIsNotMutated() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leaf = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.right = leaf;

        List<Integer> result = test.boundaryOfBinaryTree(root);

        assertEquals(List.of(1, 2, 4, 3), result);
        assertSame(left, root.left);
        assertSame(right, root.right);
        assertSame(leaf, left.right);
        assertEquals(2, left.val);
    }

    @Test
    public void testRepeatedCallsUseFreshTraversalState() {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        first.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(first));

        TreeNode second = new TreeNode(8);
        second.right = new TreeNode(9);
        second.right.left = new TreeNode(10);
        assertEquals(List.of(8, 10, 9), test.boundaryOfBinaryTree(second));
    }

    @Test
    public void testMutatingReturnedListDoesNotAffectLaterCalls() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        List<Integer> first = test.boundaryOfBinaryTree(root);
        first.clear();

        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testReferenceOracleCoversARepeatedValueLeafAndBoundary() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.right = new TreeNode(9);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(9);

        assertBoundary(root, List.of(9, 8, 9, 9, 8));
    }

    private void assertBoundary(TreeNode root, List<Integer> expected) {
        assertEquals(expected, boundaryOracle(root));
        assertEquals(expected, test.boundaryOfBinaryTree(root));
    }

    /**
     * Independent reference implementation: collect leaves and parent links first, then derive
     * the two outer paths from the first/last leaf. Identity-based membership is important because
     * LeetCode permits equal values in different nodes, while only a node itself may be emitted once.
     */
    private static List<Integer> boundaryOracle(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        List<TreeNode> leaves = new ArrayList<>();
        IdentityHashMap<TreeNode, TreeNode> parent = new IdentityHashMap<>();
        collectLeavesAndParents(root, null, parent, leaves);

        List<TreeNode> firstPath = pathToRoot(leaves.get(0), parent);
        List<TreeNode> lastPath = pathToRoot(leaves.get(leaves.size() - 1), parent);
        Collections.reverse(firstPath);
        Collections.reverse(lastPath);

        List<Integer> boundary = new ArrayList<>();
        Set<TreeNode> emitted = Collections.newSetFromMap(new IdentityHashMap<>());
        append(root, boundary, emitted);

        if (root.left != null) {
            for (int i = 1; i < firstPath.size() - 1; i++) {
                append(firstPath.get(i), boundary, emitted);
            }
        }
        for (TreeNode leaf : leaves) {
            append(leaf, boundary, emitted);
        }
        if (root.right != null) {
            for (int i = lastPath.size() - 2; i >= 1; i--) {
                append(lastPath.get(i), boundary, emitted);
            }
        }
        return boundary;
    }

    private static void collectLeavesAndParents(TreeNode node, TreeNode parent,
                                                 IdentityHashMap<TreeNode, TreeNode> parents,
                                                 List<TreeNode> leaves) {
        if (node == null) {
            return;
        }
        if (parent != null) {
            parents.put(node, parent);
        }
        if (node.left == null && node.right == null) {
            leaves.add(node);
            return;
        }
        collectLeavesAndParents(node.left, node, parents, leaves);
        collectLeavesAndParents(node.right, node, parents, leaves);
    }

    private static List<TreeNode> pathToRoot(TreeNode node, IdentityHashMap<TreeNode, TreeNode> parents) {
        List<TreeNode> path = new ArrayList<>();
        for (TreeNode current = node; current != null; current = parents.get(current)) {
            path.add(current);
        }
        return path;
    }

    private static void append(TreeNode node, List<Integer> out, Set<TreeNode> emitted) {
        if (emitted.add(node)) {
            out.add(node.val);
        }
    }

    private static TreeNode completeTree(int size) {
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(i - 5_000);
        }
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < size) {
                nodes[i].left = nodes[left];
            }
            if (right < size) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes[0];
    }

    private static int countNodes(TreeNode root) {
        int count = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            count++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }
}
