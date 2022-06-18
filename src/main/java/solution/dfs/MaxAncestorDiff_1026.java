package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given the root of a binary tree.
 * Find the maximum value V for which there exists different nodes A and B where V = |A.val - B.val|, A is an ancestor of B.
 * (A node A is an ancestor of B if either: any child of A is equal to B, or any child of A is an ancestor of B.)
 *
 * @author BorisMirage
 * Time: 2019/07/18 12:43
 * Created with IntelliJ IDEA
 */

public class MaxAncestorDiff_1026 {
    /**
     * DFS with top-down comparison.
     *
     * @param root root node
     * @return max absolute value between one node and its child
     */
    public int maxAncestorDiff(TreeNode root) {

        return dfs(root, root.val, root.val);
    }

    /**
     * DFS top-down comparison.
     * Each time, find the max and min value during the tree traversal under current path.
     * If reaches null, then the tree traversal has completed and max node diff of current path can be found.
     *
     * @param r   root node
     * @param min min value in tree
     * @param max max value in tree
     * @return max absolute value between one node and its child
     */
    private int dfs(TreeNode r, int min, int max) {

        if (r == null) {        // end point
            return max - min;
        }

        min = Math.min(min, r.val);
        max = Math.max(max, r.val);

        return Math.max(dfs(r.left, min, max), dfs(r.right, min, max));

    }
}
