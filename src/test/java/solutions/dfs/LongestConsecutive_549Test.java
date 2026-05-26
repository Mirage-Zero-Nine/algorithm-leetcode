package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LongestConsecutive_549Test {

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

    @Test
    public void testDecreasingPath() {
        // 3 -> 2 -> 1 (left chain, decreasing)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testIncreasingPath() {
        // 1 -> 2 -> 3 (left chain, increasing)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testChildParentChild() {
        // Path goes child-parent-child: 1 <- 2 -> 3
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        // increasing from left: 1->2, decreasing from right: 3->2
        // combined: 1-2-3 = length 3
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testNoConsecutive() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5); root.right = new TreeNode(10);
        assertEquals(1, test.longestConsecutive(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1); root.right = new TreeNode(1);
        // -1 -> 0 -> 1 = length 3
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testPathNotThroughRoot() {
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(4);
        // Path: 2-3-4 = length 3, not through root
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        assertEquals(1, test.longestConsecutive(root));
    }

    @Test
    public void testGiantTree() {
        // Build a left chain: 1, 2, 3, ..., 100 (increasing)
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 100; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(100, test.longestConsecutive(root));
    }
}
