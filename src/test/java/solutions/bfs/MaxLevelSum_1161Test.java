package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class MaxLevelSum_1161Test {

    private final MaxLevelSum_1161 test = new MaxLevelSum_1161();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7); root.right = new TreeNode(0);
        root.left.left = new TreeNode(7); root.left.right = new TreeNode(-8);
        assertEquals(2, test.maxLevelSum(root));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.maxLevelSum(new TreeNode(1)));
        TreeNode root = new TreeNode(989);
        root.right = new TreeNode(10250);
        assertEquals(2, test.maxLevelSum(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(3, test.maxLevelSum(root));
    }

    @Test
    public void testAllNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(-4);
        assertEquals(1, test.maxLevelSum(root));
    }

    @Test
    public void testTieReturnsSmallestLevel() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3); // level 2 sum = 5 equals level 1
        assertEquals(1, test.maxLevelSum(root));
    }

    @Test
    public void testDeeperLevelWins() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1); // sum=2
        root.left.left = new TreeNode(10);
        root.right.right = new TreeNode(10); // sum=20
        assertEquals(3, test.maxLevelSum(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(4, test.maxLevelSum(root));
    }

    @Test
    public void testRightSkewedTreeWithNegativeTail() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(-20);
        assertEquals(1, test.maxLevelSum(root));
    }

    @Test
    public void testMixedPositiveNegativeByLevel() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-10);
        root.right = new TreeNode(20); // level2 sum=10
        root.left.left = new TreeNode(30);
        root.left.right = new TreeNode(-5);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(2); // level3 sum=28
        assertEquals(3, test.maxLevelSum(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 2; i <= 120; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        assertEquals(120, test.maxLevelSum(root));
    }
}
