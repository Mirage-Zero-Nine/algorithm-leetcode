package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.function.IntUnaryOperator;
import org.junit.jupiter.api.Test;

/** Tests the complete-tree contract and the implementation's documented null-root behavior. */
public class IsCompleteTree_958Test {

    private final IsCompleteTree_958 test = new IsCompleteTree_958();

    @Test
    public void testOfficialExampleWithSixNodesIsComplete() {
        TreeNode root = tree(1, 2, 3, 4, 5, 6);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testOfficialExampleWithGapBeforeLastNodeIsNotComplete() {
        TreeNode root = tree(1, 2, 3, 4, 5, null, 7);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testNullRootIsCompleteAsDocumentedByImplementation() {
        assertTrue(test.isCompleteTree(null));
    }

    @Test
    public void testSingleNodeAtValueBoundariesIsComplete() {
        assertTrue(test.isCompleteTree(new TreeNode(1)));
        assertTrue(test.isCompleteTree(new TreeNode(1000)));
    }

    @Test
    public void testSingleNodeWithJavaIntegerExtremesIsComplete() {
        assertTrue(test.isCompleteTree(new TreeNode(Integer.MIN_VALUE)));
        assertTrue(test.isCompleteTree(new TreeNode(Integer.MAX_VALUE)));
    }

    @Test
    public void testLeftChildOnlyIsComplete() {
        assertTrue(test.isCompleteTree(tree(1, 2)));
    }

    @Test
    public void testRightChildOnlyIsNotComplete() {
        assertFalse(test.isCompleteTree(tree(1, null, 3)));
    }

    @Test
    public void testMissingLeftChildWithRightSiblingIsNotComplete() {
        assertFalse(test.isCompleteTree(tree(1, 2, 3, null, 5)));
    }

    @Test
    public void testNodeAfterAnAbsentSiblingIsNotComplete() {
        assertFalse(test.isCompleteTree(tree(1, 2, 3, 4, null, 6)));
    }

    @Test
    public void testDeepRightmostNodeAfterEarlierGapIsNotComplete() {
        assertFalse(test.isCompleteTree(tree(1, 2, 3, 4, 5, 6, 7, 8, null, null, 11)));
    }

    @Test
    public void testLeftSkewedTreeBeyondTheFirstLevelIsNotComplete() {
        assertFalse(test.isCompleteTree(tree(1, 2, null, 3, null, 4, null, 5)));
    }

    @Test
    public void testRightSkewedTreeIsNotComplete() {
        assertFalse(test.isCompleteTree(tree(1, null, 2, null, 3, null, 4)));
    }

    @Test
    public void testPerfectTreesAtSeveralHeightsAreComplete() {
        for (int height = 0; height <= 6; height++) {
            int nodeCount = (1 << (height + 1)) - 1;
            assertTrue(test.isCompleteTree(completeTree(nodeCount, valueFor(nodeCount, 0))),
                    "height=" + height);
        }
    }

    @Test
    public void testEveryValidLastLevelCutoffThroughTheOfficialMaximum() {
        for (int nodeCount = 1; nodeCount <= 100; nodeCount++) {
            assertTrue(test.isCompleteTree(completeTree(nodeCount, valueFor(nodeCount, 0))),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void testEveryNonFinalLastLevelHoleThroughTheOfficialMaximumIsRejected() {
        for (int nodeCount = 3; nodeCount <= 100; nodeCount++) {
            TreeNode root = completeTree(nodeCount, valueFor(nodeCount, 0));
            int firstLastLevelIndex = Integer.highestOneBit(nodeCount) - 1;
            if (firstLastLevelIndex < nodeCount - 1) {
                removeLeafAt(root, firstLastLevelIndex);
                assertFalse(test.isCompleteTree(root), "nodeCount=" + nodeCount);
            }
        }
    }

    @Test
    public void testAllLastLevelNodesMayBeOnTheLeft() {
        for (int level = 0; level <= 6; level++) {
            int fullBeforeLast = (1 << level) - 1;
            for (int lastLevelNodes = 1; lastLevelNodes <= (1 << level); lastLevelNodes++) {
                int nodeCount = fullBeforeLast + lastLevelNodes;
                assertTrue(test.isCompleteTree(completeTree(nodeCount, valueFor(nodeCount, 1))),
                        "level=" + level + ", lastLevelNodes=" + lastLevelNodes);
            }
        }
    }

    @Test
    public void testNodeValuesDoNotAffectCompletenessIncludingDuplicatesAndNegatives() {
        TreeNode root = tree(0, -1, -1, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        assertTrue(test.isCompleteTree(root));
        root.right.left = null;
        root.right.right = new TreeNode(Integer.MIN_VALUE);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testCompleteTreePreservesValuesAndPointers() {
        TreeNode root = completeTree(31, i -> i == 0 ? Integer.MIN_VALUE :
                i == 30 ? Integer.MAX_VALUE : i * 17 - 250);
        Snapshot snapshot = Snapshot.capture(root);

        assertTrue(test.isCompleteTree(root));
        snapshot.assertUnchanged();
    }

    @Test
    public void testRepeatedCallsAndDifferentTreesDoNotShareState() {
        TreeNode complete = completeTree(63, valueFor(63, 10));
        TreeNode incomplete = tree(1, 2, 3, 4, null, 6, 7);
        Snapshot completeSnapshot = Snapshot.capture(complete);
        Snapshot incompleteSnapshot = Snapshot.capture(incomplete);
        assertTrue(test.isCompleteTree(complete));
        assertFalse(test.isCompleteTree(incomplete));
        assertTrue(test.isCompleteTree(complete));
        assertFalse(test.isCompleteTree(incomplete));
        completeSnapshot.assertUnchanged();
        incompleteSnapshot.assertUnchanged();
    }

    @Test
    public void testExhaustiveConnectedHeapShapesUpToTenPositions() {
        for (int mask = 1; mask < (1 << 10); mask++) {
            if ((mask & 1) == 0 || !hasConnectedParents(mask)) {
                continue;
            }
            TreeNode root = fromHeapMask(mask);
            assertEquals(heapOracle(root), test.isCompleteTree(root),
                    "mask=" + Integer.toBinaryString(mask));
        }
    }

    @Test
    public void testSeededArbitraryShapesAgainstIndependentHeapOracle() {
        for (int seed = 0; seed < 80; seed++) {
            int nodeCount = 1 + ((seed * 37) % 100);
            TreeNode[] nodes = seededTree(seed, nodeCount);
            assertEquals(heapOracle(nodes[0]), test.isCompleteTree(nodes[0]),
                    "seed=" + seed + ", nodeCount=" + nodeCount);
        }
    }

    @Test
    public void testMaximumOfficialNodeCountIsComplete() {
        TreeNode root = completeTree(100, i -> i == 0 ? 1 : 1000 - i);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testMaximumOfficialNodeCountWithLastLevelGapIsNotComplete() {
        TreeNode root = completeTree(100, valueFor(100, 0));
        removeLeafAt(root, 64);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testLargerThanOfficialMaximumCompleteTreeRemainsSupported() {
        assertTrue(test.isCompleteTree(completeTree(127, valueFor(127, 0))));
    }

    @Test
    public void testLargerThanOfficialMaximumSparseTreeIsRejected() {
        TreeNode root = completeTree(127, valueFor(127, 0));
        removeLeafAt(root, 63);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testIndependentOracleMatchesAllRepresentativeTrees() {
        List<TreeNode> trees = List.of(
                tree(1),
                tree(1, 2),
                tree(1, 2, 3),
                tree(1, 2, 3, 4, 5),
                tree(1, 2, 3, 4, 5, 6),
                tree(1, 2, 3, 4, 5, null, 7),
                tree(1, null, 3, null, 6),
                tree(1, 2, 3, null, 5, 6, null),
                tree(1, 2, null, 4, 5),
                tree(1, 2, 3, 4, null, 6, 7, 8));
        for (TreeNode root : trees) {
            assertEquals(heapOracle(root), test.isCompleteTree(root));
        }
    }

    private static IntUnaryOperator valueFor(int nodeCount, int offset) {
        return i -> 1 + Math.floorMod(nodeCount * 31 + offset + i, 1000);
    }

    private static TreeNode completeTree(int nodeCount, java.util.function.IntUnaryOperator values) {
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(values.applyAsInt(i));
        }
        for (int i = 0; i < nodeCount; i++) {
            int left = i * 2 + 1;
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

    private static TreeNode tree(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[values.length];
        nodes[0] = new TreeNode(values[0]);
        Queue<Integer> parents = new ArrayDeque<>();
        parents.add(0);
        int next = 1;
        while (!parents.isEmpty() && next < values.length) {
            int parentIndex = parents.remove();
            TreeNode parent = nodes[parentIndex];
            if (next < values.length && values[next] != null) {
                nodes[next] = new TreeNode(values[next]);
                parent.left = nodes[next];
                parents.add(next);
            }
            next++;
            if (next < values.length && values[next] != null) {
                nodes[next] = new TreeNode(values[next]);
                parent.right = nodes[next];
                parents.add(next);
            }
            next++;
        }
        if (next < values.length) {
            throw new IllegalArgumentException("level-order values contain an orphan");
        }
        return nodes[0];
    }

    private static void removeLeafAt(TreeNode root, int heapIndex) {
        if (heapIndex == 0) {
            throw new IllegalArgumentException("cannot remove root from a tree in place");
        }
        TreeNode parent = nodeAtHeapIndex(root, (heapIndex - 1) / 2);
        if ((heapIndex & 1) == 1) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    private static TreeNode nodeAtHeapIndex(TreeNode root, int target) {
        if (target == 0) {
            return root;
        }
        int highest = Integer.highestOneBit(target);
        TreeNode node = root;
        for (int bit = highest >> 1; bit > 0; bit >>= 1) {
            node = (target & bit) == 0 ? node.left : node.right;
        }
        return node;
    }

    /** A complete tree occupies heap positions 0 through nodeCount - 1 without a hole. */
    private static boolean heapOracle(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<IndexedNode> queue = new ArrayDeque<>();
        queue.add(new IndexedNode(root, 0));
        long nextExpectedIndex = 0;
        while (!queue.isEmpty()) {
            IndexedNode current = queue.remove();
            if (current.index != nextExpectedIndex++) {
                return false;
            }
            if (current.node.left != null) {
                queue.add(new IndexedNode(current.node.left, current.index * 2 + 1));
            }
            if (current.node.right != null) {
                queue.add(new IndexedNode(current.node.right, current.index * 2 + 2));
            }
        }
        return true;
    }

    private static TreeNode fromHeapMask(int mask) {
        TreeNode[] nodes = new TreeNode[10];
        for (int i = 0; i < nodes.length; i++) {
            if ((mask & (1 << i)) != 0) {
                nodes[i] = new TreeNode(i + 1);
            }
        }
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }
            TreeNode parent = nodes[(i - 1) / 2];
            if ((i & 1) == 1) {
                parent.left = nodes[i];
            } else {
                parent.right = nodes[i];
            }
        }
        return nodes[0];
    }

    private static boolean hasConnectedParents(int mask) {
        for (int i = 1; i < 10; i++) {
            if ((mask & (1 << i)) != 0 && (mask & (1 << ((i - 1) / 2))) == 0) {
                return false;
            }
        }
        return true;
    }

    private static TreeNode[] seededTree(int seed, int nodeCount) {
        Random random = new Random(seed);
        TreeNode[] nodes = new TreeNode[nodeCount];
        List<TreeNode> openParents = new ArrayList<>();
        nodes[0] = new TreeNode(seed == 0 ? Integer.MIN_VALUE : seed);
        openParents.add(nodes[0]);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(openParents.size());
            TreeNode parent = openParents.get(parentIndex);
            TreeNode child = new TreeNode(i % 2 == 0 ? Integer.MAX_VALUE : -i);
            nodes[i] = child;
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
            openParents.add(child);
            if (parent.left != null && parent.right != null) {
                openParents.remove(parentIndex);
            }
        }
        return nodes;
    }

    private record IndexedNode(TreeNode node, long index) {}

    private record Snapshot(List<TreeNode> nodes, int[] values, TreeNode[] left, TreeNode[] right) {
        static Snapshot capture(TreeNode root) {
            List<TreeNode> nodes = new ArrayList<>();
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                nodes.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            int[] values = new int[nodes.size()];
            TreeNode[] left = new TreeNode[nodes.size()];
            TreeNode[] right = new TreeNode[nodes.size()];
            for (int i = 0; i < nodes.size(); i++) {
                values[i] = nodes.get(i).val;
                left[i] = nodes.get(i).left;
                right[i] = nodes.get(i).right;
            }
            return new Snapshot(nodes, values, left, right);
        }

        void assertUnchanged() {
            for (int i = 0; i < nodes.size(); i++) {
                TreeNode node = nodes.get(i);
                assertEquals(values[i], node.val);
                assertSame(left[i], node.left);
                assertSame(right[i], node.right);
            }
        }
    }
}
