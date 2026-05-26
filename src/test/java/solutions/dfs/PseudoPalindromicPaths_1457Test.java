package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class PseudoPalindromicPaths_1457Test {

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

    @Test
    public void testAllSameValues() {
        // All paths have same value -> always palindromic
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        assertEquals(2, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testNoPalindromicPath() {
        // Path 2->3->4 has all different values, not palindromic (3 odd occurrences)
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3); root.right = new TreeNode(4);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(7); root.right.right = new TreeNode(8);
        assertEquals(0, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testSinglePathPalindromic() {
        // Path: 1->2->1 -> palindromic (1 appears twice, 2 once -> one odd)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        assertEquals(1, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testSinglePathNotPalindromic() {
        // Path: 1->2->3 -> not palindromic (three different values, three odd occurrences)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(0, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testEvenLengthPalindromicPath() {
        // Path: 1->1 -> all even occurrences -> palindromic
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        assertEquals(1, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testAllPathsPalindromic() {
        // Both paths: 1->2->1 and 1->2->1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(1); root.right.right = new TreeNode(1);
        assertEquals(2, test.pseudoPalindromicPaths(root));
    }

    @Test
    public void testGiantTree() {
        // Build a complete binary tree of depth 4, all nodes value 1 -> all paths palindromic
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(1);
        root.left.left.left = new TreeNode(1); root.left.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(1); root.left.right.right = new TreeNode(1);
        root.right.left.left = new TreeNode(1); root.right.left.right = new TreeNode(1);
        root.right.right.left = new TreeNode(1); root.right.right.right = new TreeNode(1);
        // 8 leaf nodes, all paths are [1,1,1,1] -> palindromic
        assertEquals(8, test.pseudoPalindromicPaths(root));
    }
}
