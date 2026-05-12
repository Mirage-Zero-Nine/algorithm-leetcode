package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MinDepth111Test {

    private final MinDepth_111 test = new MinDepth_111();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(2, test.minDepth(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minDepth(null));
        assertEquals(1, test.minDepth(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(2, test.minDepth(root));
    }
}
