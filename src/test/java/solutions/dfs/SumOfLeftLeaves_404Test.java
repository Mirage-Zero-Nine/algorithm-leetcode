package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumOfLeftLeaves_404Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(24, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(null));
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(4, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testOnlyRightChildren() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testOnlyLeftChildren() {
        // Left chain: 1->2->3, leaf is 3 which is left leaf of 2
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-5);
        root.right = new TreeNode(3);
        assertEquals(-5, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testMultipleLeftLeaves() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(6);
        // Left leaves: 4 and 6
        assertEquals(10, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testLeftChildNotLeaf() {
        // Left child has children, so it's not a leaf
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        // Only left leaf is 3
        assertEquals(3, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testGiantTree() {
        // Complete binary tree depth 10: left leaves are at the last level at even positions
        TreeNode[] nodes = new TreeNode[1024];
        for (int i = 1; i <= 1023; i++) nodes[i] = new TreeNode(i);
        for (int i = 1; i <= 511; i++) {
            nodes[i].left = nodes[2 * i];
            nodes[i].right = nodes[2 * i + 1];
        }
        // Left leaves are nodes[2*i] where i is 512..1023 step 2 -> nodes at indices 512,514,...,1022
        int expected = 0;
        for (int i = 512; i <= 1022; i += 2) expected += i;
        assertEquals(expected, new SumOfLeftLeaves_404().sumOfLeftLeaves(nodes[1]));
    }

    // --- NEW TESTS ---

    @Test
    public void testNullReturnsZero() {
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(null));
    }

    @Test
    public void testSingleNodeReturnsZero() {
        // A single node is not a left leaf (it's the root)
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(new TreeNode(42)));
    }

    @Test
    public void testRootWithOneLeftLeaf() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(7);
        assertEquals(7, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testRootWithOnlyRightChild() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(8);
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testLeftChildHasChildrenNotCounted() {
        // root.left has children so it's NOT a leaf -> not counted
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(20);
        // Only left leaf is 10 (left child of node 2)
        assertEquals(10, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testMultipleLeftLeavesAtDifferentDepths() {
        //        1
        //       / \
        //      2   3
        //     /   / \
        //    4   5   6
        //       /
        //      7
        // Left leaves: 4 (left of 2), 7 (left of 5) => sum = 11
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        assertEquals(11, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testNegativeLeftLeafValues() {
        //      0
        //     / \
        //   -3   2
        //       /
        //     -8
        // Left leaves: -3 (left of root), -8 (left of 2) => sum = -11
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-3);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(-8);
        assertEquals(-11, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testAllLeavesAreRightLeaves() {
        //      1
        //     / \
        //    2   3
        //     \   \
        //      5   7
        // Leaves: 5 (right of 2), 7 (right of 3) — no left leaves
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        assertEquals(0, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testAllLeavesAreLeftLeaves() {
        //        1
        //       / \
        //      2   3
        //     /   /
        //    4   6
        // Leaves: 4 (left of 2), 6 (left of 3) — all leaves are left leaves
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(6);
        assertEquals(10, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }

    @Test
    public void testLargeDeepLeftSkewedTree() {
        // Left-skewed tree of depth 100: 1->2->3->...->100
        // Only the deepest node (100) is a left leaf
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 100; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        assertEquals(100, new SumOfLeftLeaves_404().sumOfLeftLeaves(root));
    }
}
