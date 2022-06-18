package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * @author BorisMirage
 * Time: 2018/10/09 16:28
 * Created with IntelliJ IDEA
 */

public class MaxDepth_104 {
    /**
     * DFS, using pre-order traversal.
     *
     * @param root root node
     * @return max depth.
     */
    public int maxDepth(TreeNode root) {

        /* Corner case and end point */
        if (root == null) {
            return 0;
        }

//        System.out.println(root.val);

        /* Keep find left sub tree and right sub tree until reaches the end */
        return Integer.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
