package solutions.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 *
 * @author BorisMirage
 * Time: 2019/06/11 15:18
 * Created with IntelliJ IDEA
 */

public class SumNumbers_129 {
    /**
     * Calculates the sum of the numbers represented by every root-to-leaf path.
     * Appending a digit to {@code prefix} produces {@code prefix * 10 + node.val}.
     *
     * @param root root of the binary tree
     * @return sum of all root-to-leaf numbers, or {@code 0} for an empty tree
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * Returns the sum of all completed root-to-leaf numbers below {@code node}.
     * {@code prefix} contains the digits from the ancestors and excludes {@code node.val}.
     *
     * @param node current node being visited
     * @param prefix number formed by the path before {@code node}
     * @return sum of all root-to-leaf numbers that continue through {@code node}
     */
    private int dfs(TreeNode node, int prefix) {
        // exit current path at null node
        if (node == null) {
            return 0;
        }

        // multiplying by 10 shifts the existing digits left before appending node.val.
        int currentNumber = prefix * 10 + node.val;
        if (node.left == null && node.right == null) {
            return currentNumber;
        }

        return dfs(node.left, currentNumber) + dfs(node.right, currentNumber);
    }
}
