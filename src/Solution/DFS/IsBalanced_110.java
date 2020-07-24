package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * A binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * @author BorisMirage
 * Time: 2019/06/11 11:33
 * Created with IntelliJ IDEA
 */

public class IsBalanced_110 {
    /**
     * DFS top-bottom traversal.
     * Time complexity: O(nlogn), each time, the left subtree and right subtree is called in recursion.
     *
     * @param root root node
     * @return if given tree is height-balanced
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return dfs(root) != -1;
    }

    /**
     * DFS traversal. If current tree has an imbalanced tree, return -1.
     * Otherwise return the height difference.
     *
     * @param root root node
     * @return if given tree is height-balanced
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;       // null node has no height
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {      // left/right/current tree is not height-balanced
            return -1;      // sub tree is not height-balanced
        }

        return Math.max(left, right) + 1;       // depth + 1
    }

    /**
     * The bottom-up approach is a reverse of the logic of the top-down approach.
     * Traverse to the bottom of the tree, then compare the height between the height of each subtree.
     * In this way, the duplicate calculation occurred in top-bottom traversal can be reduced.
     * Time complexity: O(n).
     * However, it requires a additional class to record the height of tree.
     *
     * @param root root node
     * @return if given tree is height-balanced
     */
    public boolean bottomUp(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }

        return helper(root, 1).isBalanced;
    }

    /**
     * Bottom-up traversal to find if left subtree and right subtree is balanced.
     *
     * @param r      current root node
     * @param height current tree node's height
     * @return if given tree is height-balanced
     */
    private TreeNodeWithHeight helper(TreeNode r, int height) {
        if (r == null) {
            return new TreeNodeWithHeight(-1, true);
        }

        TreeNodeWithHeight left = helper(r.left, height + 1);
        TreeNodeWithHeight right = helper(r.right, height + 1);
        if (!left.isBalanced || !right.isBalanced || Math.abs(left.height - right.height) > 1) {
            return new TreeNodeWithHeight(-1, false);
        }

        return new TreeNodeWithHeight(Math.max(left.height, right.height) + 1, true);
    }

    /**
     * Modified tree node with current node height.
     */
    static class TreeNodeWithHeight {
        boolean isBalanced;
        int height;

        /**
         * Initialization of node
         *
         * @param height     height of current node
         * @param isBalanced is current tree balanced
         */
        TreeNodeWithHeight(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}
