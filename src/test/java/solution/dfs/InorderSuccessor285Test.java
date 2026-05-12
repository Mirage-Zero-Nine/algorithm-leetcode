package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class InorderSuccessor285Test {

    private final InorderSuccessor_285 test = new InorderSuccessor_285();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1); TreeNode n3 = new TreeNode(3);
        root.left = n1; root.right = n3;
        assertEquals(2, test.inorderSuccessor(root, n1).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.inorderSuccessor(null, new TreeNode(1)));
        TreeNode root = new TreeNode(1);
        assertNull(test.inorderSuccessor(root, root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        TreeNode n3 = new TreeNode(3); TreeNode n6 = new TreeNode(6);
        root.left = n3; root.right = n6;
        n3.left = new TreeNode(2); n3.right = new TreeNode(4);
        assertEquals(4, test.inorderSuccessor(root, n3).val);
    }
}
