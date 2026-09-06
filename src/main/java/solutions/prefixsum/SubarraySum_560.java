package solutions.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, find the total number of continuous sub arrays whose sum equals to k.
 *
 * @author BorisMirage
 * Time: 2019/09/19 10:23
 * Created with IntelliJ IDEA
 */

public class SubarraySum_560 {

    /**
     * Counts the continuous subarrays whose elements add up to {@code k} in one pass.
     *
     * <p>Let {@code prefix(i)} be the sum from index {@code 0} through {@code i}.
     * A subarray from {@code j + 1} through {@code i} has this sum:</p>
     *
     * <pre>
     * prefix(i) - prefix(j)
     * </pre>
     *
     * <p>That sum equals {@code k} exactly when
     * {@code prefix(j) = prefix(i) - k}. The map stores how many earlier indices
     * have each prefix sum, so the frequency of {@code prefix(i) - k} is exactly
     * the number of valid subarrays ending at {@code i}. Every subarray is counted
     * once when its ending index is processed.</p>
     *
     * <p>Time: {@code O(n)} expected. Space: {@code O(n)}.</p>
     *
     * @param nums the input array
     * @param k    the target subarray sum
     * @return the number of continuous subarrays whose sum is {@code k}, or
     * {@code 0} when {@code nums} is {@code null} or empty
     */
    public int subarraySum(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Invariant: contains every prefix sum ending before the current index.
        Map<Integer, Integer> prefixCounts = new HashMap<>();

        // prefix(-1) = 0 represents the empty prefix before the array.
        prefixCounts.put(0, 1);
        int prefix = 0, output = 0;

        for (int n : nums) {
            prefix += n;

            // Each earlier (prefix - k) forms one subarray ending here with sum k.
            output += prefixCounts.getOrDefault(prefix - k, 0);

            // Insert afterward to keep only strictly earlier prefixes in the lookup.
            prefixCounts.put(prefix, prefixCounts.getOrDefault(prefix, 0) + 1);
        }

        return output;
    }
}
