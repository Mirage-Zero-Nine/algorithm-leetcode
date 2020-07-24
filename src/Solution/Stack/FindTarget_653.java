package Solution.Stack;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.Stack;

/**
 * Given a Binary Search Tree and a target number.
 * Return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 * @author BorisMirage
 * Time: 2019/02/22 16:34
 * Created with IntelliJ IDEA
 */

public class FindTarget_653 {
    /**
     * Use two stacks to record left sub tree and right sub tree.
     * If sum is smaller than target, record left sub tree's right sub tree.
     * If sum is larger, do reversely.
     *
     * @param root root node
     * @param k    target
     * @return if the tree contains 2 elements that their sum is equal to k
     */
    public boolean findTarget_Stack(TreeNode root, int k) {

        Stack<TreeNode> ls = new Stack<>();
        Stack<TreeNode> rs = new Stack<>();

        for (TreeNode r = root; r != null; r = r.left) {
            ls.push(r);
        }
        for (TreeNode r = root; r != null; r = r.right) {
            rs.push(r);
        }

        while (ls.size() != 0 && rs.size() != 0 && ls.peek() != rs.peek()) {
            int temp = ls.peek().val + rs.peek().val;

            if (temp == k) {
                return true;
            } else if (temp < k) {
                for (TreeNode r = ls.pop().right; r != null; r = r.left) {
                    ls.push(r);
                }
            } else {
                for (TreeNode r = rs.pop().left; r != null; r = r.right) {
                    rs.push(r);
                }
            }
        }
        return false;
    }
}
