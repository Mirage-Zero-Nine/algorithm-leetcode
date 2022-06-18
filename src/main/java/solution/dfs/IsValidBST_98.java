package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * 1. The left subtree of a node contains only nodes with keys less than the node's key.
 * 2. The right subtree of a node contains only nodes with keys greater than the node's key.
 * 3. Both the left and right subtrees must also be binary search trees.
 *
 * @author BorisMirage
 * Time: 2018/10/06 16:13
 * Created with IntelliJ IDEA
 */

public class IsValidBST_98 {
    /**
     * In-order traversal with each root's value. Compare left subtree and right subtree.
     *
     * @param root root of tree
     * @return if given tree is a valid BST
     */
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);     // avoid int overflow
    }

    /**
     * In-order traversal that compare root's value to left and right.
     *
     * @param root current root
     * @param min  min value
     * @param max  max value
     * @return if given tree is a valid BST
     */
    private boolean dfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {       // no duplicate node as well
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    /**
     * Inorder traversal.
     *
     * @param root root of tree
     * @return if given tree is a valid BST
     */
    public boolean inOrder(TreeNode root) {

        /* Corner case */
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        long[] arr = new long[]{Long.MIN_VALUE};

        return dfs(root, arr);
    }

    /**
     * In order traversal to traverse all nodes.
     * Inorder traversal can traverse all nodes by increasing order if the tree is a valid BST.
     *
     * @param r        current node
     * @param previous previous node value
     * @return if given tree is a valid BST
     */
    private boolean dfs(TreeNode r, long[] previous) {
        if (r == null) {
            return true;
        }

        boolean out;
        out = dfs(r.left, previous);

        if ((long) r.val <= previous[0]) {
            return false;
        }

        previous[0] = r.val;

        return out & dfs(r.right, previous);
    }

    /**
     * Iterative solution based on in-order traversal and stack.
     *
     * @param root root of tree
     * @return if given tree is a valid BST
     */
    public boolean stack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode previous = null;

        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                TreeNode p = stack.pop();
                if (previous != null && p.val <= previous.val) {
                    return false;
                }
                previous = p;
                current = p.right;
            }
        }

        return true;
    }
}
