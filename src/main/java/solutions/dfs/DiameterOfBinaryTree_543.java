package solutions.dfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * @author BorisMirage
 * Time: 2019/07/13 15:44
 * Created with IntelliJ IDEA
 */

public class DiameterOfBinaryTree_543 {

    /**
     * <p>The returned length is measured in edges, so a null or singleton tree has diameter zero.
     * Node values do not affect the result.  The implementation uses an iterative post-order
     * traversal so a valid tree at the 10,000-node constraint boundary cannot overflow the Java
     * call stack, and keeps all per-call state local so one invocation cannot affect another.</p>
     * <p>
     * Finds the largest number of edges on any path between two nodes.
     *
     * <p>For every node, the longest path that uses that node as its highest point has length
     * {@code leftHeight + rightHeight}.  A post-order traversal makes both child heights
     * available before evaluating the node.  Two explicit stacks replace recursive calls, and
     * an identity map stores heights for this invocation only.</p>
     *
     * @param root root node
     * @return diameter in edges, or zero for a null tree
     * @implNote Runs in O(n) time and uses O(n) auxiliary space for the traversal and heights.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> pending = new ArrayDeque<>();
        Deque<TreeNode> postOrder = new ArrayDeque<>();
        pending.push(root);
        while (!pending.isEmpty()) {
            TreeNode node = pending.pop();
            postOrder.push(node);
            if (node.left != null) {
                pending.push(node.left);
            }
            if (node.right != null) {
                pending.push(node.right);
            }
        }

        Map<TreeNode, Integer> heights = new IdentityHashMap<>();
        int diameter = 0;
        while (!postOrder.isEmpty()) {
            TreeNode node = postOrder.pop();
            int left = node.left == null ? 0 : heights.get(node.left);
            int right = node.right == null ? 0 : heights.get(node.right);

            diameter = Math.max(diameter, left + right);
            heights.put(node, Math.max(left, right) + 1);
        }

        return diameter;
    }
}
