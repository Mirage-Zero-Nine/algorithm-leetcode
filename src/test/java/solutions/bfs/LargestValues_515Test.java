package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for the level maxima required by LeetCode 515. */
public class LargestValues_515Test {

    private final LargestValues_515 solution = new LargestValues_515();

    @Test
    public void nullRootReturnsEmptyList() {
        assertEquals(List.of(), solution.largestValues(null));
    }

    @Test
    public void singleNodeReturnsItsValue() {
        assertEquals(List.of(0), solution.largestValues(new TreeNode(0)));
    }

    @Test
    public void officialExampleOne() {
        TreeNode root = tree(1, 3, 2, 5, 3, null, 9);
        assertEquals(List.of(1, 3, 9), solution.largestValues(root));
    }

    @Test
    public void officialExampleTwo() {
        assertEquals(List.of(1, 3), solution.largestValues(tree(1, 2, 3)));
    }

    @Test
    public void balancedTreeFindsEachLevelMaximum() {
        assertEquals(List.of(8, 12, 15, 20), solution.largestValues(
                tree(8, 4, 12, 2, 6, 10, 15, 1, 3, 5, 7, 9, 11, 14, 20)));
    }

    @Test
    public void levelMaximumCanDecreaseAtEachDepth() {
        assertEquals(List.of(100, 7, 6, 5), solution.largestValues(
                tree(100, 7, 3, 6, null, null, null, 5)));
    }

    @Test
    public void descendantCanExceedAHighRoot() {
        assertEquals(List.of(1, 2, 99), solution.largestValues(
                tree(1, 2, 0, null, 99)));
    }

    @Test
    public void allNegativeValuesUseTheLeastNegativeMaximum() {
        assertEquals(List.of(-10, -3, -5), solution.largestValues(
                tree(-10, -20, -3, -40, null, null, -5)));
    }

    @Test
    public void allZeroValuesRemainZero() {
        assertEquals(List.of(0, 0, 0, 0), solution.largestValues(
                tree(0, 0, 0, 0, 0, 0, 0, 0)));
    }

    @Test
    public void duplicateMaximumsAreReportedOncePerLevel() {
        assertEquals(List.of(2, 5, 3), solution.largestValues(
                tree(2, 5, 5, 3, 3, 3, 3)));
    }

    @Test
    public void maximumAtLeftmostPositionIsSelected() {
        assertEquals(List.of(0, 100, 9), solution.largestValues(
                tree(0, 100, 2, 9, 1, 3)));
    }

    @Test
    public void maximumAtMiddlePositionIsSelected() {
        assertEquals(List.of(0, 8, 100), solution.largestValues(
                tree(0, 1, 8, 2, 100, 3, 4)));
    }

    @Test
    public void maximumAtRightmostPositionIsSelected() {
        assertEquals(List.of(0, 8, 100), solution.largestValues(
                tree(0, 1, 8, 2, 3, 4, 100)));
    }

