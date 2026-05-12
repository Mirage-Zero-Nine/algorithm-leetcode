package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsSymmetric101Test {

    private final IsSymmetric_101 test = new IsSymmetric_101();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4); root.right.right = new TreeNode(3);
        assertTrue(test.isSymmetric(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isSymmetric(null));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.right = new TreeNode(3); root.right.right = new TreeNode(3);
        assertFalse(test.isSymmetric(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        assertTrue(test.isSymmetric(root));
    }
}
