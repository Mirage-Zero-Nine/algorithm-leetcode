package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LongestConsecutive_298Test {

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

    @Test
    public void testNoConsecutive() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(10);
        assertEquals(1, test.longestConsecutive(root));
    }

    @Test
    public void testLeftPath() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(4, test.longestConsecutive(root));
    }

    @Test
    public void testDecreasingNotCounted() {
        // Consecutive must be parent -> child with child = parent + 1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        assertEquals(1, test.longestConsecutive(root));
    }

    @Test
    public void testBranchingConsecutive() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testConsecutiveBreaksAndRestarts() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(5); // break
        root.right.right.right = new TreeNode(6);
        root.right.right.right.right = new TreeNode(7);
        assertEquals(3, test.longestConsecutive(root));
    }

    @Test
    public void testTwoNodesConsecutive() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, test.longestConsecutive(root));
    }

    @Test
    public void testGiantChain() {
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 1000; i++) {
            cur.right = new TreeNode(i);
            cur = cur.right;
        }
        assertEquals(1000, test.longestConsecutive(root));
    }
}
