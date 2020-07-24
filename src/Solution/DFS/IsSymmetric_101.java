package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * @author BorisMirage
 * Time: 2018/10/02 12:48
 * Created with IntelliJ IDEA
 */

public class IsSymmetric_101 {
    /**
     * Pre-order traversal.
     *
     * @param root root TreeNode
     * @return if this tree is symmetric
     */
    public boolean isSymmetric(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }

        if (root.left.val == root.right.val) {
            return isSymmetricTree(root.left, root.right);
        }

        return false;
    }

    /**
     * Almost same as Same Tree problem, only simply modified the recursion to compare left node and right node.
     *
     * @param p TreeNode 1
     * @param q TreeNode 2
     * @return if two trees are symmetric
     */
    private boolean isSymmetricTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val == q.val) {
            return isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
        }
        return false;
    }
}
