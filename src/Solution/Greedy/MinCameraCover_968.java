package Solution.Greedy;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 * Each camera at a node can monitor its parent, itself, and its immediate children.
 * Calculate the minimum number of cameras needed to monitor all nodes of the tree.
 *
 * @author BorisMirage
 * Time: 2019/07/26 14:59
 * Created with IntelliJ IDEA
 */

public class MinCameraCover_968 {
    private int res = 0;

    /**
     * Greedy. Start from the leave's parent, set a camera and remove all covered nodes.
     * Repeat this process until all nodes are covered.
     *
     * @param root root node
     * @return minimum number of cameras needed to monitor all nodes of the tree
     */
    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    /**
     * DFS to cover all nodes. If child of left or right has a camera, then current node is covered.
     *
     * @param root current root node
     * @return status of node, 0 for leaf, 1 for parent of a leaf with a camera on this node, 2 for covered without camera.
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 2;       // null node classified as covered
        }

        int left = dfs(root.left), right = dfs(root.right);

        if (left == 0 || right == 0) {
            res++;
            return 1;       // add a camera to cover leave
        }

        return (left == 1 || right == 1) ? 2 : 0;       // if any of child is as covered, then current node is covered without camera
    }
}
