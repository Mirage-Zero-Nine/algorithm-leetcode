package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for the region-counting solution to LeetCode 1145. */
public class BtreeGameWinningMove_1145Test {

    @Test
    public void singletonTreeCannotBeWon() {
        assertOutcome(new TreeNode(1), 1, 1, false);
    }

    @Test
    public void balancedThreeNodeTreeHasNoMajorityRegionAtRoot() {
        TreeNode root = completeTree(3);
        assertOutcome(root, 3, 1, false);
    }

    @Test
    public void threeNodeChainCanBeWonByTakingTheRootChildRegion() {
        TreeNode root = leftChain(3);
        assertOutcome(root, 3, 1, true);
    }

    @Test
    public void threeNodeChainHasNoMajorityWhenTheMiddleIsRed() {
        TreeNode root = leftChain(3);
        assertOutcome(root, 3, 2, false);
    }

    @Test
    public void threeNodeChainCanBeWonFromTheLeaf() {
        TreeNode root = leftChain(3);
        assertOutcome(root, 3, 3, true);
    }

    @Test
    public void balancedSevenNodeRootIsAnExactTie() {
        assertOutcome(completeTree(7), 7, 1, false);
    }

    @Test
    public void balancedSevenNodeInternalNodeLeavesTheParentRegionLargest() {
        assertOutcome(completeTree(7), 7, 2, true);
    }

    @Test
    public void balancedSevenNodeLeafLeavesSixNodesOutsideItsSubtree() {
        assertOutcome(completeTree(7), 7, 4, true);
    }

    @Test
    public void fiveNodeChainMiddleIsAnExactThreeWayTie() {
        assertOutcome(leftChain(5), 5, 3, false);
    }

    @Test
    public void fiveNodeChainNearRootHasAWinningDescendantRegion() {
        assertOutcome(leftChain(5), 5, 2, true);
    }

