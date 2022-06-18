package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Give a binary tree, return maximum sum that each element made up it has no connection to any other elements in it.
 *
 * @author BorisMirage
 * Time: 2019/02/28 09:42
 * Created with IntelliJ IDEA
 */

public class Rob_337 {
    /**
     * Pre-order traversal.
     *
     * @param root root node of tree
     * @return max sum that each element made up it has no connection to any other elements in it
     */
    public int rob(TreeNode root) {
        int[] max = postOrderTraversal(root);
        return Math.max(max[0], max[1]);
    }

    public int[] postOrderTraversal(TreeNode r) {
        int[] max = new int[2];     // max[0]: sum without current node r; max[1]: sum with current node r value

        if (r == null) {
            return max;
        }

        int[] leftMax = postOrderTraversal(r.left);
        int[] rightMax = postOrderTraversal(r.right);
        max[0] = Math.max(leftMax[0], leftMax[1]) + Math.max(rightMax[0], rightMax[1]);
        max[1] = leftMax[0] + rightMax[0] + r.val;

        return max;
    }
}
