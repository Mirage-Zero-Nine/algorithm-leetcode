package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsEvenOddTree1609Test {

    private final IsEvenOddTree_1609 test = new IsEvenOddTree_1609();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10); root.right = new TreeNode(4);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9); root.right.right = new TreeNode(5);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isEvenOddTree(new TreeNode(2)));
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(2);
        assertTrue(test.isEvenOddTree(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(4);
        assertFalse(test.isEvenOddTree(root));
    }
}
