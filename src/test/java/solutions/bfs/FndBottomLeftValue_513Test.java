package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
