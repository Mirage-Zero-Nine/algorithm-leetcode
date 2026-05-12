package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class PathSum113Test {

    private final PathSum_113 test = new PathSum_113();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13); root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        List<List<Integer>> result = test.pathSum(root, 22);
        assertEquals(1, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.pathSum(null, 1).size());
        assertEquals(0, test.pathSum(new TreeNode(1), 2).size());
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, test.pathSum(root, 3).size());
    }
}
