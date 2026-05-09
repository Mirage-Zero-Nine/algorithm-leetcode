package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DelNodes1110Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{3, 5});
        assertEquals(3, result.size());
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, new DelNodes_1110().delNodes(null, new int[]{1}).size());
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{1});
        assertEquals(2, result.size());
    }
}
