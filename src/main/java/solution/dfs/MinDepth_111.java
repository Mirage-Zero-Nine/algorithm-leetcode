package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * @author BorisMirage
 * Time: 2019/02/28 14:35
 * Created with IntelliJ IDEA
 */

public class MinDepth_111 {

    /**
     * In order traversal.
     *
     * @param root root node
     * @return depth
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = minDepth(root.left);
        int r = minDepth(root.right);

        return (l == 0 || r == 0) ? l + r + 1 : Math.min(l, r) + 1;
    }
}
