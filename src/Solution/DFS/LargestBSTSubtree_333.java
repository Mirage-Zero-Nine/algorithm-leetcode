package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree.
 * Find the largest subtree that is a Binary Search Tree, where largest means subtree with largest number of nodes in it.
 *
 * @author BorisMirage
 * Time: 2019/06/12 17:15
 * Created with IntelliJ IDEA
 */

public class LargestBSTSubtree_333 {

    private int max = 0;        // globe max

    /**
     * DFS with a tree size tracker from bottom to top to find largest sub BST.
     *
     * @param root root node
     * @return size of largest sub BST
     */
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    /**
     * DFS to traverse tree.
     * If current sub tree is not a BST, return a size tracker with size of -1.
     * Null node has a size of 0, that is to say, a leaf has a size of 1.
     * If any of the sub tree has size of -1, then it will not be a BST.
     * If current sub tree is BST, compare it to a globe max value to find the largest one.
     *
     * @param r root node
     * @return size of largest sub BST
     */
    private TreeSize dfs(TreeNode r) {

        /* End point */
        if (r == null) {
            return new TreeSize(0, Integer.MAX_VALUE, Integer.MIN_VALUE);       // reach leaf node
        }

        TreeSize left = dfs(r.left);
        TreeSize right = dfs(r.right);

        if (left.size == -1 || right.size == -1 || r.val >= right.low || r.val <= left.high) {
            return new TreeSize(-1, 0, 0);      // if subtree is not BST, then current tree is not BST
        }

        max = Math.max(max, left.size + 1 + right.size);        // find global max

        return new TreeSize(left.size + 1 + right.size, Math.min(left.low, r.val), Math.max(right.high, r.val));
    }

    /**
     * Save current tree's size and lower/upper bound of valid BST.
     */
    class TreeSize {
        int size;
        int low;        // lower bound of current BST
        int high;       // upper bound of current BST

        /**
         * Save info of tree.
         *
         * @param size tree size
         * @param low  lower bound
         * @param high upper bound
         */
        TreeSize(int size, int low, int high) {
            this.size = size;
            this.low = low;
            this.high = high;
        }
    }
}