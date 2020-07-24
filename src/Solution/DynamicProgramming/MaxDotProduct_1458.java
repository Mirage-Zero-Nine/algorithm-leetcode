package Solution.DynamicProgramming;

/**
 * Given two arrays nums1 and nums2.
 * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
 * Constraints:
 * 1. 1 <= nums1.length, nums2.length <= 500
 * 2. -1000 <= nums1[i], nums2[i] <= 1000
 *
 * @author BorisMirage
 * Time: 2020/06/19 13:12
 * Created with IntelliJ IDEA
 */

public class MaxDotProduct_1458 {
    /**
     * Longest common subsequence: select elements from both array, and find the max product.
     * First, fill the first column and row, since they are the base value.
     * Select first element in each array, then find the largest product with one element in other array.
     * Then fill the table, base case of dp[i][j] is the product of nums1[i] and nums2[j] (select in both array).
     * Add dp[i - 1][j - 1] as the initial max product, if dp[i - 1][j - 1] < 0, then add 0.
     * Compare to previous state, dp[i - 1][j] and dp[i][j - 1] and find the max one.
     * If dp[i - 1][j] or dp[i][j - 1] is larger than dp[i][j], then num1[i] and num2[j] will not be selected.
     * This is the process of finding the longest common subsequence of two strings.
     *
     * @param nums1 first array
     * @param nums2 second array
     * @return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m][n];
        dp[0][0] = nums1[0] * nums2[0];

        for (int i = 1; i < m; i++) {       // filling first column, select first element from second array
            int tmp = nums1[i] * nums2[0];
            dp[i][0] = Math.max(dp[i - 1][0], tmp);
        }
        for (int i = 1; i < n; i++) {       // filling first row, select first element from first array
            int tmp = nums1[0] * nums2[i];
            dp[0][i] = Math.max(dp[0][i - 1], tmp);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = nums1[i] * nums2[j] + Math.max(0, dp[i - 1][j - 1]);     // add positive value
                dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), dp[i][j]);
            }
        }

        return dp[m - 1][n - 1];
    }
}
