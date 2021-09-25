package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 *
 * @author BorisMirage
 * Time: 2021/09/25 13:50
 * Created with IntelliJ IDEA
 */

public class SubtreeWithAllDeepest_865 {
    int height = 0;
    TreeNode lca = null;        // LCA of deepest leaves

    /**
     * Identical question as 1123.
     * To find the subtree with all deepest is the same to find the LCA of all deepest leave nodes.
     * DFS (post-order traverse) to find the LCA of the deepest leaves.
     * Note that if there is only one node at the last level of tree, then the LCA is this leave itself.
     * Keep an integer to store the height of the tree.
     * There is at least one leaf node at tree height, which is the deepest leaf.
     * If a node's left and right subtree has same depth and they are equal to the height, then it is the target LCA.
     *
     * @param root root of the tree
     * @return the lowest common ancestor of its deepest leaves
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {

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

        depth++;
        int left = dfs(root.left, depth);
        int right = dfs(root.right, depth);

        if (left == right && right == height) {     // if left and right subtree has same depth, and same as the height
            lca = root;                             // then the LCA of deepest leaves is found
        }

        return Math.max(left, right);
    }
}
