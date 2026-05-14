package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumRootToLeaf_1022Test {

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

    @Test
    public void testSingleZero() {
        assertEquals(0, test.sumRootToLeaf(new TreeNode(0)));
    }

    @Test
    public void testLeftSkewed() {
        // Path: 1->0->1 = binary 101 = 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(1);
        assertEquals(5, test.sumRootToLeaf(root));
    }

    @Test
    public void testRightSkewed() {
        // Path: 1->1->0 = binary 110 = 6
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(0);
        assertEquals(6, test.sumRootToLeaf(root));
    }

    @Test
    public void testAllZeros() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(0, test.sumRootToLeaf(root));
    }

    @Test
    public void testAllOnes() {
        // Two paths: 1->1 = 11 = 3, 1->1 = 11 = 3, sum = 6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        assertEquals(6, test.sumRootToLeaf(root));
    }

    @Test
    public void testDeepTree() {
        // Path: 1->0->1->1 = 1011 = 11
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        assertEquals(11, test.sumRootToLeaf(root));
    }

    @Test
    public void testGiantTree() {
        // Full tree depth 3: root=1, level1=[0,1], level2=[0,1,0,1]
        // Paths: 1->0->0=100=4, 1->0->1=101=5, 1->1->0=110=6, 1->1->1=111=7, sum=22
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0); root.right = new TreeNode(1);
        root.left.left = new TreeNode(0); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(1);
        assertEquals(22, test.sumRootToLeaf(root));
    }
}