    @Test
    public void sparseLevelsCrossParentBoundaries() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(1);
        root.right = new TreeNode(10);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        assertEquals(List.of(8, 10, 9, 11), solution.largestValues(root));
    }

    @Test
    public void leftSkewedTreeHasOneMaximumPerDepth() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(4);
        assertEquals(List.of(7, 6, 5, 4), solution.largestValues(root));
    }

    @Test
    public void rightSkewedTreeHasOneMaximumPerDepth() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(List.of(1, 2, 3, 4), solution.largestValues(root));
    }

    @Test
    public void alternatingLeftAndRightChainIsNotFlattened() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(-1);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(-3);
        root.left.right.left.right = new TreeNode(14);
        assertEquals(List.of(10, -1, 12, -3, 14), solution.largestValues(root));
    }

    @Test
    public void oneChildLevelsDoNotInventMissingNodes() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(3);
        root.right.left.left.right = new TreeNode(4);
        assertEquals(List.of(5, 1, 2, 3, 4), solution.largestValues(root));
    }

    @Test
    public void integerBoundariesAreComparedWithoutOverflow() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        root.left.left = new TreeNode(Integer.MIN_VALUE);
        root.left.right = new TreeNode(Integer.MIN_VALUE);
        root.right.left = new TreeNode(Integer.MIN_VALUE);
        root.right.right = new TreeNode(Integer.MIN_VALUE);
        root.left.left.left = new TreeNode(Integer.MAX_VALUE);
        assertEquals(List.of(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE,
                        Integer.MAX_VALUE),
                solution.largestValues(root));
    }

    @Test
    public void deepestLeafCanBeTheOnlyMaximumAtItsLevel() {
        TreeNode root = tree(4, 2, 3, 1, null, null, null, null, 99);
        assertEquals(List.of(4, 3, 1, 99), solution.largestValues(root));
    }

    @Test
    public void repeatedCallsOnTheSameInstanceAreIndependent() {
        TreeNode first = tree(1, 2, 3, 4, 5);
        assertEquals(List.of(1, 3, 5), solution.largestValues(first));
        assertEquals(List.of(-1, 0, 8), solution.largestValues(tree(-1, -2, 0, 8)));
        assertEquals(List.of(1, 3, 5), solution.largestValues(first));
    }

    @Test
    public void inputTreeTopologyAndValuesAreNotMutated() {
        TreeNode root = tree(10, 4, 12, null, 5, 11, 13, null, 6);
        Map<TreeNode, NodeState> before = snapshot(root);
        assertEquals(List.of(10, 12, 13, 6), solution.largestValues(root));
        assertEquals(before, snapshot(root));
    }

    @Test
    public void returnedListDoesNotBecomeSolutionState() {
        TreeNode root = tree(3, 1, 2, 8, 4, null, 9);
        List<Integer> result = solution.largestValues(root);
        result.clear();
        result.add(12345);
        assertEquals(List.of(3, 2, 9), solution.largestValues(root));
    }

    @Test
    public void exhaustiveSmallShapesMatchIndependentDepthFirstOracle() {
        for (int mask = 0; mask < (1 << 6); mask++) {
            TreeNode root = new TreeNode(mask - 32);
            TreeNode[] nodes = new TreeNode[7];
            nodes[0] = root;
            for (int position = 1; position < 7; position++) {
                if ((mask & (1 << (position - 1))) == 0) {
                    continue;
                }
                TreeNode node = new TreeNode(position * 17 - mask);
                int parent = (position - 1) / 2;
                if (nodes[parent] == null) {
                    continue;
                }
                if (position % 2 == 1) {
                    nodes[parent].left = node;
                } else {
                    nodes[parent].right = node;
                }
                nodes[position] = node;
            }
            assertEquals(oracle(root), solution.largestValues(root), "mask=" + mask);
        }
    }

    @Test
    public void seededSparseTreesMatchIndependentDepthFirstOracle() {
        for (int seed = 0; seed < 100; seed++) {
            TreeNode root = seededTree(seed * 31L + 7, 1 + (seed * 37) % 250);
            assertEquals(oracle(root), solution.largestValues(root), "seed=" + seed);
        }
    }

    @Test
    public void seededTreesWithExtremeValuesMatchOracle() {
        for (int seed = 0; seed < 25; seed++) {
            TreeNode root = seededTree(seed + 9000L, 100 + seed);
            assignExtremePattern(root, new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE});
            assertEquals(oracle(root), solution.largestValues(root), "seed=" + seed);
        }
    }

    @Test
    public void maximumOfficialNodeCountCompleteTreeMatchesOracle() {
        int nodeCount = 10_000;
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            int value = i % 97 == 0 ? Integer.MAX_VALUE
                    : (i % 101 == 0 ? Integer.MIN_VALUE : (i * 37) ^ (i >>> 3));
            nodes[i] = new TreeNode(value);
            if (i > 0) {
                TreeNode parent = nodes[(i - 1) / 2];
                if ((i & 1) == 1) {
                    parent.left = nodes[i];
                } else {
                    parent.right = nodes[i];
                }
            }
        }
        assertEquals(oracle(nodes[0]), solution.largestValues(nodes[0]));
    }

    @Test
    public void maximumOfficialNodeCountRightSpineMatchesExpectedDepths() {
        int nodeCount = 10_000;
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        List<Integer> expected = new ArrayList<>(nodeCount);
        expected.add(Integer.MIN_VALUE);
        TreeNode current = root;
        for (int i = 1; i < nodeCount; i++) {
            int value = (i & 1) == 0 ? Integer.MAX_VALUE : -i;
            current.right = new TreeNode(value);
            current = current.right;
            expected.add(value);
        }
        assertEquals(expected, solution.largestValues(root));
    }

    private static List<Integer> oracle(TreeNode root) {
        List<Integer> maximums = new ArrayList<>();
        collectMaximums(root, 0, maximums);
        return maximums;
    }

    private static void collectMaximums(TreeNode node, int depth, List<Integer> maximums) {
        if (node == null) {
            return;
        }
        if (depth == maximums.size()) {
            maximums.add(node.val);
        } else {
            maximums.set(depth, Math.max(maximums.get(depth), node.val));
        }
        collectMaximums(node.left, depth + 1, maximums);
        collectMaximums(node.right, depth + 1, maximums);
    }

    private static TreeNode tree(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int next = 1;
        while (!queue.isEmpty() && next < values.length) {
            TreeNode parent = queue.remove();
            if (next < values.length && values[next] != null) {
                parent.left = new TreeNode(values[next]);
                queue.add(parent.left);
            }
            next++;
            if (next < values.length && values[next] != null) {
                parent.right = new TreeNode(values[next]);
                queue.add(parent.right);
            }
            next++;
        }
        return root;
    }

    private static TreeNode seededTree(long seed, int nodeCount) {
        Random random = new Random(seed);
        TreeNode root = new TreeNode(random.nextInt());
        List<TreeNode> open = new ArrayList<>();
        open.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(open.size());
            TreeNode parent = open.get(parentIndex);
            TreeNode child = new TreeNode(random.nextInt());
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
            open.add(child);
            if (parent.left != null && parent.right != null) {
                open.set(parentIndex, open.get(open.size() - 1));
                open.remove(open.size() - 1);
            }
        }
        return root;
    }

    private static void assignExtremePattern(TreeNode root, int[] values) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            node.val = values[index++ % values.length];
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private static Map<TreeNode, NodeState> snapshot(TreeNode root) {
        Map<TreeNode, NodeState> snapshot = new HashMap<>();
        capture(root, snapshot);
        return snapshot;
    }

    private static void capture(TreeNode node, Map<TreeNode, NodeState> snapshot) {
        if (node == null || snapshot.containsKey(node)) {
            return;
        }
        snapshot.put(node, new NodeState(node.val, node.left, node.right));
        capture(node.left, snapshot);
        capture(node.right, snapshot);
    }

    private record NodeState(int value, TreeNode left, TreeNode right) {
    }
}
