package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindTarget653DfsTest {

    private final FindTarget_653 test = new FindTarget_653();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(6);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 9));
        assertFalse(test.findTarget_Set(root, 28));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.findTarget_Set(null, 1));
        assertFalse(test.findTarget_Set(new TreeNode(1), 2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 8));
    }
}
