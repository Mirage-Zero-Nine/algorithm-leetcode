package Solution.Stack;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 * 1. The root is the maximum number in the array.
 * 2. The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * 3. The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 * Note:
 * 1. The size of the given array will be in the range [1,1000].
 *
 * @author BorisMirage
 * Time: 2019/06/28 13:39
 * Created with IntelliJ IDEA
 */

public class ConstructMaximumBinaryTree_654 {
    /**
     * Basic recursion.
     * Root is the largest one in array, left subtree is the left subarray max, right subtree is the right subarray max.
     *
     * @param nums given array
     * @return maximum tree
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        /* Corner case */
        if (nums.length == 0) {
            return null;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        TreeNode r = new TreeNode(nums[max]);
        r.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, max));
        r.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, max + 1, nums.length));

        return r;
    }

    /**
     * Use stack to store previous larger element in array as root.
     * If find a larger element, pop the stack to construct tree until find a larger one, which is root for current node.
     *
     * @param nums given array
     * @return maximum tree
     */
    public TreeNode useStack(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        for (int num : nums) {
            TreeNode cur = new TreeNode(num);

            while (!stack.isEmpty() && num > stack.peek().val) {
                cur.left = stack.pop();     // if cur > stack.peek, cur.left = stack.peek
            }

            if (!stack.isEmpty()) {
                stack.peek().right = cur;       //if cur < stack peek, peek.right = cur
            }
            stack.push(cur);

        }
        return stack.isEmpty() ? null : stack.get(0);
    }
}
