package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Tests LeetCode 662, Maximum Width of Binary Tree.
 *
 * <p>The problem counts the missing positions between the leftmost and rightmost node on each
 * level as if the tree were embedded in a complete binary tree. LeetCode permits 1--3000 nodes
 * and node values from -100 through 100. The implementation also documents a null-root result,
 * so that implementation-defined guard is tested separately.</p>
 */
public class WidthOfBinaryTree_662Test {

    private final WidthOfBinaryTree_662 test = new WidthOfBinaryTree_662();

    @Test
    public void testOfficialExampleOne() {
        assertWidth(4, tree(1, 3, 2, 5, 3, null, 9));
    }

    @Test
    public void testOfficialExampleTwoCountsFourMissingPositions() {
        assertWidth(7, tree(1, 3, 2, 5, null, null, 9, 6, null, 7));
    }

    @Test
    public void testOfficialExampleThree() {
        assertWidth(2, tree(1, 3, 2, 5));
    }

    @Test
    public void testNullRootUsesDocumentedImplementationGuard() {
        assertEquals(0, test.widthOfBinaryTree(null));
    }

    @Test
    public void testSingletonAtBothDocumentedValueBoundaries() {
        assertWidth(1, new TreeNode(-100));
        assertWidth(1, new TreeNode(100));
    }

    @Test
    public void testOnlyLeftChildStillHasWidthOne() {
        assertWidth(1, tree(7, -100));
    }

    @Test
    public void testOnlyRightChildStillHasWidthOne() {
        assertWidth(1, tree(7, null, 100));
    }

