package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsValidSequence1430Test {

    private final IsValidSequence_1430 test = new IsValidSequence_1430();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1); root.right = new TreeNode(0);
        root.left.left = new TreeNode(0); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        assertTrue(test.isValidSequence(root, new int[]{0, 0, 0}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isValidSequence(null, new int[]{1}));
        assertFalse(test.isValidSequence(new TreeNode(1), new int[]{2}));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1); root.right = new TreeNode(0);
        assertFalse(test.isValidSequence(root, new int[]{0, 1, 1}));
    }
}
