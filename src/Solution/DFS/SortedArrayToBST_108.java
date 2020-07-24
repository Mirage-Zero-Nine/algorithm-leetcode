package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * Height-balanced binary tree is a binary tree that the depth of the two subtrees of node never differ by more than 1.
 *
 * @author BorisMirage
 * Time: 2019/06/11 10:33
 * Created with IntelliJ IDEA
 */

public class SortedArrayToBST_108 {
    /**
     * Root of the tree is at the mid of array and sub array.
     *
     * @param nums given num array
     * @return height balanced BST
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return (nums.length == 0 ? null : new TreeNode(nums[0]));
        }

        return builder(nums, 0, nums.length - 1);
    }

    /**
     * Pre-order traverse.
     *
     * @param num   given num array
     * @param start start index
     * @param end   end index
     * @return root of height balanced BST
     */
    private TreeNode builder(int[] num, int start, int end) {

        if (start > end) {      // end point of recursion
            return null;
        }

        int mid = start + (end - start) / 2;        // avoid overflow

        TreeNode root = new TreeNode(num[mid]);

        root.left = builder(num, start, mid - 1);
        root.right = builder(num, mid + 1, end);
        return root;
    }
}
