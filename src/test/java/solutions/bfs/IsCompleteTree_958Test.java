package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsCompleteTree_958Test {

    private final IsCompleteTree_958 test = new IsCompleteTree_958();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isCompleteTree(null));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.right.right = new TreeNode(5);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testSingleNodeTree() {
        assertTrue(test.isCompleteTree(new TreeNode(1)));
    }

    @Test
    public void testLeftChildOnlyIsComplete() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testRightChildOnlyIsNotComplete() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testMissingLeftThenHasRightAtLowerLevelIsNotComplete() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testLastLevelLeftPackedIsComplete() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        assertTrue(test.isCompleteTree(root));
    }

    @Test
    public void testDeepLeftSkewedIsNotComplete() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertFalse(test.isCompleteTree(root));
    }

    @Test
    public void testGiantCompleteTree() {
        TreeNode[] nodes = new TreeNode[128];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= 63; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }
        assertTrue(test.isCompleteTree(nodes[1]));
    }
}
