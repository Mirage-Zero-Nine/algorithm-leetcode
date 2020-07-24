package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2019/06/27 11:14
 * Created with IntelliJ IDEA
 */

public class PostorderTraversal_145 {
    /**
     * Order of preorder traversal is root -> left -> right, and order of postorder traversal is left -> right -> root.
     * Therefore, add node to the head of result list will suffice the postorder traversal.
     * This is same as reverse list.
     *
     * @param root root node
     * @return postorder traversal of tree's values
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<>();

        /* Corner case */
        if (root == null) {
            return out;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();

            if (cur != null) {
                out.add(0, cur.val);        // add current root to the head of list
                stack.push(cur.left);
                stack.push(cur.right);
            }
        }
        return out;
    }
}
