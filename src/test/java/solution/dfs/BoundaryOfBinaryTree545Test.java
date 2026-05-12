package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BoundaryOfBinaryTree545Test {

    private final BoundaryOfBinaryTree_545 test = new BoundaryOfBinaryTree_545();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3); root.right.left = new TreeNode(2);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.boundaryOfBinaryTree(null));
        assertEquals(List.of(1), test.boundaryOfBinaryTree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(List.of(1, 2, 4, 5, 6, 7, 3), test.boundaryOfBinaryTree(root));
    }
}
