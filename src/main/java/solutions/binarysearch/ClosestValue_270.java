package solutions.binarysearch;

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
     * Otherwise, keep searching in BST until the iteration is ended. If two values are equally close,
     * the smaller value is retained as required by the problem contract.
     *
     * @param root   root node
     * @param target target double number
     * @return value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {

        int remain = root.val;

        while (root != null) {

            double candidateDistance = Math.abs(target - root.val);
            double remainingDistance = Math.abs(target - remain);
            if (candidateDistance < remainingDistance
                    || (candidateDistance == remainingDistance && root.val < remain)) {
                remain = root.val;      // find min value

                if (candidateDistance < 0.5) {
                    break;      // no other numbers can be closer than 0.5
                }
            }
            root = root.val > target ? root.left : root.right;
        }
        return remain;
    }
}
