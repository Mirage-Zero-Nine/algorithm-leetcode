package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

/** Contract and regression tests for {@link FindClosestLeaf_742}. */
public class FindClosestLeaf_742Test {

    @ParameterizedTest(name = "{0}")
    @MethodSource("independentContractCases")
    void independentContractCasesRemainCovered(String name, Integer[] encoding, int target) {
        assertValidAnswer(name, encoding, target);
    }

    /** Separate Surefire invocations for representative leaf, parent, tie, and boundary paths. */
    private static Stream<Arguments> independentContractCases() {
        return Stream.of(
                Arguments.of("singleton", new Integer[]{7}, 7),
                Arguments.of("left child", new Integer[]{8, 3}, 8),
                Arguments.of("right child", new Integer[]{8, null, 3}, 8),
                Arguments.of("root with two leaves", new Integer[]{8, 3, 10}, 8),
                Arguments.of("left leaf target", new Integer[]{8, 3, 10}, 3),
                Arguments.of("right leaf target", new Integer[]{8, 3, 10}, 10),
                Arguments.of("internal descendant", new Integer[]{8, 3, 10, 1, 5}, 3),
                Arguments.of("parent-side leaf", new Integer[]{8, 3, 10, 1, 5}, 10),
                Arguments.of("deep left path", new Integer[]{8, 3, 10, 1, null, null, null, 0}, 3),
                Arguments.of("deep right path", new Integer[]{8, 3, 10, null, null, null, 12}, 10),
                Arguments.of("ancestor and descendant tie", new Integer[]{8, 3, 10, 1, null, null, null, 0}, 3),
                Arguments.of("sparse right branch", new Integer[]{8, null, 10, null, 12, 11}, 10),
                Arguments.of("sparse left branch", new Integer[]{8, 3, null, 2, null, 1}, 3),
                Arguments.of("balanced root", new Integer[]{50, 25, 75, 12, 37, 62, 87}, 50),
                Arguments.of("balanced internal", new Integer[]{50, 25, 75, 12, 37, 62, 87}, 25),
                Arguments.of("balanced leaf", new Integer[]{50, 25, 75, 12, 37, 62, 87}, 12),
                Arguments.of("minimum value", new Integer[]{1000, 1, 999}, 1),
                Arguments.of("maximum value", new Integer[]{1, 2, 1000}, 1000),
                Arguments.of("negative-looking distance", new Integer[]{500, 1, 1000, 2}, 500),
                Arguments.of("uneven branches", new Integer[]{20, 10, 30, null, 15, 25, null, null, 17}, 15));
    }

