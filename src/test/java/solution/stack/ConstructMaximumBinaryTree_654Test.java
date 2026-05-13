package solution.stack;

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
}
