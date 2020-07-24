package Solution.Array;

import java.util.HashMap;

/**
 * Given an array of integers arr and an integer target.
 * You have to find two non-overlapping sub-arrays of arr each with sum equal target.
 * There can be multiple answers so find an answer where the sum of the lengths of the two sub-arrays is minimum.
 * Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if no such two sub-arrays.
 *
 * @author BorisMirage
 * Time: 2020/06/19 18:21
 * Created with IntelliJ IDEA
 */

public class MinSumOfLengths_1477 {
    /**
     * Prefix sum with an array record if there is a subarray sum to target has been found.
     * This problem is actually to find two shortest subarray that both subarray sums to target.
     * Hence, keep an array stores if there is a previous subarray sums to target with shortest length.
     * Keep a hash map to store the prefix sum and the index under this sum.
     * The array only contains positive number, so the sum is unique.
     * If found a subarray sums to target, check if one subarray has been found. Then update the shortest output.
     * Then update the current position's shortest first subarray length.
     * The hash map stores the end position of prefix sum, hence, there will be no overlapping problem.
     * The reason is that if there is a subarray has been found, it will be in [0, end].
     * Current subarray starts at [end + 1, i], no overlap exists.
     *
     * @param arr    given array
     * @param target target value
     * @return the minimum sum of the lengths of the two required sub-arrays, or return -1 if no such two sub-arrays
     */
    public int minSumOfLengths(int[] arr, int target) {
        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int n = arr.length, sum = 0, out = Integer.MAX_VALUE, previousMin = Integer.MAX_VALUE;
        int[] previous = new int[n];

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (m.containsKey(sum - target)) {
                int index = m.get(sum - target);
                if (index >= 0 && previous[index] != 0) {       // if there is a subarray has been found
                    out = Math.min(out, previous[index] + i - index);       // check if current subarray can be shorter
                }
                previousMin = Math.min(previousMin, i - index);     // check if current subarray can be shorter
            }
            m.put(sum, i);
            previous[i] = previousMin == Integer.MAX_VALUE ? 0 : previousMin;
        }

        return out == Integer.MAX_VALUE ? -1 : out;
    }

    public static void main(String[] args) {
        System.out.println(new MinSumOfLengths_1477().minSumOfLengths(new int[]{4, 3, 2, 6, 2, 3, 4}, 6));
    }
}
