package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Given a binary tree, return the tilt of the whole tree.
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node values.
 * Null node has tilt 0.
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
 *
 * @author BorisMirage
 * Time: 2019/08/14 09:38
 * Created with IntelliJ IDEA
 */

public class FindTilt_563 {
    private int sum = 0;

    /**
     * Computes subtree sums in postorder using explicit stacks. The iterative
     * traversal avoids overflowing the call stack on the 10,000-node skewed
     * trees allowed by the problem.
     *
     * @param root root node
     * @return tilt of the whole tree
     */
    public int findTilt(TreeNode root) {
        sum = 0;

        if (root == null) {
            return sum;
        }

        Deque<TreeNode> pending = new ArrayDeque<>();
        Deque<TreeNode> postorder = new ArrayDeque<>();
        Map<TreeNode, Integer> subtreeSums = new IdentityHashMap<>();
        pending.push(root);
        while (!pending.isEmpty()) {
            TreeNode current = pending.pop();
            postorder.push(current);
            if (current.left != null) {
                pending.push(current.left);
            }
            if (current.right != null) {
                pending.push(current.right);
            }
        }

        while (!postorder.isEmpty()) {
            TreeNode current = postorder.pop();
            int left = current.left == null ? 0 : subtreeSums.get(current.left);
            int right = current.right == null ? 0 : subtreeSums.get(current.right);
            sum += Math.abs(left - right);
            subtreeSums.put(current, left + right + current.val);
        }

        return sum;
    }
}
