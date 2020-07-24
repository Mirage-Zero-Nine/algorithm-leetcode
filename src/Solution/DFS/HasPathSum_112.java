package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree and a sum.
 * Determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * @author BorisMirage
 * Time: 2019/03/22 21:51
 * Created with IntelliJ IDEA
 */

public class HasPathSum_112 {
    /**
     * DFS Tree traversal.
     *
     * @param root root node
     * @param sum  given sum
     * @return if it has a root-to-leaf path such that sum of all values along the path equals the given sum
     */
    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null && sum == root.val) {
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
