package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class HasPathSum_112Test {

    private final HasPathSum_112 test = new HasPathSum_112();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7); root.left.left.right = new TreeNode(2);
        assertTrue(test.hasPathSum(root, 22));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.hasPathSum(null, 1));
        assertFalse(test.hasPathSum(new TreeNode(1), 2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertTrue(test.hasPathSum(root, 3));
        assertFalse(test.hasPathSum(root, 5));
    }

    @Test
    public void testSingleNodeMatch() {
        assertTrue(test.hasPathSum(new TreeNode(5), 5));
    }

    @Test
    public void testSingleNodeNoMatch() {
        assertFalse(test.hasPathSum(new TreeNode(5), 0));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertTrue(test.hasPathSum(root, -5));
    }

    @Test
    public void testZeroSum() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-1);
        assertTrue(test.hasPathSum(root, 0));
    }

    @Test
    public void testPathMustEndAtLeaf() {
        // Sum matches at internal node but not at leaf
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        // 1+2=3 but 2 is not a leaf
        assertFalse(test.hasPathSum(root, 3));
        assertTrue(test.hasPathSum(root, 6));
    }

    @Test
    public void testRightPath() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertTrue(test.hasPathSum(root, 4));
    }

    @Test
    public void testGiantTree() {
        // Chain of 100 nodes each with value 1, sum should be 100
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 1; i < 100; i++) {
            current.left = new TreeNode(1);
            current = current.left;
        }
        assertTrue(test.hasPathSum(root, 100));
        assertFalse(test.hasPathSum(root, 99));
    }

    @Test
    public void testNullRootAlwaysFalse() {
        assertFalse(test.hasPathSum(null, 0));
        assertFalse(test.hasPathSum(null, -1));
        assertFalse(test.hasPathSum(null, Integer.MAX_VALUE));
    }

    @Test
    public void testMultiplePathsOnlySomeSumToTarget() {
        //       5
        //      / \
        //     3   7
        //    / \   \
        //   1   4   2
        // Paths: 5->3->1=9, 5->3->4=12, 5->7->2=14
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(2);
        assertTrue(test.hasPathSum(root, 12));   // 5+3+4
        assertTrue(test.hasPathSum(root, 9));    // 5+3+1
        assertTrue(test.hasPathSum(root, 14));   // 5+7+2
        assertFalse(test.hasPathSum(root, 8));   // no path
        assertFalse(test.hasPathSum(root, 5));   // root alone not leaf
    }

    @Test
    public void testNegativeValuesAlongPath() {
        //     10
        //    /  \
        //  -3    2
        //  /      \
        // -4       1
        // Paths: 10->-3->-4=3, 10->2->1=13
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(-3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(-4);
        root.right.right = new TreeNode(1);
        assertTrue(test.hasPathSum(root, 3));
        assertTrue(test.hasPathSum(root, 13));
        assertFalse(test.hasPathSum(root, 10));
        assertFalse(test.hasPathSum(root, 7));
    }

    @Test
    public void testTargetZeroWithCancellingValues() {
        //      5
        //     / \
        //    3  -5
        //   /
        //  -8
        // Paths: 5->3->-8=0, 5->-5=0
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(-5);
        root.left.left = new TreeNode(-8);
        assertTrue(test.hasPathSum(root, 0));
    }

    @Test
    public void testPartialSumMatchesButNotAtLeafDeeper() {
        //     1
        //      \
        //       2
        //        \
        //         3
        //          \
        //           4
        // Only valid path: 1->2->3->4=10
        // 1+2+3=6 is NOT at a leaf
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertFalse(test.hasPathSum(root, 1));
        assertFalse(test.hasPathSum(root, 3));
        assertFalse(test.hasPathSum(root, 6));
        assertTrue(test.hasPathSum(root, 10));
    }

    @Test
    public void testLongChainMixedValues() {
        // Chain: 100, -50, 25, -12, 6, -3 => sum = 66
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(-50);
        root.left.left = new TreeNode(25);
        root.left.left.left = new TreeNode(-12);
        root.left.left.left.left = new TreeNode(6);
        root.left.left.left.left.left = new TreeNode(-3);
        assertTrue(test.hasPathSum(root, 66));
        assertFalse(test.hasPathSum(root, 100));
        assertFalse(test.hasPathSum(root, 50));
    }

    @Test
    public void testPropertyTrueIffExactLeafPathSum() {
        //       2
        //      / \
        //     1   3
        //    /
        //   4
        // Paths: 2->1->4=7, 2->3=5
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        // Exhaustively check: only 7 and 5 should return true
        assertTrue(test.hasPathSum(root, 7));
        assertTrue(test.hasPathSum(root, 5));
        for (int t = -10; t <= 20; t++) {
            if (t == 7 || t == 5) {
                assertTrue(test.hasPathSum(root, t), "Expected true for target=" + t);
            } else {
                assertFalse(test.hasPathSum(root, t), "Expected false for target=" + t);
            }
        }
    }

    @Test
    public void testLargeBalancedTree() {
        // Complete binary tree depth 10 (1023 nodes), all values = 1
        // Every root-to-leaf path has sum = 10
        TreeNode root = buildCompleteTree(10, 1);
        assertTrue(test.hasPathSum(root, 10));
        assertFalse(test.hasPathSum(root, 9));
        assertFalse(test.hasPathSum(root, 11));
    }

    private TreeNode buildCompleteTree(int depth, int val) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildCompleteTree(depth - 1, val);
        node.right = buildCompleteTree(depth - 1, val);
        return node;
    }
}
