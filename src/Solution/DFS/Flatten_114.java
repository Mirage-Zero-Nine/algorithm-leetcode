package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.Stack;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * @author BorisMirage
 * Time: 2019/06/27 14:11
 * Created with IntelliJ IDEA
 */

public class Flatten_114 {
    /**
     * Morris traversal.
     * Find the right most node in left sub tree. Its child is the parent of the right child of current node.
     * Link these two nodes. Then link current node's left child to current node's right child.
     *
     * @param root root of tree
     */
    public void flatten(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return;
        }

        TreeNode tmp = root;

        while (tmp != null) {

            if (tmp.left != null) {
                TreeNode rightMost = tmp.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right;
                }
                rightMost.right = tmp.right;
                tmp.right = tmp.left;
                tmp.left = null;
            }
            tmp = tmp.right;
        }
    }

    /**
     * Preorder traversal.
     * Use a stack to store the left subtree and right subtree for later traversal.
     *
     * @param root root of tree
     */
    public void flattenWithStack(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return;
        }
        Stack<TreeNode> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()) {
            TreeNode current = s.pop();
            if (current.right != null) {
                s.push(current.right);
            }
            if (current.left != null) {
                s.push(current.left);       // keep left at top of stack
            }
            current.right = (s.isEmpty()) ? null : s.peek();        // point left subtree to flatten right subtree.
            current.left = null;
        }
    }
}
