package Solution.SlidingWindow;

import java.util.HashMap;

/**
 * In an array A of 0s and 1s, how many non-empty sub arrays have sum S?
 *
 * @author BorisMirage
 * Time: 2020/02/26 11:29
 * Created with IntelliJ IDEA
 */

public class NumSubarraysWithSum_930 {
    /**
     * Sliding window.
     * Exactly K = (at most K) - (at most K - 1).
     *
     * @param A given binary array
     * @param S target sum
     * @return number of non-empty sub arrays have sum S
     */
    public int numSubarraysWithSum(int[] A, int S) {
        return sumAtMostK(A, S) - sumAtMostK(A, S - 1);
    }

    /**
     * Find number of sub arrays has at most sum K by shrinking sliding window.
     *
     * @param nums given array
     * @param s    target sum
     * @return number of sub arrays has at most sum of s
     */
    private int sumAtMostK(int[] nums, int s) {

        /* Corner case */
        if (s < 0) {        // array contains only 0 and 1 can not contain subarray with negative sum
            return 0;
        }

        int index = 0, out = 0;

        for (int i = 0; i < nums.length; i++) {
            s -= nums[i];

            while (s < 0) {
                s += nums[index++];
            }
            out += i - index + 1;
        }

        return out;
    }

    /**
     * Prefix sum solution.
     *
     * @param A given binary array
     * @param S target sum
     * @return number of non-empty sub arrays have sum S
     */
    public int numSubarraysWithSumPrefixSum(int[] A, int S) {
        int prefix = 0, out = 0;
        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);

        for (int i : A) {
            prefix += i;
            if (prefix >= S) {
                out += m.getOrDefault(prefix - S, 0);
            }
            m.put(prefix, m.getOrDefault(prefix, 0) + 1);
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new NumSubarraysWithSum_930().numSubarraysWithSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));     // 27
        System.out.println(new NumSubarraysWithSum_930().numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));     // 4
    }
}
