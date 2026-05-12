package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BalanceBST1382Test {

    private final BalanceBST_1382 test = new BalanceBST_1382();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        TreeNode result = test.balanceBST(root);
        assertEquals(2, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.balanceBST(null));
        assertEquals(1, test.balanceBST(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode result = test.balanceBST(root);
        assertEquals(4, result.val);
    }
}
