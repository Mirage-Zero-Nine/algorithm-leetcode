package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree, return the tilt of the whole tree.
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values.
 * Null node has tilt 0.
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * @author BorisMirage
 * Time: 2019/08/14 09:38
 * Created with IntelliJ IDEA
 */

public class FindTilt_563 {
    private int sum = 0;

    /**
     * DFS to find sum of sub tree. Use a global variable to count sum.
     *
     * @param root root node
     * @return tilt of the whole tree
     */
    public int findTilt(TreeNode root) {
        dfs(root);

        return sum;
    }

    /**
     * DFS.
     *
     * @param root root node
     * @return sum of root and child
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);
        sum = sum + Math.abs(left - right);
        return left + right + root.val;
    }
}
