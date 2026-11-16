package solutions.design;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

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
    // Pending ancestors on the traversal path; the top is the next smallest unvisited node.
    private final Deque<TreeNode> stack = new ArrayDeque<>();

    /**
     * Initializes a BST iterator that visits nodes in ascending order.
     * <p>
     * This iterator uses a stack to keep track of the path to the smallest remaining node.
     * When a node is returned, we check its right child and push all the way down its
     * left side. This ensures the next smallest node is always ready at the top of the stack,
     * leaving the original tree completely unchanged.
     * <p>
     * Why use a stack instead of Morris traversal?
     * Morris traversal uses O(1) space by temporarily rewriting tree pointers, but if you
     * stop iterating early, the tree is left modified. Using a stack takes a little extra
     * memory, but it lets you stop iterating at any time safely.
     * <p>
     * Time complexity: O(h) to initialize, O(1) amortized per next() call.
     * Space complexity: O(h) for the stack.
     *
     * @param root the root node of the binary search tree
     */
    public BSTIterator_173(TreeNode root) {
        leftMost(root);
    }

    /**
     * Returns the next smallest key. The caller must only call this when {@link #hasNext()} is true.
     */
    public int next() {
        TreeNode next = stack.pop();
        leftMost(next.right);
        return next.val;
    }

    /**
     * Reports whether another node remains without advancing the traversal.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Adds the path from a subtree root to its smallest node to the pending path stack.
     */
    private void leftMost(TreeNode n) {
        while (n != null) {
            stack.push(n);
            n = n.left;
        }
    }
}
