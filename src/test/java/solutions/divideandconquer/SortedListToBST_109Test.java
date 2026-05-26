package solutions.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SortedListToBST_109Test {

    private final SortedListToBST_109 test = new SortedListToBST_109();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        TreeNode result = test.sortedListToBST(build(-10, -3, 0, 5, 9));
        assertEquals(0, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortedListToBST(null));
        assertEquals(1, test.sortedListToBST(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3, 4, 5, 6, 7));
        assertEquals(4, result.val);
    }

    @Test
    public void testTwoElements() {
        TreeNode result = test.sortedListToBST(build(1, 2));
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertNull(result.right);
    }

    @Test
    public void testThreeElements() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3));
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testFourElements() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3, 4));
        // mid of [1,2,3,4] with fast/slow: slow ends at index 2 -> val 3
        assertEquals(3, result.val);
    }

    @Test
    public void testNegativeValues() {
        TreeNode result = test.sortedListToBST(build(-5, -3, -1));
        assertEquals(-3, result.val);
        assertEquals(-5, result.left.val);
        assertEquals(-1, result.right.val);
    }

    @Test
    public void testAllSameValues() {
        TreeNode result = test.sortedListToBST(build(5, 5, 5, 5, 5));
        assertEquals(5, result.val);
    }

    @Test
    public void testBalancedProperty() {
        TreeNode result = test.sortedListToBST(build(1, 2, 3, 4, 5, 6, 7));
        // Should be balanced: left subtree depth and right subtree depth differ by at most 1
        int leftDepth = depth(result.left);
        int rightDepth = depth(result.right);
        assertTrue(Math.abs(leftDepth - rightDepth) <= 1);
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = i;
        TreeNode result = test.sortedListToBST(build(vals));
        // Root should be the middle element
        assertEquals(500, result.val);
        // Tree should be balanced
        int leftDepth = depth(result.left);
        int rightDepth = depth(result.right);
        assertTrue(Math.abs(leftDepth - rightDepth) <= 1);
    }

    @Test
    public void testSixElements() {
        TreeNode result = test.sortedListToBST(build(10, 20, 30, 40, 50, 60));
        // mid of 6 elements: slow ends at index 3 -> val 40
        assertEquals(40, result.val);
    }

    @Test
    public void testNullHead() {
        assertNull(test.sortedListToBST(null));
    }

    @Test
    public void testSingleNode() {
        TreeNode result = test.sortedListToBST(build(42));
        assertEquals(42, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testLargeListBSTAndBalanced() {
        int n = 1000;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        TreeNode result = test.sortedListToBST(build(vals));
        // Property: tree is BST
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
        // Property: tree is height-balanced
        int minD = minDepth(result), maxD = depth(result);
        assertTrue(maxD - minD <= 1, "Tree not height-balanced: max=" + maxD + " min=" + minD);
    }

    @Test
    public void testNegativeValuesList() {
        TreeNode result = test.sortedListToBST(build(-100, -50, -25, -10, -1));
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
        assertTrue(Math.abs(depth(result.left) - depth(result.right)) <= 1);
    }

    @Test
    public void testAllSameValuesBST() {
        // All same values: BST property uses <= for left in some impls
        TreeNode result = test.sortedListToBST(build(7, 7, 7, 7, 7, 7, 7));
        // Inorder should match input
        var inorder = new java.util.ArrayList<Integer>();
        inorderTraversal(result, inorder);
        assertEquals(java.util.List.of(7, 7, 7, 7, 7, 7, 7), inorder);
    }

    @Test
    public void testInorderMatchesOriginalList() {
        int[] vals = {-10, -3, 0, 5, 9, 12, 15, 20};
        TreeNode result = test.sortedListToBST(build(vals));
        var inorder = new java.util.ArrayList<Integer>();
        inorderTraversal(result, inorder);
        var expected = new java.util.ArrayList<Integer>();
        for (int v : vals) expected.add(v);
        assertEquals(expected, inorder);
    }

    @Test
    public void testSequential1ToN() {
        int n = 15;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        TreeNode result = test.sortedListToBST(build(vals));
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
        var inorder = new java.util.ArrayList<Integer>();
        inorderTraversal(result, inorder);
        assertEquals(n, inorder.size());
        for (int i = 0; i < n; i++) assertEquals(i + 1, inorder.get(i));
    }

    @Test
    public void testTwoNodesStructure() {
        TreeNode result = test.sortedListToBST(build(10, 20));
        // With fast/slow: slow lands on index 1 -> val 20 is root, left subtree is [10]
        var inorder = new java.util.ArrayList<Integer>();
        inorderTraversal(result, inorder);
        assertEquals(java.util.List.of(10, 20), inorder);
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testPropertyHeightBalancedLarge() {
        int n = 500;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i * 3 - 200;
        TreeNode result = test.sortedListToBST(build(vals));
        assertTrue(isBalanced(result), "Tree is not height-balanced for n=" + n);
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    private int minDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.min(minDepth(node.left), minDepth(node.right));
    }

    private boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val < min || node.val > max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    private boolean isBalanced(TreeNode node) {
        return balancedHeight(node) != -1;
    }

    private int balancedHeight(TreeNode node) {
        if (node == null) return 0;
        int l = balancedHeight(node.left), r = balancedHeight(node.right);
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) return -1;
        return 1 + Math.max(l, r);
    }

    private void inorderTraversal(TreeNode node, java.util.List<Integer> list) {
        if (node == null) return;
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }
}
