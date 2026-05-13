package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindTarget653DfsTest {

    private final FindTarget_653 test = new FindTarget_653();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 9));
        assertFalse(test.findTarget_Set(root, 28));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.findTarget_Set(null, 1));
        assertFalse(test.findTarget_Set(new TreeNode(1), 2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 8));
    }

    @Test
    public void testSumOfRootAndLeft() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 8)); // 5+3
    }

    @Test
    public void testSumOfTwoLeaves() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 6)); // 2+4
    }

    @Test
    public void testTargetNotAchievable() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertFalse(test.findTarget_Set(root, 1));
    }

    @Test
    public void testSingleNodeTargetDouble() {
        // single node, target = 2*val means we need two same values - not possible with one node
        assertFalse(test.findTarget_Set(new TreeNode(5), 10));
    }

    @Test
    public void testTwoNodesFound() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertTrue(test.findTarget_Set(root, 3)); // 1+2
    }

    @Test
    public void testTwoNodesNotFound() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertFalse(test.findTarget_Set(root, 4));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-3); root.right = new TreeNode(3);
        assertTrue(test.findTarget_Set(root, 0)); // -3+3
    }

    @Test
    public void testGiantTree() {
        // Build a balanced BST with 1023 nodes (depth 10)
        TreeNode root = buildBalancedBST(1, 1023);
        assertTrue(test.findTarget_Set(root, 1 + 1023)); // smallest + largest
        assertFalse(test.findTarget_Set(root, 1023 + 1023)); // impossible
    }

    private TreeNode buildBalancedBST(int lo, int hi) {
        if (lo > hi) return null;
        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedBST(lo, mid - 1);
        node.right = buildBalancedBST(mid + 1, hi);
        return node;
    }
}
