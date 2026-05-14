package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class UpsideDownBinaryTree_156Test {

    private final UpsideDownBinaryTree_156 test = new UpsideDownBinaryTree_156();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(4, result.val);
        assertEquals(5, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.upsideDownBinaryTree(null));
        assertEquals(1, test.upsideDownBinaryTree(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(2, result.val);
        assertEquals(3, result.left.val);
        assertEquals(1, result.right.val);
    }

    @Test
    public void testNullRoot() {
        assertNull(test.upsideDownBinaryTree(null));
    }

    @Test
    public void testSingleNode() {
        TreeNode root = new TreeNode(42);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(42, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testTwoLevelsNoRight() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(2, result.val);
        assertNull(result.left);
        assertEquals(1, result.right.val);
    }

    @Test
    public void testThreeLevelsDeep() {
        // 1 -> left:2, right:3; 2 -> left:4, right:5; 4 -> left:6, right:7
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6); root.left.left.right = new TreeNode(7);
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(6, result.val);
        assertEquals(7, result.left.val);
        assertEquals(4, result.right.val);
        assertEquals(5, result.right.left.val);
        assertEquals(2, result.right.right.val);
        assertEquals(3, result.right.right.left.val);
        assertEquals(1, result.right.right.right.val);
    }

    @Test
    public void testIterativeMatchesRecursive() {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2); root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4); root1.left.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2); root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4); root2.left.right = new TreeNode(5);

        TreeNode r1 = test.upsideDownBinaryTree(root1);
        TreeNode r2 = test.iterative(root2);
        assertEquals(r1.val, r2.val);
        assertEquals(r1.left.val, r2.left.val);
        assertEquals(r1.right.val, r2.right.val);
    }

    @Test
    public void testIterativeNull() {
        assertNull(test.iterative(null));
    }

    @Test
    public void testIterativeSingleNode() {
        TreeNode root = new TreeNode(99);
        TreeNode result = test.iterative(root);
        assertEquals(99, result.val);
    }

    @Test
    public void testGiantLeftSkewedTree() {
        // Build a left-skewed tree of depth 100
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        for (int i = 1; i <= 100; i++) {
            cur.left = new TreeNode(i);
            cur.right = new TreeNode(i + 1000);
            cur = cur.left;
        }
        TreeNode result = test.upsideDownBinaryTree(root);
        assertEquals(100, result.val); // leftmost node becomes root
    }
}
