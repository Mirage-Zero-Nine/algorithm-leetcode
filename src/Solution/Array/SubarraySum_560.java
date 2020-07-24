package Solution.Array;

import java.util.HashMap;

/**
 * Given an array of integers and an integer k, find the total number of continuous sub arrays whose sum equals to k.
 *
 * @author BorisMirage
 * Time: 2019/09/19 10:23
 * Created with IntelliJ IDEA
 */

public class SubarraySum_560 {
    /**
     * Calculate the prefix sum of array.
     * Array may contains negative numbers. Therefore, use a hash map to save numbers of subarray with this sum.
     * At each element in array, find if sum - k exist in hash map.
     * If so, then there are m.get(sum - k) subarray sum equals to k.
     *
     * @param nums given int array
     * @param k    target number
     * @return total number of continuous sub arrays whose sum equals to k
     */
    public int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> m = new HashMap<>();
        int out = 0;
        m.put(0, 1);        // if prefix sum + current value == target, then at least 1 sub array is found

        int prefix = 0;
        for (int i : nums) {
            prefix += i;      // calculate prefix sum

            /*
             * prefix - k:
             * Assume prefix from 0 to j has prefix, that sum(0, j) = prefix - k.
             * Then it means, the sub array sum from j to i is equal to k. */
            out += m.getOrDefault(prefix - k, 0);
            m.put(prefix, m.getOrDefault(prefix, 0) + 1);
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new SubarraySum_560().subarraySum(new int[]{4, 1, 3, 7}, 4));
    }
}
