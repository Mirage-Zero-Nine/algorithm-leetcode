package solutions.binarysearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Algorithm's runtime complexity must be in the order of O(log n).
 * The implementation returns -1 for a null or empty input array and does not modify the input.
 *
 * @author BorisMirage
 * Time: 2018/06/19 15:57
 * Created with IntelliJ IDEA
 */

public class Search_33 {
    /**
     * Performs binary search while identifying the sorted half around the midpoint.
     *
     * <p>The search maintains an inclusive candidate range {@code [left, right]}. The midpoint is
     * checked first; if it is not the target, it is no longer a candidate and can be discarded
     * with {@code middle - 1} or {@code middle + 1}. At least one half of a rotated sorted array
     * is sorted, so the comparisons identify which half can still contain the target. The
     * {@code <=} loop condition is intentional: it checks the final one-element candidate inside
     * the loop, and the method returns {@code -1} only after the range becomes empty.
     *
     * <p>Complexity: {@code O(log n)} time and {@code O(1)} auxiliary space. The input array is
     * read-only and is not modified.
     *
     * @param nums   ascending distinct values rotated at an unknown pivot; {@code null} and an
     *               empty array return {@code -1}
     * @param target value to locate
     * @return the index of {@code target}, or {@code -1} if it is absent
     */
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        // The inclusive range [left, right] contains every index that can still hold target.
        // Because the midpoint is checked before either update, removing it with middle - 1 or
        // middle + 1 preserves that invariant. The <= condition also checks a final singleton
        // range before the updates can make the candidate range empty.
        while (left <= right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return middle;
            }

            // When left and middle refer to the same index, or when the left half is sorted,
            // <= keeps the inclusive left endpoint in the sorted-half classification.
            if (nums[left] <= nums[middle]) {
                // Keep target == nums[left]: the lower bound is inclusive. The upper bound is
                // strict because middle was already checked and is deliberately discarded.
                if (nums[left] <= target && target < nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                // This half is sorted from middle through right. Keep target == nums[right],
                // while target == nums[middle] was handled above, hence the strict lower bound.
                if (nums[middle] < target && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }

        return -1;
    }
}
