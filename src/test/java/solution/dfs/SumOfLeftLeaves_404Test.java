package solution.dfs;

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
}
