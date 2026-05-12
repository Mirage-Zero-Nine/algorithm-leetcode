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
}
