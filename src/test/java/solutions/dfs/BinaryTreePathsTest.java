package solutions.dfs;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract tests for LeetCode 257, including implementation-defined null and
 * full-Java-int inputs supported by {@link BinaryTreePaths}.
 */
class BinaryTreePathsTest {

    private final BinaryTreePaths solution = new BinaryTreePaths();

    @Test
    void leetCodeExample() {
        assertPathsEqual(
                List.of("1->2->5", "1->3"),
                solution.binaryTreePaths(TreeParser.deserialize("1,2,3,null,5")));
    }

    @Test
    void nullRootReturnsEmptyList() {
        assertTrue(solution.binaryTreePaths(null).isEmpty());
    }

    @Test
    void singletonZeroIsItsOwnRootToLeafPath() {
        assertPathsEqual(List.of("0"), solution.binaryTreePaths(new TreeNode(0)));
    }

    @Test
    void singletonIntBoundariesAreFormattedExactly() {
        assertPathsEqual(List.of(String.valueOf(Integer.MIN_VALUE)),
                solution.binaryTreePaths(new TreeNode(Integer.MIN_VALUE)));
        assertPathsEqual(List.of(String.valueOf(Integer.MAX_VALUE)),
                solution.binaryTreePaths(new TreeNode(Integer.MAX_VALUE)));
    }

    @Test
    void balancedTreeReturnsEveryLeafPath() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");

