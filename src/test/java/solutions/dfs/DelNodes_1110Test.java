package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Contract and mutation tests for {@link DelNodes_1110}. */
public class DelNodes_1110Test {

    @Test
    public void officialExampleOne() {
        assertForestMatches(fullSevenNodeTree(), new int[]{3, 5}, new DelNodes_1110());
    }

    @Test
    public void officialExampleTwo() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        assertForestMatches(root, new int[]{3}, new DelNodes_1110());
    }

    @Test
    public void deleteNothingRetainsOriginalTreeAndIdentity() {
        TreeNode root = fullSevenNodeTree();
        TreeNode left = root.left;
        assertForestMatches(root, new int[]{}, new DelNodes_1110());
        assertSame(left, root.left);
        assertEquals(List.of(serialize(root)), forestShape(List.of(root)));
    }

    @Test
    public void deleteEveryNodeReturnsEmptyForest() {
        assertForestMatches(fullSevenNodeTree(), new int[]{1, 2, 3, 4, 5, 6, 7}, new DelNodes_1110());
    }

    @Test
    public void nullRootReturnsEmptyForest() {
        assertEquals(List.of(), new DelNodes_1110().delNodes(null, new int[]{1, 2}));
    }

    @Test
    public void singletonIsRetainedWhenNotDeleted() {
        TreeNode root = new TreeNode(42);
        List<TreeNode> forest = new DelNodes_1110().delNodes(root, new int[]{7});
        assertEquals(List.of(42), rootValues(forest));
        assertSame(root, forest.get(0));
    }

    @Test
    public void singletonIsDeleted() {
        assertForestMatches(new TreeNode(42), new int[]{42}, new DelNodes_1110());
    }

    @Test
    public void deletingLeavesPreservesTheirParentBranches() {
        TreeNode root = fullSevenNodeTree();
        assertForestMatches(root, new int[]{4, 7}, new DelNodes_1110());
        assertEquals(null, root.left.left);
        assertEquals(null, root.right.right);
        assertEquals(2, root.left.val);
        assertEquals(6, root.right.left.val);
    }

    @Test
    public void deletingInternalNodeWithTwoChildrenCreatesTwoRoots() {
        TreeNode root = fullSevenNodeTree();
        assertForestMatches(root, new int[]{2}, new DelNodes_1110());
        assertEquals(null, root.left);
    }

    @Test
    public void deletingInternalNodeWithOneChildPromotesOnlyChild() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        TreeNode promoted = root.left.right;
        List<TreeNode> forest = new DelNodes_1110().delNodes(root, new int[]{2});
        assertEquals(Set.of(1, 3), new HashSet<>(rootValues(forest)));
        assertTrue(forest.contains(promoted));
    }

    @Test
    public void deletingRootPromotesBothChildren() {
        TreeNode root = fullSevenNodeTree();
        TreeNode left = root.left;
        TreeNode right = root.right;
        List<TreeNode> forest = new DelNodes_1110().delNodes(root, new int[]{1});
        assertEquals(Set.of(2, 3), new HashSet<>(rootValues(forest)));
        assertTrue(forest.contains(left));
        assertTrue(forest.contains(right));
    }

    @Test
    public void deletingParentAndChildDoesNotPromoteDeletedChild() {
        TreeNode root = fullSevenNodeTree();
        assertForestMatches(root, new int[]{2, 4}, new DelNodes_1110());
        assertEquals(Set.of(1, 5), new HashSet<>(rootValues(new DelNodes_1110().delNodes(fullSevenNodeTree(), new int[]{2, 4}))));
    }

    @Test
    public void deletingSeveralAncestorsAndDescendantsProducesCorrectForest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);
        assertForestMatches(root, new int[]{1, 4, 8}, new DelNodes_1110());
    }

    @Test
    public void absentDeleteValuesLeaveAllNodesReachable() {
        assertForestMatches(fullSevenNodeTree(), new int[]{8, 999, 1000}, new DelNodes_1110());
    }

    @Test
    public void duplicateDeleteValuesAreHarmless() {
        assertForestMatches(fullSevenNodeTree(), new int[]{3, 3, 5, 5}, new DelNodes_1110());
    }

    @Test
    public void deletingThreeTopLevelsLeavesEveryGrandchildAsRoot() {
        List<TreeNode> forest = new DelNodes_1110().delNodes(fullSevenNodeTree(), new int[]{1, 2, 3});
        assertEquals(Set.of(4, 5, 6, 7), new HashSet<>(rootValues(forest)));
        assertEquals(4, forest.size());
    }

    @Test
    public void retainedNodesKeepIdentityAndDeletedLinksAreDetached() {
        TreeNode root = fullSevenNodeTree();
        TreeNode retainedFour = root.left.left;
        TreeNode retainedSix = root.right.left;
        List<TreeNode> forest = new DelNodes_1110().delNodes(root, new int[]{2, 3});
        assertTrue(forest.contains(retainedFour));
        assertTrue(forest.contains(retainedSix));
        assertEquals(null, root.left);
        assertEquals(null, root.right);
    }

    @Test
    public void nonpositiveAndNegativeValuesAreHandledByTheClass() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(Integer.MIN_VALUE);
        root.left.left = new TreeNode(Integer.MAX_VALUE);
        assertForestMatches(root, new int[]{-1, Integer.MIN_VALUE}, new DelNodes_1110());
    }

    @Test
    public void problemValueBoundsAreHandled() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1000);
        root.right = new TreeNode(999);
        assertForestMatches(root, new int[]{1000}, new DelNodes_1110());
    }

    @Test
    public void repeatedCallsClearPreviousDeletionState() {
        DelNodes_1110 solver = new DelNodes_1110();
        assertForestMatches(fullSevenNodeTree(), new int[]{2, 3}, solver);
        TreeNode second = new TreeNode(10);
        second.left = new TreeNode(11);
        assertForestMatches(second, new int[]{}, solver);
    }

    @Test
    public void aNullCallAfterADeletedTreeClearsPreviousResults() {
        DelNodes_1110 solver = new DelNodes_1110();
        assertForestMatches(new TreeNode(1), new int[]{}, solver);
        assertEquals(List.of(), solver.delNodes(null, new int[]{}));
    }

    @Test
    public void aFreshSolverDoesNotShareForestState() {
        List<TreeNode> firstForest = new DelNodes_1110().delNodes(new TreeNode(1), new int[]{});
        List<TreeNode> secondForest = new DelNodes_1110().delNodes(new TreeNode(2), new int[]{});
        assertEquals(List.of(1), rootValues(firstForest));
        assertEquals(List.of(2), rootValues(secondForest));
    }

    @Test
    public void deletingRootLeavesPromotedSubtreesIntact() {
        List<TreeNode> forest = new DelNodes_1110().delNodes(fullSevenNodeTree(), new int[]{1});
        TreeNode rootTwo = findRoot(forest, 2);
        TreeNode rootThree = findRoot(forest, 3);
        assertEquals(4, rootTwo.left.val);
        assertEquals(7, rootThree.right.val);
    }

    @Test
    public void generatedSmallTreesMatchIndependentParentOracle() {
        for (int n = 1; n <= 40; n++) {
            TreeNode root = new TreeNode(1);
            TreeNode[] nodes = new TreeNode[n];
            nodes[0] = root;
            for (int i = 1; i < n; i++) {
                nodes[i] = new TreeNode(i + 1);
                TreeNode parent = nodes[(i - 1) / 2];
                if (i % 2 == 1) {
                    parent.left = nodes[i];
                } else {
                    parent.right = nodes[i];
                }
            }
            List<Integer> deleted = new ArrayList<>();
            for (int value = 1; value <= n; value++) {
                if ((value + n) % 6 == 0) {
                    deleted.add(value);
                }
            }
            assertForestMatches(root, deleted.stream().mapToInt(Integer::intValue).toArray(), new DelNodes_1110());
        }
    }

    @Test
    public void maximumOneThousandNodeChainMatchesOracle() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= 1000; value++) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        int[] deleted = new int[333];
        for (int i = 0; i < deleted.length; i++) {
            deleted[i] = 3 * (i + 1);
        }
        assertForestMatches(root, deleted, new DelNodes_1110());
    }

    @Test
    public void maximumOneThousandNodeCompleteTreeMatchesOracle() {
        TreeNode root = completeTree(1000);
        int[] deleted = new int[100];
        for (int i = 0; i < deleted.length; i++) {
            deleted[i] = 10 * (i + 1);
        }
        assertForestMatches(root, deleted, new DelNodes_1110());
    }

    @Test
    public void deletingAllLeavesOfACompleteTreeLeavesOnlyInternalNodes() {
        TreeNode root = completeTree(15);
        assertForestMatches(root, new int[]{8, 9, 10, 11, 12, 13, 14, 15}, new DelNodes_1110());
    }

    @Test
    public void oneSidedTreesPromoteTheOnlyChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertForestMatches(root, new int[]{1, 3}, new DelNodes_1110());
    }

    @Test
    public void deletionSetCanContainEveryValidValueWithoutAnIssue() {
        TreeNode root = completeTree(1000);
        int[] deleted = new int[1000];
        for (int i = 0; i < deleted.length; i++) {
            deleted[i] = i + 1;
        }
        assertForestMatches(root, deleted, new DelNodes_1110());
    }

    private static TreeNode fullSevenNodeTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }

    private static TreeNode completeTree(int size) {
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(i + 1);
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

    private static void assertForestMatches(TreeNode root, int[] toDelete, DelNodes_1110 solver) {
        Map<Integer, Snapshot> snapshot = snapshot(root);
        Set<Integer> deleted = new HashSet<>();
        for (int value : toDelete) {
            deleted.add(value);
        }
        List<String> expected = expectedForest(snapshot, root == null ? null : root.val, deleted);
        List<TreeNode> actual = solver.delNodes(root, toDelete);
        assertEquals(expected, forestShape(actual));
        for (TreeNode tree : actual) {
            assertNotNull(tree);
            assertFalse(deleted.contains(tree.val));
        }
    }

    private static Map<Integer, Snapshot> snapshot(TreeNode root) {
        Map<Integer, Snapshot> nodes = new HashMap<>();
        if (root == null) {
            return nodes;
        }
        ArrayDeque<TreeNode> pending = new ArrayDeque<>();
        pending.push(root);
        while (!pending.isEmpty()) {
            TreeNode node = pending.pop();
            nodes.put(node.val, new Snapshot(node.val,
                    node.left == null ? null : node.left.val,
                    node.right == null ? null : node.right.val));
            if (node.right != null) {
                pending.push(node.right);
            }
            if (node.left != null) {
                pending.push(node.left);
            }
        }
        return nodes;
    }

    private static List<String> expectedForest(Map<Integer, Snapshot> nodes, Integer root, Set<Integer> deleted) {
        if (root == null) {
            return List.of();
        }
        Map<Integer, Integer> parent = new HashMap<>();
        for (Snapshot node : nodes.values()) {
            if (node.left != null) {
                parent.put(node.left, node.val);
            }
            if (node.right != null) {
                parent.put(node.right, node.val);
            }
        }
        List<String> result = new ArrayList<>();
        for (Snapshot node : nodes.values()) {
            Integer p = parent.get(node.val);
            if (!deleted.contains(node.val) && (p == null || deleted.contains(p))) {
                result.add(expectedShape(node.val, nodes, deleted));
            }
        }
        Collections.sort(result);
        return result;
    }

    private static String expectedShape(int value, Map<Integer, Snapshot> nodes, Set<Integer> deleted) {
        Snapshot node = nodes.get(value);
        String left = node.left == null || deleted.contains(node.left)
                ? "#" : expectedShape(node.left, nodes, deleted);
        String right = node.right == null || deleted.contains(node.right)
                ? "#" : expectedShape(node.right, nodes, deleted);
        return node.val + "(" + left + "," + right + ")";
    }

    private static List<String> forestShape(List<TreeNode> forest) {
        List<String> result = new ArrayList<>();
        for (TreeNode root : forest) {
            result.add(serialize(root));
        }
        Collections.sort(result);
        return result;
    }

    private static String serialize(TreeNode node) {
        if (node == null) {
            return "#";
        }
        return node.val + "(" + serialize(node.left) + "," + serialize(node.right) + ")";
    }

    private static List<Integer> rootValues(List<TreeNode> forest) {
        List<Integer> values = new ArrayList<>();
        for (TreeNode node : forest) {
            values.add(node.val);
        }
        return values;
    }

    private static TreeNode findRoot(List<TreeNode> forest, int value) {
        for (TreeNode node : forest) {
            if (node.val == value) {
                return node;
            }
        }
        throw new AssertionError("missing root " + value);
    }

    private record Snapshot(int val, Integer left, Integer right) {
    }
}
