package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MinDepth_111Test {

    private final MinDepth_111 test = new MinDepth_111();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(2, test.minDepth(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minDepth(null));
        assertEquals(1, test.minDepth(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(2, test.minDepth(root));
    }

    @Test
    public void testLeftSkewed() {
        // Only left children - min depth is the full depth
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, test.minDepth(root));
    }

    @Test
    public void testRightSkewed() {
        // Only right children - min depth is the full depth
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(4, test.minDepth(root));
    }

    @Test
    public void testBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(3, test.minDepth(root));
    }

    @Test
    public void testUnbalancedLeftDeeper() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        assertEquals(2, test.minDepth(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, test.minDepth(root));
    }

    @Test
    public void testAsymmetric() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        // right subtree is leaf at depth 2, left goes to depth 3
        assertEquals(2, test.minDepth(root));
    }

    @Test
    public void testGiantTree() {
        // Build a deep left chain with a short right branch
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i <= 1000; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        root.right = new TreeNode(99); // short branch at depth 2
        assertEquals(2, test.minDepth(root));
    }
}
