package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindTilt_563Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, new FindTilt_563().findTilt(null));
        assertEquals(0, new FindTilt_563().findTilt(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(9);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        assertEquals(15, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testLeftSkewedTree() {
        // 1 -> 2 -> 3 (all left children)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        // node 3: tilt = 0, node 2: tilt = |3 - 0| = 3, node 1: tilt = |5 - 0| = 5
        assertEquals(8, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testRightSkewedTree() {
        // 1 -> 2 -> 3 (all right children)
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        // node 3: tilt = 0, node 2: tilt = |0 - 3| = 3, node 1: tilt = |0 - 5| = 5
        assertEquals(8, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testPerfectBalancedTree() {
        // Perfect tree: all subtrees have equal sums -> tilt at each internal node is 0
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(3);
        assertEquals(0, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        // node -2: tilt=0, node -3: tilt=0, node -1: tilt=|-2 - (-3)| = 1
        assertEquals(1, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testMixedPositiveNegative() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(5); root.right = new TreeNode(-5);
        // node 5: tilt=0, node -5: tilt=0, node 0: tilt=|5 - (-5)| = 10
        assertEquals(10, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testSingleLeftChild() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        // node 2: tilt=0, node 1: tilt=|2 - 0| = 2
        assertEquals(2, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testLeetCodeExample21357() {
        // LeetCode example: [21,7,14,1,1,2,2,3,3]
        TreeNode root = new TreeNode(21);
        root.left = new TreeNode(7); root.right = new TreeNode(14);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(2); root.right.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3); root.left.left.right = new TreeNode(3);
        // node(3): 0, node(3): 0, node(1 left-left): |3-3|=0, node(1 left-right): 0
        // node(2 right-left): 0, node(2 right-right): 0
        // node(7): |1+3+3 - 1| = 6, node(14): |2 - 2| = 0
        // node(21): |7+1+3+3+1 - (14+2+2)| = |15 - 18| = 3
        assertEquals(9, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testGiantTree() {
        // Build a deep left-skewed tree with 100 nodes
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 100; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        // Each node has tilt = sum of its left subtree (since right is always 0)
        // This is a valid large test; just verify it runs and returns a positive value
        int result = new FindTilt_563().findTilt(root);
        // The tilt should be sum of all left subtree sums at each node
        // Manually: node i has left subtree sum = sum(i+1..100), tilt = that value
        // Total = sum_{i=1}^{99} sum(i+1..100)
        // Let's compute expected: sum(k..100) for k from 2 to 100
        int expected = 0;
        for (int i = 1; i <= 99; i++) {
            // subtree sum below node i = sum from (i+1) to 100
            int subtreeSum = 0;
            for (int j = i + 1; j <= 100; j++) {
                subtreeSum += j;
            }
            expected += subtreeSum; // tilt at node i = |subtreeSum - 0|
        }
        assertEquals(expected, result);
    }
}
