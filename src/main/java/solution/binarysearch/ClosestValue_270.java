package solution.binarysearch;

import library.tree.binarytree.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * @author BorisMirage
 * Time: 2019/06/12 19:18
 * Created with IntelliJ IDEA
 */

public class ClosestValue_270 {
    /**
     * Traverse of tree until find the closest value in tree.
     * If the absolute difference between target and node value is less than 0.5, then the closest node is found.
     * Otherwise, keep searching in BST until the iteration is ended.
     *
     * @param root   root node
     * @param target target double number
     * @return value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {

        int remain = root.val;

        while (root != null) {

            if (Math.abs(target - root.val) < Math.abs(target - remain)) {
                remain = root.val;      // find min value

                if (Math.abs(target - root.val) < 0.5) {
                    break;      // no other numbers can be closer than 0.5
                }
            }
            root = root.val > target ? root.left : root.right;
        }
        return remain;
    }
}
