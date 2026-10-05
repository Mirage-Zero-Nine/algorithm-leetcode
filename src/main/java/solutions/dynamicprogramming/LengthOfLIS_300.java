package solutions.dynamicprogramming;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * @author BorisMirage
 * Time: 2019/06/23 14:42
 * Created with IntelliJ IDEA
 */

public class LengthOfLIS_300 {
    /**
     * Returns the length of the longest strictly increasing subsequence using the
     * minimum-tail representation {@code tails[k]} for length {@code k + 1}.
     * Values larger than the last active tail append a new length; all other values
     * replace their lower-bound tail without changing the represented length.
     *
     * @param nums input values; {@code null} and empty arrays return zero
     * @return strict LIS length
     * @implNote Time complexity: {@code O(n log n)} for {@code n = nums.length};
     * auxiliary space complexity: {@code O(n)} for the local tail array.
     */
    public int lengthOfLIS(int[] nums) {
        // corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        int maxLength = 1;

        // A smaller tail is easier to extend with a later value, so each tails entry
        // keeps the best (smallest) ending value seen for its subsequence length. The
        // active entries are sorted: a longer increasing subsequence must end above
        // the shorter one, and choosing the smallest tail for each length preserves
        // that ordering.

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tails[maxLength - 1]) {
                // A value above the largest tail extends the longest known subsequence.
                tails[maxLength++] = nums[i];
            } else {
                // binarySearch returns the first tail >= nums[i]. Replacing it with this
                // smaller/equal ending preserves the length: earlier tails stay smaller,
                // and the next unchanged tail stays larger. Thus the active prefix remains
                // sorted, and equal values never append.
                tails[binarySearch(nums[i], maxLength, tails)] = nums[i];
            }
        }
        return maxLength;
    }

    /**
     * Returns the lower-bound index in the active tail prefix: the first position whose
     * tail is greater than or equal to {@code current}. Replacing that position gives
     * the smallest available tail for the same subsequence length; an equal value is
     * handled by this same lower-bound result and does not create a longer sequence.
     * The caller supplies a sorted active prefix in {@code [0, maxLength)}.
     *
     * @param current value whose replacement position is needed
     * @param maxLength number of active entries in {@code tails}
     * @param tails sorted active tail values
     * @return first index {@code i} with {@code tails[i] >= current}
     * @implNote The search keeps a possible lower-bound position instead of returning
     * immediately on equality; the first qualifying position is still needed, and the
     * loop converges to it. Time complexity: {@code O(log L)}, where {@code L} is the
     * active prefix length; auxiliary space complexity: {@code O(1)}.
     */
    private int binarySearch(int current, int maxLength, int[] tails) {
        int left = 0, right = maxLength;

        // Search [left, right) for the first qualifying tail; entries before left
        // are known to be smaller than current, and entries at/after right are not needed.
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (tails[middle] < current) {
                // This tail and every earlier tail are too small to replace current's
                // position, so the first tail >= current must be to the right.
                left = middle + 1;
            } else {
                // middle is a possible first tail >= current. Keep it in the search;
                // equality is handled here too, while an earlier qualifying tail may exist.
                right = middle;
            }
        }

        // left == right is the first active position whose tail is >= current.
        return left;
    }
}
