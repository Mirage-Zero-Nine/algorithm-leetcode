package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumNumbers_129Test {

    private final SumNumbers_129 test = new SumNumbers_129();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(25, test.sumNumbers(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.sumNumbers(null));
        assertEquals(1, test.sumNumbers(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9); root.right = new TreeNode(0);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(1);
        assertEquals(1026, test.sumNumbers(root));
    }

    @Test
    public void testSingleDigitRoot() {
        assertEquals(0, test.sumNumbers(new TreeNode(0)));
        assertEquals(9, test.sumNumbers(new TreeNode(9)));
    }

    @Test
    public void testLeftOnlyPath() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        // Path: 1->2->3 = 123
        assertEquals(123, test.sumNumbers(root));
    }

    @Test
    public void testRightOnlyPath() {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        // Path: 5->6->7 = 567
        assertEquals(567, test.sumNumbers(root));
    }

    @Test
    public void testAllZeros() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        // Paths: 0->0 = 0, 0->0 = 0, sum = 0
        assertEquals(0, test.sumNumbers(root));
    }

    @Test
    public void testThreeLeaves() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        // Paths: 1->2->4 = 124, 1->3 = 13, sum = 137
        assertEquals(137, test.sumNumbers(root));
    }

    @Test
    public void testNegativeCase() {
        // Tree with value 0 at root and leaves
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        // Path: 0->1 = 1
        assertEquals(1, test.sumNumbers(root));
    }

    @Test
    public void testBalancedTreeFourLeaves() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        // Paths: 124 + 125 + 136 + 137 = 522
        assertEquals(522, test.sumNumbers(root));
    }

    @Test
    public void testGiantTree() {
        // Deep linear tree: 1->2->3->4->5->6->7->8->9
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= 9; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        // Path: 123456789
        assertEquals(123456789, test.sumNumbers(root));
    }
}
