package Solution.BinarySearch;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an integer array nums, return a new counts array.
 * The counts array has the property where counts[i] is the number of SMALLER elements to the right of nums[i].
 *
 * @author BorisMirage
 * Time: 2019/06/13 15:59
 * Created with IntelliJ IDEA
 */

public class CountSmaller_315 {
    /**
     * Create a binary tree to store the elements with a counter that counting all elements smaller than current one.
     *
     * @param nums given int array
     * @return array contains numbers of element in nums[i, length - 1] that is smaller than nums[i]
     */
    public List<Integer> countSmaller(int[] nums) {

        List<Integer> out = new LinkedList<>();

        /* Corner case */
        if (nums.length < 1) {
            return out;
        }

        BinaryTreeCount root = new BinaryTreeCount(nums[nums.length - 1]);
        out.add(0);

        for (int i = nums.length - 2; i > -1; i--) {
            out.add(0, insert(root, nums[i]));      // insert elements to tree
        }

        return out;
    }

    /**
     * Insert array value into binary tree and count all left elements that is smaller than current value.
     * This is a insert operation, therefore, it will only count elements at certain part of array.
     * For instance, in this problem, only the elements that at right of nums[i] will be counted.
     *
     * @param root root node
     * @param val  value
     * @return # of elements that are smaller than given value
     */
    private int insert(BinaryTreeCount root, int val) {

        int count = 0;      // count # of elements smaller than current number

        while (true) {

            if (val > root.val) {       // if current value is larger than root value, insert to right child
                count += root.count;        // add all elements that are smaller than current root
                if (root.right == null) {
                    root.right = new BinaryTreeCount(val);      // reaches the end and insert node
                    break;
                }
                root = root.right;
            } else {        // insert to left child if small or equal to root
                root.count++;
                if (root.left == null) {
                    root.left = new BinaryTreeCount(val);       // reaches the end and insert node
                    break;
                }
                root = root.left;
            }
        }
        return count;
    }

    /**
     * This is a binary tree node.
     * However, unlike normal binary tree, it has a counter that counts the elements smaller than current node.
     */
    static class BinaryTreeCount {
        int val, count = 1;
        BinaryTreeCount left, right;

        BinaryTreeCount(int val) {
            this.val = val;
        }
    }

    /**
     * Brutal method. Direct traverse in num array.
     * Analysis:
     * Time complexity: O(n^2)
     * Space complexity: O(n)
     *
     * @param nums given array
     * @return smaller numbers after self
     */
    private List<Integer> brutal(int[] nums) {
        List<Integer> out = new LinkedList<>();

        if (nums.length == 0) {
            return out;
        }

        for (int i = 0; i < nums.length; i++) {
            int small = 0;
            for (int j = i + 1; j < nums.length; j++) {
                small = (nums[i] > nums[j]) ? small + 1 : small;
            }
            out.add(small);
        }
        return out;
    }

    public static void main(String[] args) {
        CountSmaller_315 test = new CountSmaller_315();
        System.out.println(test.countSmaller(new int[]{2, 8, 5, 7, 1, 3}));
    }
}


