package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class Rob337Test {

    private final Rob_337 test = new Rob_337();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(3); root.right.right = new TreeNode(1);
        assertEquals(7, test.rob(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.rob(null));
        assertEquals(1, test.rob(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        assertEquals(9, test.rob(root));
    }
}
