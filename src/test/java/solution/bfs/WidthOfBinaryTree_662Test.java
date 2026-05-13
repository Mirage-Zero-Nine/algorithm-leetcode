package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class WidthOfBinaryTree_662Test {

    private final WidthOfBinaryTree_662 test = new WidthOfBinaryTree_662();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.right = new TreeNode(2);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        assertEquals(4, test.widthOfBinaryTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.widthOfBinaryTree(null));
        assertEquals(1, test.widthOfBinaryTree(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.right.right = new TreeNode(5);
        assertEquals(4, test.widthOfBinaryTree(root));
    }

    @Test
    public void testLeftSkewedTreeWidthOne() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(1, test.widthOfBinaryTree(root));
    }

    @Test
    public void testRightSkewedTreeWidthOne() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(1, test.widthOfBinaryTree(root));
    }

    @Test
    public void testSparseTreeLargeGapWidth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.right.right.right = new TreeNode(15);
        assertEquals(8, test.widthOfBinaryTree(root));
    }

    @Test
    public void testCompleteTreeWidth() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(4, test.widthOfBinaryTree(root));
    }

    @Test
    public void testTwoNodesWidthTwo() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        assertEquals(2, test.widthOfBinaryTree(root));
    }

    @Test
    public void testMixedDepthWidthFromMiddleLevel() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        assertEquals(2, test.widthOfBinaryTree(root));
    }

    @Test
    public void testGiantPerfectTree() {
        TreeNode[] nodes = new TreeNode[128];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= 63; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }
        assertEquals(64, test.widthOfBinaryTree(nodes[1]));
    }
}
