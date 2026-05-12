package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class TwoSumBSTs1214Test {

    private final TwoSumBSTs_1214 test = new TwoSumBSTs_1214();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1); t1.right = new TreeNode(4);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(0); t2.right = new TreeNode(3);
        assertTrue(test.twoSumBSTs(t1, t2, 5));
        assertFalse(test.twoSumBSTs(t1, t2, 10));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.twoSumBSTs(null, null, 1));
        assertFalse(test.twoSumBSTs(new TreeNode(1), new TreeNode(2), 5));
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(5); t1.left = new TreeNode(3); t1.right = new TreeNode(7);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(2); t2.right = new TreeNode(6);
        assertTrue(test.twoSumBSTs(t1, t2, 9));
    }
}
