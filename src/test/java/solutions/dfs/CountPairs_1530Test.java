package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests the leaf-pair distance contract with an independent tree-path oracle. */
public class CountPairs_1530Test {

    @Test
    public void officialExampleOne() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        assertEquals(1, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void officialExampleTwo() {
        TreeNode root = completeTree(3);

        assertEquals(2, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void officialExampleThree() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(2);

        assertEquals(1, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void nullRootHasNoPairs() {
        assertEquals(0, new CountPairs_1530().countPairs(null, 1));
    }

    @Test
    public void singletonHasNoPair() {
        assertEquals(0, new CountPairs_1530().countPairs(new TreeNode(99), 10));
    }

    @Test
    public void distanceOneExcludesSiblingLeaves() {
        TreeNode root = twoLeafTree(1, 2, 3);

        assertEquals(0, new CountPairs_1530().countPairs(root, 1));
    }

    @Test
    public void distanceTwoIncludesSiblingLeaves() {
        TreeNode root = twoLeafTree(1, 2, 3);

        assertEquals(1, new CountPairs_1530().countPairs(root, 2));
    }

    @Test
    public void distanceBoundaryIsInclusive() {
        TreeNode root = completeTree(3);

        assertEquals(expectedPairs(root, 4), new CountPairs_1530().countPairs(root, 4));
        assertEquals(6, expectedPairs(root, 4));
    }

    @Test
    public void distanceJustBelowBoundaryExcludesCrossBranchPairs() {
        TreeNode root = completeTree(3);

        assertEquals(2, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void oneLeafDegenerateTreeHasNoPair() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);

        assertEquals(0, new CountPairs_1530().countPairs(root, 10));
    }

    @Test
    public void sparseTreeUsesShortestLeafPaths() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        assertEquals(expectedPairs(root, 5), new CountPairs_1530().countPairs(root, 5));
    }

    @Test
    public void leftSkewedBranchAtDistanceNineIsIncluded() {
        TreeNode root = chainWithRightLeaf(8, false);

        assertEquals(1, new CountPairs_1530().countPairs(root, 9));
    }

    @Test
    public void rightSkewedBranchAtDistanceNineIsIncluded() {
        TreeNode root = chainWithRightLeaf(8, true);

        assertEquals(1, new CountPairs_1530().countPairs(root, 9));
    }

    @Test
    public void maximumDistanceTenIsInclusive() {
        TreeNode root = chainWithRightLeaf(9, false);

        assertEquals(0, new CountPairs_1530().countPairs(root, 9));
        assertEquals(1, new CountPairs_1530().countPairs(root, 10));
    }

    @Test
    public void distanceZeroHasNoPairs() {
        TreeNode root = twoLeafTree(1, 2, 3);

        assertEquals(0, new CountPairs_1530().countPairs(root, 0));
    }

    @Test
    public void negativeDistanceHasNoPairs() {
        TreeNode root = completeTree(3);

        assertEquals(0, new CountPairs_1530().countPairs(root, -4));
    }

    @Test
    public void leafValuesDoNotAffectPairCount() {
        TreeNode first = completeTree(4);
        TreeNode second = completeTree(4);
        assignValues(first, new int[] {Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -7, 42, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17});

        assertEquals(new CountPairs_1530().countPairs(first, 6),
                new CountPairs_1530().countPairs(second, 6));
    }

    @Test
    public void reusedSolverDoesNotAccumulateCounts() {
        CountPairs_1530 solver = new CountPairs_1530();
        TreeNode paired = twoLeafTree(1, 2, 3);
        TreeNode singleLeaf = new TreeNode(4);
        singleLeaf.left = new TreeNode(5);

        assertEquals(1, solver.countPairs(paired, 2));
        assertEquals(0, solver.countPairs(singleLeaf, 10));
        assertEquals(1, solver.countPairs(paired, 2));
    }

    @Test
    public void nullCallAfterSuccessfulCallResetsSolver() {
        CountPairs_1530 solver = new CountPairs_1530();

        assertEquals(1, solver.countPairs(twoLeafTree(1, 2, 3), 2));
        assertEquals(0, solver.countPairs(null, 10));
        assertEquals(0, solver.countPairs(new TreeNode(8), 10));
    }

    @Test
    public void repeatedDistancesOnSameTreeAreIndependent() {
        CountPairs_1530 solver = new CountPairs_1530();
        TreeNode root = completeTree(3);

        assertEquals(2, solver.countPairs(root, 3));
        assertEquals(6, solver.countPairs(root, 4));
        assertEquals(2, solver.countPairs(root, 3));
    }

    @Test
    public void inputTreeIsNotMutated() {
        TreeNode root = completeTree(5);
        String before = shape(root);

        new CountPairs_1530().countPairs(root, 7);

        assertEquals(before, shape(root));
    }

    @Test
    public void allPairsWithinMaximumDistanceAreCounted() {
        TreeNode root = completeTree(6);

        assertEquals(496, new CountPairs_1530().countPairs(root, 10));
    }

    @Test
    public void exhaustiveSmallShapesMatchIndependentOracle() {
        int checked = 0;
        for (int nodes = 1; nodes <= 6; nodes++) {
            for (TreeNode root : allShapes(nodes)) {
                for (int distance = 1; distance <= 10; distance++) {
                    int expected = expectedPairs(root, distance);
                    assertEquals(expected, new CountPairs_1530().countPairs(root, distance),
                            "nodes=" + nodes + ", distance=" + distance + ", shape=" + shape(root));
                    checked++;
                }
            }
        }
        assertTrue(checked > 1_000);
    }

    @Test
    public void seededIrregularTreesMatchIndependentOracle() {
        Random random = new Random(1530L);
        for (int trial = 0; trial < 120; trial++) {
            TreeNode root = randomTree(1 + random.nextInt(45), random);
            int distance = 1 + random.nextInt(10);

            assertEquals(expectedPairs(root, distance), new CountPairs_1530().countPairs(root, distance),
                    "trial=" + trial + ", distance=" + distance + ", shape=" + shape(root));
        }
    }

    @Test
    public void maximumNodeCountMatchesIndependentOracle() {
        TreeNode root = completeByNodeCount(1_024);
        int expected = expectedPairs(root, 10);

        assertEquals(expected, new CountPairs_1530().countPairs(root, 10));
    }

    @Test
    public void independentInstancesDoNotShareState() {
        TreeNode root = completeTree(4);

        int expected = expectedPairs(root, 5);
        assertEquals(expected, new CountPairs_1530().countPairs(root, 5));
        assertEquals(expected, new CountPairs_1530().countPairs(root, 5));
    }

    private static TreeNode twoLeafTree(int rootValue, int leftValue, int rightValue) {
        TreeNode root = new TreeNode(rootValue);
        root.left = new TreeNode(leftValue);
        root.right = new TreeNode(rightValue);
        return root;
    }

    /** Builds a root-to-leaf chain with {@code chainEdges} edges and a root sibling leaf. */
    private static TreeNode chainWithRightLeaf(int chainEdges, boolean useRightChain) {
        TreeNode root = new TreeNode(0);
        TreeNode cursor = root;
        for (int edge = 0; edge < chainEdges; edge++) {
            TreeNode child = new TreeNode(edge + 1);
            if (useRightChain) {
                cursor.right = child;
            } else {
                cursor.left = child;
            }
            cursor = child;
        }
        if (useRightChain) {
            root.left = new TreeNode(10_001);
        } else {
            root.right = new TreeNode(10_001);
        }
        return root;
    }

    private static TreeNode completeTree(int levels) {
        if (levels == 0) {
            return null;
        }
        TreeNode root = new TreeNode(1);
        root.left = completeTree(levels - 1);
        root.right = completeTree(levels - 1);
        return root;
    }

    private static TreeNode completeByNodeCount(int nodeCount) {
        List<TreeNode> nodes = new ArrayList<>(nodeCount);
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(new TreeNode((i % 100) + 1));
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

    private static TreeNode randomTree(int nodeCount, Random random) {
        TreeNode root = new TreeNode(1 + random.nextInt(100));
        List<TreeNode> available = new ArrayList<>();
        available.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(available.size());
            TreeNode parent = available.get(parentIndex);
            TreeNode child = new TreeNode(1 + random.nextInt(100));
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
            available.add(child);
            if (parent.left != null && parent.right != null) {
                available.remove(parentIndex);
            }
        }
        return root;
    }

    private static List<TreeNode> allShapes(int nodes) {
        List<TreeNode> result = new ArrayList<>();
        if (nodes == 0) {
            result.add(null);
            return result;
        }
        for (int leftNodes = 0; leftNodes < nodes; leftNodes++) {
            int rightNodes = nodes - 1 - leftNodes;
            for (TreeNode left : allShapes(leftNodes)) {
                for (TreeNode right : allShapes(rightNodes)) {
                    TreeNode root = new TreeNode(nodes);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    /** Counts pairs by building the undirected leaf path relation independently of the DFS solution. */
    private static int expectedPairs(TreeNode root, int distance) {
        if (root == null || distance <= 0) {
            return 0;
        }
        List<TreeNode> leaves = new ArrayList<>();
        Map<TreeNode, TreeNode> parents = new IdentityHashMap<>();
        collectLeaves(root, null, parents, leaves);
        int pairs = 0;
        for (int first = 0; first < leaves.size(); first++) {
            for (int second = first + 1; second < leaves.size(); second++) {
                if (pathDistance(leaves.get(first), leaves.get(second), parents) <= distance) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    private static void collectLeaves(TreeNode node, TreeNode parent,
            Map<TreeNode, TreeNode> parents, List<TreeNode> leaves) {
        if (node == null) {
            return;
        }
        parents.put(node, parent);
        if (node.left == null && node.right == null) {
            leaves.add(node);
            return;
        }
        collectLeaves(node.left, node, parents, leaves);
        collectLeaves(node.right, node, parents, leaves);
    }

    private static int pathDistance(TreeNode first, TreeNode second, Map<TreeNode, TreeNode> parents) {
        Map<TreeNode, Integer> firstAncestors = new IdentityHashMap<>();
        int distance = 0;
        for (TreeNode node = first; node != null; node = parents.get(node)) {
            firstAncestors.put(node, distance++);
        }
        distance = 0;
        for (TreeNode node = second; node != null; node = parents.get(node)) {
            Integer firstDistance = firstAncestors.get(node);
            if (firstDistance != null) {
                return firstDistance + distance;
            }
            distance++;
        }
        throw new AssertionError("Leaves must have a common root");
    }

    private static void assignValues(TreeNode root, int[] values) {
        List<TreeNode> nodes = new ArrayList<>();
        collectNodes(root, nodes);
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).val = values[i % values.length];
        }
    }

    private static void collectNodes(TreeNode node, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        collectNodes(node.left, nodes);
        collectNodes(node.right, nodes);
    }

    private static String shape(TreeNode node) {
        if (node == null) {
            return "#";
        }
        return node.val + "(" + shape(node.left) + "," + shape(node.right) + ")";
    }
}
