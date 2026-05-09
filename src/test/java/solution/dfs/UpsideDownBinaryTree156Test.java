package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class UpsideDownBinaryTree156Test {

    private final UpsideDownBinaryTree_156 test = new UpsideDownBinaryTree_156();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(4, result.val);
        assertEquals(5, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.upsideDownBinaryTree(null));
        assertEquals(1, test.upsideDownBinaryTree(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(2, result.val);
        assertEquals(3, result.left.val);
        assertEquals(1, result.right.val);
    }
}
