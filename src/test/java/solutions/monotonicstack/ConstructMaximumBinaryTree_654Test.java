package solutions.monotonicstack;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructMaximumBinaryTree_654Test {
    private final ConstructMaximumBinaryTree_654 solver = new ConstructMaximumBinaryTree_654();

    @Test public void testBasicRecursion() {
        TreeNode root = solver.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        assertEquals(6, root.val);
        assertEquals(3, root.left.val);
        assertEquals(5, root.right.val);
    }

    @Test public void testSingleElement() {
        TreeNode root = solver.constructMaximumBinaryTree(new int[]{1});
        assertEquals(1, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test public void testEmpty() {
        assertNull(solver.constructMaximumBinaryTree(new int[]{}));
    }

    @Test public void testDecreasing() {
        // [3,2,1] -> root=3, right=2, right.right=1
        TreeNode root = solver.constructMaximumBinaryTree(new int[]{3, 2, 1});
        assertEquals(3, root.val);
        assertNull(root.left);
        assertEquals(2, root.right.val);
    }

    @Test public void testStackMethod() {
        TreeNode root = solver.useStack(new int[]{3, 2, 1, 6, 0, 5});
        assertEquals(6, root.val);
        assertEquals(3, root.left.val);
        assertEquals(5, root.right.val);
    }

    @Test public void testStackEmpty() {
        assertNull(solver.useStack(new int[]{}));
    }

    @Test public void testIncreasing() {
        // [1,2,3] -> root=3, left=2, left.left=1
        TreeNode root = solver.constructMaximumBinaryTree(new int[]{1, 2, 3});
        assertEquals(3, root.val);
        assertEquals(2, root.left.val);
        assertEquals(1, root.left.left.val);
        assertNull(root.right);
    }

    @Test public void testStackSingle() {
        TreeNode root = solver.useStack(new int[]{5});
        assertEquals(5, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test public void testStackNull() {
        assertNull(solver.useStack(null));
    }

    @Test public void testTwoElements() {
        TreeNode root = solver.constructMaximumBinaryTree(new int[]{1, 2});
        assertEquals(2, root.val);
        assertEquals(1, root.left.val);
        assertNull(root.right);
    }

    @Test public void testGiant() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = i;
        TreeNode root = solver.constructMaximumBinaryTree(nums);
        assertEquals(999, root.val);
    }

    @Test public void testAdditionalContractsAndBothImplementations() {
        int[][] cases = {{-3, -1, -2}, {-5, -4, -3, -2, -1}, {1, -1, 0},
                {9, 1, 8, 2}, {-2, 4, -1, 3}, {7, 6, 5, 4, 3, 2, 1},
                {1, 7, 2, 6, 3, 5}, {-10, -20, -30, -5}};
        for (int[] nums : cases) {
            assertSameTree(solver.constructMaximumBinaryTree(nums), solver.useStack(nums));
        }
        assertNull(solver.constructMaximumBinaryTree(new int[]{}));
        assertNull(solver.useStack(new int[]{}));
    }

    private void assertSameTree(TreeNode expected, TreeNode actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }
        assertEquals(expected.val, actual.val);
        assertSameTree(expected.left, actual.left);
        assertSameTree(expected.right, actual.right);
    }

    @Test public void testNegativeValues() { assertSameTree(solver.constructMaximumBinaryTree(new int[]{-4,-1,-3}),solver.useStack(new int[]{-4,-1,-3})); }
    @Test public void testRootMiddle() { TreeNode n=solver.constructMaximumBinaryTree(new int[]{1,5,2}); assertEquals(5,n.val); assertEquals(1,n.left.val); assertEquals(2,n.right.val); }
    @Test public void testRootLast() { TreeNode n=solver.useStack(new int[]{1,2,3}); assertEquals(3,n.val); assertEquals(2,n.left.val); }
    @Test public void testRootFirst() { TreeNode n=solver.useStack(new int[]{3,2,1}); assertEquals(3,n.val); assertEquals(2,n.right.val); }
    @Test public void testMixedNegatives() { assertSameTree(solver.constructMaximumBinaryTree(new int[]{-2,4,-1,3}),solver.useStack(new int[]{-2,4,-1,3})); }
    @Test public void testAlternatingPeaks() { assertSameTree(solver.constructMaximumBinaryTree(new int[]{2,1,4,3,5}),solver.useStack(new int[]{2,1,4,3,5})); }
    @Test public void testTwoNegative() { TreeNode n=solver.constructMaximumBinaryTree(new int[]{-2,-5}); assertEquals(-2,n.val); assertEquals(-5,n.right.val); }
    @Test public void testRepeatedCall() { solver.constructMaximumBinaryTree(new int[]{1}); assertSameTree(solver.constructMaximumBinaryTree(new int[]{2,1}),solver.useStack(new int[]{2,1})); }
}
