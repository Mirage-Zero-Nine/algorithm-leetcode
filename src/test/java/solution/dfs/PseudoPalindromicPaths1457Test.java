package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class PseudoPalindromicPaths1457Test {

    private final PseudoPalindromicPaths_1457 test = new PseudoPalindromicPaths_1457();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3); root.right = new TreeNode(1);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        assertEquals(2, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.pseudoPalindromicPaths(null));
        assertEquals(1, test.pseudoPalindromicPaths(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(1);
        assertEquals(2, test.pseudoPalindromicPaths(root));
    }
}
