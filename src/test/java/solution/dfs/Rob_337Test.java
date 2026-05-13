package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Rob_337Test {

    private final Rob_337 test = new Rob_337();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(3); root.right.right = new TreeNode(1);
        assertEquals(7, test.rob(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.rob(null));
        assertEquals(1, test.rob(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        assertEquals(9, test.rob(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        // Can't rob both (connected), pick max = 2
        assertEquals(2, test.rob(root));
    }

    @Test
    public void testGrandchildrenBetter() {
        // root=1, children=100,100 -> rob children = 200
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(100); root.right = new TreeNode(100);
        assertEquals(200, test.rob(root));
    }

    @Test
    public void testRootBetter() {
        // root=100, children=1,1, grandchildren=1,1,1,1
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(1);
        // Rob root + grandchildren = 100 + 4 = 104 vs children = 2 + max of grandchildren
        assertEquals(104, test.rob(root));
    }

    @Test
    public void testAllZeros() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(0, test.rob(root));
    }

    @Test
    public void testLeftSkewed() {
        // 4 -> 3 -> 2 -> 1 (left skewed)
        // Optimal: rob 4 + 2 = 6 or 3 + 1 = 4 -> pick 6
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        assertEquals(6, test.rob(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(1);
        root.right.right.right = new TreeNode(5);
        // Optimal: rob 5 + 5 = 10 (skip root and 3rd level)
        assertEquals(10, test.rob(root));
    }

    @Test
    public void testNegativeLikeCase() {
        // All values are 0 except leaves
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        root.left.left = new TreeNode(5); root.right.right = new TreeNode(5);
        assertEquals(10, test.rob(root));
    }

    @Test
    public void testGiantTree() {
        // Build a complete binary tree of depth 10, all values 1
        TreeNode root = buildTree(10);
        // Optimal strategy on uniform tree: pick alternate levels
        int result = test.rob(root);
        // Just verify it's positive and reasonable (> half the nodes)
        assertEquals(true, result >= 512);
    }

    private TreeNode buildTree(int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(1);
        node.left = buildTree(depth - 1);
        node.right = buildTree(depth - 1);
        return node;
    }
}
