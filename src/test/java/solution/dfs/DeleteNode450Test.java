package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DeleteNode450Test {

    private final DeleteNode_450 test = new DeleteNode_450();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        TreeNode result = test.deleteNode(root, 3);
        assertEquals(5, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deleteNode(null, 1));
        assertEquals(1, test.deleteNode(new TreeNode(1), 2).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode result = test.deleteNode(root, 5);
        assertEquals(7, result.val);
    }
}
