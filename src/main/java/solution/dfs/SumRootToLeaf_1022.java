package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree, each node has value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most significant bit.
 * For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * Return the sum of these numbers.
 *
 * @author BorisMirage
 * Time: 2019/07/26 14:11
 * Created with IntelliJ IDEA
 */

public class SumRootToLeaf_1022 {
    /**
     * Tree DFS traversal. Note that sum * 2 + val is the calculation of binary number such as "110011".
     *
     * @param root root node
     * @return the sum of numbers
     */
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode r, int sum) {
        if (r == null) {
            return 0;
        }
        sum = sum * 2 + r.val;

        return (r.left == null && r.right == null) ? sum : dfs(r.left, sum) + dfs(r.right, sum);
    }
}