    @Test
    void officialShapeAndSmallBoundaryCases() {
        List<Case> cases = List.of(
                new Case("single node", new Integer[]{1}, 1),
                new Case("two nodes left", new Integer[]{1, 2}, 1),
                new Case("two nodes right", new Integer[]{1, null, 2}, 1),
                new Case("historical three-node chain", new Integer[]{1, 2, null, 3}, 1),
                new Case("root has two leaves", new Integer[]{1, 2, 3}, 1),
                new Case("target is left leaf", new Integer[]{1, 2, 3}, 2),
                new Case("target is right leaf", new Integer[]{1, 2, 3}, 3),
                new Case("historical four-node root target", new Integer[]{1, 2, 3, 4}, 1),
                new Case("historical four-node internal target", new Integer[]{1, 2, null, 3, 4}, 2),
                new Case("target has two equally close leaves", new Integer[]{1, 2, 3, 4, 5}, 2),
                new Case("official example shape", new Integer[]{1, 3, 2}, 1),
                new Case("target leaf in deep left branch", new Integer[]{1, 2, 3, 4, 5, null, null}, 4),
                new Case("target internal with descendant leaf", new Integer[]{1, 2, 3, 4, null, null, 6}, 2),
                new Case("historical target two with leaf four", new Integer[]{1, 2, 3, 4}, 2),
                new Case("historical deep parent path", new Integer[]{1, 2, 3, 4, null, null, null, 5}, 4),
                new Case("descendant leaf is closest", new Integer[]{1, 2, 3, 4, null, null, 6, 5}, 4),
                new Case("another descendant leaf is closest", new Integer[]{1, 2, 3, 4, null, null, 6, 5}, 2),
                new Case("right-heavy sparse tree", new Integer[]{1, null, 2, null, 3, 4}, 3),
                new Case("left-heavy sparse tree", new Integer[]{1, 2, null, 3, null, 4}, 2),
                new Case("complete tree target root", new Integer[]{10, 5, 15, 3, 7, 12, 20}, 10),
                new Case("complete tree target internal", new Integer[]{10, 5, 15, 3, 7, 12, 20}, 5),
                new Case("complete tree target deep leaf", new Integer[]{10, 5, 15, 3, 7, 12, 20}, 3),
                new Case("uneven branch tie", new Integer[]{8, 4, 12, 2, 6, null, 14, 1, null, 5, 7}, 4),
                new Case("descendant leaf beats distant ancestor", new Integer[]{8, 4, 12, 2, 6, null, 14, 1}, 2),
                new Case("target near far-right leaf", new Integer[]{8, 4, 12, 2, 6, null, 14, 1}, 12),
                new Case("sparse mixed branches", new Integer[]{20, 10, 30, null, 15, 25, null, null, 17}, 15),
                new Case("target is only leaf", new Integer[]{9, 4, null, 2}, 2),
                new Case("zig-zag branch", new Integer[]{1, 2, null, null, 3, null, 4}, 2),
                new Case("tie among three leaves", new Integer[]{1, 2, 3, 4, 5, 6, 7}, 1));

        for (Case testCase : cases) {
            assertValidAnswer(testCase.name(), testCase.tree(), testCase.target());
        }
    }

    @Test
    void targetedTreesExerciseBothDirectionsAndTieHandling() {
        assertValidAnswer("deep target with descendant", new Integer[]{50, 20, 80, 10, 30, 70, 90, 5, null, null, 35}, 20);
        assertValidAnswer("descendant is closer than ancestor", new Integer[]{50, 20, 80, 10, 30, 70, 90, 5, null, null, 35}, 10);
        assertValidAnswer("right descendant is closest", new Integer[]{50, 20, 80, 10, 30, 70, 90, null, 15}, 10);
        assertValidAnswer("left descendant is closest", new Integer[]{50, 20, 80, 10, 30, 70, 90, 65}, 70);
        assertValidAnswer("ancestor-side leaf beats deep descendant", new Integer[]{1, 2, 3, 4, null, null, null, 5, null, 6, null, 7}, 2);
        assertValidAnswer("ancestor and descendant leaves tie", new Integer[]{1, 2, 3, 4, null, null, null, 5, null, 6, null, 7}, 4);
        assertValidAnswer("two leaves at equal distance", new Integer[]{50, 20, 80, 10, 30, 70, 90}, 50);
        assertValidAnswer("target is leaf among complete tree", new Integer[]{50, 20, 80, 10, 30, 70, 90}, 90);
        assertValidAnswer("one-sided internal target", new Integer[]{50, 20, null, 10, null, 5}, 20);
        assertValidAnswer("alternating sparse path", new Integer[]{50, 20, null, null, 30, null, 40}, 20);
    }

    @Test
    void valueBoundariesAndAdversarialShapesMatchTheIndependentOracle() {
        List<Case> cases = List.of(
                new Case("minimum and maximum values at leaves", new Integer[]{500, 1, 1000}, 500),
                new Case("minimum value is the target leaf", new Integer[]{1000, 1, 999, null, 2}, 1),
                new Case("maximum value is an internal target", new Integer[]{1, 2, 1000, 3, null, 999}, 1000),
                new Case("maximum-value root on a long right path", new Integer[]{1000, null, 999, null, 998, null, 1}, 1000),
                new Case("minimum-value root with two deep branches", new Integer[]{1, 500, 1000, 250, null, null, 750, 125}, 1),
                new Case("alternating maximum-depth branches", new Integer[]{500, 250, 750, null, 300, 700, null, null, 350, null, null, 650}, 300));

        for (Case testCase : cases) {
            assertValidAnswer(testCase.name(), testCase.tree(), testCase.target());
        }
    }

