package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaximumAverageSubtree_1120Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(6); root.right = new TreeNode(1);
        assertEquals(6.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(null), 0.0001);
        assertEquals(1.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(new TreeNode(1)), 0.0001);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(5.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testAllSameValues() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5); root.right = new TreeNode(5);
        assertEquals(5.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        // Leaf 20 has avg 20, root has avg 15
        assertEquals(20.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(10);
        assertEquals(10.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testNegativeNodeValues() {
        // With negative values, max average could still be a single positive leaf
        TreeNode root = new TreeNode(-5);
        root.left = new TreeNode(10); root.right = new TreeNode(-3);
        assertEquals(10.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testSubtreeAverageBetterThanLeaf() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(9); root.right = new TreeNode(8);
        // Subtree {9} avg=9, {8} avg=8, whole tree avg=6. Max is 9.
        assertEquals(9.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testWholeTreeIsBest() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(10); root.right = new TreeNode(10);
        // All subtrees have avg 10
        assertEquals(10.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testFractionalAverage() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(7);
        // Leaf 7 avg=7, leaf 3 avg=3, subtree(2,7) avg=4.5, whole=(1+2+3+7)/4=3.25
        assertEquals(7.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(root), 0.0001);
    }

    @Test
    public void testGiantTree() {
        // Complete binary tree with increasing values; leaf with max value should be max avg
        TreeNode[] nodes = new TreeNode[128];
        for (int i = 1; i <= 127; i++) nodes[i] = new TreeNode(i);
        for (int i = 1; i <= 63; i++) {
            nodes[i].left = nodes[2 * i];
            nodes[i].right = nodes[2 * i + 1];
        }
        // Max leaf value is 127
        assertEquals(127.0, new MaximumAverageSubtree_1120().maximumAverageSubtree(nodes[1]), 0.0001);
    }
}
