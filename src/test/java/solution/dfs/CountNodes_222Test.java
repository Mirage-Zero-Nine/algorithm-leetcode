package solution.dfs;

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
