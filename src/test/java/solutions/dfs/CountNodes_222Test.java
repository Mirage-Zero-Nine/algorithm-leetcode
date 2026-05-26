package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class CountNodes_222Test {

    private final CountNodes_222 test = new CountNodes_222();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        assertEquals(6, test.countNodes(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countNodes(null));
        assertEquals(1, test.countNodes(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(7, test.countNodes(root));
    }

    @Test
    public void testNullTree() {
        assertEquals(0, test.countNodes(null));
    }

    @Test
    public void testSingleNode() {
        assertEquals(1, test.countNodes(new TreeNode(1)));
    }

    @Test
    public void testPerfectTreeDepth2() {
        // 3 nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(3, test.countNodes(root));
    }

    @Test
    public void testPerfectTreeDepth3() {
        // 7 nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(7, test.countNodes(root));
    }

    @Test
    public void testCompleteTreeLastLevelPartialLeft() {
        // 4 nodes: complete tree with only left child on last level
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        assertEquals(4, test.countNodes(root));
    }

    @Test
    public void testCompleteTreeLastLevelPartialBoth() {
        // 5 nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(5, test.countNodes(root));
    }

    @Test
    public void testLeftOnlyChain() {
        // 2 nodes (still a complete tree)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, test.countNodes(root));
    }

    @Test
    public void testGiantPerfectTree() {
        // Build a perfect binary tree of depth 10 (1023 nodes)
        TreeNode root = buildPerfectTree(10);
        assertEquals(1023, test.countNodes(root));
    }

    @Test
    public void testGiantCompleteTree() {
        // Build a complete tree with 1000 nodes
        TreeNode root = buildCompleteTree(1000);
        assertEquals(1000, test.countNodes(root));
    }

    @Test
    public void testPerfectTreeDepth4() {
        // 15 nodes
        TreeNode root = buildPerfectTree(4);
        assertEquals(15, test.countNodes(root));
    }

    @Test
    public void testPerfectTreeDepth5() {
        // 31 nodes
        TreeNode root = buildPerfectTree(5);
        assertEquals(31, test.countNodes(root));
    }

    @Test
    public void testLastLevelOnlyOneNodeDeep() {
        // Depth 4 perfect (15 nodes) + 1 node on last level = 16
        TreeNode root = buildCompleteTree(16);
        assertEquals(16, test.countNodes(root));
    }

    @Test
    public void testLastLevelFullMinusOne() {
        // Depth 4 perfect = 15, full last level = 31, minus one = 30
        TreeNode root = buildCompleteTree(30);
        assertEquals(30, test.countNodes(root));
    }

    @Test
    public void testCompleteNotPerfectVariousSizes() {
        // Test several complete-but-not-perfect trees
        for (int n : new int[]{5, 6, 9, 10, 12, 20, 25}) {
            TreeNode root = buildCompleteTree(n);
            assertEquals(n, test.countNodes(root));
        }
    }

    @Test
    public void testPropertyCrossCheckWithRecursiveCount() {
        // Cross-check countNodes against a naive recursive count for various sizes
        for (int n = 0; n <= 100; n++) {
            TreeNode root = buildCompleteTree(n);
            assertEquals(naiveCount(root), test.countNodes(root), "Failed for n=" + n);
        }
    }

    @Test
    public void testPerfectTreeAllDepths() {
        // depth 1->1, 2->3, 3->7, 4->15, 5->31
        int[] expected = {1, 3, 7, 15, 31};
        for (int d = 1; d <= 5; d++) {
            TreeNode root = buildPerfectTree(d);
            assertEquals(expected[d - 1], test.countNodes(root), "Failed for depth=" + d);
        }
    }

    private int naiveCount(TreeNode node) {
        if (node == null) return 0;
        return 1 + naiveCount(node.left) + naiveCount(node.right);
    }

    private TreeNode buildPerfectTree(int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(1);
        node.left = buildPerfectTree(depth - 1);
        node.right = buildPerfectTree(depth - 1);
        return node;
    }

    private TreeNode buildCompleteTree(int n) {
        if (n <= 0) return null;
        TreeNode[] nodes = new TreeNode[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new TreeNode(i);
        for (int i = 1; i <= n; i++) {
            if (2 * i <= n) nodes[i].left = nodes[2 * i];
            if (2 * i + 1 <= n) nodes[i].right = nodes[2 * i + 1];
        }
        return nodes[1];
    }
}
