package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InvertTree226Test {

    private final InvertTree_226 test = new InvertTree_226();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(7, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.invertTree(null));
        assertEquals(1, test.invertTree(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
    }
}
