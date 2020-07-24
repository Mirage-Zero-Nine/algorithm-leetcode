package Solution.SlidingWindow;

/**
 * Given a binary array nums, you should delete one element from it.
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array.
 * Return 0 if there is no such subarray.
 *
 * @author BorisMirage
 * Time: 2020/06/27 11:58
 * Created with IntelliJ IDEA
 */

public class LongestSubarray_1493 {
    /**
     * Sliding window problem.
     * Shrink window when there is more than one 0 in window.
     * Note that at least one element should be removed.
     *
     * @param nums given array
     * @return the size of the longest non-empty subarray containing only 1's, or 0 if there is no such subarray
     */
    public int longestSubarray(int[] nums) {
        int count = 0, out = 0, start = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            }
            if (count > 1) {
                while (count > 1) {
                    if (nums[start++] == 0) {
                        count--;
                    }
                }
            }

            out = Math.max(out, i - start);     // not including the removed element
        }

        return out;
    }
}
