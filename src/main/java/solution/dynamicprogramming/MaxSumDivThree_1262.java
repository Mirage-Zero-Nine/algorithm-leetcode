package solution.dynamicprogramming;

/**
 * Given an array nums of integers, find the maximum possible sum of elements of the array that is divisible by three.
 *
 * @author BorisMirage
 * Time: 2020/01/16 17:49
 * Created with IntelliJ IDEA
 */

public class MaxSumDivThree_1262 {
    /**
     * Dynamic programming.
     * There are three possibilities: divisible, remainder 1, remainder 2.
     * The given array only contains positive integer, therefore, the cumulative sum will always be increasing.
     * Use a integer array to store previous round largest sum with each remainder: 0, 1, 2.
     * Each element in given array will have a different sum when added to different element in dp array.
     * The new sum will have a new remainder, compare the new sum and this new remainder sum, choose larger one.
     * Finally, return the first item in dp array, since it represents largest sum with 0 remainder (divisible).
     *
     * @param nums given array
     * @return the maximum possible sum of elements of the array that is divisible by three
     */
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];

        for (int n : nums) {
            int[] tmp = dp.clone();     // previous sum with remainder of 0, 1, 2

            for (int i : dp) {
                int index = (n + i) % 3;        // calculate the remainder of sum of element in array and previous sum
                tmp[index] = Math.max(i + n, tmp[index]);       // find the larger one among them
            }
            dp = tmp;
        }

        return dp[0];
    }
}
