package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given two binary trees, write a function to check if they are the same tree.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * @author BorisMirage
 * Time: 2018/10/02 12:10
 * Created with IntelliJ IDEA
 */

public class IsSameTree_100 {

    /**
     * Any kind of traversal of tree (in-order, post-order, pre-order) will suffice.
     * If sub nodes do not be in same situation (null or not null), return false.
     * If value in tree node is same, continue recursion in sub nodes.
     *
     * @param p TreeNode 1
     * @param q TreeNode 2
     * @return if two trees are same
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {       // end point
            return true;
        }

        if (p == null || q == null) {       // if given two trees has different child node, return false
            return false;
        }
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}

