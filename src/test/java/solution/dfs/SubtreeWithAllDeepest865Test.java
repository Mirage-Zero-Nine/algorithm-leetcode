package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SubtreeWithAllDeepest865Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5); root.right = new TreeNode(1);
        root.left.left = new TreeNode(6); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7); root.left.right.right = new TreeNode(4);
        assertEquals(2, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(null));
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, new SubtreeWithAllDeepest_865().subtreeWithAllDeepest(root).val);
    }
}
