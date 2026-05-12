package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumOfLeftLeaves404Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(24, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(null));
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(4, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }
}
