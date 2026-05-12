package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsCompleteTree958Test {

    private final IsCompleteTree_958 test = new IsCompleteTree_958();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isCompleteTree(null));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertTrue(test.isCompleteTree(root));
    }
}
