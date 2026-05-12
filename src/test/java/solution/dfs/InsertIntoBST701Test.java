package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InsertIntoBST701Test {

    private final InsertIntoBST_701 test = new InsertIntoBST_701();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.insertIntoBST(root, 5);
        assertEquals(5, result.right.left.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.insertIntoBST(null, 1).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.insertIntoBST(root, 4);
        assertEquals(4, result.left.right.val);
    }
}
