package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 145: Given a binary tree, return the postorder traversal of its nodes' values.
 * Left -> current -> right.
 *
 * @author BorisMirage
 * Time: 2019/06/27 11:14
 * Created with IntelliJ IDEA
 */

public class PostorderTraversal {
    /**
     * Returns the postorder traversal of a binary tree using an explicit stack.
     * The stack visits nodes in root-right-left order, and reversing the collected
     * values produces the required left-right-root order.
     * This method takes {@code O(n)} time and uses {@code O(n)} auxiliary space.
     *
     * @param root root node
     * @return node values in postorder traversal order
     */
    public List<Integer> postorderTraversalStack(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> output = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            output.add(current.val);
            if (current.left != null) {
                stack.push(current.left);
            }
            if (current.right != null) {
                stack.push(current.right);
            }
        }

        Collections.reverse(output);
        return output;
    }

    /**
     * Returns the postorder traversal of a binary tree using recursion.
     * Each recursive call visits the left subtree, the right subtree, and then the
     * current node. This method takes {@code O(n)} time and uses {@code O(h)} call
     * stack space, where {@code h} is the tree height.
     *
     * @param root root node
     * @return node values in postorder traversal order
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        var output = new ArrayList<Integer>();
        traversal(output, root);
        return output;
    }

    /**
     * Actual traversal function.
     *
     * @param tmp  output list
     * @param root current node
     */
    private void traversal(List<Integer> tmp, TreeNode root) {
        if (root == null) {
            return;
        }

        traversal(tmp, root.left);
        traversal(tmp, root.right);
        tmp.add(root.val);
    }
}
