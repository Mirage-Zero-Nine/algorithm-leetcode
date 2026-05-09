package solution.findkth;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KthSmallest_230Test {
    private final KthSmallest_230 solver = new KthSmallest_230();

    private TreeNode buildBST() {
        //      3
        //     / \
        //    1   4
        //     \
        //      2
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        return root;
    }

    @Test public void testFirstSmallest() {
        assertEquals(1, solver.kthSmallest(buildBST(), 1));
    }

    @Test public void testSecondSmallest() {
        assertEquals(2, solver.kthSmallest(buildBST(), 2));
    }

    @Test public void testFourthSmallest() {
        assertEquals(4, solver.kthSmallest(buildBST(), 4));
    }

    @Test public void testSingleNode() {
        assertEquals(1, solver.kthSmallest(new TreeNode(1), 1));
    }

    @Test public void testNullRoot() {
        assertEquals(-1, solver.kthSmallest(null, 1));
    }
}
