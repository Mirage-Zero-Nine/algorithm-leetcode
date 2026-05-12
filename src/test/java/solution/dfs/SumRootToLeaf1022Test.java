package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumRootToLeaf1022Test {

    private final SumRootToLeaf_1022 test = new SumRootToLeaf_1022();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0); root.right = new TreeNode(1);
        root.left.left = new TreeNode(0); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(1);
        assertEquals(22, test.sumRootToLeaf(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.sumRootToLeaf(null));
        assertEquals(1, test.sumRootToLeaf(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(0);
        assertEquals(5, test.sumRootToLeaf(root));
    }
}
