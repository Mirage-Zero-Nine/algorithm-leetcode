package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given two binary search trees root1 and root2.
 * Return a list containing all the integers from both trees sorted in ascending order.
 *
 * @author BorisMirage
 * Time: 2020/01/31 15:05
 * Created with IntelliJ IDEA
 */

public class GetAllElements_1305 {
    /**
     * In-order traverse iterator.
     * Use two stacks to store all parent nodes in in-order traverse order.
     *
     * @param root1 first root of tree
     * @param root2 second root of tree
     * @return list containing all the integers from both trees sorted in ascending order
     */
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> out = new LinkedList<>();

        Stack<TreeNode> tmp1 = inorder(root1, new Stack<>()), tmp2 = inorder(root2, new Stack<>());
        TreeNode current;

        while (!tmp1.isEmpty() || !tmp2.isEmpty()) {
            if (tmp1.isEmpty() || (!tmp2.isEmpty() && (tmp2.peek().val < tmp1.peek().val))) {
                current = tmp2.pop();
                out.add(current.val);
                tmp2 = inorder(current.right, tmp2);
            } else if (tmp2.isEmpty() || (tmp1.peek().val <= tmp2.peek().val)) {
                current = tmp1.pop();
                out.add(current.val);
                tmp1 = inorder(current.right, tmp1);
            }
        }

        return out;
    }

    /**
     * Return a stack contains all nodes in in-order traverse order.
     *
     * @param root current root
     * @param tmp  stack with previous nodes
     * @return stack contains all nodes in in-order traverse order
     */
    private Stack<TreeNode> inorder(TreeNode root, Stack<TreeNode> tmp) {

        /* Corner case */
        if (root == null) {
            return tmp;
        }

        while (root != null) {
            tmp.push(root);
            root = root.left;
        }

        return tmp;
    }
}
