package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SortedArrayToBST_108Test {

    private final SortedArrayToBST_108 test = new SortedArrayToBST_108();

    @Test
    public void testHappyCases() {
        TreeNode result = test.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        assertEquals(0, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortedArrayToBST(new int[]{}));
        assertEquals(1, test.sortedArrayToBST(new int[]{1}).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(4, result.val);
    }

    @Test
    public void testTwoElements() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2});
        assertEquals(1, result.val);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testThreeElements() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2, 3});
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertEquals(3, result.right.val);
    }

    @Test
    public void testNegativeValues() {
        TreeNode result = test.sortedArrayToBST(new int[]{-5, -3, -1, 0, 2});
        assertEquals(-1, result.val);
    }

    @Test
    public void testFourElements() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2, 3, 4});
        assertEquals(2, result.val);
        assertEquals(1, result.left.val);
        assertEquals(3, result.right.val);
        assertEquals(4, result.right.right.val);
    }

    @Test
    public void testBalancedStructure() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7});
        // Root should be 4, left subtree root 2, right subtree root 6
        assertEquals(4, result.val);
        assertEquals(2, result.left.val);
        assertEquals(6, result.right.val);
    }

    @Test
    public void testLeafNodes() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7});
        // Leaves: 1, 3, 5, 7
        assertNull(result.left.left.left);
        assertNull(result.left.left.right);
    }

    @Test
    public void testGiantArray() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = i;
        TreeNode result = test.sortedArrayToBST(nums);
        assertEquals(499, result.val);
    }

    @Test
    public void testEmptyArrayReturnsNull() {
        assertNull(test.sortedArrayToBST(new int[]{}));
    }

    @Test
    public void testSingleElementSingleNode() {
        TreeNode result = test.sortedArrayToBST(new int[]{42});
        assertEquals(42, result.val);
        assertNull(result.left);
        assertNull(result.right);
    }

    @Test
    public void testAllSameValues() {
        TreeNode result = test.sortedArrayToBST(new int[]{5, 5, 5, 5, 5});
        assertEquals(5, result.val);
        // All nodes should be 5
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(result, inorder);
        assertTrue(inorder.stream().allMatch(v -> v == 5));
        assertEquals(5, inorder.size());
    }

    @Test
    public void testNegativeAndPositiveMixed() {
        int[] nums = {-100, -50, -1, 0, 1, 50, 100};
        TreeNode result = test.sortedArrayToBST(nums);
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(result, inorder);
        for (int i = 0; i < nums.length; i++) {
            assertEquals(nums[i], inorder.get(i));
        }
    }

    @Test
    public void testSequence1ToN() {
        int[] nums = new int[15];
        for (int i = 0; i < 15; i++) nums[i] = i + 1;
        TreeNode result = test.sortedArrayToBST(nums);
        assertEquals(8, result.val);
        assertTrue(isBalanced(result));
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testLargeArray1000Seed42() {
        Random rand = new Random(42L);
        int[] nums = rand.ints(1000, -100000, 100000).sorted().distinct().toArray();
        TreeNode result = test.sortedArrayToBST(nums);
        assertTrue(isBalanced(result));
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(result, inorder);
        assertEquals(nums.length, inorder.size());
        for (int i = 0; i < nums.length; i++) {
            assertEquals(nums[i], inorder.get(i));
        }
    }

    @Test
    public void testPropertyInorderMatchesInput() {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode result = test.sortedArrayToBST(nums);
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(result, inorder);
        assertEquals(nums.length, inorder.size());
        for (int i = 0; i < nums.length; i++) {
            assertEquals(nums[i], inorder.get(i));
        }
    }

    @Test
    public void testPropertyHeightBalanced() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        TreeNode result = test.sortedArrayToBST(nums);
        assertTrue(isBalanced(result));
    }

    @Test
    public void testPropertyIsBST() {
        int[] nums = {-20, -15, -10, -5, 0, 5, 10, 15, 20};
        TreeNode result = test.sortedArrayToBST(nums);
        assertTrue(isBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    private void inorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderTraversal(node.left, result);
        result.add(node.val);
        inorderTraversal(node.right, result);
    }

    private boolean isBalanced(TreeNode node) {
        return height(node) != -1;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int left = height(node.left);
        if (left == -1) return -1;
        int right = height(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    private boolean isBST(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.val < min || node.val > max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }
}
