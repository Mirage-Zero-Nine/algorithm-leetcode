package solutions.dynamicprogramming;

/**
 * Given the amount of money in each house on a street, return the largest
 * amount that can be collected without robbing two neighboring houses.
 *
 * <p>House values are non-negative integers. The problem supplies between one
 * and one hundred houses, with each value between zero and four hundred. The
 * solver also returns zero for an empty array as a useful extension for callers
 * outside the original problem contract.</p>
 *
 * @author BorisMirage
 * Time: 2019/02/27 23:00
 * Created with IntelliJ IDEA
 */

public class Rob_198 {
    /**
     * Computes the maximum non-adjacent sum using rolling dynamic-programming
     * states.
     *
     * <p>Let {@code best[i]} be the maximum sum using houses {@code 0..i}.
     * The transition is {@code best[i] = max(best[i - 1], best[i - 2] + nums[i])}:
     * skipping house {@code i} keeps the first value, while robbing it requires
     * the best solution through {@code i - 2}. The two variables in the loop
     * hold those two previous states, and the temporary variable preserves the
     * old {@code best[i - 1]} before advancing the window. Thus the invariant
     * is maintained at every iteration and the final {@code max} is the optimum
     * for the full array. The input array is not modified.</p>
     *
     * @param nums house values in the range 0 through 400
     * @return the maximum sum of non-adjacent values
     * @implNote Runs in {@code O(n)} time and {@code O(1)} auxiliary space.
     */
    public int rob(int[] nums) {
        // corner cases
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // At the first loop iteration (i = 2), previousMax is dp[0] = nums[0] = dp[i - 2],
        // while max is dp[1] = max(nums[0], nums[1]) = dp[i - 1]. Therefore,
        // previousMax + nums[i] is the candidate for robbing house i.
        int previousMax = nums[0], max = Math.max(nums[0], nums[1]);

        // Before processing i, previousMax = best[i - 2] and max = best[i - 1].
        for (int i = 2; i < nums.length; i++) {
            // Choose between skipping i and adding i to the best prefix through i - 2.
            int tmp = max; // old best[i - 1], needed before max advances
            max = Math.max(previousMax + nums[i], max);
            previousMax = tmp; // advance previousMax to old best[i - 1]
        }
        return max;
    }
}
