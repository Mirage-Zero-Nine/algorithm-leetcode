package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxAncestorDiff1026Test {

    private final MaxAncestorDiff_1026 test = new MaxAncestorDiff_1026();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3); root.right = new TreeNode(10);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(6);
        assertEquals(7, test.maxAncestorDiff(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.maxAncestorDiff(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); root.right.right = new TreeNode(0);
        root.right.right.left = new TreeNode(3);
        assertEquals(3, test.maxAncestorDiff(root));
    }
}
