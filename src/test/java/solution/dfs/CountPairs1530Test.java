package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class CountPairs1530Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        assertEquals(1, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, new CountPairs_1530().countPairs(null, 1));
        assertEquals(0, new CountPairs_1530().countPairs(new TreeNode(1), 1));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(2, new CountPairs_1530().countPairs(root, 3));
    }
}
