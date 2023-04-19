package solution.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of non-negative numbers and a target integer k.
 * Write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k.
 *
 * @author BorisMirage
 * Time: 2019/10/24 16:01
 * Created with IntelliJ IDEA
 */

public class CheckSubarraySum_523 {
    /**
     * Prefix sum.
     * The principle is that, (a + (n * x)) % x == (a % x).
     * Therefore, the prefix sum can be converted to a1 % k + a2 % k + ... + an % k.
     * If in array exist a1 % k + ... + ai % k == ai+1 % k + ... + ai+j % k, then (i, i + j) the sum is multiple of k.
     *
     * @param nums given int array
     * @param k    multiple of k
     * @return if the array has a continuous subarray of size at least 2 that sums up to a multiple of k
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);

        int prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (k != 0) {
                prefixSum %= k;     // (a + (n * x)) % x == (a % x)
            }

            Integer previous = m.get(prefixSum);
            if (previous != null) {
                if ((i - previous) > 1) {
                    return true;
                }
            } else {
                m.put(prefixSum, i);
            }
        }

        return false;
    }

    /**
     * Normal traversal approach, slow but simple. (Note: this method will TLE).
     * Calculate the prefix sum, then traverse the array.
     * If from (0, i) there exist a prefix sum that the difference is the multiple of k, return true.
     * Note that this approach has to deal with lots of corner case.
     *
     * @param nums given int array
     * @param k    multiple of k
     * @return if the array has a continuous subarray of size at least 2 that sums up to a multiple of k
     */
    public boolean checkSubarraySumByTraverseArray(int[] nums, int k) {

        /* Corner case */
        if (nums.length < 2) {
            return false;
        }

        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i > 1 && nums[i - 1] == 0 && nums[i - 2] == 0) {
                return true;
            }
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        if (k == 0) {
            return false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = i - 2; j >= 0; j--) {

                if (Math.abs(prefix[i] - prefix[j]) % k == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
