package Solution.DataStructure;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). It will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note:
 * 1. next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * 2. next() call will always be valid. There will be at least a next smallest number in the BST when next() is called.
 *
 * @author BorisMirage
 * Time: 2019/06/13 16:21
 * Created with IntelliJ IDEA
 */

public class BSTIterator_173 {

    private Stack<TreeNode> s = new Stack<>();      // save right child

    /**
     * Implement a In-order traversal with a stack.
     * Initially, find all right child of current node and move current node to leftmost child of root.
     *
     * @param root given root node
     */
    public BSTIterator_173(TreeNode root) {
        this.leftMost(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode next = s.pop();
        if (next.right != null) {
            leftMost(next.right);
        }
        return next.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !this.s.isEmpty();
    }

    /**
     * Find left most node of given node
     *
     * @param node given node
     */
    private void leftMost(TreeNode node) {
        while (node != null) {
            this.s.push(node);
            node = node.left;
        }
    }
}
