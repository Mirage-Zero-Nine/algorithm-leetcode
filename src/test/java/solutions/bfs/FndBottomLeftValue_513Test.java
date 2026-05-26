package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FndBottomLeftValue_513Test {

    private final FndBottomLeftValue_513 test = new FndBottomLeftValue_513();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        assertEquals(1, test.findBottomLeftValue(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findBottomLeftValue(null));
        assertEquals(1, test.findBottomLeftValue(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        assertEquals(7, test.findBottomLeftValue(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(7);
        root.left.left.left = new TreeNode(6);
        assertEquals(6, test.findBottomLeftValue(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(4, test.findBottomLeftValue(root));
    }

    @Test
    public void testBottomLeftComesFromLeftSubtree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.right.left = new TreeNode(50);
        root.right.right = new TreeNode(60);
        assertEquals(40, test.findBottomLeftValue(root));
    }

    @Test
    public void testBottomLeftWhenDeepestNodeInRightSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        assertEquals(5, test.findBottomLeftValue(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-4);
        assertEquals(-4, test.findBottomLeftValue(root));
    }

    @Test
    public void testLastLevelMultipleNodesPickLeftmost() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        assertEquals(4, test.findBottomLeftValue(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 200; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        assertEquals(200, test.findBottomLeftValue(root));
    }
}
