package solution.dfs;

import library.tree.binarytree.TreeNode;

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
        if (nums.length == 0) {
            return null;
        }

        return builder(nums, 0, nums.length - 1);
    }

    /**
     * Pre-order traverse.
     * Note that root located at middle of the sorted array.
     *
     * @param nums  given num array
     * @param left  start index
     * @param right right index
     * @return root of height balanced BST
     */
    private TreeNode builder(int[] nums, int left, int right) {

        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2; // root located at middle of the sorted array
        TreeNode root = new TreeNode(nums[mid]);

        root.left = builder(nums, left, mid - 1); // left subtree, like binary search
        root.right = builder(nums, mid + 1, right);

        return root;
    }
}
