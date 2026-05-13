package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindTarget_653Test {

    private final FindTarget_653 test = new FindTarget_653();

    @Test
    public void testHappyCase1() {
        // [5, 3, 6, 2, 4, null, 7], target = 9
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertTrue(test.findTarget_Set(root, 9));
    }

    @Test
    public void testHappyCase2() {
        // [5, 3, 6, 2, 4, null, 7], target = 28
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertFalse(test.findTarget_Set(root, 28));
    }

    @Test
    public void testHappyCase3() {
        // [2, 1, 3], target = 4
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        assertTrue(test.findTarget_Set(root, 4));
    }

    @Test
    public void testHappyCase4() {
        // [2, 1, 3], target = 1
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        assertFalse(test.findTarget_Set(root, 1));
    }

    @Test
    public void testNegativeCase() {
        // Single node tree, target = 10. Cannot have two elements.
        TreeNode root = new TreeNode(5);
        assertFalse(test.findTarget_Set(root, 10));
    }

    @Test
    public void testEdgeCaseEmpty() {
        assertFalse(test.findTarget_Set(null, 0));
    }

    @Test
    public void testEdgeCaseDuplicateValues() {
        // BST doesn't usually have duplicate values, but the problem says "two elements".
        // [1], target = 2
        TreeNode root = new TreeNode(1);
        assertFalse(test.findTarget_Set(root, 2));
    }

    @Test
    public void testEdgeCaseNegativeValues() {
        // [0, -1, 2], target = 1
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(2);
        assertTrue(test.findTarget_Set(root, 1));
        assertTrue(test.findTarget_Set(root, -1));
        assertFalse(test.findTarget_Set(root, 0));
    }

    @Test
    public void testEdgeCaseTargetIsZero() {
        // [0, -1, 1], target = 0
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(1);
        assertTrue(test.findTarget_Set(root, 0));
    }

    @Test
    public void testLargeCase() {
        // Skewed tree (linked list style), target is at the end.
        TreeNode root = new TreeNode(0);
        TreeNode curr = root;
        for (int i = 1; i <= 1000; i++) {
            curr.right = new TreeNode(i);
            curr = curr.right;
        }
        assertTrue(test.findTarget_Set(root, 1999)); // 999 + 1000
        assertFalse(test.findTarget_Set(root, 3000));
    }
}
