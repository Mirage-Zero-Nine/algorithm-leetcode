package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SumNumbers_129Test {

    private final SumNumbers_129 solution = new SumNumbers_129();

    @Test
    public void sumNumbersReturnsZeroForEmptyTree() {
        assertEquals(0, solution.sumNumbers(null));
    }

    @Test
    public void sumNumbersReturnsDigitForSingleNode() {
        assertEquals(0, solution.sumNumbers(new TreeNode(0)));
        assertEquals(9, solution.sumNumbers(new TreeNode(9)));
    }

    @Test
    public void sumNumbersAddsTwoRootToLeafNumbers() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        // Paths: 12 + 13 = 25
        assertEquals(25, solution.sumNumbers(root));
    }

    @Test
    public void sumNumbersCountsOnlyCompleteRootToLeafPaths() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        // Paths: 495 + 491 + 40 = 1026
        assertEquals(1026, solution.sumNumbers(root));
    }

    @Test
    public void sumNumbersDoesNotCountMissingChildrenAsPaths() {
        TreeNode leftPath = new TreeNode(1);
        leftPath.left = new TreeNode(2);
        leftPath.left.left = new TreeNode(3);

        TreeNode rightPath = new TreeNode(5);
        rightPath.right = new TreeNode(6);
        rightPath.right.right = new TreeNode(7);

        assertEquals(123, solution.sumNumbers(leftPath));
        assertEquals(567, solution.sumNumbers(rightPath));
    }

    @Test
    public void sumNumbersHandlesLeadingZeros() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(5);

        // Paths: 01 + 005 = 1 + 5 = 6
        assertEquals(6, solution.sumNumbers(root));
    }

    @Test
    public void sumNumbersAddsAllPathsInBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Paths: 124 + 125 + 136 + 137 = 522
        assertEquals(522, solution.sumNumbers(root));
    }

    @Test
    public void sumNumbersHandlesDeepRootToLeafPath() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int digit = 2; digit <= 9; digit++) {
            current.left = new TreeNode(digit);
            current = current.left;
        }

        assertEquals(123_456_789, solution.sumNumbers(root));
    }

    @Test
    public void sumNumbersHandlesLargeCompleteTree() {
        TreeNode root = buildCompleteTree(8, 1);

        // 255 nodes, 128 leaves, and every path represents 11,111,111.
        assertEquals(1_422_222_208, solution.sumNumbers(root));
    }

    private TreeNode buildCompleteTree(int levels, int digit) {
        TreeNode node = new TreeNode(digit);
        if (levels > 1) {
            node.left = buildCompleteTree(levels - 1, digit);
            node.right = buildCompleteTree(levels - 1, digit);
        }
        return node;
    }
}
