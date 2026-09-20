package solutions.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

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
     * The input contains 1 to 100,000 nodes and each node value is in [-10,000, 10,000].
     * A null root is also accepted by this implementation and returns zero.
     * The traversal carries the maximum value on each root-to-node path and uses an explicit stack
     * so valid deep trees do not depend on the JVM call-stack depth.
     * <p>
     * Iterative pre-order traversal with the maximum value seen on each node's root path.
     * An explicit stack keeps the solution safe for the problem's valid 100,000-node skewed trees.
     *
     * @param root root of the tree
     * @return the number of good nodes in the binary tree
     * @implNote The traversal runs in O(n) time and uses O(h) auxiliary space, where h is the
     * tree height (O(n) in the worst case).
     */
    public int goodNodes(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        int count = 0;
        Deque<TreeNode> nodes = new ArrayDeque<>();
        Deque<Integer> pathMaximums = new ArrayDeque<>();
        nodes.push(root);
        pathMaximums.push(root.val);

        while (!nodes.isEmpty()) {
            TreeNode current = nodes.pop();
            int pathMaximum = pathMaximums.pop();
            if (current.val >= pathMaximum) {
                count++;
                pathMaximum = current.val;
            }
            if (current.right != null) {
                nodes.push(current.right);
                pathMaximums.push(pathMaximum);
            }
            if (current.left != null) {
                nodes.push(current.left);
                pathMaximums.push(pathMaximum);
            }
        }
        return count;
    }
}
