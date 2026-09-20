package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for counting root-to-node path maxima in a binary tree. */
public class GoodNodes_1448Test {

    private final GoodNodes_1448 test = new GoodNodes_1448();

    @Test
    public void testOfficialExampleOne() {
        TreeNode root = node(3, node(1, node(3), null), node(4, node(1), node(5)));
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testOfficialExampleTwo() {
        TreeNode root = node(3, node(3, node(4), node(2)), null);
        assertEquals(3, test.goodNodes(root));
    }

    @Test
    public void testNullRootIsSupportedByImplementation() {
        assertEquals(0, test.goodNodes(null));
    }

    @Test
    public void testSingletonRootIsAlwaysGood() {
        assertEquals(1, test.goodNodes(new TreeNode(1)));
    }

    @Test
    public void testOnlyRootGoodOnBothBranches() {
        TreeNode root = node(10, node(5, node(1), node(4)), node(3, node(2), node(0)));
        assertEquals(1, test.goodNodes(root));
    }

    @Test
    public void testEveryNodeGoodOnIncreasingPath() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testMixedPathsCountIndependently() {
        TreeNode root = node(2,
                node(4, node(3), node(5)),
                node(1, node(2), node(6)));
        assertEquals(5, test.goodNodes(root));
    }

    @Test
    public void testAllEqualValuesAreGoodIncludingDescendants() {
        TreeNode root = node(5, node(5, node(5), null), node(5, null, node(5)));
        assertEquals(5, test.goodNodes(root));
    }

    @Test
    public void testTiesWithPathMaximumCountAsGood() {
        TreeNode root = node(7,
                node(2, node(7), node(1, null, node(7))),
                node(7, node(6), node(8)));
        // Root, both 7-valued children, both 7 descendants, and 8 are good.
        assertEquals(5, test.goodNodes(root));
    }

    @Test
    public void testNegativeValuesAndZero() {
        TreeNode root = node(-1, node(-2, node(-3), node(0)), node(-1, null, node(-1)));
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testIntegerBoundaryValues() {
        TreeNode root = node(Integer.MIN_VALUE,
                node(Integer.MIN_VALUE, node(-1), null),
                node(Integer.MAX_VALUE, node(Integer.MIN_VALUE), node(Integer.MAX_VALUE)));
        assertEquals(5, test.goodNodes(root));
    }

    @Test
    public void testSparseTreeUsesTheMaximumOnEachActualPath() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(8);
        root.right.left.right = new TreeNode(10);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testBalancedTreeWithIndependentBranchMaxima() {
        TreeNode root = node(5,
                node(1, node(6, node(0), null), node(5)),
                node(4, node(3), node(7, node(6), node(8))));
        assertEquals(5, test.goodNodes(root));
    }

    @Test
    public void testLeftSkewedDecreasingPathOnlyRootGood() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        root.left.left.left.left = new TreeNode(5);
        assertEquals(1, test.goodNodes(root));
    }

    @Test
    public void testRightSkewedPathWithRepeatedMaxima() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        root.right.right.right.right = new TreeNode(3);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testInputTopologyAndValuesAreNotMutated() {
        TreeNode root = node(3, node(1, node(3), null), node(4, null, node(2)));
        String before = snapshot(root);
        assertEquals(3, test.goodNodes(root));
        assertEquals(before, snapshot(root));
    }

    @Test
    public void testRepeatedCallsDoNotRetainCountState() {
        TreeNode first = node(5, node(1), node(6));
        TreeNode second = node(0, node(-1), node(1));
        assertEquals(2, test.goodNodes(first));
        assertEquals(2, test.goodNodes(second));
        assertEquals(2, test.goodNodes(first));
        assertEquals(0, test.goodNodes(null));
        assertEquals(2, test.goodNodes(second));
    }

    @Test
    public void testSameInstanceHandlesIndependentTrees() {
        assertEquals(4, test.goodNodes(node(0, node(1), node(-1, node(0), node(2)))));
        assertEquals(1, test.goodNodes(node(20, node(19), node(18))));
    }

    @Test
    public void testSeededRandomTreesAgainstIndependentPathMaximumOracle() {
        Random random = new Random(1_448_2026L);
        for (int caseNumber = 0; caseNumber < 200; caseNumber++) {
            TreeNode root = randomTree(random, 1 + random.nextInt(100));
            assertEquals(pathMaximumOracle(root), test.goodNodes(root), "random case " + caseNumber);
        }
    }

    @Test
    public void testSmallTreesWithExtremeRandomValuesAgainstOracle() {
        Random random = new Random(44_813L);
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            TreeNode root = randomTree(random, 1 + random.nextInt(25));
            setValuesByHeapIndex(root, random);
            assertEquals(pathMaximumOracle(root), test.goodNodes(root), "extreme case " + caseNumber);
        }
    }

