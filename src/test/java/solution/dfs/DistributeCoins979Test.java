package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DistributeCoins979Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(2, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, new DistributeCoins_979().distributeCoins(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(3); root.right = new TreeNode(0);
        assertEquals(3, new DistributeCoins_979().distributeCoins(root));
    }
}
