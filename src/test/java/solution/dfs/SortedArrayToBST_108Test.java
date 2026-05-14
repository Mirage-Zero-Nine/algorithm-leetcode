package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}
