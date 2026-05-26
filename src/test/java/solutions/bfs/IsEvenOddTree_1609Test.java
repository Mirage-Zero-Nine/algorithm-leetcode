package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsEvenOddTree_1609Test {

    private final IsEvenOddTree_1609 test = new IsEvenOddTree_1609();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10); root.right = new TreeNode(4);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(9); root.right.right = new TreeNode(5);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isEvenOddTree(new TreeNode(2)));
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4); root.right = new TreeNode(2);
        assertTrue(test.isEvenOddTree(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(4);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testSingleOddRootIsValid() {
        assertTrue(test.isEvenOddTree(new TreeNode(1)));
    }

    @Test
    public void testEvenLevelContainsEvenValueInvalid() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testEvenLevelNotStrictlyIncreasingInvalid() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(9);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testOddLevelContainsOddValueInvalid() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(8);
        root.right = new TreeNode(3);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testOddLevelNotStrictlyDecreasingInvalid() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(6);
        assertFalse(test.isEvenOddTree(root));
    }

    @Test
    public void testValidThreeLevelEvenOddTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10); root.right = new TreeNode(8);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7); root.right.right = new TreeNode(9);
        assertTrue(test.isEvenOddTree(root));
    }

    @Test
    public void testGiantValidTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(20);
        root.right = new TreeNode(18);

        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        root.left.left.left = new TreeNode(40);
        root.left.left.right = new TreeNode(38);
        root.left.right.left = new TreeNode(36);
        root.left.right.right = new TreeNode(34);
        root.right.left.left = new TreeNode(32);
        root.right.left.right = new TreeNode(30);
        root.right.right.left = new TreeNode(28);
        root.right.right.right = new TreeNode(26);

        assertTrue(test.isEvenOddTree(root));
    }
}
