package Solution.DynamicProgramming;

/**
 * Given an array of integers, return the maximum sum for a non-empty subarray with at most one element deletion.
 * Note that the subarray needs to be non-empty after deleting one element.
 *
 * @author BorisMirage
 * Time: 2019/09/08 15:44
 * Created with IntelliJ IDEA
 */

public class MaximumSum_1186 {
    /**
     * Dynamic programming.
     * The different part in this problem compare to normal max subarray sum is that, 2 arrays is required.
     * First array saves max sum from 0 to i, max[i] = Math.max(arr[i], max1[i - 1] + arr[i]).
     * During the above traverse, calculate the max subarray sum, max2[i] = Math.max(arr[i], max2[i + 1] + arr[i]).
     * Second array saves max sum from arr.length - 1 to 0.
     * Finally, compare max sum with removing one elements in array.
     *
     * @param arr given array
     * @return the maximum sum for a non-empty subarray with at most one element deletion
     */
    public int maximumSum(int[] arr) {
        int n = arr.length;

        int[] max1 = new int[n], max2 = new int[n];
        int max = arr[0];
        max1[0] = arr[0];

        for (int i = 1; i < n; i++) {       // max sub array sum from left to right
            max1[i] = Math.max(arr[i], max1[i - 1] + arr[i]);
            max = Math.max(max, max1[i]);
        }

        max2[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {      // max sub array sum from right to left
            max2[i] = Math.max(arr[i], max2[i + 1] + arr[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max, max1[i - 1] + max2[i + 1]);       // remove 1 element
        }

        return max;
    }
}
