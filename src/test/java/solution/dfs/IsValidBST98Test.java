package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsValidBST98Test {

    private final IsValidBST_98 test = new IsValidBST_98();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        assertTrue(test.isValidBST(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(6);
        assertFalse(test.isValidBST(root));
        assertTrue(test.isValidBST(null));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(4);
        assertTrue(test.isValidBST(root));
    }
}
