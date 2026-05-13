package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsValidSequence_1430Test {

    private final IsValidSequence_1430 test = new IsValidSequence_1430();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1); root.right = new TreeNode(0);
        root.left.left = new TreeNode(0); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        assertTrue(test.isValidSequence(root, new int[]{0, 0, 0}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isValidSequence(null, new int[]{1}));
        assertFalse(test.isValidSequence(new TreeNode(1), new int[]{2}));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1); root.right = new TreeNode(0);
        assertFalse(test.isValidSequence(root, new int[]{0, 1, 1}));
    }

    @Test
    public void testSingleNodeMatch() {
        assertTrue(test.isValidSequence(new TreeNode(5), new int[]{5}));
    }

    @Test
    public void testSingleNodeNoMatch() {
        assertFalse(test.isValidSequence(new TreeNode(5), new int[]{3}));
    }

    @Test
    public void testSequenceTooShort() {
        // Sequence ends before reaching a leaf
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertFalse(test.isValidSequence(root, new int[]{1, 2})); // not a leaf
    }

    @Test
    public void testSequenceTooLong() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertFalse(test.isValidSequence(root, new int[]{1, 2, 3}));
    }

    @Test
    public void testRightPathValid() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        assertTrue(test.isValidSequence(root, new int[]{1, 3, 4}));
    }

    @Test
    public void testEmptyArray() {
        // arr length 0 -> index 0 >= arr.length -> false
        assertFalse(test.isValidSequence(new TreeNode(1), new int[]{}));
    }

    @Test
    public void testMultiplePaths() {
        // Tree with two valid paths of same length
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.right.right = new TreeNode(4);
        assertTrue(test.isValidSequence(root, new int[]{1, 2, 3}));
        assertTrue(test.isValidSequence(root, new int[]{1, 2, 4}));
        assertFalse(test.isValidSequence(root, new int[]{1, 2, 5}));
    }

    @Test
    public void testGiantTree() {
        // Build a left-skewed tree of depth 50
        TreeNode root = new TreeNode(0);
        TreeNode cur = root;
        int[] arr = new int[50];
        for (int i = 1; i < 50; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
            arr[i] = i;
        }
        assertTrue(test.isValidSequence(root, arr));
        // Wrong last element
        int[] wrongArr = arr.clone();
        wrongArr[49] = 999;
        assertFalse(test.isValidSequence(root, wrongArr));
    }
}
