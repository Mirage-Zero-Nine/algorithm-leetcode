package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LargestBSTSubtree333Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5); root.right = new TreeNode(15);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);
        assertEquals(3, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new LargestBSTSubtree_333().largestBSTSubtree(null));
        assertEquals(1, new LargestBSTSubtree_333().largestBSTSubtree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        assertEquals(7, new LargestBSTSubtree_333().largestBSTSubtree(root));
    }
}
