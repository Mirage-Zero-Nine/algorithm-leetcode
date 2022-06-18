package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty.
 * Flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes.
 * Return the new root.
 *
 * @author BorisMirage
 * Time: 2019/07/06 15:21
 * Created with IntelliJ IDEA
 */

public class UpsideDownBinaryTree_156 {
    /**
     * For each node, this "upside down" operates same.
     * Convert left child of current node to its parent, and current node becomes left child's right child.
     * Current node's right child becomes left child's (now converted to current node's parent) left child.
     * Note that in this problem, right child will either be leave node, or empty.
     *
     * @param root root node of given tree
     * @return flipped tree
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {

        /* Corner case and end point */
        if (root == null || root.left == null && root.right == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);     // return left most child node

        /*
         * "Upside down" first is to turn previous left most child to new root node.
         * Then move previous root to its left child's right node, and move root's left child to new root's left. */
        root.left.left = root.right;
        root.left.right = root;

        root.left = null;       // in case this is the last level
        root.right = null;

        return newRoot;     // return current level root for next recursion
    }

    /**
     * Iterative solution.
     * For each node, this "upside down" operates same.
     * Convert left child of current node to its parent, and current node becomes left child's right child.
     * Current node's right child becomes left child's (now converted to current node's parent) left child.
     * Note that in this problem, right child will either be leave node, or empty.
     *
     * @param root root node of given tree
     * @return flipped tree
     */
    public TreeNode iterative(TreeNode root) {
        TreeNode current = root, previous = null, tmp = null, next;

        while (current != null) {
            next = current.left;        // store next node first
            current.left = tmp;         // left child is previous node's right child, initially, it is null
            tmp = current.right;        // save next tmp node
            current.right = previous;   // current node's right child is previous node
            previous = current;         // make previous node to current node
            current = next;             // move current node to next
        }

        return previous;
    }
}
