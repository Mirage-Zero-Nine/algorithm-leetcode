package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 145: Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2019/06/27 11:14
 * Created with IntelliJ IDEA
 */

public class PostorderTraversal {
    /**
     * Iterative approach. Using a stack to store the nodes during the traverse.
     * Each time, pop up the top of the stack, if it's not null, add its value to the first element of output list.
     * The reason is that the post order traversal is left -> right -> root.
     * The order of using stack is actually root -> right -> left. Hence, we need to reverse it.
     *
     * @param root root node
     * @return postorder traversal of tree's values
     */
    public List<Integer> postorderTraversalStack(TreeNode root) {
        List<Integer> output = new ArrayList<>();

        if (root == null) {
            return output;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current != null) {
                output.addFirst(current.val);
                stack.push(current.left);
                stack.push(current.right);
            }
        }
        return output;
    }

    /**
     * Normal post order traversal. Following the order of left -> right -> root for each node.
     *
     * @param root root node
     * @return postorder traversal of tree's values
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
