package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindTilt563Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new FindTilt_563().findTilt(null));
        assertEquals(0, new FindTilt_563().findTilt(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(9);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        assertEquals(15, new FindTilt_563().findTilt(root));
    }
}
