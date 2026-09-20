package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * (The values of the nodes may still be duplicates.)
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * The left-most node is defined as a leaf node you could reach when you firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * @author BorisMirage
 * Time: 2020/03/03 17:46
 * Created with IntelliJ IDEA
 */

public class BoundaryOfBinaryTree_545 {
    /**
     * The iterative traversal visits each node at most once for the leaves pass and uses O(n)
     * auxiliary space for its explicit stack, avoiding recursion-depth failures on skewed trees.
     * <p>
     * Collect the four boundary parts iteratively to keep the solution safe for the maximum
     * allowed tree depth. The left and right boundary are single paths, while the leaves are
     * visited with an explicit stack in left-to-right order. Excluding boundary leaves keeps each
     * node from being added twice. The four passes together take O(n) time and O(n) auxiliary
     * space in the worst case.
     *
     * @param root root of tree
     * @return the values of its boundary in anti-clockwise direction starting from root
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> out = new LinkedList<>();
        out.add(root.val);

        leftBoundary(root.left, out);       // left boundary
        leaves(root.left, out);             // leaves under left subtree of root
        leaves(root.right, out);            // leaves under right subtree of root
        rightBound(root.right, out);        // right boundary

        return out;
    }

    /**
     * Add all left boundary nodes of tree except the left-most leaf.
     * <p>
     * The path is followed iteratively, choosing the left child whenever it exists and otherwise
     * the right child. This is equivalent to the recursive definition without consuming call
     * stack space proportional to the tree height.
     * <p>
     * This takes O(h) time and O(1) auxiliary space, where h is the selected boundary height.
     *
     * @param root root node
     * @param out  output list
     */
    private void leftBoundary(TreeNode root, List<Integer> out) {
        while (root != null && (root.left != null || root.right != null)) {
            out.add(root.val);
            root = root.left == null ? root.right : root.left;
        }
    }

    /**
     * Add all leaves of current tree in left-to-right order.
     * <p>
     * A LIFO stack pushes the right child before the left child, so the left subtree is processed
     * first without recursive calls.
     * <p>
     * This takes O(n) time and O(n) auxiliary space in the worst case.
     *
     * @param root root of tree
     * @param out  output list
     */
    private void leaves(TreeNode root, List<Integer> out) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.left == null && current.right == null) {
                out.add(current.val);
                continue;
            }
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    /**
     * Add all right boundary nodes of tree except the right-most leaf, in reverse order.
     * <p>
     * Values are pushed onto an explicit stack while following the right child whenever possible,
     * then popped to produce the required bottom-up order without recursive calls.
     * <p>
     * This takes O(h) time and O(h) auxiliary space, where h is the selected boundary height.
     *
     * @param root root node
     * @param out  output list
     */
    private void rightBound(TreeNode root, List<Integer> out) {
        Deque<Integer> boundary = new ArrayDeque<>();
        while (root != null && (root.left != null || root.right != null)) {
            boundary.push(root.val);
            root = root.right == null ? root.left : root.right;
        }

        while (!boundary.isEmpty()) {
            out.add(boundary.pop());
        }
    }
}
