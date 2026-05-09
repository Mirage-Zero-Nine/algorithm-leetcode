package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestor235Test {

    private final LowestCommonAncestor_235 test = new LowestCommonAncestor_235();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(6);
        TreeNode n2 = new TreeNode(2); TreeNode n8 = new TreeNode(8);
        root.left = n2; root.right = n8;
        n2.left = new TreeNode(0); n2.right = new TreeNode(4);
        assertEquals(6, test.lowestCommonAncestor(root, n2, n8).val);
    }

    @Test
    public void testEdgeCases() {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        root.left = n1;
        assertEquals(2, test.lowestCommonAncestor(root, root, n1).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(6);
        TreeNode n2 = new TreeNode(2); TreeNode n4 = new TreeNode(4);
        root.left = n2; n2.right = n4;
        assertEquals(2, test.lowestCommonAncestor(root, n2, n4).val);
    }
}
