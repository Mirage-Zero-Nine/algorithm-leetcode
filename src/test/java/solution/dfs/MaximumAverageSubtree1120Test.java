package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaximumAverageSubtree1120Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6); root.right = new TreeNode(1);
        assertEquals(6.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(null), 0.0001);
        assertEquals(1.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(new TreeNode(1)), 0.0001);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(5.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }
}
