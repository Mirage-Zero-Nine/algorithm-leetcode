package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class HasPathSum112Test {

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
}
