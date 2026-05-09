package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumNumbers129Test {

    private final SumNumbers_129 test = new SumNumbers_129();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(25, test.sumNumbers(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.sumNumbers(null));
        assertEquals(1, test.sumNumbers(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9); root.right = new TreeNode(0);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(1);
        assertEquals(1026, test.sumNumbers(root));
    }
}
