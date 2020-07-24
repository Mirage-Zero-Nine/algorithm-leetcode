package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * @author BorisMirage
 * Time: 2019/06/12 11:48
 * Created with IntelliJ IDEA
 */

public class CountUnivalSubtrees_250 {
    /**
     * DFS bottom-up to iterate the tree and find every univalue tree.
     * Avoid to use global variable, there may be multi-threading problem when using global variable.
     *
     * @param root root node
     * @return number of uni-value subtrees
     */
    public int countUnivalSubtrees(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        int[] max = new int[1];     // avoid multi-threading problem by using local variable
        helper(root, max);

        return max[0];
    }

    /**
     * If left and right subtree is univalue tree and root value equals to subtree, then current tree is univalue tree.
     *
     * @param r root
     * @return number of uni-value subtrees
     */
    private boolean helper(TreeNode r, int[] max) {

        if (r == null) {
            return true;
        }

        boolean left = helper(r.left, max);
        boolean right = helper(r.right, max);

        /*
         * Conditions: (match all conditions)
         * 1. Left and right subtree are both univalue (null is counted as univalue).
         * 2. Left subtree root value equals to root value, and right subtree root is same as root value.  */
        if (left && right && (r.left == null || r.left.val == r.val) && (r.right == null || r.right.val == r.val)) {
            max[0]++;
            return true;
        }

        return false;
    }
}
