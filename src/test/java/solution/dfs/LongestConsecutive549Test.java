package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LongestConsecutive549Test {

    private final LongestConsecutive_549 test = new LongestConsecutive_549();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(2, test.longestConsecutive(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestConsecutive(null));
        assertEquals(1, test.longestConsecutive(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        root.left.left = new TreeNode(2); root.right.right = new TreeNode(4);
        assertEquals(4, test.longestConsecutive(root));
    }
}
