package solutions.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a non-empty binary tree, find the maximum path sum.
 * A path is defined as a sequence of nodes from starting node to node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * @author BorisMirage
 * Time: 2019/06/11 14:17
 * Created with IntelliJ IDEA
 */

public class MaxPathSum_124 {
    private int MAX = Integer.MIN_VALUE;

    /**
     * Finds the best non-empty path by visiting children before their parent. Each node combines
     * its value with the useful downward paths from both children and records that complete path
     * in {@code MAX}. Every path has one highest node, so considering each node this way covers
     * every possible optimum, including paths that never reach the tree's root.
     *
     * <p>The tree is not modified. The running maximum is reset for each call, allowing sequential
     * reuse; concurrent calls on the same instance are not supported. Problem inputs contain
     * 1 to 30,000 nodes with values from -1,000 to 1,000, so sums fit in an {@code int}. A singleton
     * also supports any {@code int} value. Very deep trees may exhaust the JVM's recursion stack.
     *
     * <p>Time complexity: O(n), where n is the number of nodes; each node is visited once.
     * <p>Auxiliary space complexity: O(h), where h is the tree height, for the recursion stack;
     * this is O(n) for a chain and O(log n) for a balanced tree.
     *
     * @param root root of a non-empty binary tree
     * @return largest sum of a path containing at least one node
     */
    public int maxPathSum(TreeNode root) {
        // A previous call must not affect this tree, even when every value here is negative.
        MAX = Integer.MIN_VALUE;
        dfs(root);
        return MAX;
    }

    /**
     * Returns the best downward path starting at this node, while updating {@code MAX} with
     * complete paths anywhere in its subtree. Negative child gains are discarded because ending
     * at this node is better than including a branch that reduces the sum.
     *
     * <p>The complete candidate may use both children, but the returned path must leave this node
     * as an endpoint so its parent can extend it. Taking both children and then the parent would
     * give this node three path edges, which is a branch rather than a single path. Therefore,
     * {@code Math.max(left, right) + root.val} keeps only the better child, or neither if both
     * gains are zero. The returned sum can still be negative; the parent decides whether to use it.
     *
     * <p>Time complexity: O(s), where s is the number of nodes in this subtree; each is visited once.
     * <p>Auxiliary space complexity: O(h), where h is this subtree's height, for recursive calls.
     *
     * @param root current subtree root, or {@code null} for a missing child
     * @return best downward sum including this node, or zero for a missing child
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Omitting a negative branch improves the path without disconnecting it.
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        // This completed path can turn from one child to the other through the current node.
        MAX = Math.max(MAX, left + right + root.val);
        // The parent can extend only one arm: both arms plus the parent would create a fork.
        return Math.max(left, right) + root.val;
    }
}
