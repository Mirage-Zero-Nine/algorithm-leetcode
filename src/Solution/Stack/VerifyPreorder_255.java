package Solution.Stack;

import java.util.Stack;

/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * Each number in the sequence is unique.
 *
 * @author BorisMirage
 * Time: 2019/06/27 16:06
 * Created with IntelliJ IDEA
 */

public class VerifyPreorder_255 {
    /**
     * Follow the pre-order traversal rule. In BST, left children is always smaller than root node.
     * If current element is smaller than top of stack, current node is still in left sub tree.
     * Otherwise, current node is previous node's right child.
     * Update min value to current subtree's root when traverse to right subtree.
     * Each time, check if current node is smaller than current tree's root node.
     * Since the traversal process is to find left child in BST, if current node is smaller than root, return false.
     *
     * @param preorder given array
     * @return whether it is the correct preorder traversal sequence of a binary search tree
     */
    public boolean verifyPreorder(int[] preorder) {
        int min = Integer.MIN_VALUE;
        Stack<Integer> s = new Stack<>();

        for (int i : preorder) {
            if (i < min) {      // if left subtree is smaller than root value, then it is invalid BST
                return false;
            }
            while (!s.empty() && i > s.peek()) {        // if i > s.peek(), then i is in right subtree
                min = s.pop();      // update min value to current subtree's root when traverse to right subtree
            }

            s.push(i);      // current root value in subtree
        }

        return true;
    }

    /**
     * Use array instead of stack to find root of subtree.
     * Use a pointer to point at current root of tree.
     * The predecessor in array can be used as stack to store current root.
     *
     * @param preorder given array
     * @return whether it is the correct preorder traversal sequence of a binary search tree
     */
    private boolean constantSpace(int[] preorder) {

        /* Corner case */
        if (preorder.length < 2) {
            return true;
        }

        int min = Integer.MIN_VALUE, p = -1;

        for (int i : preorder) {
            if (i < min) {
                return false;
            }
            while (p >= 0 && i > preorder[p]) {
                min = preorder[p--];        // in-place find root node of current right subtree
            }
            preorder[++p] = i;              // in-place fill new root of current tree
        }

        return true;
    }

    public static void main(String[] args) {
        VerifyPreorder_255 test = new VerifyPreorder_255();
        System.out.println(test.verifyPreorder(new int[]{5, 2, 1, 3, 6}));
    }
}