    @Test
    public void testDeepLeftSkewHasWidthOne() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 300; i++) {
            current.left = new TreeNode(value(i));
            current = current.left;
        }
        assertWidth(1, root);
    }

    @Test
    public void testDeepRightSkewHasWidthOne() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 300; i++) {
            current.right = new TreeNode(value(-i));
            current = current.right;
        }
        assertWidth(1, root);
    }

    @Test
    public void testPerfectTreesHaveTheirLastLevelWidth() {
        for (int height = 0; height <= 8; height++) {
            int nodeCount = (1 << (height + 1)) - 1;
            assertWidth(1 << height, completeTree(nodeCount), "height=" + height);
        }
    }

    @Test
    public void testCompletePrefixWithPartialLastLevelUsesOnlyPresentEndpoints() {
        for (int nodeCount = 1; nodeCount <= 200; nodeCount++) {
            TreeNode root = completeTree(nodeCount);
            assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void testSparseGapAtAnInteriorLevel() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.right.right.right = new TreeNode(15);
        assertWidth(8, root);
    }

    @Test
    public void testWidthCanComeFromADeeperLevelThanTheRootChildren() {
        TreeNode root = tree(1, 2, null, 3, 4);
        assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root));
        assertEquals(2, normalizedWidth(root));
    }

    @Test
    public void testValuesIncludingDuplicatesAndSignedBoundariesDoNotAffectWidth() {
        TreeNode root = tree(-100, 100, -100, -100, 100, 100, -100, 100, null, null, -100);
        assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root));
    }

    @Test
    public void testTwoSeparatedNodesAtTheSameLevelHaveGapWidth() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
        assertEquals(8, normalizedWidth(root));
        assertEquals(8, test.widthOfBinaryTree(root));
    }

    @Test
    public void testRepeatedCallsOnTheSameTreeRemainIndependentOfMutatedIndexValues() {
        TreeNode root = tree(1, 3, 2, 5, 3, null, 9, 6, null, 7);
        TreeNode originalLeft = root.left;
        int expected = normalizedWidth(root);
        assertEquals(expected, test.widthOfBinaryTree(root));
        assertEquals(expected, test.widthOfBinaryTree(root));
        assertSame(originalLeft, root.left);
    }

    @Test
    public void testDifferentTreesDoNotShareInstanceState() {
        TreeNode wide = tree(1, 2, 3, 4, null, null, 7);
        TreeNode narrow = new TreeNode(-100);
        assertEquals(normalizedWidth(wide), test.widthOfBinaryTree(wide));
        assertEquals(1, test.widthOfBinaryTree(narrow));
        assertEquals(normalizedWidth(wide), test.widthOfBinaryTree(wide));
    }

    @Test
    public void testAllConnectedShapesThroughEightHeapPositionsAgainstIndependentOracle() {
        for (int mask = 1; mask < (1 << 8); mask++) {
            if (!hasConnectedParents(mask)) {
                continue;
            }
            TreeNode root = fromHeapMask(mask);
            assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root),
                    "mask=" + Integer.toBinaryString(mask));
        }
    }

    @Test
    public void testSeededArbitraryShapesAgainstNormalizedIndexOracle() {
        for (int seed = 0; seed < 100; seed++) {
            int nodeCount = 1 + (seed * 53 % 120);
            TreeNode root = seededTree(seed, nodeCount);
            assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root),
                    "seed=" + seed + ", nodeCount=" + nodeCount);
        }
    }

    @Test
    public void testMaximumOfficialNodeCountCompletePrefix() {
        TreeNode root = completeTree(3000);
        assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root));
    }

    @Test
    public void testMaximumOfficialNodeCountRightSkew() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 3000; i++) {
            current.right = new TreeNode(value(i));
            current = current.right;
        }
        assertEquals(1, test.widthOfBinaryTree(root));
    }

    @Test
    public void testDeepIndexOverflowWithValidWidthWithinThirtyTwoBitRange() {
        TreeNode root = deepOverflowSafeTree();
        // The two endpoints are separated by 2^30 complete-tree positions, despite only 3000 nodes.
        assertEquals(1 << 30, normalizedWidth(root));
        assertEquals(1 << 30, test.widthOfBinaryTree(root));
    }

    @Test
    public void testMaximumNodeValuesOnAWideSparseTree() {
        TreeNode root = tree(-100, 100, -100, 100, null, null, -100, null, null, null, 100);
        assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root));
    }

    @Test
    public void testSingleDeepBranchAfterAWideLevelDoesNotIncreaseLaterWidth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        TreeNode tail = root.left.left;
        for (int i = 0; i < 100; i++) {
            tail.left = new TreeNode(value(i));
            tail = tail.left;
        }
        assertEquals(normalizedWidth(root), test.widthOfBinaryTree(root));
        assertEquals(4, normalizedWidth(root));
    }

    @Test
    public void testFreshTreesWithTheSameShapeAndDifferentValuesHaveTheSameWidth() {
        TreeNode first = completeTree(63);
        TreeNode second = completeTree(63);
        assignValues(first, -100);
        assignValues(second, 100);
        assertEquals(normalizedWidth(first), test.widthOfBinaryTree(first));
        assertEquals(normalizedWidth(second), test.widthOfBinaryTree(second));
    }

    private void assertWidth(int expected, TreeNode root) {
        assertEquals(expected, normalizedWidth(root));
        assertEquals(expected, test.widthOfBinaryTree(root));
    }

    private void assertWidth(int expected, TreeNode root, String message) {
        assertEquals(expected, normalizedWidth(root), message);
        assertEquals(expected, test.widthOfBinaryTree(root), message);
    }

    private static int normalizedWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<IndexedNode> queue = new ArrayDeque<>();
        queue.add(new IndexedNode(root, 0L));
        long maximum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            long first = queue.peekFirst().index;
            long last = queue.peekLast().index;
            maximum = Math.max(maximum, last - first + 1);
            for (int i = 0; i < size; i++) {
                IndexedNode current = queue.removeFirst();
                long relative = current.index - first;
                if (current.node.left != null) {
                    queue.addLast(new IndexedNode(current.node.left, relative * 2));
                }
                if (current.node.right != null) {
                    queue.addLast(new IndexedNode(current.node.right, relative * 2 + 1));
                }
            }
        }
        return Math.toIntExact(maximum);
    }

    private static TreeNode completeTree(int nodeCount) {
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(value(i));
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
        Deque<Integer> parents = new ArrayDeque<>();
        parents.add(0);
        int next = 1;
        while (!parents.isEmpty() && next < values.length) {
            int parentIndex = parents.removeFirst();
            TreeNode parent = nodes[parentIndex];
            if (next < values.length && values[next] != null) {
                nodes[next] = new TreeNode(values[next]);
                parent.left = nodes[next];
                parents.addLast(next);
            }
            next++;
            if (next < values.length && values[next] != null) {
                nodes[next] = new TreeNode(values[next]);
                parent.right = nodes[next];
                parents.addLast(next);
            }
            next++;
        }
        return nodes[0];
    }

    private static TreeNode fromHeapMask(int mask) {
        TreeNode[] nodes = new TreeNode[8];
        for (int i = 0; i < nodes.length; i++) {
            if ((mask & (1 << i)) != 0) {
                nodes[i] = new TreeNode(value(i));
            }
        }
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }
            TreeNode parent = nodes[(i - 1) / 2];
            if (i % 2 == 1) {
                parent.left = nodes[i];
            } else {
                parent.right = nodes[i];
            }
        }
        return nodes[0];
    }

    private static boolean hasConnectedParents(int mask) {
        if ((mask & 1) == 0) {
            return false;
        }
        for (int i = 1; i < 8; i++) {
            if ((mask & (1 << i)) != 0 && (mask & (1 << ((i - 1) / 2))) == 0) {
                return false;
            }
        }
        return true;
    }

    private static TreeNode seededTree(int seed, int nodeCount) {
        Random random = new Random(seed * 7919L + 17);
        TreeNode root = new TreeNode(value(random.nextInt()));
        List<TreeNode> open = new ArrayList<>();
        open.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(open.size());
            TreeNode parent = open.get(parentIndex);
            TreeNode child = new TreeNode(value(random.nextInt()));
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
                open.remove(parentIndex);
            }
        }
        return root;
    }

    private static TreeNode deepOverflowSafeTree() {
        TreeNode root = new TreeNode(0);
        TreeNode prefix = root;
        for (int i = 0; i < 100; i++) {
            prefix.right = new TreeNode(value(i));
            prefix = prefix.right;
        }

        TreeNode leftEndpoint = prefix;
        TreeNode rightEndpoint = prefix;
        for (int i = 0; i < 30; i++) {
            leftEndpoint.left = new TreeNode(value(i + 100));
            leftEndpoint = leftEndpoint.left;
            rightEndpoint.right = new TreeNode(value(i + 200));
            rightEndpoint = rightEndpoint.right;
        }

        // Use the remaining node budget on one branch. It cannot create a wider later level.
        int existingNodes = 1 + 100 + 30 + 30;
        TreeNode tail = leftEndpoint;
        for (int i = existingNodes; i < 3000; i++) {
            tail.left = new TreeNode(value(i));
            tail = tail.left;
        }
        return root;
    }

    private static void assignValues(TreeNode root, int offset) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int value = offset;
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            node.val = value;
            value = value == 100 ? -100 : value + 1;
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
    }

    private static int value(int seed) {
        return Math.floorMod(seed * 31 + 17, 201) - 100;
    }

    private record IndexedNode(TreeNode node, long index) {
    }
}
