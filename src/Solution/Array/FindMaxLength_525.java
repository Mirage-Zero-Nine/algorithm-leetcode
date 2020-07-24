package Solution.Array;

import java.util.HashMap;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * @author BorisMirage
 * Time: 2019/10/29 21:14
 * Created with IntelliJ IDEA
 */

public class FindMaxLength_525 {
    /**
     * Prefix sum with hash map.
     * First, convert all 0s in array to -1. Then this problem is normal prefix sum problem.
     *
     * @param nums given int array
     * @return find the maximum length of a contiguous subarray with equal number of 0 and 1
     */
    public int findMaxLength(int[] nums) {
        int n = nums.length, max = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);       // first element in prefix sum array

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (m.containsKey(sum)) {
                max = Math.max(max, i - m.get(sum));        // when key exist, don't update
            } else {
                m.put(sum, i);
            }
        }

        return max;
    }
}
