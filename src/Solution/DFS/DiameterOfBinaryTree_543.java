package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * @author BorisMirage
 * Time: 2019/07/13 15:44
 * Created with IntelliJ IDEA
 */

public class DiameterOfBinaryTree_543 {
    private int max = 0;        // global max

    /**
     * Find max depth in both left sub tree and right subtree.
     *
     * @param root root node
     * @return max path
     */
    public int diameterOfBinaryTree(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }
        path(root);
        return max;
    }

    /**
     * Find current tree node max depth.
     *
     * @param root root node
     * @return max depth of current node
     */
    private int path(TreeNode root) {

        /* End point */
        if (root == null) {
            return 0;
        }

        int left = path(root.left);     // left root depth
        int right = path(root.right);       // right root depth

        max = Math.max(max, left + right);      // global max sum of path

        return Math.max(left, right) + 1;       // current max
    }
}
