package solution.stack;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindTarget_653Test {
    private final FindTarget_653 solver = new FindTarget_653();

    private TreeNode buildBST() {
        //     5
        //    / \
        //   3   6
        //  / \   \
        // 2   4   7
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        return root;
    }

    @Test public void testFound() {
        assertTrue(solver.findTarget_Stack(buildBST(), 9)); // 2+7 or 3+6 or 4+5
    }

    @Test public void testNotFound() {
        assertFalse(solver.findTarget_Stack(buildBST(), 28));
    }

    @Test public void testSmallestPair() {
        assertTrue(solver.findTarget_Stack(buildBST(), 5)); // 2+3
    }

    @Test public void testLargestPair() {
        assertTrue(solver.findTarget_Stack(buildBST(), 13)); // 6+7
    }

    @Test public void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertFalse(solver.findTarget_Stack(root, 2));
    }

    @Test public void testNullRoot() {
        assertFalse(solver.findTarget_Stack(null, 5));
    }

    @Test public void testTwoNodes() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertTrue(solver.findTarget_Stack(root, 3));
    }

    @Test public void testNegativeTarget() {
        assertFalse(solver.findTarget_Stack(buildBST(), 1));
    }

    @Test public void testTargetZero() {
        assertFalse(solver.findTarget_Stack(buildBST(), 0));
    }

    @Test public void testGiantBST() {
        TreeNode root = buildBalanced(1, 1000);
        assertTrue(solver.findTarget_Stack(root, 1001)); // 1+1000 or 500+501
        assertFalse(solver.findTarget_Stack(root, 2001));
    }

    private TreeNode buildBalanced(int lo, int hi) {
        if (lo > hi) return null;
        int mid = (lo + hi) / 2;
        TreeNode n = new TreeNode(mid);
        n.left = buildBalanced(lo, mid - 1);
        n.right = buildBalanced(mid + 1, hi);
        return n;
    }
}