    @Test
    void seededArbitraryTreesMatchIndependentParentMapOracle() {
        Random random = new Random(742_2026L);
        FindClosestLeaf_742 solver = new FindClosestLeaf_742();
        int checkedInputs = 0;

        // The shape is generated independently of the solver, and every generated
        // target is a real node. This exercises child, parent, sibling, and tie paths.
        for (int treeNumber = 0; treeNumber < 40; treeNumber++) {
            final int treeIndex = treeNumber;
            int size = 1 + random.nextInt(40);
            TreeNode root = randomTree(size, random);
            List<TreeNode> nodes = nodes(root);
            int targets = Math.min(nodes.size(), 8);
            for (int i = 0; i < targets; i++) {
                TreeNode target = nodes.get((i * 13 + treeNumber) % nodes.size());
                Expected expected = oracle(root, target.val);
                int actual = solver.findClosestLeaf(root, target.val);
                assertTrue(expected.leaves().contains(actual),
                        () -> "tree " + treeIndex + ", target " + target.val
                                + ": returned " + actual + ", expected " + expected.leaves());
                assertEquals(expected.distance(), distanceToLeaf(root, target.val, actual));
                checkedInputs++;
            }
        }

        assertTrue(checkedInputs >= 200);
    }

    @Test
    void solvingDoesNotMutateValuesOrChildTopology() {
        TreeNode root = tree(new Integer[]{500, 200, 800, 100, 300, 700, 1000, null, 150, 250, null, 650});
        List<TreeNode> beforeNodes = nodes(root);
        Map<TreeNode, TreeNode[]> beforeChildren = new IdentityHashMap<>();
        Map<TreeNode, Integer> beforeValues = new IdentityHashMap<>();
        for (TreeNode node : beforeNodes) {
            beforeChildren.put(node, new TreeNode[]{node.left, node.right});
            beforeValues.put(node, node.val);
        }

        FindClosestLeaf_742 solver = new FindClosestLeaf_742();
        assertValidAnswer("non-mutating target", root, 300, solver);
        assertValidAnswer("non-mutating root", root, 500, solver);

        assertEquals(beforeNodes.size(), nodes(root).size());
        for (TreeNode node : beforeNodes) {
            assertEquals(beforeValues.get(node), node.val);
            assertSame(beforeChildren.get(node)[0], node.left);
            assertSame(beforeChildren.get(node)[1], node.right);
        }
    }

    @Test
    void nullRootIsTheDocumentedImplementationGuard() {
        assertEquals(-1, new FindClosestLeaf_742().findClosestLeaf(null, 123));
    }

