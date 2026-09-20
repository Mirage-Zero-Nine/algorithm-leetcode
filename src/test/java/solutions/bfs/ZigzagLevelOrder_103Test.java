package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for the breadth-first zigzag traversal of a binary tree. */
public class ZigzagLevelOrder_103Test {

    private final ZigzagLevelOrder_103 test = new ZigzagLevelOrder_103();

    @Test
    public void testOfficialExampleOne() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        assertEquals(List.of(List.of(3), List.of(20, 9), List.of(15, 7)),
                test.zigzagLevelOrder(root));
    }

    @Test
    public void testOfficialExampleTwoSingleton() {
        assertEquals(List.of(List.of(1)), test.zigzagLevelOrder(new TreeNode(1)));
    }

    @Test
    public void testOfficialExampleThreeEmptyTree() {
        assertEquals(List.of(), test.zigzagLevelOrder(null));
    }

    @Test
    public void testRootAndTwoChildrenReverseTheSecondLevel() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(1);

        assertEquals(List.of(List.of(0), List.of(1, -1)), test.zigzagLevelOrder(root));
    }

    @Test
    public void testPerfectTreeAlternatesEveryLevel() {
        TreeNode root = completeTree(15);

        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
        assertEquals(List.of(
                List.of(-100),
                List.of(-26, -63),
                List.of(11, 48, 85, -79),
                List.of(16, -21, -58, -95, 69, 32, -5, -42)),
                test.zigzagLevelOrder(root));
    }

    @Test
    public void testSparseCrossParentOrdering() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(6);
        root.left.right.right = new TreeNode(5);

        assertEquals(List.of(
                List.of(0), List.of(2, 1), List.of(3, 4), List.of(6, 5)),
                test.zigzagLevelOrder(root));
    }

    @Test
    public void testLeftSkewedTreePreservesSingleValueLevels() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= 8; value++) {
            current.left = new TreeNode(value);
            current = current.left;
        }

        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
    }

    @Test
    public void testRightSkewedTreePreservesSingleValueLevels() {
        TreeNode root = new TreeNode(8);
        TreeNode current = root;
        for (int value = 7; value >= 1; value--) {
            current.right = new TreeNode(value);
            current = current.right;
        }

        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
    }

    @Test
    public void testAlternatingSkewedTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int value = 1; value <= 12; value++) {
            if ((value & 1) == 1) {
                current.left = new TreeNode(value);
                current = current.left;
            } else {
                current.right = new TreeNode(value);
                current = current.right;
            }
        }

        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
    }

    @Test
    public void testDuplicateValuesRemainInTheirPositionalOrder() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        root.left.right.left = new TreeNode(5);

        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
    }

    @Test
    public void testNegativeAndZeroValuesWithinProblemBounds() {
        TreeNode root = new TreeNode(-100);
        root.left = new TreeNode(-50);
        root.right = new TreeNode(0);
        root.left.right = new TreeNode(50);
        root.right.left = new TreeNode(100);

        assertEquals(List.of(
                List.of(-100), List.of(0, -50), List.of(50, 100)),
                test.zigzagLevelOrder(root));
    }

    @Test
    public void testBoundaryValuesCanBeMixedAcrossAllLevels() {
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(-100);
        root.right = new TreeNode(100);
        root.left.left = new TreeNode(100);
        root.left.right = new TreeNode(-100);
        root.right.left = new TreeNode(-100);
        root.right.right = new TreeNode(100);

        assertEquals(List.of(
                List.of(100),
                List.of(100, -100),
                List.of(100, -100, -100, 100)),
                test.zigzagLevelOrder(root));
    }

    @Test
    public void testUnevenDepthsDoNotCreateEmptyLevels() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.right.right = new TreeNode(50);
        root.left.left.right = new TreeNode(60);
        root.right.right.left = new TreeNode(70);
        root.left.left.right.left = new TreeNode(80);

        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
    }

    @Test
    public void testDifferentParentsAtSameLevelAreNotGroupedSeparately() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.left.left.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.left.left.right.right = new TreeNode(8);
        root.right.left.left.left = new TreeNode(9);

        assertEquals(List.of(
                List.of(1), List.of(3, 2), List.of(4, 5), List.of(7, 6),
                List.of(8, 9)), test.zigzagLevelOrder(root));
    }

    @Test
    public void testManyDeterministicShapesAgainstIndependentDepthFirstOracle() {
        Random random = new Random(103_2026L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int nodeCount = random.nextInt(80);
            TreeNode root = randomTree(random, nodeCount);
            assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root),
                    "failed generated tree " + caseNumber + " with " + nodeCount + " nodes");
        }
    }

    @Test
    public void testCompleteTreeAtTheTwoThousandNodeBoundary() {
        TreeNode root = completeTree(2_000);
        List<List<Integer>> actual = test.zigzagLevelOrder(root);

        assertEquals(expectedByDepthFirstTraversal(root), actual);
        assertEquals(2_000, actual.stream().mapToInt(List::size).sum());
        assertEquals(11, actual.size());
        assertEquals(977, actual.get(10).size());
    }

    @Test
    public void testMaximumDepthTwoThousandNodeLeftSpine() {
        TreeNode root = new TreeNode(-100);
        TreeNode current = root;
        for (int i = 1; i < 2_000; i++) {
            current.left = new TreeNode((i % 201) - 100);
            current = current.left;
        }

        List<List<Integer>> actual = test.zigzagLevelOrder(root);
        assertEquals(expectedByDepthFirstTraversal(root), actual);
        assertEquals(2_000, actual.size());
        assertEquals(List.of(100), actual.get(100 + 100));
    }

    @Test
    public void testGeneratedTreesAtVaryingSizesUseEveryNodeExactlyOnce() {
        Random random = new Random(7L);
        for (int nodeCount : List.of(1, 2, 3, 4, 7, 31, 127, 511)) {
            TreeNode root = randomTree(random, nodeCount);
            List<List<Integer>> actual = test.zigzagLevelOrder(root);
            assertEquals(expectedByDepthFirstTraversal(root), actual);
            assertEquals(nodeCount, actual.stream().mapToInt(List::size).sum());
        }
    }

    @Test
    public void testNullResultsAreFreshAndMutableWithoutSharedState() {
        List<List<Integer>> first = test.zigzagLevelOrder(null);
        List<List<Integer>> second = test.zigzagLevelOrder(null);

        assertNotSame(first, second);
        first.add(new ArrayList<>(List.of(1)));
        assertEquals(List.of(), second);
    }

    @Test
    public void testResultsAndRowsAreFreshAcrossInvocations() {
        TreeNode root = completeTree(7);
        List<List<Integer>> first = test.zigzagLevelOrder(root);
        List<List<Integer>> second = test.zigzagLevelOrder(root);

        assertEquals(first, second);
        assertNotSame(first, second);
        assertNotSame(first.get(0), second.get(0));
        assertNotSame(first.get(1), second.get(1));

        first.get(1).set(0, 99);
        first.add(new ArrayList<>(List.of(42)));
        assertEquals(expectedByDepthFirstTraversal(root), second);
        assertEquals(-100, root.val);
    }

    @Test
    public void testInputTreeTopologyAndValuesAreNotModified() {
        TreeNode root = completeTree(15);
        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        TreeNode originalGrandchild = root.left.right;
        List<List<Integer>> before = expectedByDepthFirstTraversal(root);

        test.zigzagLevelOrder(root);

        assertEquals(before, expectedByDepthFirstTraversal(root));
        assertSame(originalLeft, root.left);
        assertSame(originalRight, root.right);
        assertSame(originalGrandchild, root.left.right);
    }

    @Test
    public void testSameInstanceCanBeReusedForIndependentTrees() {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        TreeNode second = new TreeNode(10);
        second.right = new TreeNode(11);
        second.right.left = new TreeNode(12);

        assertEquals(expectedByDepthFirstTraversal(first), test.zigzagLevelOrder(first));
        assertEquals(expectedByDepthFirstTraversal(second), test.zigzagLevelOrder(second));
        assertEquals(expectedByDepthFirstTraversal(first), test.zigzagLevelOrder(first));
    }

    @Test
    public void testRepeatedCallsAfterNullDoNotLeakQueueOrDirection() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(8);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(6);

        assertEquals(List.of(), test.zigzagLevelOrder(null));
        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
        assertEquals(List.of(), test.zigzagLevelOrder(null));
        assertEquals(expectedByDepthFirstTraversal(root), test.zigzagLevelOrder(root));
    }

    @Test
    public void testEveryLevelContainsOnlyNodesAtThatDepth() {
        TreeNode root = completeTree(31);
        List<List<Integer>> actual = test.zigzagLevelOrder(root);

        assertEquals(5, actual.size());
        assertEquals(List.of(-100), actual.get(0));
        assertEquals(2, actual.get(1).size());
        assertEquals(4, actual.get(2).size());
        assertEquals(8, actual.get(3).size());
        assertEquals(16, actual.get(4).size());
        assertEquals(expectedByDepthFirstTraversal(root), actual);
    }

    @Test
    public void testZeroValuedTreeDoesNotLookLikeMissingNodes() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0);

        assertEquals(List.of(List.of(0), List.of(0, 0), List.of(0, 0)),
                test.zigzagLevelOrder(root));
    }

    private static TreeNode completeTree(int nodeCount) {
        if (nodeCount == 0) {
            return null;
        }
        List<TreeNode> nodes = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(new TreeNode((i * 37 % 201) - 100));
        }
        for (int i = 0; i < nodeCount; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < nodeCount) {
                nodes.get(i).left = nodes.get(left);
            }
            if (right < nodeCount) {
                nodes.get(i).right = nodes.get(right);
            }
        }
        return nodes.get(0);
    }

    private static TreeNode randomTree(Random random, int nodeCount) {
        if (nodeCount == 0) {
            return null;
        }
        TreeNode root = new TreeNode(random.nextInt(201) - 100);
        List<TreeNode> availableParents = new ArrayList<>();
        availableParents.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(availableParents.size());
            TreeNode parent = availableParents.get(parentIndex);
            TreeNode child = new TreeNode(random.nextInt(201) - 100);
            if (parent.left == null && parent.right == null) {
                if (random.nextBoolean()) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            availableParents.add(child);
            if (parent.left != null && parent.right != null) {
                availableParents.remove(parentIndex);
            }
        }
        return root;
    }

    /**
     * Independent oracle: a pre-order traversal records each node by depth, then
     * reverses only odd-numbered levels. It does not depend on the solution's queue
     * frontier or its linked-list insertion strategy.
     */
    private static List<List<Integer>> expectedByDepthFirstTraversal(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }

        Deque<NodeAtDepth> stack = new ArrayDeque<>();
        stack.push(new NodeAtDepth(root, 0));
        while (!stack.isEmpty()) {
            NodeAtDepth current = stack.pop();
            while (levels.size() <= current.depth) {
                levels.add(new ArrayList<>());
            }
            levels.get(current.depth).add(current.node.val);
            if (current.node.right != null) {
                stack.push(new NodeAtDepth(current.node.right, current.depth + 1));
            }
            if (current.node.left != null) {
                stack.push(new NodeAtDepth(current.node.left, current.depth + 1));
            }
        }
        for (int depth = 1; depth < levels.size(); depth += 2) {
            Collections.reverse(levels.get(depth));
        }
        return levels;
    }

    private static final class NodeAtDepth {
        private final TreeNode node;
        private final int depth;

        private NodeAtDepth(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
