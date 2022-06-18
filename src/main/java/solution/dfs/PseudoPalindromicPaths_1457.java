package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree where node values are digits from 1 to 9.
 * A path in the tree is pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 * @author BorisMirage
 * Time: 2020/05/25 10:13
 * Created with IntelliJ IDEA
 */

public class PseudoPalindromicPaths_1457 {
    /**
     * DFS + bit mask to count the number of odd occurrence.
     * If a path has at least one palindromic permutation, then it can only contains at most one odd occurrence of int.
     * The general approach is to count the appearance of int during the DFS, then check the occurrence at leaf node.
     * However, since the node only contains ints from 1 - 9, then keep an integer to count the occurrence will suffice.
     * Flip the ith bit at each node, if the int has odd occurrence, it will finally be 1.
     * If the int has odd occurrence, it will finally be 0.
     * Check the number of 1 at leaf node. If there is at most one 1, then this path is valid.
     *
     * @param root root of the tree
     * @return the number of pseudo-palindromic paths going from the root node to leaf nodes
     */
    public int pseudoPalindromicPaths(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] total = new int[1];
        dfs(root, 0, total);

        return total[0];
    }

    /**
     * DFS to collect all the node values in path.
     * If current node is leaf, check the number of odd occurrence in path.
     * If at most one odd occurrence in path, then is path is pseudo-palindromic.
     *
     * @param root  current node
     * @param count occurrence of ints in path
     * @param total total pseudo-palindromic path
     */
    private void dfs(TreeNode root, int count, int[] total) {

        if (root == null) {
            return;
        }

        count ^= 1 << root.val - 1;     // bit mask, flip the ith bit at root.val

        /*
         * The odd node in the bit count will represented as 1, since it will flip odd times.
         * If the path is pseudo-palindromic, then at most one 1 can be allowed in the count.
         * count & (count - 1) will remove the right most 1 in the count.
         * If there are more than one 1 in count, then it will not be 0 and the path is not pseudo-palindromic. */
        if (root.left == null && root.right == null && (count & (count - 1)) == 0) {
            total[0]++;
        }

        dfs(root.left, count, total);
        dfs(root.right, count, total);
    }
}