    @Test
    public void leftSubtreeCanBeTheWinningBlueRegion() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(7);
        assertOutcome(root, 7, 1, true);
    }

    @Test
    public void rightSubtreeCanBeTheWinningBlueRegion() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        assertOutcome(root, 7, 1, true);
    }

    @Test
    public void parentSideCanBeTheWinningBlueRegion() {
        assertOutcome(completeTree(7), 7, 4, true);
    }

    @Test
    public void internalNodeCanHaveThreeEqualRegions() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        assertOutcome(root, 7, 2, false);
    }

    @Test
    public void rightHeavyTreeCanBeAnExactTieAtAnInternalNode() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        assertOutcome(root, 5, 3, false);
    }

    @Test
    public void sparseTreeStillUsesTheOutsideComponent() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.left.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertOutcome(root, 7, 3, true);
    }

    @Test
    public void sparseTreeCanHaveAWinningLeftRegion() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertOutcome(root, 7, 1, true);
    }

    @Test
    public void nineNodeBalancedRootIsAnExactTie() {
        assertOutcome(balancedTree(9), 9, 1, false);
    }

    @Test
    public void nineNodeBalancedLeafLeavesAWinningParentRegion() {
        assertOutcome(balancedTree(9), 9, 4, true);
    }

    @Test
    public void maximumOddNodeCountBalancedRootCannotWin() {
        assertOutcome(balancedTree(99), 99, 1, false);
    }

    @Test
    public void maximumOddNodeCountChainNearRootCanWin() {
        assertOutcome(leftChain(99), 99, 2, true);
    }

    @Test
    public void maximumOddNodeCountChainMiddleIsAnExactTie() {
        assertOutcome(leftChain(99), 99, 50, false);
    }

    @Test
    public void maximumOddNodeCountRightChainExercisesTheOtherChildField() {
        assertOutcome(rightChain(99), 99, 98, true);
    }

    @Test
    public void allValidTargetsOnASevenNodeTreeMatchTheIndependentOracle() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.left.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        for (int x = 1; x <= 7; x++) {
            assertAgainstOracle(root, 7, x);
        }
    }

    @Test
    public void seededIrregularTreesCheckEveryPossibleRedTarget() {
        Random random = new Random(1145L);
        for (int caseNumber = 0; caseNumber < 24; caseNumber++) {
            int n = 1 + 2 * random.nextInt(50);
            TreeNode root = randomBinaryTree(n, random);
            for (int x = 1; x <= n; x++) {
                assertAgainstOracle(root, n, x);
            }
        }
    }

    @Test
    public void repeatedCallsWithValidTargetsDoNotShareAResult() {
        TreeNode root = completeTree(7);
        BtreeGameWinningMove_1145 solver = new BtreeGameWinningMove_1145();
        assertEquals(oracle(root, 7, 1), solver.btreeGameWinningMove(root, 7, 1));
        assertEquals(oracle(root, 7, 2), solver.btreeGameWinningMove(root, 7, 2));
        assertEquals(oracle(root, 7, 4), solver.btreeGameWinningMove(root, 7, 4));
        assertEquals(oracle(root, 7, 1), solver.btreeGameWinningMove(root, 7, 1));
    }

    @Test
    public void inputTreeIsNotMutated() {
        TreeNode root = completeTree(15);
        String before = structure(root);
        assertAgainstOracle(root, 15, 8);
        assertEquals(before, structure(root));
    }

    @Test
    public void separateSolverInstancesRemainIndependent() {
        TreeNode first = leftChain(5);
        TreeNode second = completeTree(7);
        BtreeGameWinningMove_1145 firstSolver = new BtreeGameWinningMove_1145();
        BtreeGameWinningMove_1145 secondSolver = new BtreeGameWinningMove_1145();
        assertEquals(oracle(first, 5, 3), firstSolver.btreeGameWinningMove(first, 5, 3));
        assertEquals(oracle(second, 7, 2), secondSolver.btreeGameWinningMove(second, 7, 2));
        assertEquals(oracle(first, 5, 2), firstSolver.btreeGameWinningMove(first, 5, 2));
    }

    @Test
    public void minimumAndMaximumLabelsAreValidTargets() {
        TreeNode root = completeTree(7);
        assertOutcome(root, 7, 1, false);
        assertOutcome(root, 7, 7, true);
    }

    @Test
    public void oneChildTargetUsesZeroForTheMissingRegion() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        assertOutcome(root, 5, 3, true);
    }

    @Test
    public void rootWithUnequalChildrenCanBeWonByTheLargerChild() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        assertOutcome(root, 7, 1, true);
    }

    private static void assertOutcome(TreeNode root, int n, int x, boolean expected) {
        assertEquals(expected, oracle(root, n, x), "fixture expectation disagrees with independent oracle");
        BtreeGameWinningMove_1145 solver = new BtreeGameWinningMove_1145();
        assertEquals(expected, solver.btreeGameWinningMove(root, n, x));
    }

    private static void assertAgainstOracle(TreeNode root, int n, int x) {
        boolean expected = oracle(root, n, x);
        BtreeGameWinningMove_1145 solver = new BtreeGameWinningMove_1145();
        assertEquals(expected, solver.btreeGameWinningMove(root, n, x),
                () -> "n=" + n + ", x=" + x + ", tree=" + structure(root));
    }

    /**
     * Independently computes the sizes of the connected components after removing x.
     * A blue choice wins exactly when one of those components contains more than n/2 nodes.
     */
    private static boolean oracle(TreeNode root, int n, int x) {
        Map<TreeNode, List<TreeNode>> graph = new IdentityHashMap<>();
        TreeNode target = buildGraphAndFindTarget(root, x, graph);
        if (target == null) {
            return false;
        }

        Set<TreeNode> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        visited.add(target);
        int largestRegion = 0;
        for (TreeNode neighbor : graph.get(target)) {
            if (visited.add(neighbor)) {
                int regionSize = 0;
                Deque<TreeNode> queue = new ArrayDeque<>();
                queue.add(neighbor);
                while (!queue.isEmpty()) {
                    TreeNode current = queue.remove();
                    regionSize++;
                    for (TreeNode next : graph.get(current)) {
                        if (visited.add(next)) {
                            queue.add(next);
                        }
                    }
                }
                largestRegion = Math.max(largestRegion, regionSize);
            }
        }
        return largestRegion > n / 2;
    }

    private static TreeNode buildGraphAndFindTarget(
            TreeNode root, int targetValue, Map<TreeNode, List<TreeNode>> graph) {
        if (root == null) {
            return null;
        }
        TreeNode target = null;
        Set<TreeNode> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (!seen.add(current)) {
                continue;
            }
            graph.computeIfAbsent(current, ignored -> new ArrayList<>());
            if (current.val == targetValue) {
                target = current;
            }
            if (current.left != null) {
                connect(graph, current, current.left);
                stack.push(current.left);
            }
            if (current.right != null) {
                connect(graph, current, current.right);
                stack.push(current.right);
            }
        }
        return target;
    }

    private static void connect(Map<TreeNode, List<TreeNode>> graph, TreeNode first, TreeNode second) {
        graph.computeIfAbsent(first, ignored -> new ArrayList<>()).add(second);
        graph.computeIfAbsent(second, ignored -> new ArrayList<>()).add(first);
    }

    private static TreeNode completeTree(int n) {
        TreeNode[] nodes = new TreeNode[n + 1];
        for (int value = 1; value <= n; value++) {
            nodes[value] = new TreeNode(value);
        }
        for (int value = 1; value <= n; value++) {
            if (2 * value <= n) {
                nodes[value].left = nodes[2 * value];
            }
            if (2 * value + 1 <= n) {
                nodes[value].right = nodes[2 * value + 1];
            }
        }
        return nodes[1];
    }

    private static TreeNode balancedTree(int n) {
        int[] nextValue = {1};
        return balancedTree(n, nextValue);
    }

    private static TreeNode balancedTree(int size, int[] nextValue) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nextValue[0]++);
        int leftSize = (size - 1) / 2;
        root.left = balancedTree(leftSize, nextValue);
        root.right = balancedTree(size - 1 - leftSize, nextValue);
        return root;
    }

    private static TreeNode leftChain(int n) {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= n; value++) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        return root;
    }

    private static TreeNode rightChain(int n) {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= n; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        return root;
    }

    private static TreeNode randomBinaryTree(int n, Random random) {
        TreeNode[] nodes = new TreeNode[n + 1];
        for (int value = 1; value <= n; value++) {
            nodes[value] = new TreeNode(value);
        }
        for (int value = 2; value <= n; value++) {
            List<Integer> candidates = new ArrayList<>();
            for (int parent = 1; parent < value; parent++) {
                if (nodes[parent].left == null || nodes[parent].right == null) {
                    candidates.add(parent);
                }
            }
            int parent = candidates.get(random.nextInt(candidates.size()));
            if (nodes[parent].left == null
                    && (nodes[parent].right != null || random.nextBoolean())) {
                nodes[parent].left = nodes[value];
            } else {
                nodes[parent].right = nodes[value];
            }
        }
        return nodes[1];
    }

    private static String structure(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + "(" + structure(root.left) + "," + structure(root.right) + ")";
    }
}
