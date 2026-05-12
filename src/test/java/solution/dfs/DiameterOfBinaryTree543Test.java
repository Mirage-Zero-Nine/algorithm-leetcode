package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DiameterOfBinaryTree543Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(3, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new DiameterOfBinaryTree_543().diameterOfBinaryTree(null));
        assertEquals(0, new DiameterOfBinaryTree_543().diameterOfBinaryTree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        assertEquals(4, new DiameterOfBinaryTree_543().diameterOfBinaryTree(root));
    }
}
