package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class HasPathSum_112Test {

    private final HasPathSum_112 test = new HasPathSum_112();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        assertTrue(test.hasPathSum(root, 22));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.hasPathSum(null, 1));
        assertFalse(test.hasPathSum(new TreeNode(1), 2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertTrue(test.hasPathSum(root, 3));
        assertFalse(test.hasPathSum(root, 5));
    }

    @Test
    public void testSingleNodeMatch() {
        assertTrue(test.hasPathSum(new TreeNode(5), 5));
    }

    @Test
    public void testSingleNodeNoMatch() {
        assertFalse(test.hasPathSum(new TreeNode(5), 0));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertTrue(test.hasPathSum(root, -5));
    }

    @Test
    public void testZeroSum() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-1);
        assertTrue(test.hasPathSum(root, 0));
    }

    @Test
    public void testPathMustEndAtLeaf() {
        // Sum matches at internal node but not at leaf
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        // 1+2=3 but 2 is not a leaf
        assertFalse(test.hasPathSum(root, 3));
        assertTrue(test.hasPathSum(root, 6));
    }

    @Test
    public void testRightPath() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertTrue(test.hasPathSum(root, 4));
    }

    @Test
    public void testGiantTree() {
        // Chain of 100 nodes each with value 1, sum should be 100
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 1; i < 100; i++) {
            current.left = new TreeNode(1);
            current = current.left;
        }
        assertTrue(test.hasPathSum(root, 100));
        assertFalse(test.hasPathSum(root, 99));
    }
}
