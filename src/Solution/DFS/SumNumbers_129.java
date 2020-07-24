package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

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
     * DFS.
     *
     * @param root root node
     * @return total sum of all root-to-leaf numbers
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * Add each node's value with 10^n
     *
     * @param r root node
     * @param n power
     * @return total sum of all root-to-leaf numbers
     */
    public int dfs(TreeNode r, int n) {

        /* End point */
        if (r == null) {
            return 0;
        }

        if (r.right == null && r.left == null) {
            return n * 10 + r.val;
        }

        return dfs(r.left, n * 10 + r.val) + dfs(r.right, n * 10 + r.val);
    }
}
