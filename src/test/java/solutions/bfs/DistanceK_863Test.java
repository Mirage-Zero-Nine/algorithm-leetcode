package solutions.bfs;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/** Tests both parent-building implementations against an independent undirected-tree oracle. */
public class DistanceK_863Test {

    private DistanceK_863 solution;

    @BeforeEach
    void setUp() {
        solution = new DistanceK_863();
    }

    @Test
    void officialExample() {
        assertHistoricalCase(() -> TreeParser.deserialize("3,5,1,6,2,0,8,null,null,7,4"),
                5, 2, List.of(1, 4, 7));
    }

    @Test
    void officialSingletonTooFar() {
        assertCase(() -> TreeParser.deserialize("1"), 1, 3);
    }

    @Test
    void singletonAtDistanceZero() {
        assertCase(() -> new TreeNode(9), 9, 0);
    }

    @Test
    void rootAtDistanceOne() {
        assertCase(() -> TreeParser.deserialize("1,2,3"), 1, 1);
    }

    @Test
    void rootAtDistanceTwo() {
        assertCase(() -> TreeParser.deserialize("1,2,3,4,5,6,7"), 1, 2);
    }

    @Test
    void internalTargetAtDistanceZero() {
        assertCase(() -> TreeParser.deserialize("10,6,14,4,8,12,16"), 6, 0);
    }

    @Test
    void internalTargetSeesChildrenAndParent() {
        assertCase(() -> TreeParser.deserialize("10,6,14,4,8,12,16"), 6, 1);
    }

    @Test
    void internalTargetAtDistanceTwo() {
        assertCase(() -> TreeParser.deserialize("10,6,14,4,8,12,16"), 6, 2);
    }

    @Test
    void leafTargetAtDistanceOne() {
        assertCase(() -> TreeParser.deserialize("10,6,14,4,8,12,16"), 4, 1);
    }

    @Test
    void leafTargetAtDistanceTwo() {
        assertCase(() -> TreeParser.deserialize("10,6,14,4,8,12,16"), 4, 2);
    }

    @Test
    void skewedLeftTree() {
        assertCase(() -> TreeParser.deserialize("1,2,null,3,null,4,null,5"), 3, 2);
    }

    @Test
    void skewedRightTree() {
        assertCase(() -> TreeParser.deserialize("1,null,2,null,3,null,4,null,5"), 4, 3);
    }

    @Test
    void sparseTreeAcrossBranches() {
        assertCase(() -> TreeParser.deserialize("8,3,13,null,5,11,null,null,6"), 5, 3);
    }

    @Test
    void noNodeAtRequestedDistance() {
        assertCase(() -> TreeParser.deserialize("1,2,3,4"), 4, 4);
    }

    @Test
    void targetHasOnlyOneAvailableDirection() {
        assertCase(() -> TreeParser.deserialize("1,2,3,null,null,4"), 4, 2);
    }

    @Test
    void distanceIncludesNodesOnBothSidesOfTarget() {
        assertCase(() -> TreeParser.deserialize("20,10,30,5,15,25,35,2,7,12,17"), 10, 2);
    }

    @Test
    void sparseTreeWithDistanceOneLeaves() {
        assertCase(() -> TreeParser.deserialize("50,20,80,null,30,70,null,null,40"), 30, 1);
    }

    @Test
    void duplicateCallsOnSameInstanceDoNotContaminateResults() {
        TreeNode first = TreeParser.deserialize("1,2,3,4,5,6,7");
        TreeNode second = TreeParser.deserialize("10,20,null,30,null,40");
        assertIterableEquals(List.of(1, 4, 5), sorted(solution.distanceK(first, find(first, 2), 1)));
        assertIterableEquals(List.of(20, 40), sorted(solution.distanceKDFSBFS(second, find(second, 30), 1)));
    }

    @Test
    void historicalFixturesRemainCoveredByBothApproaches() {
        assertHistoricalCase(() -> TreeParser.deserialize("1,2,3"), 2, 0, List.of(2));
        assertHistoricalCase(() -> TreeParser.deserialize("1,2,3"), 2, 5, List.of());
        assertHistoricalCase(() -> new TreeNode(9), 9, 1, List.of());
        assertHistoricalCase(() -> TreeParser.deserialize("10,6,14,4,8,12,16"),
                10, 2, List.of(4, 8, 12, 16));
        assertHistoricalCase(() -> TreeParser.deserialize("7,3,9"), 9, 0, List.of(9));
        assertHistoricalCase(() -> TreeParser.deserialize("7,3,9"), 9, 4, List.of());
        assertHistoricalCase(() -> rightChain(201), 100, 50, List.of(50, 150));
    }

