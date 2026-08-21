package solutions.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree and a sum.
 * Determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * @author BorisMirage
 * Time: 2019/03/22 21:51
 * Created with IntelliJ IDEA
 */

public class HasPathSum_112 {

    /**
     * Determines whether the tree contains a root-to-leaf path whose values
     * add up to {@code sum}.
     *
     * <p>The recursive call carries the remaining sum needed after visiting
     * the current node. A path is accepted only when the current node is a
     * leaf and its value exactly uses the remaining sum.</p>
     *
     * @param root the root of the binary tree
     * @param sum  the required root-to-leaf path sum
     * @return {@code true} if a matching root-to-leaf path exists; otherwise
     * {@code false}
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        int current = sum - root.val;

        // Only a leaf can complete a valid root-to-leaf path. Matching at an
        // internal node is not sufficient, even when its remaining sum is 0.
        if (root.left == null && root.right == null && current == 0) {
            return true;
        }

        return hasPathSum(root.left, current) || hasPathSum(root.right, current);
    }
}
