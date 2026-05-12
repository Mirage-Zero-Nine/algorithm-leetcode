package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsBalanced110Test {

    private final IsBalanced_110 test = new IsBalanced_110();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertTrue(test.isBalanced(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isBalanced(null));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertFalse(test.isBalanced(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        assertTrue(test.isBalanced(root));
    }
}
