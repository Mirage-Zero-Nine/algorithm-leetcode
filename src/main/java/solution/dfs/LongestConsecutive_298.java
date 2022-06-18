package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * Path refers to any sequence of nodes from starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * @author BorisMirage
 * Time: 2019/06/12 15:21
 * Created with IntelliJ IDEA
 */

public class LongestConsecutive_298 {
    /**
     * Postorder traverse.
     *
     * @param root root of tree
     * @return length of longest consecutive path
     */
    public int longestConsecutive(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        int[] max = new int[1];
        dfs(root, max);

        return max[0];
    }

    /**
     * Postorder traverse.
     * Check nodes if current node value is equal to target value (previous node value + 1).
     *
     * @param r root node
     */
    private int dfs(TreeNode r, int[] max) {

        if (r == null) {
            return 0;
        }

        int left = dfs(r.left, max);
        int right = dfs(r.right, max);
        int local = 1;

        if (r.left != null && r.left.val == r.val + 1) {
            local = Math.max(left + 1, local);
        }
        if (r.right != null && r.right.val == r.val + 1) {
            local = Math.max(right + 1, local);
        }

        max[0] = Math.max(max[0], local);

        return local;
    }
}
