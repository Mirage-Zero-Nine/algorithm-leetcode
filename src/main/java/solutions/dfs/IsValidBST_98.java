package solutions.dfs;

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
     * Validates the tree by carrying the open interval allowed for each node.
     *
     * <p>The root may contain any {@code int}. A node in the left subtree must
     * be smaller than its ancestor, so the ancestor becomes the node's upper
     * bound. Likewise, a node in the right subtree must be greater than its
     * ancestor, so the ancestor becomes its lower bound. Passing both bounds
     * down the tree catches violations caused by any ancestor, not only by a
     * node's immediate parent. The strict comparisons also reject duplicates.</p>
     *
     * <p>{@code long} bounds are used because {@link Integer#MIN_VALUE} and
     * {@link Integer#MAX_VALUE} are valid node values and therefore cannot be
     * used safely as exclusive {@code int} sentinels.</p>
     *
     * @param root root of the tree
     * @return {@code true} if the tree satisfies the strict BST ordering
     * @see #isValidBSTInOrder(TreeNode)
     * @see #isValidBSTStack(TreeNode)
     */
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Recursively validates the subtree rooted at {@code root} within an open
     * interval. The interval is narrowed for each child: the left child keeps
     * the lower bound and uses the current value as its upper bound, while the
     * right child uses the current value as its lower bound.
     *
     * @param root current subtree root
     * @param min exclusive lower bound inherited from an ancestor
     * @param max exclusive upper bound inherited from an ancestor
     * @return {@code true} if every node in this subtree is within its bounds
     */
    private boolean dfs(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    /**
     * Validates the tree using recursive in-order traversal.
     *
     * <p>An in-order traversal of a valid BST visits values in strictly
     * increasing order. The one-element array lets recursive calls share the
     * previously visited value; it is initialized below {@code int}'s range
     * so {@link Integer#MIN_VALUE} remains valid. Any value that is not
     * greater than the previous value proves that the tree is invalid.</p>
     *
     * @param root root of the tree
     * @return {@code true} if the in-order sequence is strictly increasing
     * @see #isValidBST(TreeNode)
     * @see #isValidBSTStack(TreeNode)
     */
    public boolean isValidBSTInOrder(TreeNode root) {
        // corner case
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }

        // a mutable reference shared by all recursive calls.
        long[] array = new long[]{Long.MIN_VALUE};

        return dfs(root, array);
    }

    /**
     * Performs the recursive in-order traversal used by
     * {@link #isValidBSTInOrder(TreeNode)}.
     *
     * @param r current node
     * @param previous one-element array containing the last visited value
     * @return {@code true} if this subtree continues a strictly increasing
     *         in-order sequence
     */
    private boolean dfs(TreeNode r, long[] previous) {
        if (r == null) {
            return true;
        }

        boolean out = dfs(r.left, previous);

        // a valid BST must produce a strictly increasing in-order sequence.
        if ((long) r.val <= previous[0]) {
            return false;
        }

        // make this node the predecessor for the next visited node.
        previous[0] = r.val;

        return out && dfs(r.right, previous);
    }

    /**
     * Validates the tree by simulating recursive in-order traversal with an
     * explicit stack.
     *
     * <p>The stack stores the path to the next node whose left subtree has
     * been processed. When a node is popped, it is the next in-order value and
     * must be strictly greater than the previously popped node. This is the
     * iterative counterpart of {@link #isValidBSTInOrder(TreeNode)} and exits
     * as soon as the ordering is violated.</p>
     *
     * @param root root of the tree
     * @return {@code true} if the in-order sequence is strictly increasing
     */
    public boolean isValidBSTStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode previous = null;

        // push the entire left path; the top is the next in-order node.
        while (!stack.isEmpty() || current != null) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                // pop the next in-order node and compare it with its predecessor.
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
