package solution.math;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * Note:
 * 1. Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * 2. Could you do it in-place with O(1) extra space?
 *
 * @author BorisMirage
 * Time: 2019/08/29 10:19
 * Created with IntelliJ IDEA
 */

public class Rotate_189 {
    /**
     * Rotate [0, nums.length - k - 1] and [nums.length - k, nums.length] first.
     * Then rotate whole array.
     *
     * @param nums given int array
     * @param k    rotate the array to the right by k steps
     */
    public void rotate(int[] nums, int k) {
        if (nums.length < 2) {
            return;
        }

        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);

    }

    /**
     * Reverse array in given range.
     *
     * @param arr   given array
     * @param start start index
     * @param end   end index
     */
    private void reverse(int[] arr, int start, int end) {
        int tmp;
        while (start < end) {
            tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }
}
