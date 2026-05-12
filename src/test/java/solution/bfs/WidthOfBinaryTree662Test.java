package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class WidthOfBinaryTree662Test {

    private final WidthOfBinaryTree_662 test = new WidthOfBinaryTree_662();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.right = new TreeNode(2);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        assertEquals(4, test.widthOfBinaryTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.widthOfBinaryTree(null));
        assertEquals(1, test.widthOfBinaryTree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.right.right = new TreeNode(5);
        assertEquals(4, test.widthOfBinaryTree(root));
    }
}
