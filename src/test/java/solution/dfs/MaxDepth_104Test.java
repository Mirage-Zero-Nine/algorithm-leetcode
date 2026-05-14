package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxDepth_104Test {

    private final MaxDepth_104 test = new MaxDepth_104();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxDepth(null));
        assertEquals(1, test.maxDepth(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 10; i++) { cur.left = new TreeNode(i); cur = cur.left; }
        assertEquals(10, test.maxDepth(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testBalancedDepth3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, test.maxDepth(root));
    }

    @Test
    public void testLeftDeeperThanRight() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        assertEquals(4, test.maxDepth(root));
    }

    @Test
    public void testRightDeeperThanLeft() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        assertEquals(5, test.maxDepth(root));
    }

    @Test
    public void testZigZag() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        assertEquals(4, test.maxDepth(root));
    }

    @Test
    public void testGiantTree() {
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i <= 5000; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        assertEquals(5001, test.maxDepth(root));
    }
}
