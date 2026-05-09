package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindNearestRightNode1602Test {

    private final FindNearestRightNode_1602 test = new FindNearestRightNode_1602();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); TreeNode n3 = new TreeNode(3);
        root.left = n2; root.right = n3;
        assertEquals(3, test.findNearestRightNode(root, n2).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertNull(test.findNearestRightNode(root, n2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4); TreeNode n5 = new TreeNode(5);
        root.left = n2; root.right = n3;
        n2.left = n4; n2.right = n5;
        assertEquals(5, test.findNearestRightNode(root, n4).val);
    }
}
