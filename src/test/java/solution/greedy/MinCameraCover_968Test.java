package solution.greedy;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinCameraCover_968Test {

    @Test public void testSingleNode() {
        TreeNode root = new TreeNode(0);
        assertEquals(1, new MinCameraCover_968().minCameraCover(root));
    }

    @Test public void testNull() {
        assertEquals(0, new MinCameraCover_968().minCameraCover(null));
    }

    @Test public void testThreeNodes() {
        //     0
        //    / \
        //   0   0
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        assertEquals(1, new MinCameraCover_968().minCameraCover(root));
    }

    @Test public void testLeftChain() {
        // Chain of 4 nodes
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        assertEquals(2, new MinCameraCover_968().minCameraCover(root));
    }

    @Test public void testComplexCase() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.left.right.left = new TreeNode(0);
        assertEquals(2, new MinCameraCover_968().minCameraCover(root));
    }
}
