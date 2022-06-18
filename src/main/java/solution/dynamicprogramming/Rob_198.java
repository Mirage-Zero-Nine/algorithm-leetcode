package solution.dynamicprogramming;


/**
 * Given a list of non-negative integers array.
 * Determine the maximum sum, that each element consist the sum is not adjacent in given array.
 *
 * @author BorisMirage
 * Time: 2019/02/27 23:00
 * Created with IntelliJ IDEA
 */

public class Rob_198 {
    /**
     * Dynamic programming with 1D table.
     * dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
     *
     * @param nums given array
     * @return maximum sum that is made up by nonadjacent elements
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int premax = nums[0];        // actually only constant space is required
        if (nums.length == 1) {
            return premax;
        }

        int max = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = max;
            max = Math.max(premax + nums[i], max);
            premax = temp;
        }
        return max;
    }

    // test
    public static void main(String[] args) {
        int[] t = {1, 2, 3, 4, 5};
        Rob_198 test = new Rob_198();
        System.out.println(test.rob(t));
    }
}
