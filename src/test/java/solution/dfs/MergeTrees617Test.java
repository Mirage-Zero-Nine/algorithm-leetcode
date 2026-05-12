package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MergeTrees617Test {

    private final MergeTrees_617 test = new MergeTrees_617();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(3); t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);
        TreeNode t2 = new TreeNode(2); t2.left = new TreeNode(1); t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4); t2.right.right = new TreeNode(7);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(3, result.val);
        assertEquals(4, result.left.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.mergeTrees(null, null));
        assertEquals(1, test.mergeTrees(new TreeNode(1), null).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(1); t1.left = new TreeNode(2); t1.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(5); t2.right = new TreeNode(6);
        TreeNode result = test.mergeTrees(t1, t2);
        assertEquals(5, result.val);
    }
}
