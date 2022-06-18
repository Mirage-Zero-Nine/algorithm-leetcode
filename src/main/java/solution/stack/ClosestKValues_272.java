package solution.stack;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note:
 * 1. Given target value is a floating point.
 * 2. You may assume k is always valid, that is: k ≤ total nodes.
 * 3. You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * @author BorisMirage
 * Time: 2019/11/11 17:06
 * Created with IntelliJ IDEA
 */
public class ClosestKValues_272 {
    /**
     * Naive solution. Traverse the tree with inorder traverse.
     * Use two stacks to store all values less than target and all values larger than target.
     * After the inorder traverse is completed, check the top of two stacks and find the closer one.
     * Pop that element out, continue until k closest elements.
     *
     * @param root   root node
     * @param target target value
     * @param k      find k closest numbers
     * @return k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (root == null) {
            return out;
        }

        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();

        inorder(root, left, true, target);      // find all elements smaller than target, top of stack is the largest
        inorder(root, right, false, target);    // find all elements larger than target, top of stack is the smallest

        while (k-- > 0) {
            int tmp;
            if (left.isEmpty()) {
                tmp = right.pop();
            } else if (right.isEmpty()) {
                tmp = left.pop();
            } else {        // find closet one from stacks
                tmp = (Math.abs(left.peek() - target) > Math.abs(right.peek() - target)) ? right.pop() : left.pop();
            }
            out.add(tmp);
        }

        return out;
    }

    /**
     * Store all elements that are smaller/larger than target into stack.
     * The normal order of inorder traverse is left -> root -> right, which will be used in right subtree.
     * The left subtree will use the reverse order of inorder traverse (right -> root -> left).
     * In the recursion, it is required to assure the top of stack is the floor/ceiling element of target.
     *
     * @param r       root node
     * @param s       stack stores value of nodes
     * @param reverse true if traverse in reverse order of inorder traversal (right -> root -> left)
     * @param target  target value
     */
    private void inorder(TreeNode r, Stack<Integer> s, boolean reverse, double target) {

        /* Corner case */
        if (r == null) {
            return;
        }

        TreeNode next = (reverse) ? r.right : r.left;
        inorder(next, s, reverse, target);

        if ((reverse && target > r.val) || (!reverse && target <= r.val)) {
            return;     // if traverse to nodes larger/smaller than target, break recursion
        }

        s.push(r.val);
        next = (reverse) ? r.left : r.right;
        inorder(next, s, reverse, target);
    }
}