    @Test
    void maximumSizedSkewedTreeAndRepeatedCalls() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= 1000; value++) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        assertEquals(1000, new FindClosestLeaf_742().findClosestLeaf(root, 500));
        assertEquals(1000, new FindClosestLeaf_742().findClosestLeaf(root, 1));

        TreeNode historical = new TreeNode(1);
        historical.right = new TreeNode(2);
        current = historical;
        for (int value = 3; value <= 100; value++) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        assertEquals(2, new FindClosestLeaf_742().findClosestLeaf(historical, 1));

        // Reusing one solver across A-B-A catches accidental state retained between calls.
        FindClosestLeaf_742 solver = new FindClosestLeaf_742();
        TreeNode first = tree(new Integer[]{100, 40, 160, 20, 60, 140, 180});
        TreeNode second = tree(new Integer[]{200, 100, 300, 50, 150, 250, 350});
        assertTrue(oracle(first, 40).leaves().contains(solver.findClosestLeaf(first, 40)));
        assertTrue(oracle(second, 200).leaves().contains(solver.findClosestLeaf(second, 200)));
        assertTrue(oracle(first, 40).leaves().contains(solver.findClosestLeaf(first, 40)));
    }

    @Test
    void targetAtEachRoleInBalancedTree() {
        Integer[] shape = {50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93};
        for (int target : new int[]{50, 25, 75, 12, 37, 62, 87, 6, 18, 31, 43, 56, 68, 81, 93}) {
            assertValidAnswer("balanced target " + target, shape, target);
        }
    }

    @Test
    void sparseRightBranchesUseParentEdges() {
        assertValidAnswer("right then left", new Integer[]{10, null, 20, null, 30, 25, null, 24}, 30);
        assertValidAnswer("right target to left leaf", new Integer[]{10, null, 20, 15, 30, null, 16}, 20);
        assertValidAnswer("deep right leaf target", new Integer[]{10, null, 20, null, 30, null, 40}, 30);
    }

    @Test
    void minimumDistanceIsMeasuredInEdgesNotValues() {
        assertValidAnswer("large value adjacent", new Integer[]{1, 1000, 2, null, null, 3}, 2);
        assertValidAnswer("small value far away", new Integer[]{1000, 2, 999, 1}, 1000);
        assertValidAnswer("signed-looking implementation values", new Integer[]{500, 1, 1000, 2, null, null, 999}, 500);
    }

    @Test
    void maximumValueTargetAndLeafBoundaries() {
        assertValidAnswer("max root", new Integer[]{1000, 999, 998, 1, 2, 3, 4}, 1000);
        assertValidAnswer("max leaf", new Integer[]{1, 2, 1000}, 1000);
        assertValidAnswer("min leaf", new Integer[]{1000, 1, 999}, 1);
    }

    @Test
    void repeatedSameTargetOnSameTreeRemainsStable() {
        TreeNode root = tree(new Integer[]{40, 20, 60, 10, 30, 50, 70, 5, 15, 25, 35});
        FindClosestLeaf_742 solver = new FindClosestLeaf_742();
        int first = solver.findClosestLeaf(root, 20);
        int second = solver.findClosestLeaf(root, 20);
        assertEquals(first, second);
        assertTrue(oracle(root, 20).leaves().contains(first));
    }

    @Test
    void nullAndSingletonGuardsRemainIndependent() {
        FindClosestLeaf_742 solver = new FindClosestLeaf_742();
        assertEquals(-1, solver.findClosestLeaf(null, 1));
        assertEquals(77, solver.findClosestLeaf(new TreeNode(77), 77));
        assertEquals(-1, solver.findClosestLeaf(null, 77));
    }

    private static void assertValidAnswer(String name, Integer[] encoding, int target) {
        TreeNode root = tree(encoding);
        assertValidAnswer(name, root, target, new FindClosestLeaf_742());
    }

    private static void assertValidAnswer(String name, TreeNode root, int target) {
        assertValidAnswer(name, root, target, new FindClosestLeaf_742());
    }

    private static void assertValidAnswer(String name, TreeNode root, int target, FindClosestLeaf_742 solver) {
        Expected expected = oracle(root, target);
        int actual = solver.findClosestLeaf(root, target);
        assertTrue(expected.leaves().contains(actual),
                () -> name + ": returned " + actual + ", expected one of minimum-distance leaves " + expected.leaves());
        assertEquals(expected.distance(), distanceToLeaf(root, target, actual), name);
    }

    /** Generates a valid binary tree with unique values in the official range. */
    private static TreeNode randomTree(int size, Random random) {
        int[] values = new int[1000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i + 1;
        }
        for (int i = 0; i < size; i++) {
            int swap = i + random.nextInt(values.length - i);
            int value = values[i];
            values[i] = values[swap];
            values[swap] = value;
        }

        TreeNode root = new TreeNode(values[0]);
        List<TreeNode> built = new ArrayList<>();
        built.add(root);
        for (int i = 1; i < size; i++) {
            TreeNode parent;
            do {
                parent = built.get(random.nextInt(built.size()));
            } while (parent.left != null && parent.right != null);
            TreeNode child = new TreeNode(values[i]);
            if (parent.left == null && (parent.right != null || random.nextBoolean())) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            built.add(child);
        }
        return root;
    }

    private static List<TreeNode> nodes(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            result.add(node);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }

    /** Builds a compact breadth-first encoding and rejects non-null orphan entries. */
    private static TreeNode tree(Integer[] values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < values.length) {
            TreeNode parent = queue.remove();
            if (index < values.length && values[index] != null) {
                parent.left = new TreeNode(values[index]);
                queue.add(parent.left);
            }
            index++;
            if (index < values.length && values[index] != null) {
                parent.right = new TreeNode(values[index]);
                queue.add(parent.right);
            }
            index++;
        }
        while (index < values.length) {
            assertTrue(values[index] == null, "tree encoding contains an unreachable non-null node");
            index++;
        }
        return root;
    }

    /** Independent undirected-tree oracle: graph distance from target to every leaf. */
    private static Expected oracle(TreeNode root, int target) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> discover = new ArrayDeque<>();
        discover.add(root);
        TreeNode targetNode = null;
        while (!discover.isEmpty()) {
            TreeNode node = discover.remove();
            if (node.val == target) {
                targetNode = node;
            }
            if (node.left != null) {
                parent.put(node.left, node);
                discover.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                discover.add(node.right);
            }
        }
        assertTrue(targetNode != null, "target must be a member of the tree");
        Queue<TreeNode> breadth = new ArrayDeque<>();
        Map<TreeNode, Integer> distances = new HashMap<>();
        breadth.add(targetNode);
        distances.put(targetNode, 0);
        int minimum = Integer.MAX_VALUE;
        Set<Integer> leaves = new HashSet<>();
        while (!breadth.isEmpty()) {
            TreeNode node = breadth.remove();
            int distance = distances.get(node);
            if (distance > minimum) {
                continue;
            }
            if (node.left == null && node.right == null) {
                minimum = distance;
                leaves.add(node.val);
            }
            List<TreeNode> neighbors = new ArrayList<>(3);
            if (node.left != null) neighbors.add(node.left);
            if (node.right != null) neighbors.add(node.right);
            if (parent.containsKey(node)) neighbors.add(parent.get(node));
            for (TreeNode neighbor : neighbors) {
                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, distance + 1);
                    breadth.add(neighbor);
                }
            }
        }
        return new Expected(minimum, leaves);
    }

    /** Computes the actual graph distance to the returned value, without the oracle's minimum pruning. */
    private static int distanceToLeaf(TreeNode root, int target, int leaf) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> discover = new ArrayDeque<>();
        discover.add(root);
        TreeNode targetNode = null;
        while (!discover.isEmpty()) {
            TreeNode node = discover.remove();
            if (node.val == target) targetNode = node;
            if (node.left != null) {
                parent.put(node.left, node);
                discover.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                discover.add(node.right);
            }
        }
        Queue<TreeNode> breadth = new ArrayDeque<>();
        Map<TreeNode, Integer> distance = new HashMap<>();
        breadth.add(targetNode);
        distance.put(targetNode, 0);
        while (!breadth.isEmpty()) {
            TreeNode node = breadth.remove();
            int d = distance.get(node);
            if (node.val == leaf && node.left == null && node.right == null) return d;
            List<TreeNode> neighbors = new ArrayList<>(3);
            if (node.left != null) neighbors.add(node.left);
            if (node.right != null) neighbors.add(node.right);
            if (parent.containsKey(node)) neighbors.add(parent.get(node));
            for (TreeNode neighbor : neighbors) {
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, d + 1);
                    breadth.add(neighbor);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private record Case(String name, Integer[] tree, int target) {}

    private record Expected(int distance, Set<Integer> leaves) {}
}
