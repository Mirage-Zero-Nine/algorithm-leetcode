package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class GoodNodes1448Test {

    private final GoodNodes_1448 test = new GoodNodes_1448();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1); root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(5);
        assertEquals(4, test.goodNodes(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.goodNodes(null));
        assertEquals(1, test.goodNodes(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(3); root.right = new TreeNode(4);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(2);
        assertEquals(4, test.goodNodes(root));
    }
}
