package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Flatten114Test {

    private final Flatten_114 test = new Flatten_114();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        test.flatten(root);
        assertEquals(1, root.val);
        assertEquals(2, root.right.val);
        assertNull(root.left);
    }

    @Test
    public void testEdgeCases() {
        test.flatten(null);
        TreeNode root = new TreeNode(1);
        test.flatten(root);
        assertEquals(1, root.val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        test.flatten(root);
        assertEquals(2, root.right.val);
        assertEquals(3, root.right.right.val);
    }
}
