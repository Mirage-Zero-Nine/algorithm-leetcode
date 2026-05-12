package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxDepth104Test {

    private final MaxDepth_104 test = new MaxDepth_104();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(3, test.maxDepth(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxDepth(null));
        assertEquals(1, test.maxDepth(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 10; i++) { cur.left = new TreeNode(i); cur = cur.left; }
        assertEquals(10, test.maxDepth(root));
    }
}
