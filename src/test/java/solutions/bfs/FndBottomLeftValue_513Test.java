package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class FndBottomLeftValue_513Test {

    private final FndBottomLeftValue_513 test = new FndBottomLeftValue_513();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        assertEquals(1, test.findBottomLeftValue(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findBottomLeftValue(null));
        assertEquals(1, test.findBottomLeftValue(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        assertEquals(7, test.findBottomLeftValue(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        assertEquals(6, test.findBottomLeftValue(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(4, test.findBottomLeftValue(root));
    }

    @Test
    public void testBottomLeftComesFromLeftSubtree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.right.left = new TreeNode(50);
        root.right.right = new TreeNode(60);
        assertEquals(40, test.findBottomLeftValue(root));
    }

    @Test
    public void testBottomLeftWhenDeepestNodeInRightSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        assertEquals(5, test.findBottomLeftValue(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-4);
        assertEquals(-4, test.findBottomLeftValue(root));
    }

    @Test
    public void testLastLevelMultipleNodesPickLeftmost() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(4, test.findBottomLeftValue(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 200; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        assertEquals(200, test.findBottomLeftValue(root));
    }

    /**
     * Exercises the complete valid shape/value space represented by a deterministic collection of
     * trees.  The expected value is calculated by a depth-first oracle that tracks the first node
     * encountered at the greatest depth, independently of the solution's level-order traversal.
     */
    @Test
    public void testDiverseTreesAgainstIndependentDepthOracle() {
        List<TreeNode> trees = new ArrayList<>();

        trees.add(tree(0));
        trees.add(tree(Integer.MIN_VALUE));
        trees.add(tree(Integer.MAX_VALUE));
        trees.add(tree(0, tree(-1), tree(1)));
        trees.add(tree(0, null, tree(1)));
        trees.add(tree(0, tree(-1), null));
        trees.add(tree(10, tree(20, tree(40), null), tree(30)));
        trees.add(tree(10, tree(20), tree(30, tree(50, tree(70), null), tree(60))));
        trees.add(tree(1, tree(2, null, tree(4)), tree(3, tree(5), null)));
        trees.add(tree(1, tree(2, tree(4), tree(5)), tree(3, null, tree(6, tree(7), null))));
        trees.add(tree(-1, tree(-2, null, tree(-4, tree(-8), null)), tree(-3)));
        trees.add(tree(8, tree(4, tree(2), null), tree(12, null, tree(14, tree(13), null))));
        trees.add(tree(5, tree(3, tree(1, null, tree(0)), tree(4)), tree(9, null, tree(11))));
        trees.add(tree(100, tree(50, null, tree(75, null, tree(80))), tree(150, tree(125), null)));
        trees.add(tree(1, tree(2, tree(4, null, tree(8)), null), tree(3, null, tree(7, tree(6), null))));
        trees.add(tree(42, tree(-10, tree(-20), tree(-5)), tree(99, tree(98, null, tree(97)), tree(100))));
        trees.add(tree(0, tree(1, tree(2, tree(3), null), null), tree(4, null, tree(5, null, tree(6)))));
        trees.add(tree(7, tree(3, tree(1), tree(5, null, tree(6))), tree(9, tree(8), tree(10))));
        trees.add(tree(2, tree(1, null, tree(0)), tree(3, tree(4, null, tree(5)), null)));
        trees.add(tree(-7, tree(-8, tree(-9, null, tree(-10)), null), tree(-6, null, tree(-5, tree(-4), null))));
        trees.add(tree(1, tree(2, tree(4), tree(5, tree(8), tree(9))), tree(3, tree(6), tree(7, null, tree(10)))));
        trees.add(tree(11, tree(22, null, tree(33, tree(44), null)), tree(55, tree(66, null, tree(77)), tree(88))));
        trees.add(tree(0, tree(Integer.MIN_VALUE, null, tree(-1)), tree(Integer.MAX_VALUE, tree(1), null)));
        trees.add(tree(6, tree(5, tree(4, tree(3, tree(2, tree(1))))), tree(7)));
        trees.add(tree(6, tree(5), tree(7, null, tree(8, null, tree(9, null, tree(10))))));

        for (TreeNode root : trees) {
            int expected = oracle(root, 0, new Best());
            String before = shape(root);
            assertEquals(expected, test.findBottomLeftValue(root), "unexpected result for " + before);
            assertEquals(before, shape(root), "solution must not mutate the tree");
        }
    }

    /** Covers the problem's maximum node count without relying on the implementation for expected data. */
    @Test
    public void testMaximumNodeCountCompletePrefix() {
        int nodeCount = 10_000;
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(i == 9_999 ? Integer.MAX_VALUE : i - 5_000);
        }
        for (int i = 0; i < nodeCount; i++) {
            int left = i * 2 + 1;
            int right = left + 1;
            if (left < nodeCount) nodes[i].left = nodes[left];
            if (right < nodeCount) nodes[i].right = nodes[right];
        }

        // The first node on the deepest populated level is the first heap index at that depth.
        int depthStart = 0;
        int nextLevel = 1;
        while (nextLevel <= nodeCount) {
            depthStart = nextLevel - 1;
            nextLevel *= 2;
        }
        assertEquals(nodes[depthStart].val, test.findBottomLeftValue(nodes[0]));
    }

    @Test
    public void testBoundaryValuesAndDuplicateValuesAtLastLevel() {
        TreeNode root = tree(Integer.MIN_VALUE,
                tree(Integer.MIN_VALUE, tree(Integer.MAX_VALUE), tree(Integer.MIN_VALUE)),
                tree(Integer.MAX_VALUE, tree(Integer.MIN_VALUE), tree(Integer.MAX_VALUE)));
        assertEquals(Integer.MAX_VALUE, test.findBottomLeftValue(root));

        TreeNode duplicateTree = tree(42,
                tree(42, tree(42), tree(42)),
                tree(42, tree(42), tree(42)));
        assertEquals(42, test.findBottomLeftValue(duplicateTree));
    }

    @Test
    public void testRepeatedCallsAreIndependentAndDoNotMutateTopology() {
        TreeNode first = tree(10, tree(20, null, tree(30)), tree(40));
        TreeNode second = tree(-10, tree(-20), tree(-30, tree(-40), null));
        String firstShape = shape(first);
        String secondShape = shape(second);

        assertEquals(30, test.findBottomLeftValue(first));
        assertEquals(-40, test.findBottomLeftValue(second));
        assertEquals(30, test.findBottomLeftValue(first));
        assertEquals(firstShape, shape(first));
        assertEquals(secondShape, shape(second));
    }

    /**
     * Exercises arbitrary sparse shapes and value distributions with an oracle that traverses each
     * level independently.  The seed makes failures reproducible while varying which deepest
     * node is leftmost, including cases where the deepest node is in the right subtree.
     */
    @Test
    public void testSeededSparseTreesAgainstIndependentBreadthFirstOracle() {
        Random random = new Random(513_2026L);
        for (int caseNumber = 0; caseNumber < 200; caseNumber++) {
            TreeNode root = randomTree(random, 1 + random.nextInt(250));
            String before = shape(root);
            assertEquals(breadthFirstOracle(root), test.findBottomLeftValue(root),
                    "unexpected result for seeded tree " + caseNumber);
            assertEquals(before, shape(root), "solution must not mutate seeded tree " + caseNumber);
        }
    }

    /** Covers the official 10,000-node limit with the maximum possible depth. */
    @Test
    public void testMaximumNodeCountRightSpine() {
        final int nodeCount = 10_000;
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        TreeNode current = root;
        for (int i = 1; i < nodeCount; i++) {
            current.right = new TreeNode(i == nodeCount - 1 ? Integer.MAX_VALUE : i);
            current = current.right;
        }

        assertEquals(Integer.MAX_VALUE, test.findBottomLeftValue(root));
    }

    @Test
    public void testLastLevelStartsInRightSubtree() {
        TreeNode root = tree(10, tree(5), tree(15, null, tree(20, tree(18), null)));
        assertEquals(18, test.findBottomLeftValue(root));
    }

    @Test
    public void testAlternatingSparseLevelsPreserveQueueOrder() {
        TreeNode root = tree(1, tree(2, null, tree(4, tree(8), null)),
                tree(3, tree(5), tree(6, null, tree(9))));
        assertEquals(8, test.findBottomLeftValue(root));
    }

    @Test
    public void testBoundaryValuesAtDifferentDepths() {
        TreeNode root = tree(Integer.MAX_VALUE, tree(Integer.MIN_VALUE, tree(0)), tree(42));
        assertEquals(0, test.findBottomLeftValue(root));
    }

    @Test
    public void testSingleChildAtEveryLevel() {
        TreeNode root = tree(0, tree(1, null, tree(2, tree(3), null)), null);
        assertEquals(3, test.findBottomLeftValue(root));
    }

    @Test
    public void testFreshInstanceAndRepeatedNullCalls() {
        assertEquals(9, new FndBottomLeftValue_513().findBottomLeftValue(tree(9)));
        assertEquals(0, test.findBottomLeftValue(null));
        assertEquals(0, test.findBottomLeftValue(null));
    }

    private static TreeNode tree(int value) {
        return new TreeNode(value);
    }

    private static TreeNode tree(int value, TreeNode left) {
        return tree(value, left, null);
    }

    private static TreeNode tree(int value, TreeNode left, TreeNode right) {
        TreeNode node = new TreeNode(value);
        node.left = left;
        node.right = right;
        return node;
    }

    private static TreeNode randomTree(Random random, int maximumNodes) {
        TreeNode root = new TreeNode(randomValue(random, 0));
        Queue<TreeNode> pending = new ArrayDeque<>();
        pending.add(root);
        int created = 1;
        while (!pending.isEmpty() && created < maximumNodes) {
            TreeNode parent = pending.remove();
            if (created < maximumNodes && random.nextInt(100) < 68) {
                parent.left = new TreeNode(randomValue(random, created++));
                pending.add(parent.left);
            }
            if (created < maximumNodes && random.nextInt(100) < 68) {
                parent.right = new TreeNode(randomValue(random, created++));
                pending.add(parent.right);
            }
        }
        return root;
    }

    private static int randomValue(Random random, int index) {
        if (index % 31 == 0) return Integer.MIN_VALUE;
        if (index % 37 == 0) return Integer.MAX_VALUE;
        return random.nextInt(401) - 200;
    }

    private static int breadthFirstOracle(TreeNode root) {
        Queue<TreeNode> currentLevel = new ArrayDeque<>();
        currentLevel.add(root);
        int leftmost = root.val;
        while (!currentLevel.isEmpty()) {
            leftmost = currentLevel.peek().val;
            int nodesThisLevel = currentLevel.size();
            for (int i = 0; i < nodesThisLevel; i++) {
                TreeNode node = currentLevel.remove();
                if (node.left != null) currentLevel.add(node.left);
                if (node.right != null) currentLevel.add(node.right);
            }
        }
        return leftmost;
    }

    private static int oracle(TreeNode node, int depth, Best best) {
        if (node == null) return -1;
        if (depth > best.depth) {
            best.depth = depth;
            best.value = node.val;
        }
        oracle(node.left, depth + 1, best);
        oracle(node.right, depth + 1, best);
        return best.value;
    }

    private static String shape(TreeNode node) {
        if (node == null) return "#";
        return node.val + "(" + shape(node.left) + "," + shape(node.right) + ")";
    }

    private static final class Best {
        private int depth = -1;
        private int value;
    }
}
