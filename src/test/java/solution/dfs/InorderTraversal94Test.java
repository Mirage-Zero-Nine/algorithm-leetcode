package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InorderTraversal94Test {

    private final InorderTraversal_94 test = new InorderTraversal_94();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); root.right.left = new TreeNode(3);
        assertEquals(List.of(1, 3, 2), test.inorderTraversal(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.inorderTraversal(null));
        assertEquals(List.of(1), test.inorderTraversal(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), test.inorderTraversal(root));
    }
}
