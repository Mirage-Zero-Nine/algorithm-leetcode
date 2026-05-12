package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BtreeGameWinningMove1145Test {

    private final BtreeGameWinningMove_1145 test = new BtreeGameWinningMove_1145();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertTrue(test.btreeGameWinningMove(root, 7, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.btreeGameWinningMove(new TreeNode(1), 1, 1));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertTrue(test.btreeGameWinningMove(root, 5, 1));
    }
}
