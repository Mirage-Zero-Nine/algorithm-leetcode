package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests the single BFS approach for LeetCode 1161. */
public class MaxLevelSum_1161Test {

    private final MaxLevelSum_1161 solution = new MaxLevelSum_1161();

    @Test
    public void officialExampleOne() {
        assertEquals(2, solution.maxLevelSum(tree(1, 7, 0, 7, -8, null, null)));
    }

    @Test
    public void officialExampleTwo() {
        assertEquals(2, solution.maxLevelSum(
                tree(989, null, 10250, 98693, -89388, null, null, null, -32127)));
    }

    @Test
    public void singletonIsLevelOne() {
        assertEquals(1, solution.maxLevelSum(new TreeNode(1)));
    }

    @Test
    public void singletonAtOfficialLowerValueBoundary() {
        assertEquals(1, solution.maxLevelSum(new TreeNode(-100_000)));
    }

    @Test
    public void allNegativeValuesPreferTheLeastNegativeLevel() {
        TreeNode root = tree(-100, -20, -3, -40, null, null, -5);
        assertEquals(2, solution.maxLevelSum(root));
    }

    @Test
    public void allZeroValuesChooseTheFirstLevelOnTie() {
        assertEquals(1, solution.maxLevelSum(tree(0, 0, 0, 0, 0, 0, 0)));
    }

    @Test
    public void rootAndChildrenTieChoosesRoot() {
        assertEquals(1, solution.maxLevelSum(tree(5, 2, 3)));
    }

    @Test
    public void equalSumsAtSeveralLevelsChooseTheSmallestLevel() {
        TreeNode root = tree(4, 2, 2, 1, 1, 1, 1);
        // Every level sums to 4, so the required answer is the first level.
        assertEquals(1, solution.maxLevelSum(root));
    }

    @Test
    public void balancedTreeUsesTheSumRatherThanTheLargestIndividualNode() {
        TreeNode root = tree(9, 8, 7, 4, 4, 4, 4);
        assertEquals(3, solution.maxLevelSum(root));
    }

    @Test
    public void maximumCanOccurAtTheLeftAndRightEndsOfSparseLevel() {
        TreeNode root = new TreeNode(-50);
        root.left = new TreeNode(20);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(100_000);
        root.right.right = new TreeNode(100_000);
        assertEquals(3, solution.maxLevelSum(root));
    }

