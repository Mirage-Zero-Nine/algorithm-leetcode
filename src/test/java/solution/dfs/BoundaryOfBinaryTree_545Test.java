package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BoundaryOfBinaryTree_545Test {

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

    @Test
    public void testOnlyLeftSubtree() {
        // root has only left subtree: 1 -> 2 -> 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testOnlyRightSubtree() {
        // root has only right subtree: 1 -> 2 -> 3
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(1, 3, 2), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testLeftBoundaryGoesRight() {
        // Left boundary goes right when no left child: 1 -> left:2(no left child) -> 2.right=4(leaf)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 4, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testRightBoundaryGoesLeft() {
        // Right boundary goes left when no right child
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        assertEquals(List.of(1, 2, 5, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testCompleteTreeDepth3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8); root.left.left.right = new TreeNode(9);
        root.right.right.left = new TreeNode(10); root.right.right.right = new TreeNode(11);
        assertEquals(List.of(1, 2, 4, 8, 9, 5, 6, 10, 11, 7, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testRootWithTwoLeaves() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testZigzagLeftBoundary() {
        // Left boundary zigzags: 1 -> left:2 -> 2.right:4 -> 4.left:5(leaf)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(5);
        assertEquals(List.of(1, 2, 4, 5, 3), test.boundaryOfBinaryTree(root));
    }

    @Test
    public void testGiantTree() {
        // Build a deep left-skewed tree with right leaf at bottom
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 20; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        current.right = new TreeNode(99);
        // Boundary: root(0), left boundary (1..20), leaves (99), no right boundary
        List<Integer> result = test.boundaryOfBinaryTree(root);
        assertEquals(0, result.get(0));
        assertEquals(99, result.get(result.size() - 1));
        assertEquals(22, result.size()); // root + 20 left boundary nodes + 1 leaf
    }
}
