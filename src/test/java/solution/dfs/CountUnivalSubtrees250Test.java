package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class CountUnivalSubtrees250Test {

    private final CountUnivalSubtrees_250 test = new CountUnivalSubtrees_250();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(5);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countUnivalSubtrees(null));
        assertEquals(1, test.countUnivalSubtrees(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        assertEquals(5, test.countUnivalSubtrees(root));
    }
}