    @Test
    void repeatedCallsAThenBThenAForEachApproach() {
        TreeNode bfsA = TreeParser.deserialize("1,2,3,4,5,6,7");
        TreeNode bfsB = TreeParser.deserialize("10,20,null,30,null,40");
        assertIterableEquals(List.of(1, 4, 5), sorted(solution.distanceK(bfsA, find(bfsA, 2), 1)));
        assertIterableEquals(List.of(20, 40), sorted(solution.distanceK(bfsB, find(bfsB, 30), 1)));
        assertIterableEquals(List.of(1, 4, 5), sorted(solution.distanceK(bfsA, find(bfsA, 2), 1)));

        TreeNode dfsA = TreeParser.deserialize("1,2,3,4,5,6,7");
        TreeNode dfsB = TreeParser.deserialize("10,20,null,30,null,40");
        assertIterableEquals(List.of(1, 4, 5), sorted(solution.distanceKDFSBFS(dfsA, find(dfsA, 2), 1)));
        assertIterableEquals(List.of(20, 40), sorted(solution.distanceKDFSBFS(dfsB, find(dfsB, 30), 1)));
        assertIterableEquals(List.of(1, 4, 5), sorted(solution.distanceKDFSBFS(dfsA, find(dfsA, 2), 1)));
    }

    @Test
    void nullRootReturnsEmptyForBothApproaches() {
        assertEquals(List.of(), solution.distanceK(null, null, 0));
        assertEquals(List.of(), solution.distanceKDFSBFS(null, null, 1000));
    }

    @Test
    void maximumKReturnsEmpty() {
        assertCase(() -> TreeParser.deserialize("1,2,3,4,5,6,7"), 4, 1000);
    }

    @Test
    void completeTreeWithManyNodes() {
        assertCase(() -> completeTree(255), 127, 4);
    }

    @Test
    void maximumOfficialNodeCountOnAChain() {
        assertCase(() -> rightChain(500), 250, 249);
    }

    @Test
    void maximumOfficialKOnMaximumSizedTree() {
        assertCase(() -> rightChain(500), 0, 1000);
    }

    @Test
    void maximumOfficialNodeValue500() {
        assertCase(() -> ascendingRightChain(1, 500), 500, 499);
    }

    @Test
    void seededTreesMatchIndependentOracle() {
        for (int seed = 0; seed < 24; seed++) {
            int size = 1 + (seed * 47) % 500;
            int targetValue = (seed * 83) % size;
            int k = seed % 13;
            int treeSeed = seed;
            assertCase(() -> randomTree(treeSeed, size), targetValue, k);
        }
    }

    @Test
    void zeroValuedRootAtDistanceOne() {
        assertCase(() -> completeTree(7), 0, 1);
    }

    private void assertCase(Supplier<TreeNode> builder, int targetValue, int k) {
        TreeNode first = builder.get();
        TreeNode firstTarget = find(first, targetValue);
        List<Integer> expected = oracle(first, firstTarget, k);
        Map<TreeNode, NodeState> before = snapshot(first);

        List<Integer> actualBfs = solution.distanceK(first, firstTarget, k);
        assertIterableEquals(expected, sorted(actualBfs));
        assertUnchanged(before, first, "distanceK must not mutate the tree");

        TreeNode second = builder.get();
        TreeNode secondTarget = find(second, targetValue);
        Map<TreeNode, NodeState> secondBefore = snapshot(second);
        List<Integer> actualDfsBfs = solution.distanceKDFSBFS(second, secondTarget, k);
        assertIterableEquals(expected, sorted(actualDfsBfs));
        assertUnchanged(secondBefore, second, "distanceKDFSBFS must not mutate the tree");
    }

    private void assertHistoricalCase(Supplier<TreeNode> builder, int targetValue, int k,
                                      List<Integer> expected) {
        TreeNode bfsRoot = builder.get();
        Map<TreeNode, NodeState> bfsBefore = snapshot(bfsRoot);
        assertIterableEquals(expected, sorted(solution.distanceK(bfsRoot, find(bfsRoot, targetValue), k)));
        assertUnchanged(bfsBefore, bfsRoot, "distanceK changed a historical fixture");

        TreeNode dfsRoot = builder.get();
        Map<TreeNode, NodeState> dfsBefore = snapshot(dfsRoot);
        assertIterableEquals(expected, sorted(solution.distanceKDFSBFS(dfsRoot, find(dfsRoot, targetValue), k)));
        assertUnchanged(dfsBefore, dfsRoot, "distanceKDFSBFS changed a historical fixture");
    }

