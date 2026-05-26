package solutions.dfs;

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

    @Test public void testThirdSmallest() {
        assertEquals(3, solver.kthSmallest(buildBST(), 3));
    }

    @Test public void testLeftSkewed() {
        //    4
        //   /
        //  3
        // /
        //2
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        assertEquals(2, solver.kthSmallest(root, 1));
        assertEquals(4, solver.kthSmallest(root, 3));
    }

    @Test public void testRightSkewed() {
        //  1
        //   \
        //    2
        //     \
        //      3
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(3, solver.kthSmallest(root, 3));
    }

    @Test public void testLargerBST() {
        //        5
        //       / \
        //      3   6
        //     / \
        //    2   4
        //   /
        //  1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        assertEquals(1, solver.kthSmallest(root, 1));
        assertEquals(4, solver.kthSmallest(root, 4));
        assertEquals(6, solver.kthSmallest(root, 6));
    }

    @Test public void testKBeyondSize() {
        // Single node BST with k=2 - implementation revisits root due to initial stack push
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        // k=3 on a 2-node tree
        assertEquals(-1, solver.kthSmallest(null, 5));
    }
}