    @Test
    public void testMaximumNodeCountCompleteBinaryTree() {
        TreeNode root = completeTree(100_000, index -> (index * 37) % 20_001 - 10_000);
        assertEquals(pathMaximumOracle(root), test.goodNodes(root));
    }

    @Test
    public void testMaximumDepthWithinLeetCodeNodeLimit() {
        TreeNode root = rightChain(100_000, index -> 7);
        assertEquals(100_000, test.goodNodes(root));
    }

    @Test
    public void testDeepPathUsesLatestMaximumRatherThanGlobalMaximum() {
        TreeNode root = rightChain(20_001, index -> 10_000 - index);
        assertEquals(1, test.goodNodes(root));
    }

    @Test
    public void testOfficialValueBoundsOnEveryNode() {
        TreeNode root = node(-10_000,
                node(-10_000, node(10_000), null),
                node(10_000, node(-10_000), node(10_000)));
        assertEquals(5, test.goodNodes(root));
    }

    private static TreeNode node(int value) {
        return new TreeNode(value);
    }

    private static TreeNode node(int value, TreeNode left, TreeNode right) {
        TreeNode node = new TreeNode(value);
        node.left = left;
        node.right = right;
        return node;
    }

    /** Independent iterative oracle: a node is good iff it reaches the path maximum. */
    private static int pathMaximumOracle(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Deque<NodeAndMaximum> stack = new ArrayDeque<>();
        stack.push(new NodeAndMaximum(root, Integer.MIN_VALUE));
        while (!stack.isEmpty()) {
            NodeAndMaximum current = stack.pop();
            int pathMaximum = Math.max(current.maximum, current.node.val);
            if (current.node.val >= current.maximum) {
                count++;
            }
            if (current.node.right != null) {
                stack.push(new NodeAndMaximum(current.node.right, pathMaximum));
            }
            if (current.node.left != null) {
                stack.push(new NodeAndMaximum(current.node.left, pathMaximum));
            }
        }
        return count;
    }

    private static TreeNode randomTree(Random random, int size) {
        TreeNode root = new TreeNode(randomValue(random));
        List<TreeNode> parentsWithSpace = new ArrayList<>();
        parentsWithSpace.add(root);
        for (int i = 1; i < size; i++) {
            TreeNode child = new TreeNode(randomValue(random));
            int parentIndex = random.nextInt(parentsWithSpace.size());
            TreeNode parent = parentsWithSpace.get(parentIndex);
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
            if (parent.left != null && parent.right != null) {
                parentsWithSpace.remove(parentIndex);
            }
            parentsWithSpace.add(child);
        }
        return root;
    }

    private static int randomValue(Random random) {
        return random.nextInt(20_001) - 10_000;
    }

    private static void setValuesByHeapIndex(TreeNode root, Random random) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            node.val = random.nextBoolean() ? Integer.MIN_VALUE + random.nextInt(10_000)
                    : Integer.MAX_VALUE - random.nextInt(10_000);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private static TreeNode completeTree(int size, IntUnaryOperator values) {
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(values.applyAsInt(i));
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

    private static TreeNode rightChain(int size, IntUnaryOperator values) {
        TreeNode root = new TreeNode(values.applyAsInt(0));
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            current.right = new TreeNode(values.applyAsInt(i));
            current = current.right;
        }
        return root;
    }

    private static String snapshot(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder result = new StringBuilder();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            result.append(node.val).append(", ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result.toString();
    }

    private record NodeAndMaximum(TreeNode node, int maximum) {
    }
}