    /** Independent oracle: convert the tree to an identity-keyed undirected graph, then BFS layers. */
    private List<Integer> oracle(TreeNode root, TreeNode target, int k) {
        if (root == null || target == null || k < 0) {
            return List.of();
        }
        Map<TreeNode, List<TreeNode>> graph = new IdentityHashMap<>();
        Deque<TreeNode> pending = new ArrayDeque<>();
        pending.add(root);
        while (!pending.isEmpty()) {
            TreeNode node = pending.remove();
            graph.computeIfAbsent(node, ignored -> new ArrayList<>());
            if (node.left != null) {
                graph.get(node).add(node.left);
                graph.computeIfAbsent(node.left, ignored -> new ArrayList<>()).add(node);
                pending.add(node.left);
            }
            if (node.right != null) {
                graph.get(node).add(node.right);
                graph.computeIfAbsent(node.right, ignored -> new ArrayList<>()).add(node);
                pending.add(node.right);
            }
        }

        Set<TreeNode> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        Deque<TreeNode> level = new ArrayDeque<>();
        level.add(target);
        visited.add(target);
        for (int distance = 0; distance < k && !level.isEmpty(); distance++) {
            int size = level.size();
            while (size-- > 0) {
                for (TreeNode next : graph.getOrDefault(level.remove(), List.of())) {
                    if (visited.add(next)) {
                        level.add(next);
                    }
                }
            }
        }
        List<Integer> values = new ArrayList<>();
        for (TreeNode node : level) {
            values.add(node.val);
        }
        return sorted(values);
    }

    private TreeNode find(TreeNode root, int value) {
        Deque<TreeNode> pending = new ArrayDeque<>();
        pending.add(root);
        while (!pending.isEmpty()) {
            TreeNode node = pending.remove();
            if (node.val == value) {
                return node;
            }
            if (node.left != null) pending.add(node.left);
            if (node.right != null) pending.add(node.right);
        }
        throw new IllegalArgumentException("target is not in tree: " + value);
    }

    private Map<TreeNode, NodeState> snapshot(TreeNode root) {
        Map<TreeNode, NodeState> result = new IdentityHashMap<>();
        Deque<TreeNode> pending = new ArrayDeque<>();
        pending.add(root);
        while (!pending.isEmpty()) {
            TreeNode node = pending.remove();
            result.put(node, new NodeState(node.val, node.left, node.right));
            if (node.left != null) pending.add(node.left);
            if (node.right != null) pending.add(node.right);
        }
        return result;
    }

    private void assertUnchanged(Map<TreeNode, NodeState> before, TreeNode root, String message) {
        Map<TreeNode, NodeState> after = snapshot(root);
        assertEquals(before.keySet(), after.keySet(), message + " (node identity set)");
        for (Map.Entry<TreeNode, NodeState> entry : before.entrySet()) {
            NodeState expected = entry.getValue();
            NodeState actual = after.get(entry.getKey());
            assertEquals(expected.value(), actual.value(), message + " (value)");
            assertEquals(expected.left(), actual.left(), message + " (left reference)");
            assertEquals(expected.right(), actual.right(), message + " (right reference)");
        }
    }

    private List<Integer> sorted(List<Integer> values) {
        List<Integer> copy = new ArrayList<>(values);
        Collections.sort(copy);
        return copy;
    }

    private TreeNode rightChain(int size) {
        return ascendingRightChain(0, size - 1);
    }

    private TreeNode ascendingRightChain(int firstValue, int lastValue) {
        TreeNode root = new TreeNode(firstValue);
        TreeNode current = root;
        for (int value = firstValue + 1; value <= lastValue; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        return root;
    }

    private TreeNode completeTree(int size) {
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) nodes[i] = new TreeNode(i);
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < size) nodes[i].left = nodes[left];
            if (right < size) nodes[i].right = nodes[right];
        }
        return nodes[0];
    }

    /** Builds a deterministic binary tree with unique values and arbitrary parent/child shape. */
    private TreeNode randomTree(int seed, int size) {
        TreeNode[] nodes = new TreeNode[size];
        for (int value = 0; value < size; value++) {
            nodes[value] = new TreeNode(value);
        }
        Random random = new Random(seed);
        List<TreeNode> parentsWithSpace = new ArrayList<>();
        parentsWithSpace.add(nodes[0]);
        for (int value = 1; value < size; value++) {
            int parentIndex = random.nextInt(parentsWithSpace.size());
            TreeNode parent = parentsWithSpace.get(parentIndex);
            if (parent.left == null && parent.right == null) {
                if (random.nextBoolean()) {
                    parent.left = nodes[value];
                } else {
                    parent.right = nodes[value];
                }
            } else if (parent.left == null) {
                parent.left = nodes[value];
            } else {
                parent.right = nodes[value];
            }
            parentsWithSpace.add(nodes[value]);
            if (parent.left != null && parent.right != null) {
                parentsWithSpace.remove(parentIndex);
            }
        }
        return nodes[0];
    }

    private record NodeState(int value, TreeNode left, TreeNode right) {
    }
}
