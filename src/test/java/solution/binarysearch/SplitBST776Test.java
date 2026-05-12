package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SplitBST776Test {

    private final SplitBST_776 test = new SplitBST_776();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode[] result = test.splitBST(root, 2);
        assertEquals(2, result[0].val);
        assertEquals(4, result[1].val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode[] result = test.splitBST(null, 1);
        assertNull(result[0]);
        assertNull(result[1]);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        TreeNode[] result = test.splitBST(root, 4);
        assertEquals(3, result[0].val);
        assertEquals(5, result[1].val);
    }
}
