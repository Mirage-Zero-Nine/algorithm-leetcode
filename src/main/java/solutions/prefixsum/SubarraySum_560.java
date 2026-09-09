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
     * Counts all non-empty contiguous subarrays whose sum is {@code k}.
     *
     * <p>Let {@code prefixSum[r]} be the sum through the current index and let
     * {@code prefixSum[l - 1]} be the sum before a candidate subarray. The candidate sums to
     * {@code k} exactly when:</p>
     *
     * <pre>{@code
     * prefixSum[r] - prefixSum[l - 1] = k
     * prefixSum[l - 1] = prefixSum[r] - k
     * }</pre>
     *
     * <p>The map stores the number of earlier occurrences of each prefix sum, so its count for
     * {@code prefixSum - k} is added at each position. It stores frequencies rather than only
     * presence because repeated prefix sums represent different valid starting positions. The
     * initial entry {@code 0 -> 1} represents the prefix before index zero and counts subarrays
     * that begin at the first element.</p>
     *
     * <p>Each element is processed once, giving {@code O(n)} expected time and {@code O(n)} auxiliary
     * space for an input of length {@code n}. The input array is not modified.</p>
     *
     * @param nums the array to search; {@code null} and empty arrays produce zero
     * @param k    the required subarray sum; any {@code int} value is accepted
     * @return the number of non-empty contiguous subarrays whose sum equals {@code k}
     */
    public int subarraySum(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prefixSum = 0, count = 0;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        map.put(0, 1);

        for (int n : nums) {
            prefixSum += n;
            // An earlier prefix of prefixSum - k leaves exactly k between that prefix and here.
            count += map.getOrDefault(prefixSum - k, 0);
            // Record this prefix only after counting, so a subarray must be non-empty.
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