    @Test
    public void crossParentNodesAreSummedAtTheirSharedDepth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(100);
        root.right = new TreeNode(-100);
        root.left.right = new TreeNode(60);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(-10);
        assertEquals(3, solution.maxLevelSum(root));
    }

    @Test
    public void deeperLevelCanBeatAHighRootWithSeveralModerateValues() {
        TreeNode root = tree(1, 1, 1, 10, 10, 10, 10);
        assertEquals(3, solution.maxLevelSum(root));
    }

    @Test
    public void leftSkewedTreeCanHaveItsDeepestLevelMaximum() {
        TreeNode root = new TreeNode(-100_000);
        TreeNode current = root;
        for (int i = 1; i < 8; i++) {
            current.left = new TreeNode(i == 7 ? 100_000 : -100_000);
            current = current.left;
        }
        assertEquals(8, solution.maxLevelSum(root));
    }

    @Test
    public void rightSkewedTreeWithNegativeTailKeepsTheRootMaximum() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(-20);
        assertEquals(1, solution.maxLevelSum(root));
    }

    @Test
    public void alternatingSkewedBranchesRemainSeparateLevels() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(20);
        root.left.right = new TreeNode(30);
        root.left.right.left = new TreeNode(-100);
        root.left.right.left.right = new TreeNode(10);
        assertEquals(3, solution.maxLevelSum(root));
    }

    @Test
    public void mixedPositiveAndNegativeLevelsUseTheirTotal() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(-5);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(2);
        assertEquals(3, solution.maxLevelSum(root));
    }

    @Test
    public void officialValueBoundariesAreSummedWithoutChangingTheWinner() {
        TreeNode root = tree(-100_000, 100_000, 100_000, -100_000, -100_000, -100_000, -100_000);
        assertEquals(2, solution.maxLevelSum(root));
    }

    @Test
    public void maximumOfficialWidthUsesLongOracleForTheLevelSum() {
        int nodeCount = 10_000;
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            int value = i < 4_095 ? -100_000 : 100_000;
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
        assertEquals(oracleByLongLevels(nodes[0]), solution.maxLevelSum(nodes[0]));
    }

    @Test
    public void maximumOfficialDepthIsHandledWithoutRecursion() {
        TreeNode root = new TreeNode(-100_000);
        TreeNode current = root;
        for (int i = 1; i < 10_000; i++) {
            current.right = new TreeNode(i == 9_999 ? 100_000 : -100_000);
            current = current.right;
        }
        assertEquals(10_000, solution.maxLevelSum(root));
    }

    @Test
    public void seededTreesMatchAnIndependentDepthFirstLongOracle() {
        for (int seed = 0; seed < 120; seed++) {
            TreeNode root = seededTree(0x1161L + seed, 1 + (seed * 47 % 350));
            assertEquals(oracleByDepthFirstLong(root), solution.maxLevelSum(root), "seed=" + seed);
        }
    }

    @Test
    public void allParentClosedHeapShapesThroughPositionSevenMatchOracle() {
        int validMaskCount = 0;
        for (int mask = 0; mask < (1 << 7); mask++) {
            if (!isParentClosedMask(mask)) {
                continue;
            }
            validMaskCount++;
            TreeNode root = connectedShape(mask);
            assertEquals(oracleByDepthFirstLong(root), solution.maxLevelSum(root), "mask=" + mask);
        }
        assertEquals(35, validMaskCount);
    }

    @Test
    public void inputTopologyAndValuesAreNotMutated() {
        TreeNode root = tree(10, 4, 12, null, 5, 11, 13, null, 6);
        Map<TreeNode, NodeState> before = snapshot(root);
        assertEquals(3, solution.maxLevelSum(root));
        Map<TreeNode, NodeState> after = snapshot(root);
        assertEquals(before.size(), after.size());
        for (Map.Entry<TreeNode, NodeState> entry : before.entrySet()) {
            assertEquals(entry.getValue(), after.get(entry.getKey()));
        }
    }

    @Test
    public void repeatedCallsOnTheSameInstanceAreIndependent() {
        TreeNode first = tree(1, 2, 3, 4, 5);
        assertEquals(3, solution.maxLevelSum(first));
        assertEquals(1, solution.maxLevelSum(tree(-1, -2, -3)));
        assertEquals(3, solution.maxLevelSum(first));
    }

    @Test
    public void callerRootReferenceIsPreserved() {
        TreeNode root = tree(1, 2, 3, 4, 5, 6, 7);
        TreeNode left = root.left;
        TreeNode right = root.right;
        assertEquals(3, solution.maxLevelSum(root));
        assertSame(left, root.left);
        assertSame(right, root.right);
    }

    @Test
    public void aZeroTotalAtAnEarlyLevelBeatsLaterNegativeTotals() {
        TreeNode root = tree(0, 5, -5, -10, 10, -20, 20);
        assertEquals(1, solution.maxLevelSum(root));
    }

    @Test
    public void negativeRootCanLoseToAZeroSumLevel() {
        TreeNode root = tree(-1, 4, -4, -100, -100, -100, -100);
        assertEquals(2, solution.maxLevelSum(root));
    }

    private static TreeNode tree(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            throw new IllegalArgumentException("official inputs require a non-null root");
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int next = 1;
        while (!queue.isEmpty() && next < values.length) {
            TreeNode parent = queue.remove();
            if (values[next] != null) {
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

    private static TreeNode connectedShape(int mask) {
        TreeNode[] nodes = new TreeNode[8];
        nodes[0] = new TreeNode((mask % 11) - 5);
        for (int position = 1; position < nodes.length; position++) {
            if ((mask & (1 << (position - 1))) == 0) {
                continue;
            }
            TreeNode parent = nodes[(position - 1) / 2];
            if (parent == null) {
                throw new IllegalStateException("parent-closed mask produced a missing parent");
            }
            TreeNode child = new TreeNode(position * 13 - 40);
            if ((position & 1) == 1) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            nodes[position] = child;
        }
        return nodes[0];
    }

    private static boolean isParentClosedMask(int mask) {
        for (int position = 1; position < 8; position++) {
            if ((mask & (1 << (position - 1))) != 0) {
                int parent = (position - 1) / 2;
                if (parent > 0 && (mask & (1 << (parent - 1))) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static TreeNode seededTree(long seed, int nodeCount) {
        Random random = new Random(seed);
        TreeNode root = new TreeNode(random.nextInt(200_001) - 100_000);
        List<TreeNode> open = new ArrayList<>();
        open.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(open.size());
            TreeNode parent = open.get(parentIndex);
            TreeNode child = new TreeNode(random.nextInt(200_001) - 100_000);
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

    private static int oracleByDepthFirstLong(TreeNode root) {
        List<Long> sums = new ArrayList<>();
        collect(root, 0, sums);
        long best = Long.MIN_VALUE;
        int bestLevel = 1;
        for (int depth = 0; depth < sums.size(); depth++) {
            if (sums.get(depth) > best) {
                best = sums.get(depth);
                bestLevel = depth + 1;
            }
        }
        return bestLevel;
    }

    private static void collect(TreeNode node, int depth, List<Long> sums) {
        if (node == null) {
            return;
        }
        if (depth == sums.size()) {
            sums.add((long) node.val);
        } else {
            sums.set(depth, sums.get(depth) + node.val);
        }
        collect(node.left, depth + 1, sums);
        collect(node.right, depth + 1, sums);
    }

    private static int oracleByLongLevels(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        long best = Long.MIN_VALUE;
        int level = 1;
        int bestLevel = 1;
        while (!queue.isEmpty()) {
            long sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (sum > best) {
                best = sum;
                bestLevel = level;
            }
            level++;
        }
        return bestLevel;
    }

    private static Map<TreeNode, NodeState> snapshot(TreeNode root) {
        Map<TreeNode, NodeState> states = new IdentityHashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (states.put(node, new NodeState(node.val, node.left, node.right)) != null) {
                continue;
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return states;
    }

    private record NodeState(int value, TreeNode left, TreeNode right) {
    }
}
