package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxPathSum124Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(6, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-3, new MaxPathSum_124().maxPathSum(new TreeNode(-3)));
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(42, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13); root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        assertEquals(48, new MaxPathSum_124().maxPathSum(root));
    }
}
