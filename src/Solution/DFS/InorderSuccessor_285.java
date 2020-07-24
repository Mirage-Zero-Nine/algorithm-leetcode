package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.Stack;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 * Note:
 * 1. If the given node has no in-order successor in the tree, return null.
 * 2. It's guaranteed that the values of the tree are unique.
 *
 * @author BorisMirage
 * Time: 2019/06/13 10:27
 * Created with IntelliJ IDEA
 */

public class InorderSuccessor_285 {
    /**
     * Time complexity: O(n).
     * Space complexity: O(h), height of BST.
     *
     * @param root root node
     * @param p    target node
     * @return in-order successor of that node in the BST
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {

        /* Corner case */
        if (root == null) {
            return null;
        }
        if (root.val == p.val) {
            return root.right;
        }

        Stack<TreeNode> s = new Stack<>();
        TreeNode tmp;
        while (root != null) {
            s.push(root);
            root = root.left;
        }

        while (!s.isEmpty()) {
            tmp = s.pop();
            if (tmp.val == p.val) {
                return (tmp.right == null) ? ((s.size() == 0) ? null : s.pop()) : findSmallest(tmp.right);
            } else {
                TreeNode n = tmp.right;
                while (n != null) {
                    s.push(n);
                    n = n.left;
                }
            }
        }

        return null;
    }

    private TreeNode findSmallest(TreeNode r) {
        while (r.left != null) {
            r = r.left;
        }
        return r;
    }

    /**
     * Successor will be at right sub tree, or previous root, or null.
     * The target is to find p's closest node (in inorder traversal) and the node's value is larger than p's value.
     * The in-order traversal in BST is to reveal the ascending order of elements in tree.
     *
     * @param root root node
     * @param p    target node
     * @return in-order successor in the tree, or null
     */
    public TreeNode recursion(TreeNode root, TreeNode p) {

        return successor(root, p);
    }

    /**
     * p is the largest element in current tree:
     * The in-order successor is the parent of current BST’s root.
     * p is in the right sub tree:
     * Recursively find a tree that is the right sub tree of p, the in-order successor is the smallest in the subtree.
     * Or returns previous condition if p is the largest element in BST.
     * p is in the left sub tree:
     * Recursively found p in left subtree, and then turned into previous condition.
     *
     * @param r current root
     * @param p target node
     * @return in-order successor, or null
     */
    private TreeNode successor(TreeNode r, TreeNode p) {
        if (r == null) {
            return null;
        }

        if (p.val >= r.val) {
            return successor(r.right, p);        // rightmost sub tree's successor is at its right sub tree
        } else {        // p.val < r.val -> left subtree

            TreeNode left = successor(r.left, p);
            return (left == null) ? r : left;
        }
    }

    /**
     * In-order predecessor of BST.
     * Idea is almost the same, simply switch the condition, since predecessor is the previous element in BST.
     *
     * @param root current root
     * @param p    target node
     * @return in-order predecessor, or null
     */
    private TreeNode predecessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        if (root.val >= p.val) {
            return predecessor(root.left, p);       // find smaller number
        } else {
            TreeNode right = predecessor(root.right, p);
            return (right != null) ? right : root;
        }
    }
}
