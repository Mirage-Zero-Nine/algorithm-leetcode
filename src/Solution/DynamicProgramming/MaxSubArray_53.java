package Solution.DynamicProgramming;

/**
 * Given an integer array nums
 * Find the contiguous sub array (containing at least one number) which has the largest sum and return its sum.
 *
 * @author BorisMirage
 * Time: 2018/06/29 19:34
 * Created with IntelliJ IDEA
 */

public class MaxSubArray_53 {
    /**
     * One-pass traverse array. Use an int to store the prefix sum in array.
     * If prefix sum is less than 0, reset sum to 0.
     * Compare prefix sum to max subarray sum, and select the larger one during the traverse.
     *
     * @param nums input int array
     * @return max contiguous sub array sum
     */
    public int maxSubArray(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE, current = 0;

        for (int n : nums) {
            current += n;
            max = Math.max(max, current);
            if (current < 0) {
                current = 0;
            }
        }

        return max;
    }
}
