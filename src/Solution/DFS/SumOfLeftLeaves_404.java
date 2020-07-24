package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Find the sum of all left leaves in a given binary tree.
 *
 * @author BorisMirage
 * Time: 2019/06/12 17:57
 * Created with IntelliJ IDEA
 */

public class SumOfLeftLeaves_404 {

    private int sum = 0;

    /**
     * DFS.
     *
     * @param root root node
     * @return sum of all left leaves
     */
    public int sumOfLeftLeaves(TreeNode root) {

        dfs(root);
        return sum;
    }

    /**
     * DFS to find all leaves, and if current root has a left leaf, add its value to sum.
     *
     * @param r root
     * @return if root is a leaf
     */
    private boolean dfs(TreeNode r) {

        /* End point */
        if (r == null) {
            return false;
        }

        if (r.left == null && r.right == null) {
            return true;        // found a leaf
        }

        if (dfs(r.left)) {
            sum += r.left.val;
        }
        dfs(r.right);
        return false;
    }
}
