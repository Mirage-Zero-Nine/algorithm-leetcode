package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST.
 * Return the root node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * 1. Search for a node to remove.
 * 2. If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 *
 * @author BorisMirage
 * Time: 2019/06/15 13:20
 * Created with IntelliJ IDEA
 */

public class DeleteNode_450 {
    /**
     * There are two sub tasks in this problem.
     * First, find the node in tree.
     * Second, find the successor in sub tree and replace the target node with this successor.
     * Find the node in BST: based on character of BST, basic in-order traverse in BST.
     * Second, there are two basic conditions: child is null or child is not null.
     * If any child is null, return the not null child.
     * If both child is null, return null.
     * If node has two subtrees, then find the left-most child in right subtree.
     * This is similar to the process of finding successor in BST.
     *
     * @param root root node in tree
     * @param key  node to be delete
     * @return BST after delete operation
     */
    public TreeNode deleteNode(TreeNode root, int key) {

        /* Corner case */
        if (root == null) {
            return null;
        }

        /*
         * If key is smaller than root's value, then only consider the left subtree.
         * If key is larger than root's value, only consider the right subtree.
         * Otherwise, delete this node. */
        if (key < root.val) {
            root.left = deleteNode(root.left, key);     // attach result node to left subtree
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);   // attach result node to right subtree
        } else {        // if found the target node (no duplicate here), delete it

            /*
             * If the left subtree is empty, return the right subtree and the node is delete.
             * Return right subtree if left subtree is empty.
             * These two cases do not require to consider the predecessor/successor. */
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            /*
             * If left subtree and right subtree are both non-empty, then find the smallest value in right subtree.
             * Find the largest element in left subtree is also fine.
             * This smallest node will replace the current node as delete.
             * Link the left subtree of deleted node to the smallest node in right subtree.
             * Return the modified tree. */
            TreeNode tmp = root.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            tmp.left = root.left;
            return root.right;
        }

        return root;
    }

    /**
     * Iterative approach.
     *
     * @param root root node in tree
     * @param key  node to be delete
     * @return BST after delete operation
     */
    public TreeNode iterative(TreeNode root, int key) {
        TreeNode current = root;
        TreeNode previous = null;

        while (current != null && current.val != key) {     // find target node
            previous = current;
            if (key < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (previous == null) {
            return deleteRootNode(current);
        }

        if (previous.left == current) {
            previous.left = deleteRootNode(current);
        } else {
            previous.right = deleteRootNode(current);
        }

        return root;
    }

    /**
     * Remove node and attach the subtree to previous node.
     *
     * @param root node
     * @return tree with node removed
     */
    private TreeNode deleteRootNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }

        TreeNode tmp = root.right;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        tmp.left = root.left;

        return root.right;
    }
}
