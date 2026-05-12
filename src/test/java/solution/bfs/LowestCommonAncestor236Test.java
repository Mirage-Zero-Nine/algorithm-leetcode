package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestor236Test {

    private final LowestCommonAncestor_236 test = new LowestCommonAncestor_236();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5); TreeNode n1 = new TreeNode(1);
        root.left = n5; root.right = n1;
        n5.left = new TreeNode(6); n5.right = new TreeNode(2);
        n1.left = new TreeNode(0); n1.right = new TreeNode(8);
        assertEquals(3, test.lowestCommonAncestor(root, n5, n1).val);
    }

    @Test
    public void testEdgeCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertEquals(1, test.lowestCommonAncestor(root, root, n2).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5); TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6); TreeNode n4 = new TreeNode(4);
        root.left = n5; root.right = n1;
        n5.left = n6; n5.right = n4;
        assertEquals(5, test.lowestCommonAncestor(root, n6, n4).val);
    }
}
