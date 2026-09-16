package solutions.binarysearch;

import java.security.InvalidParameterException;

/**
 * Suppose an non-empty array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * Assume no duplicate exists in the array.
 *
 * @author BorisMirage
 * Time: 2019/02/20 22:26
 * Created with IntelliJ IDEA
 */

public class FindMin_153 {
    /**
     * Uses binary search to locate the rotation point, which is the minimum value.
     *
     * <p>At every iteration, {@code [left, right]} is an inclusive interval containing the minimum. If
     * {@code nums[mid]} is greater than {@code nums[right]}, the interval crosses the rotation point, so
     * the minimum is strictly after {@code mid}. Otherwise, the portion through {@code mid} still contains
     * the minimum and {@code mid} must remain a candidate. The distinct-value constraint makes the first
     * comparison strict and removes ambiguity at the midpoint.
     *
     * <p>The method does not mutate {@code nums}. A null or empty array is outside the problem contract and
     * is rejected defensively.
     *
     * @param nums a non-empty rotated ascending array containing distinct integers
     * @return the minimum value in {@code nums}
     * @throws InvalidParameterException if {@code nums} is null or empty
     * @implNote The time complexity is {@code O(log n)} and the auxiliary space complexity is {@code O(1)}.
     */
    public int findMin(int[] nums) {
        // corner cases
        if (nums == null || nums.length == 0) {
            throw new InvalidParameterException();
        }

        int left = 0, right = nums.length - 1;

        // The interval is inclusive. When left == right it contains one candidate, so the minimum is known
        // and another midpoint calculation could not shrink the interval further.
        while (left < right) {
            int mid = left + (right - left) / 2;

            // nums[right] < nums[mid] means mid is in the larger, pre-rotation segment while right is in
            // the smaller segment. Because values are distinct, mid cannot be the minimum; it is strictly
            // to the left of the rotation point, so discard it and everything before it.
            if (nums[right] < nums[mid]) {
                left = mid + 1;

                // Otherwise nums[mid] < nums[right], so [mid, right] is ordered and the minimum is at or before
                // mid. Keep mid as a candidate by moving only the right boundary to mid.
            } else {
                right = mid;
            }
        }

        // The loop invariant says the minimum remains in [left, right]. On exit left == right, so this sole
        // remaining candidate is the minimum value.
        return nums[left];
    }
}
