package solution.dfs;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalanceBST_1382Test {

    private final BalanceBST_1382 test = new BalanceBST_1382();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        TreeNode result = test.balanceBST(root);
        assertEquals(2, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.balanceBST(null));
        assertEquals(1, test.balanceBST(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(6);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5); root.right.right = new TreeNode(7);
        TreeNode result = test.balanceBST(root);
        assertEquals(4, result.val);
    }

    @Test
    public void testAlreadyBalancedTreeStaysValid() {
        TreeNode root = node(4,
                node(2, new TreeNode(1), new TreeNode(3)),
                node(6, new TreeNode(5), new TreeNode(7)));

        TreeNode result = test.balanceBST(root);

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), inorder(result));
        assertTrue(isBalanced(result));
    }

    @Test
    public void testLeftSkewedTreeBecomesBalanced() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);

        TreeNode result = test.balanceBST(root);

        assertEquals(List.of(1, 2, 3, 4, 5), inorder(result));
        assertTrue(isBalanced(result));
    }

    @Test
    public void testTwoNodeTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        TreeNode result = test.balanceBST(root);

        assertEquals(List.of(1, 2), inorder(result));
        assertTrue(isBalanced(result));
    }

    @Test
    public void testThreeNodeRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);

        TreeNode result = test.balanceBST(root);

        assertEquals(2, result.val);
        assertEquals(List.of(1, 2, 3), inorder(result));
        assertTrue(isBalanced(result));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-5);
        root.right = new TreeNode(-4);
        root.right.right = new TreeNode(-3);
        root.right.right.right = new TreeNode(-2);
        root.right.right.right.right = new TreeNode(-1);

        TreeNode result = test.balanceBST(root);

        assertEquals(List.of(-5, -4, -3, -2, -1), inorder(result));
        assertTrue(isBalanced(result));
    }

    @Test
    public void testMixedNegativeAndPositiveValues() {
        TreeNode root = new TreeNode(-3);
        root.right = new TreeNode(-1);
        root.right.right = new TreeNode(0);
        root.right.right.right = new TreeNode(2);
        root.right.right.right.right = new TreeNode(5);

        TreeNode result = test.balanceBST(root);

        assertEquals(List.of(-3, -1, 0, 2, 5), inorder(result));
        assertTrue(isBalanced(result));
    }

    @Test
    public void testGiantRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= 127; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }

        TreeNode result = test.balanceBST(root);

        assertEquals(127, inorder(result).size());
        assertTrue(isBalanced(result));
        assertEquals(64, result.val);
    }

    private TreeNode node(int val, TreeNode left, TreeNode right) {
        TreeNode root = new TreeNode(val);
        root.left = left;
        root.right = right;
        return root;
    }

    private List<Integer> inorder(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        inorder(root, output);
        return output;
    }

    private void inorder(TreeNode root, List<Integer> output) {
        if (root == null) {
            return;
        }

        inorder(root.left, output);
        output.add(root.val);
        inorder(root.right, output);
    }

    private boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }
}