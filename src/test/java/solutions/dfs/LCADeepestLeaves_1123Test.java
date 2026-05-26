package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LCADeepestLeaves_1123Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5); root.right = new TreeNode(1);
        root.left.left = new TreeNode(6); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0); root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7); root.left.right.right = new TreeNode(4);
        assertEquals(2, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(new LCADeepestLeaves_1123().lcaDeepestLeaves(null));
        assertEquals(1, new LCADeepestLeaves_1123().lcaDeepestLeaves(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testLeftDeeper() {
        // 1 -> left: 2 -> left: 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        assertEquals(3, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testRightDeeper() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        assertEquals(4, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testAllLeavesAtSameDepth() {
        // Perfect binary tree depth 2: LCA is root
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(1, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testDeepestLeavesOnLeftSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6); root.left.right.right = new TreeNode(7);
        assertEquals(2, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testSingleChainLeft() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(4, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-4); root.right.right = new TreeNode(-5);
        assertEquals(-1, new LCADeepestLeaves_1123().lcaDeepestLeaves(root).val);
    }

    @Test
    public void testGiantTree() {
        // Build a complete binary tree of depth 10 (1023 nodes)
        TreeNode[] nodes = new TreeNode[1024];
        for (int i = 1; i <= 1023; i++) nodes[i] = new TreeNode(i);
        for (int i = 1; i <= 511; i++) {
            nodes[i].left = nodes[2 * i];
            nodes[i].right = nodes[2 * i + 1];
        }
        // All leaves at same depth -> LCA is root
        assertEquals(1, new LCADeepestLeaves_1123().lcaDeepestLeaves(nodes[1]).val);
    }
}
