package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class ClosestValue270Test {

    private final ClosestValue_270 test = new ClosestValue_270();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        assertEquals(4, test.closestValue(root, 3.714286));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.closestValue(new TreeNode(1), 4.428571));
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertEquals(2, test.closestValue(root, 2.0));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(4);
        assertEquals(4, test.closestValue(root, 4.1));
    }
}
