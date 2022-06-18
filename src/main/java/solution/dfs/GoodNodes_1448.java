package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree root.
 * A node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 *
 * @author BorisMirage
 * Time: 2020/05/19 15:07
 * Created with IntelliJ IDEA
 */

public class GoodNodes_1448 {
    /**
     * Pre-order traverse with current path's max tree node value.
     * If current node is larger than the max value, then one good node is found and max path value should be updated.
     *
     * @param root root of the tree
     * @return the number of good nodes in the binary tree
     */
    public int goodNodes(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        int[] count = new int[1];
        dfs(root, root.val, count);

        return count[0];
    }

    /**
     * Pre-order traverse to count the number of good nodes.
     *
     * @param root  root of the tree
     * @param max   max value in current path
     * @param count int array stores the number of good nodes during the traverse
     */
    private void dfs(TreeNode root, int max, int[] count) {

        if (root == null) {
            return;
        }

        if (root.val >= max) {
            count[0]++;
            max = root.val;
        }

        dfs(root.left, max, count);
        dfs(root.right, max, count);
    }
}
