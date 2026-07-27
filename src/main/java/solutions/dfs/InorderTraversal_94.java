package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the in order traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2018/10/02 20:22
 * Created with IntelliJ IDEA
 */

public class InorderTraversal_94 {
    /**
     * Returns the inorder traversal of a binary tree using recursion.
     * The traversal visits the left subtree, the current node, and then the right subtree.
     *
     * @param root root node
     * @return list of nodes in inorder traversal order
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        dfs(output, root);
        return output;
    }

    private void dfs(List<Integer> output, TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(output, root.left);
        output.add(root.val);
        dfs(output, root.right);
    }

    /**
     * Returns the inorder traversal of a binary tree using an explicit stack.
     * Nodes are pushed while traversing left, then popped and visited before their right subtrees are traversed.
     *
     * @param root root node
     * @return list of nodes in inorder traversal order
     */
    public List<Integer> inorderTraversalStack(TreeNode root) {

        List<Integer> output = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            output.add(current.val);
            current = current.right;
        }
        return output;
    }
}
