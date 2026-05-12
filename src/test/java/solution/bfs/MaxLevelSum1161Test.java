package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxLevelSum1161Test {

    private final MaxLevelSum_1161 test = new MaxLevelSum_1161();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7); root.right = new TreeNode(0);
        root.left.left = new TreeNode(7); root.left.right = new TreeNode(-8);
        assertEquals(2, test.maxLevelSum(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.maxLevelSum(new TreeNode(1)));
        TreeNode root = new TreeNode(989);
        root.right = new TreeNode(10250);
        assertEquals(2, test.maxLevelSum(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(3, test.maxLevelSum(root));
    }
}
