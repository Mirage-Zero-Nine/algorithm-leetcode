package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LargestValues_515Test {

    private final LargestValues_515 test = new LargestValues_515();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.right = new TreeNode(2);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        assertEquals(List.of(1, 3, 9), test.largestValues(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.largestValues(null));
        assertEquals(List.of(1), test.largestValues(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.right.right = new TreeNode(5);
        assertEquals(List.of(1, 3, 5), test.largestValues(root));
    }

    @Test
    public void testAllNegativeValues() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-40);
        root.right.right = new TreeNode(-5);
        assertEquals(List.of(-10, -3, -5), test.largestValues(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(4);
        assertEquals(List.of(7, 6, 5, 4), test.largestValues(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.largestValues(root));
    }

    @Test
    public void testLevelWithDuplicateMaximums() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(2, 5, 3), test.largestValues(root));
    }

    @Test
    public void testMixedDepthSparseTree() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(1);
        root.right = new TreeNode(10);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(9);
        root.right.left.left = new TreeNode(11);
        assertEquals(List.of(8, 10, 9, 11), test.largestValues(root));
    }

    @Test
    public void testZeroValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        assertEquals(List.of(0, 0), test.largestValues(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 120; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        List<Integer> out = test.largestValues(root);
        assertEquals(121, out.size());
        assertEquals(120, out.get(out.size() - 1));
    }
}
