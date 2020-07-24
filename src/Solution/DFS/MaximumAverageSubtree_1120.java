package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given the root of a binary tree, find the maximum average value of any subtree of that tree.
 *
 * @author BorisMirage
 * Time: 2019/07/19 13:58
 * Created with IntelliJ IDEA
 */

public class MaximumAverageSubtree_1120 {

    private double max = 0;

    /**
     * DFS.
     * Note that the return value in DFS is the average of current tree and count of tree, instead of max average.
     *
     * @param root root node
     * @return maximum average value of any subtree of that tree
     */
    public double maximumAverageSubtree(TreeNode root) {
        if (root == null) {
            return 0.0;
        }
        dfs(root);
        return max;
    }

    /**
     * Find current tree's average and node count.
     *
     * @param r root node
     * @return double array, average of nodes value in current tree and count of nodes in current tree.
     */
    private double[] dfs(TreeNode r) {

        if (r == null) {
            return new double[]{0, 0};
        }

        double[] left = dfs(r.left);
        double[] right = dfs(r.right);

        /*
         * Average has these parts:
         * 1. left average * left node count.
         * 2. right average * right node count.
         * 3. root value * 1.
         * Calculate current tree's average and compare to global max average. */
        double ave = ((left[0] * left[1] + right[0] * right[1] + (double) r.val) / (left[1] + right[1] + 1));

        this.max = Math.max(ave, this.max);     // find new global max

        return new double[]{ave, left[1] + right[1] + 1};
    }
}
