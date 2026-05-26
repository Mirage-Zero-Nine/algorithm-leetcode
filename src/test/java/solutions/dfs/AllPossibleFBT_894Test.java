package solutions.dfs;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllPossibleFBT_894Test {

    private final AllPossibleFBT_894 test = new AllPossibleFBT_894();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.allPossibleFBT(7).size());
        assertEquals(1, test.allPossibleFBT(1).size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.allPossibleFBT(2).size());
        assertEquals(0, test.allPossibleFBT(4).size());
    }

    @Test
    public void testLargeCase() {
        assertEquals(14, test.allPossibleFBT(9).size());
    }

    @Test
    public void testSingleNodeTreeStructure() {
        List<TreeNode> trees = test.allPossibleFBT(1);

        assertEquals(1, trees.size());
        assertEquals(0, trees.getFirst().val);
        assertEquals(1, countNodes(trees.getFirst()));
        assertTrue(isFullBinaryTree(trees.getFirst()));
    }

    @Test
    public void testThreeNodesHappyCase() {
        List<TreeNode> trees = test.allPossibleFBT(3);

        assertEquals(1, trees.size());
        assertEquals(3, countNodes(trees.getFirst()));
        assertTrue(isFullBinaryTree(trees.getFirst()));
    }

    @Test
    public void testFiveNodesHappyCase() {
        List<TreeNode> trees = test.allPossibleFBT(5);

        assertEquals(2, trees.size());
        for (TreeNode root : trees) {
            assertEquals(5, countNodes(root));
            assertTrue(isFullBinaryTree(root));
        }
    }

    @Test
    public void testSevenNodesAllReturnedTreesAreFull() {
        List<TreeNode> trees = test.allPossibleFBT(7);

        assertEquals(5, trees.size());
        for (TreeNode root : trees) {
            assertEquals(7, countNodes(root));
            assertTrue(isFullBinaryTree(root));
        }
    }

    @Test
    public void testZeroNodesEdgeCase() {
        assertTrue(test.allPossibleFBT(0).isEmpty());
    }

    @Test
    public void testNegativeNodeCountEdgeCase() {
        assertTrue(test.allPossibleFBT(-1).isEmpty());
    }

    @Test
    public void testGiantCase() {
        List<TreeNode> trees = test.allPossibleFBT(15);

        assertEquals(429, trees.size());
        for (TreeNode root : trees) {
            assertEquals(15, countNodes(root));
            assertTrue(isFullBinaryTree(root));
        }
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private boolean isFullBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        return root.left != null && root.right != null
                && isFullBinaryTree(root.left)
                && isFullBinaryTree(root.right);
    }
}
