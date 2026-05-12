package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LongestConsecutive298Test {

    private final LongestConsecutive_298 test = new LongestConsecutive_298();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3); root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4); root.right.right.right = new TreeNode(5);
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestConsecutive(null));
        assertEquals(1, test.longestConsecutive(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3); root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5); root.right.right.right.right = new TreeNode(6);
        assertEquals(5, test.longestConsecutive(root));
    }
}
