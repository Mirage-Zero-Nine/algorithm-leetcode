package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * Especially, this path can be either increasing or decreasing.
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 * @author BorisMirage
 * Time: 2020/04/03 18:44
 * Created with IntelliJ IDEA
 */

public class LongestConsecutive_549 {
    /**
     * Postorder traversal.
     *
     * @param root root of the tree
     * @return the length of Longest Consecutive Path in Binary Tree
     */
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] max = new int[1];
        dfs(root, max);

        return max[0];
    }

    /**
     * Postorder traversal to find max path.
     * Note that the path may be increasing or decreasing. Hence, they should be counted differently.
     *
     * @param root root of tree
     * @param max  max path length
     * @return max path length on current node
     */
    public int[] dfs(TreeNode root, int[] max) {

        if (root == null) {     // reaches leaf
            return new int[2];
        }

        int[] left = dfs(root.left, max), right = dfs(root.right, max);     // postorder

        int increasing = 1, decreasing = 1;     // length of increasing and decreasing path

        if (root.left != null) {        // check left child
            if (root.val - root.left.val == 1) {
                increasing = left[0] + 1;
            } else if (root.left.val - root.val == 1) {
                decreasing = left[1] + 1;
            }
        }

        if (root.right != null) {       // check right child
            if (root.val - root.right.val == 1) {
                increasing = Math.max(increasing, right[0] + 1);
            } else if (root.right.val - root.val == 1) {
                decreasing = Math.max(decreasing, right[1] + 1);
            }
        }

        max[0] = Math.max(max[0], increasing + decreasing - 1);

        return new int[]{increasing, decreasing};
    }
}
