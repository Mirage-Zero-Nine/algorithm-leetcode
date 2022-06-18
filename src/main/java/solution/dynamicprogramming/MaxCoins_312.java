package solution.dynamicprogramming;

/**
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * @author BorisMirage
 * Time: 2018/09/23 16:26
 * Created with IntelliJ IDEA
 */

public class MaxCoins_312 {
    /**
     * Divide and conquer with memoization.
     * The sub problem is to select the current balloon as the last one to shoot.
     * In this way, recursively find its left and right max coins.
     *
     * @param nums input int array
     * @return max score
     */
    public int maxCoins(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int[] coins = new int[nums.length + 2];
        System.arraycopy(nums, 0, coins, 1, nums.length);
        coins[0] = coins[coins.length - 1] = 1;

        return findMax(new int[coins.length][coins.length], coins, 0, coins.length - 1);
    }

    /**
     * Divide and conquer with memoization.
     * Recursively, find the max coins in left part and in right part, with current balloon scores.
     *
     * @param mem   memorization array
     * @param coins given coin array
     * @param left  left bound
     * @param right right bound
     * @return max coins from left to right
     */
    private int findMax(int[][] mem, int[] coins, int left, int right) {

        if (left + 1 == right) {
            return 0;       // out of boundary
        }

        if (mem[left][right] > 0) {
            return mem[left][right];        // return current max (memorization)
        }
        int maxCoins = 0;

        for (int i = left + 1; i < right; ++i) {
            maxCoins = Math.max(coins[i] * coins[left] * coins[right] + findMax(mem, coins, left, i) + findMax(mem, coins, i, right), maxCoins);
        }
        mem[left][right] = maxCoins;

        return maxCoins;
    }

    /**
     * Dynamic programming solution.
     * dp[i][j]: max coins can be obtained from i to j.
     * dp[i][j] = max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + nums[i] * nums[j] * nums[j]), where i <= k <= j.
     * Time complexity: O(n^3)
     * Space complexity: O(n^2)
     *
     * @param nums given array
     * @return max score
     */
    public int dp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                for (int k = i; k <= j; k++) {

                    /*
                     * If current element is the first or last element in array, the value that out of array count as 1.
                     * But this 1 will not be added into max score. */
                    int coins = nums[k] * ((i > 0) ? nums[i - 1] : 1) * ((j < nums.length - 1) ? nums[j + 1] : 1);
                    coins += (k > i) ? dp[i][k - 1] : 0;
                    coins += (k < j) ? dp[k + 1][j] : 0;
                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 8};
        MaxCoins_312 test = new MaxCoins_312();
        System.out.println(test.maxCoins(arr));
    }
}
