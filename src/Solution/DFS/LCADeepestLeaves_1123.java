package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a rooted binary tree, return the lowest common ancestor of its deepest leaves.
 *
 * @author BorisMirage
 * Time: 2020/05/20 18:10
 * Created with IntelliJ IDEA
 */

public class LCADeepestLeaves_1123 {
    int height = 0;
    TreeNode lca = null;        // LCA of deepest leaves

    /**
     * DFS (post-order traverse) to find the LCA of the deepest leaves.
     * Note that if there is only one node at the last level of tree, then the LCA is this leave itself.
     * Keep an integer to store the height of the tree.
     * There is at least one leaf node at tree height, which is the deepest leaf.
     * If a node's left and right subtree has same depth and they are equal to the height, then it is the target LCA.
     *
     * @param root root of the tree
     * @return the lowest common ancestor of its deepest leaves
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return null;
        }

        dfs(root, 0);
        return lca;
    }

    /**
     * DFS to find the LCA of the deepest leaves.
     * If current node is LCA of the deepest leaves, then the depth of left and right subtree should be the same.
     * Also, the depth of two subtrees should be the same to the max depth.
     * This means the depth passed by its left and right subtree should be same.
     *
     * @param root  current node
     * @param depth depth of current node
     * @return max depth of left and right subtree
     */
    private int dfs(TreeNode root, int depth) {
        height = Math.max(height, depth);

        if (root == null) {
            return depth;
        }

        int left = dfs(root.left, depth + 1);
        int right = dfs(root.right, depth + 1);

        if (left == right && right == height) {     // if left and right subtree has same depth, and same as the height
            lca = root;                             // then the LCA of deepest leaves is found
        }

        return Math.max(left, right);
    }
}
