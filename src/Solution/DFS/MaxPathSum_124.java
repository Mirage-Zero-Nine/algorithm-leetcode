package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * A path is defined as a sequence of nodes from starting node to node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * @author BorisMirage
 * Time: 2019/06/11 14:17
 * Created with IntelliJ IDEA
 */

public class MaxPathSum_124 {
    private int max = Integer.MIN_VALUE;

    /**
     * Use a globe max value to store max path, and use a local max to store temporary path max.
     *
     * @param root root node
     * @return max path in tree
     */
    public int maxPathSum(TreeNode root) {
        traversal(root);
        return max;
    }

    /**
     * Pre-order traversal to find path sum.
     *
     * @param r root node
     * @return max path in local tree
     */
    private int traversal(TreeNode r) {
        if (r == null) {
            return 0;
        }

        int left = Math.max(0, traversal(r.left));
        int right = Math.max(0, traversal(r.right));
        max = Math.max(max, left + right + r.val);      // globe max

        return Math.max(left, right) + r.val;       // local max
    }
}
