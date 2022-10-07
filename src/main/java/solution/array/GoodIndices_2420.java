package solution.array;

import java.util.LinkedList;
import java.util.List;

/**
 * You are given a 0-indexed integer array nums of size n and a positive integer k.
 * We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
 * The k elements that are just before the index i are in non-increasing order.
 * The k elements that are just after the index i are in non-decreasing order.
 * Return an array of all good indices sorted in increasing order.
 *
 * @author BorisMirage
 * Time: 2022/10/06 23:29
 * Created with IntelliJ IDEA
 */

public class GoodIndices_2420 {
    /**
     * Keep 2 arrays, one to count the length of non-increasing subarray, the other one counts non-decreasing subarray.
     * Traverse array again from left to right, each time check the length of non-increasing subarray.
     * Also check the non-decreasing subarray from (i + k) in non-decreasing subarray.
     * The non-decreasing array can only be obtained  from the (i + k)th position.
     *
     * @param nums given number array
     * @param k    length of required subarray
     * @return all good indices sorted in increasing order
     */
    public List<Integer> goodIndices(int[] nums, int k) {
        int n = nums.length;
        int[] nonDecreasingSubarrayLength = new int[n];
        int[] nonIncreasingSubarrayLength = new int[n];

        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                nonIncreasingSubarrayLength[i] = nonIncreasingSubarrayLength[i - 1] + 1;
            }
            if (nums[i] >= nums[i - 1]) {
                nonDecreasingSubarrayLength[i] = nonDecreasingSubarrayLength[i - 1] + 1;
            }
        }

        List<Integer> output = new LinkedList<>();
        for (int i = k; i < n - k; i++) {
            if (nonIncreasingSubarrayLength[i - 1] >= k - 1 && nonDecreasingSubarrayLength[i + k] >= k - 1) {
                output.add(i);
            }
        }

        return output;
    }
}
