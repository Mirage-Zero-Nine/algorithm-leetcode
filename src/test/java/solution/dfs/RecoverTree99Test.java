package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class RecoverTree99Test {

    private final RecoverTree_99 test = new RecoverTree_99();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.left.right = new TreeNode(2);
        test.recoverTree(root);
        assertEquals(3, root.val);
        assertEquals(1, root.left.val);
    }

    @Test
    public void testEdgeCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        test.recoverTree(root);
        assertEquals(3, root.right.left.val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3); root.right = new TreeNode(1);
        test.recoverTree(root);
        assertEquals(1, root.left.val);
        assertEquals(3, root.right.val);
    }
}
