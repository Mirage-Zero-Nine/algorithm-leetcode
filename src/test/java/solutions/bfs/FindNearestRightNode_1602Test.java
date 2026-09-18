package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

public class FindNearestRightNode_1602Test {

    private final FindNearestRightNode_1602 test = new FindNearestRightNode_1602();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); TreeNode n3 = new TreeNode(3);
        root.left = n2; root.right = n3;
        assertEquals(3, test.findNearestRightNode(root, n2).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertNull(test.findNearestRightNode(root, n2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4); TreeNode n5 = new TreeNode(5);
        root.left = n2; root.right = n3;
        n2.left = n4; n2.right = n5;
        assertEquals(5, test.findNearestRightNode(root, n4).val);
    }

    @Test
    public void testRootHasNoRightNeighbor() {
        TreeNode root = new TreeNode(10);
        assertNull(test.findNearestRightNode(root, root));
    }

    @Test
    public void testRightNeighborAtSecondLevel() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.left = n2;
        root.right = n3;
        assertEquals(3, test.findNearestRightNode(root, n2).val);
    }

    @Test
    public void testRightMostAtSecondLevelReturnsNull() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.left = n2;
        root.right = n3;
        assertNull(test.findNearestRightNode(root, n3));
    }

    @Test
    public void testSparseTreeSameLevelNeighbor() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        root.left = n2; root.right = n3;
        n2.right = n5; n3.right = n7;
        assertEquals(7, test.findNearestRightNode(root, n5).val);
    }

    @Test
    public void testLastNodeInLevelReturnsNull() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        root.left = n2; root.right = n3;
        n2.left = n4;
        assertNull(test.findNearestRightNode(root, n4));
    }

    @Test
    public void testTargetReturnsDistinctRightNode() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        assertSame(right, test.findNearestRightNode(root, left));
    }

    @Test
    public void testRightmostTargetUsesNodeIdentity() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        assertNull(test.findNearestRightNode(root, right));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("representativeTrees")
    public void testRepresentativeTreesUseIndependentLevelOrderOracle(
            String name, Integer[] values, int targetPosition) {
        Fixture fixture = Fixture.fromLevelOrder(values);
        assertNotNull(fixture.nodes[targetPosition], name);
        Snapshot snapshot = Snapshot.capture(fixture.nodes);
        TreeNode expected = expectedNeighbor(fixture.root, fixture.nodes[targetPosition]);
        TreeNode actual = test.findNearestRightNode(fixture.root, fixture.nodes[targetPosition]);
        assertSame(expected, actual, name);
        snapshot.assertUnchanged();
    }

    private static Stream<Arguments> representativeTrees() {
        return Stream.of(
                Arguments.of("root only", new Integer[]{42}, 0),
                Arguments.of("root left child", new Integer[]{1, 2, 3}, 1),
                Arguments.of("root right child", new Integer[]{1, 2, 3}, 2),
                Arguments.of("official example target 4", new Integer[]{1, 2, 3, null, 4, 5, 6}, 4),
                Arguments.of("official example rightmost 6", new Integer[]{1, 2, 3, null, 4, 5, 6}, 6),
                Arguments.of("cross-parent gap", new Integer[]{1, 2, 3, null, 4, null, 5}, 4),
                Arguments.of("cross-parent final node", new Integer[]{1, 2, 3, null, 4, null, 5}, 6),
                Arguments.of("left chain", new Integer[]{1, 2, null, 3, null, 4, null, 5}, 3),
                Arguments.of("right chain", new Integer[]{1, null, 2, null, 3, null, 4, null, 5}, 4),
                Arguments.of("wide level target first", new Integer[]{10, 20, 30, 40, 50, 60, 70}, 3),
                Arguments.of("wide level middle", new Integer[]{10, 20, 30, 40, 50, 60, 70}, 4),
                Arguments.of("wide level last", new Integer[]{10, 20, 30, 40, 50, 60, 70}, 6),
                Arguments.of("sparse alternating children", new Integer[]{10, 11, 12, null, 13, null, 14, null, null, 15}, 4),
                Arguments.of("minimum and maximum values", new Integer[]{100000, 1, 99999, 2, null, 3}, 1),
                Arguments.of("deep sparse left subtree", new Integer[]{8, 4, 12, 2, null, null, 14, 1, null, null, 3}, 7),
                Arguments.of("deep sparse cross-parent neighbor", new Integer[]{8, 4, 12, 2, 6, 10, 14, null, 3, null, 7, 9}, 8),
                Arguments.of("only one node at target level", new Integer[]{1, 2, null, 3, null, 4}, 5),
                Arguments.of("several absent children before neighbor", new Integer[]{1, 2, 3, 4, null, null, 7, null, 5}, 3),
                Arguments.of("distinct values left occurrence", new Integer[]{5, 6, 7, null, 8, 9}, 1),
                Arguments.of("distinct values right occurrence", new Integer[]{5, 6, 7, null, 8, 9}, 2),
                Arguments.of("positive boundary values", new Integer[]{6, 4, 5, 1, null, 2}, 3),
                Arguments.of("complete depth four", new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 8),
                Arguments.of("complete depth four last", new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 14),
                Arguments.of("official second shape", new Integer[]{3, null, 4, 2}, 2)
        );
    }

    @Test
    public void testMaximumConstraintTree() {
        int nodeCount = 100_000;
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(i + 1);
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

        Snapshot snapshot = Snapshot.capture(nodes);
        TreeNode expected = expectedNeighbor(nodes[0], nodes[50_000]);
        assertSame(expected, test.findNearestRightNode(nodes[0], nodes[50_000]));
        assertNull(test.findNearestRightNode(nodes[0], nodes[nodeCount - 1]));
        snapshot.assertUnchanged();
    }

    private static TreeNode expectedNeighbor(TreeNode root, TreeNode target) {
        if (root == null || target == null) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<TreeNode> level = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();
                level.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            for (int i = 0; i < level.size(); i++) {
                if (level.get(i) == target) {
                    return i + 1 < level.size() ? level.get(i + 1) : null;
                }
            }
        }
        return null;
    }

    private record Fixture(TreeNode root, TreeNode[] nodes) {
        private static Fixture fromLevelOrder(Integer[] values) {
            if (values.length == 0 || values[0] == null) {
                throw new IllegalArgumentException("A representative fixture needs a root");
            }
            TreeNode[] nodes = new TreeNode[values.length];
            nodes[0] = new TreeNode(values[0]);
            Queue<Integer> pending = new ArrayDeque<>();
            pending.add(0);
            int next = 1;
            int connected = 1;
            while (!pending.isEmpty() && next < values.length) {
                int parentPosition = pending.remove();
                TreeNode parent = nodes[parentPosition];
                if (next < values.length && values[next] != null) {
                    parent.left = nodes[next] = new TreeNode(values[next]);
                    pending.add(next);
                    connected++;
                }
                next++;
                if (next < values.length && values[next] != null) {
                    parent.right = nodes[next] = new TreeNode(values[next]);
                    pending.add(next);
                    connected++;
                }
                next++;
            }
            int expectedConnected = 0;
            Set<Integer> uniqueValues = new HashSet<>();
            for (Integer value : values) {
                if (value != null) {
                    assertTrue(value >= 1 && value <= 100_000,
                            "Node.val must be within the official range: " + value);
                    assertTrue(uniqueValues.add(value), "official fixtures require distinct node values: " + value);
                    expectedConnected++;
                }
            }
            assertEquals(expectedConnected, connected, "fixture contains an orphaned non-null token");
            return new Fixture(nodes[0], nodes);
        }
    }

    private record Snapshot(TreeNode[] nodes, int[] values, TreeNode[] left, TreeNode[] right) {
        private static Snapshot capture(TreeNode[] nodes) {
            int[] values = new int[nodes.length];
            TreeNode[] left = new TreeNode[nodes.length];
            TreeNode[] right = new TreeNode[nodes.length];
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    values[i] = nodes[i].val;
                    left[i] = nodes[i].left;
                    right[i] = nodes[i].right;
                }
            }
            return new Snapshot(nodes, values, left, right);
        }

        private void assertUnchanged() {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i] != null) {
                    assertEquals(values[i], nodes[i].val, "solution changed node value at position " + i);
                    assertSame(left[i], nodes[i].left, "solution changed left child at position " + i);
                    assertSame(right[i], nodes[i].right, "solution changed right child at position " + i);
                }
            }
        }
    }

    @Test
    public void testGiantCompleteTree() {
        TreeNode[] nodes = new TreeNode[128];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= 63; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }
        TreeNode root = nodes[1];
        assertEquals(65, test.findNearestRightNode(root, nodes[64]).val);
        assertNull(test.findNearestRightNode(root, nodes[127]));
    }
}
