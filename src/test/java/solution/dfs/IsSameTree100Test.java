package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsSameTree100Test {

    private final IsSameTree_100 test = new IsSameTree_100();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2); t2.right = new TreeNode(3);
        assertTrue(test.isSameTree(t1, t2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isSameTree(null, null));
        assertFalse(test.isSameTree(new TreeNode(1), null));
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2);
        TreeNode t2 = new TreeNode(1); t2.right = new TreeNode(2);
        assertFalse(test.isSameTree(t1, t2));
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4); t1.left.right = new TreeNode(5);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(2); t2.right = new TreeNode(3);
        t2.left.left = new TreeNode(4); t2.left.right = new TreeNode(5);
        assertTrue(test.isSameTree(t1, t2));
    }
}
