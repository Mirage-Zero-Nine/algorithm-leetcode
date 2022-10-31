package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
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
     * Use a stack to store nodes.
     * Each time push right subtree to stack, then push left subtree. In this way to keep left subtree at top of stack.
     *
     * @param root root node of tree
     * @return preorder traversal of tree's values
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> out = new LinkedList<>();

        if (root == null) {
            return out;
        }
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                out.add(node.val);
                stack.push(node.right);
                stack.push(node.left);      // keep left at top of stack
            }
        }

        return out;
    }
}
