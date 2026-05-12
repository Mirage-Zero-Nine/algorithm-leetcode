package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IncreasingBST897Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(8);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(2, result.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new IncreasingBST_897().increasingBST(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode result = new IncreasingBST_897().increasingBST(root);
        assertEquals(1, result.val);
        assertEquals(7, result.right.right.right.right.right.right.val);
    }
}