        assertPathsEqual(
                List.of("1->2->4", "1->2->5", "1->3->6", "1->3->7"),
                solution.binaryTreePaths(root));
    }

    @Test
    void sparseTreeWithMissingChildrenUsesOnlyActualEdges() {
        TreeNode root = TreeParser.deserialize("8,4,12,null,6,10,null,null,7");

        assertPathsEqual(
                List.of("8->4->6->7", "8->12->10"),
                solution.binaryTreePaths(root));
    }

    @Test
    void leftSkewedTreeHasOneDeepPath() {
        TreeNode root = leftChain(1, 2, 3, 4, 5);

        assertPathsEqual(List.of("1->2->3->4->5"), solution.binaryTreePaths(root));
    }

    @Test
    void rightSkewedTreeHasOneDeepPath() {
        TreeNode root = rightChain(5, 4, 3, 2, 1);

        assertPathsEqual(List.of("5->4->3->2->1"), solution.binaryTreePaths(root));
    }

    @Test
    void alternatingSingleChildDirectionsArePreserved() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        root.left.right.left.right = new TreeNode(5);

        assertPathsEqual(List.of("1->2->3->4->5"), solution.binaryTreePaths(root));
    }

    @Test
    void onlyLeftChildrenAtSeveralBranches() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.right.left = new TreeNode(50);
        root.right.left.left = new TreeNode(60);

        assertPathsEqual(
                List.of("10->20->40", "10->30->50->60"),
                solution.binaryTreePaths(root));
    }

    @Test
    void onlyRightChildrenAtSeveralBranches() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.right = new TreeNode(40);
        root.right.right = new TreeNode(50);
        root.right.right.right = new TreeNode(60);

        assertPathsEqual(
                List.of("10->20->40", "10->30->50->60"),
                solution.binaryTreePaths(root));
    }

    @Test
    void negativeZeroAndPositiveValuesUseArrowSeparatorsOnly() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(0);
        root.right = new TreeNode(10);
        root.left.right = new TreeNode(-1);

        assertPathsEqual(
                List.of("-10->0->-1", "-10->10"),
                solution.binaryTreePaths(root));
    }

    @Test
    void duplicateValuedLeavesRetainMultiplicity() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        // A set-based assertion would incorrectly accept only one result here.
        assertPathsEqual(List.of("1->2", "1->2"), solution.binaryTreePaths(root));
    }

    @Test
    void duplicateValuesAlongLongerPathsRetainDistinctOccurrences() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(7);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(7);

        assertPathsEqual(
                List.of("7->7->7", "7->7->7", "7->7->7"),
                solution.binaryTreePaths(root));
    }

    @Test
    void signedIntBoundaryValuesCanAppearAtEveryDepth() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(Integer.MAX_VALUE);
        root.left.right = new TreeNode(Integer.MIN_VALUE);
        root.right.left = new TreeNode(0);

        assertPathsEqual(
                List.of(
                        Integer.MIN_VALUE + "->-1->" + Integer.MIN_VALUE,
                        Integer.MIN_VALUE + "->" + Integer.MAX_VALUE + "->0"),
                solution.binaryTreePaths(root));
    }

    @Test
    void branchWithLeafAndNonLeafSiblingStopsAtLeavesOnly() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        assertPathsEqual(
                List.of("1->2", "1->3->4", "1->3->5"),
                solution.binaryTreePaths(root));
    }

    @Test
    void arbitraryOutputOrderIsAccepted() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        List<String> expected = List.of("1->2->4", "1->2->5", "1->3->6", "1->3->7");
        List<String> actual = solution.binaryTreePaths(root);

        assertPathsEqual(expected, actual);
        assertEquals(4, actual.size());
    }

    @Test
    void independentIterativeOracleCoversAHandBuiltIrregularTree() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(15);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(4);
        root.right.left = new TreeNode(-8);
        root.right.left.right = new TreeNode(3);
        root.right.right = new TreeNode(15);

        assertPathsEqual(independentLeafPaths(root), solution.binaryTreePaths(root));
    }

    @Test
    void deterministicRandomTreesMatchIndependentOracle() {
        Random random = new Random(257L);

        for (int iteration = 0; iteration < 60; iteration++) {
            int nodeCount = 1 + random.nextInt(40);
            TreeNode root = randomTree(nodeCount, random);
            assertPathsEqual(independentLeafPaths(root), solution.binaryTreePaths(root));
        }
    }

    @Test
    void exactLeetCodeMaximumNodeCountMatchesIndependentOracle() {
        TreeNode root = randomTreeWithDeterministicValues(100);

        assertPathsEqual(independentLeafPaths(root), solution.binaryTreePaths(root));
        assertEquals(leafCount(root), solution.binaryTreePaths(root).size());
    }

    @Test
    void maximumNodeCountLeftChainDoesNotLoseDeepLeaf() {
        int[] values = new int[100];
        for (int i = 0; i < values.length; i++) {
            values[i] = i - 50;
        }
        TreeNode root = leftChain(values);

        assertPathsEqual(independentLeafPaths(root), solution.binaryTreePaths(root));
    }

    @Test
    void maximumNodeCountBalancedTreeReturnsExpectedLeafCount() {
        TreeNode root = completeTree(100);
        List<String> actual = solution.binaryTreePaths(root);

        assertPathsEqual(independentLeafPaths(root), actual);
        assertEquals(50, actual.size());
    }

    @Test
    void inputTreeIsNotMutated() {
        TreeNode root = TreeParser.deserialize("1,2,3,null,5,null,7");
        String before = TreeParser.serialize(root);

        solution.binaryTreePaths(root);

        assertEquals(before, TreeParser.serialize(root));
    }

    @Test
    void repeatedCallsDoNotLeakTraversalState() {
        TreeNode root = TreeParser.deserialize("1,2,3,null,5");
        List<String> expected = independentLeafPaths(root);

        assertPathsEqual(expected, solution.binaryTreePaths(root));
        assertPathsEqual(expected, solution.binaryTreePaths(root));
        assertPathsEqual(List.of(), solution.binaryTreePaths(null));
        assertPathsEqual(expected, solution.binaryTreePaths(root));
    }

    @Test
    void eachCallReturnsAnIndependentMutableResultList() {
        TreeNode root = TreeParser.deserialize("1,2,3");
        List<String> first = solution.binaryTreePaths(root);
        List<String> second = solution.binaryTreePaths(root);

        assertNotSame(first, second);
        first.clear();
        assertPathsEqual(List.of("1->2", "1->3"), second);
        assertPathsEqual(List.of("1->2", "1->3"), solution.binaryTreePaths(root));
    }

    @Test
    void pathsHaveNoTrailingArrowOrEmptySegments() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(3);
        root.right = new TreeNode(-4);

        for (String path : solution.binaryTreePaths(root)) {
            assertTrue(!path.startsWith("->"));
            assertTrue(!path.endsWith("->"));
            assertTrue(Arrays.stream(path.split("->")).noneMatch(String::isEmpty));
        }
    }

    private void assertPathsEqual(List<String> expected, List<String> actual) {
        List<String> expectedSorted = new ArrayList<>(expected);
        List<String> actualSorted = new ArrayList<>(actual);
        expectedSorted.sort(String::compareTo);
        actualSorted.sort(String::compareTo);
        assertEquals(expectedSorted, actualSorted);
    }

    /**
     * Iterative oracle that records root-to-leaf paths independently of the
     * production method's recursive traversal and uses a stack with explicit
     * path snapshots.
     */
    private List<String> independentLeafPaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }

        Deque<NodeAndPath> stack = new ArrayDeque<>();
        stack.push(new NodeAndPath(root, String.valueOf(root.val)));
        while (!stack.isEmpty()) {
            NodeAndPath current = stack.pop();
            TreeNode node = current.node();
            if (node.left == null && node.right == null) {
                paths.add(current.path());
                continue;
            }
            if (node.right != null) {
                stack.push(new NodeAndPath(node.right, current.path() + "->" + node.right.val));
            }
            if (node.left != null) {
                stack.push(new NodeAndPath(node.left, current.path() + "->" + node.left.val));
            }
        }
        return paths;
    }

    private record NodeAndPath(TreeNode node, String path) {
    }

    private TreeNode leftChain(int... values) {
        TreeNode root = new TreeNode(values[0]);
        TreeNode current = root;
        for (int i = 1; i < values.length; i++) {
            current.left = new TreeNode(values[i]);
            current = current.left;
        }
        return root;
    }

    private TreeNode rightChain(int... values) {
        TreeNode root = new TreeNode(values[0]);
        TreeNode current = root;
        for (int i = 1; i < values.length; i++) {
            current.right = new TreeNode(values[i]);
            current = current.right;
        }
        return root;
    }

    private TreeNode randomTree(int nodeCount, Random random) {
        TreeNode root = new TreeNode(valueFor(random, 0));
        List<TreeNode> available = new ArrayList<>();
        available.add(root);

        for (int i = 1; i < nodeCount; i++) {
            TreeNode parent = available.get(random.nextInt(available.size()));
            TreeNode child = new TreeNode(valueFor(random, i));
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
            available.removeIf(node -> node.left != null && node.right != null);
            available.add(child);
        }
        return root;
    }

    private TreeNode randomTreeWithDeterministicValues(int nodeCount) {
        Random random = new Random(257257L);
        return randomTree(nodeCount, random);
    }

    private int valueFor(Random random, int index) {
        return switch (index % 8) {
            case 0 -> Integer.MIN_VALUE;
            case 1 -> Integer.MAX_VALUE;
            case 2 -> -100;
            case 3 -> 100;
            case 4 -> 0;
            default -> random.nextInt(11) - 5;
        };
    }

    private TreeNode completeTree(int nodeCount) {
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(i - 50);
        }
        for (int i = 0; i < nodeCount; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < nodeCount) {
                nodes[i].left = nodes[left];
            }
            if (right < nodeCount) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes[0];
    }

    private int leafCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return leafCount(root.left) + leafCount(root.right);
    }
}
