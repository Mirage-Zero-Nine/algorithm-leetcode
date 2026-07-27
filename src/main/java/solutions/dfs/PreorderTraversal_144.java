package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2019/06/27 10:52
 * Created with IntelliJ IDEA
 */

public class PreorderTraversal_144 {

    /**
     * Returns the preorder traversal of a binary tree using recursion.
     * Each recursive call visits the current node, the left subtree, and then the
     * right subtree. This method takes {@code O(n)} time and uses {@code O(h)}
     * auxiliary call-stack space, where {@code n} is the number of nodes and
     * {@code h} is the tree height.
     *
     * @param root root node of the tree
     * @return node values in preorder traversal order
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // corner case
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> output = new ArrayList<>();
        dfs(output, root);
        return output;
    }

    private void dfs(List<Integer> output, TreeNode root) {
        if (root == null) {
            return;
        }

        output.add(root.val);
        dfs(output, root.left);
        dfs(output, root.right);
    }

    /**
     * Returns the preorder traversal of a binary tree using an explicit stack.
     * The right child is pushed before the left child so that the left child is
     * visited first. This method takes {@code O(n)} time and uses {@code O(h)}
     * auxiliary stack space, where {@code n} is the number of nodes and
     * {@code h} is the tree height.
     *
     * @param root root node of the tree
     * @return node values in preorder traversal order
     */
    public List<Integer> preorderTraversalStack(TreeNode root) {
        // corner case
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> output = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                output.add(node.val);
                stack.push(node.right);
                stack.push(node.left);      // keep left at top of stack
            }
        }

        return output;
    }
}
