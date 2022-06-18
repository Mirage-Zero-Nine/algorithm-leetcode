package solution.findkth;

import library.tree.binarytree.TreeNode;

import java.util.Stack;

/**
 * Given a binary search tree, write a function to find the kth smallest element in it.
 *
 * @author BorisMirage
 * Time: 2019/06/24 15:21
 * Created with IntelliJ IDEA
 */

public class KthSmallest_230 {
    /**
     * In-order traversal.
     *
     * @param root root node
     * @param k    kth smallest element
     * @return kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {

        /* Corner case */
        if (root == null) {
            return -1;
        }

        Stack<TreeNode> s = new Stack<>();
        s.add(root);
        TreeNode current = root;

        while (current != null || !s.isEmpty()) {

            while (current != null) {
                s.add(current);
                current = current.left;
            }

            current = s.pop();
            if (--k == 0) {
                break;
            }
            current = current.right;
        }

        return (current == null) ? -1 : current.val;
    }
}
