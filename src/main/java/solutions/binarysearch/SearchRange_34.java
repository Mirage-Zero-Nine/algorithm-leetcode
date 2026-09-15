package solutions.binarysearch;

/**
 * Given an array of int sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 *
 * @author BorisMirage
 * Time: 2018/06/19 20:58
 * Created with IntelliJ IDEA
 */

public class SearchRange_34 {
    /**
     * Returns the inclusive range occupied by {@code target}.
     *
     * <p>Each boundary search keeps a closed interval containing the answer. The
     * first search discards values strictly smaller than the target; the second
     * discards values strictly greater than it. Once the interval has one index,
     * the final equality check distinguishes a missing target from a found one.</p>
     *
     * @param nums   sorted array to search; {@code null} and empty arrays have no range
     * @param target value whose first and last positions should be returned
     * @return {@code [firstIndex, lastIndex]}, or {@code [-1, -1]} if absent
     */
    public int[] searchRange(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        return new int[]{findBeginning(nums, target), findEnding(nums, target)};
    }

    /**
     * Finds the leftmost index whose value is at least {@code target}.
     *
     * <p>If the midpoint is too small, no index at or before it can be the first
     * target, so {@code left} advances past the midpoint. Otherwise the midpoint
     * remains a possible first occurrence and {@code right} moves to it. Thus the
     * interval always contains the first target, when one exists.</p>
     *
     * @return the first target index, or {@code -1} when the converged value differs
     */
    private int findBeginning(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    /**
     * Finds the rightmost index whose value is at most {@code target}.
     *
     * <p>If the midpoint is too large, no index at or after it can be the last
     * target, so {@code right} moves before it. Otherwise the midpoint remains a
     * possible last occurrence and {@code left} moves to it.</p>
     *
     * <p>The midpoint is deliberately right-biased. With a two-element interval,
     * ordinary floor division would produce {@code mid == left}; the update
     * {@code left = mid} would then make no progress and could loop forever. Adding
     * one guarantees {@code mid > left} whenever {@code left < right}, so every
     * iteration shrinks the closed interval.</p>
     *
     * @return the last target index, or {@code -1} when the converged value differs
     */
    private int findEnding(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // Right bias is required because the successful branch sets left = mid.
            int mid = left + (right - left) / 2 + 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}
