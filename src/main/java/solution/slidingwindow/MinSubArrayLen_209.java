package solution.slidingwindow;

/**
 * Given an array of n positive integers and a positive integer s.
 * Find the minimal length of a contiguous subarray of which the sum ≥ s.
 * If there isn't one, return 0 instead.
 *
 * @author BorisMirage
 * Time: 2019/06/18 16:11
 * Created with IntelliJ IDEA
 */

public class MinSubArrayLen_209 {
    /**
     * Sliding window.
     * Shrink the window if current subarray sum is larger than target sum and find the minimum one.
     *
     * @param s    given target sum
     * @param nums given positive array
     * @return minimal length of a contiguous subarray of which the sum ≥ s
     */
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length, window = n + 1, start = 0;

        for (int i = 0; i < n; i++) {
            s -= nums[i];
            while (s <= 0) {
                window = Math.min(i - start + 1, window);
                s += nums[start++];
            }
        }

        return window % (n + 1);
    }

    public static void main(String[] args) {
        MinSubArrayLen_209 test = new MinSubArrayLen_209();
        System.out.println(test.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
