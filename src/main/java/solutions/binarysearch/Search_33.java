package solutions.binarysearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Algorithm's runtime complexity must be in the order of O(log n).
 *
 * @author BorisMirage
 * Time: 2018/06/19 15:57
 * Created with IntelliJ IDEA
 */

public class Search_33 {
    /**
     * Performs binary search while identifying the sorted half around the midpoint.
     *
     * <p>The comparisons use inclusive bounds because an endpoint is a valid candidate. The
     * midpoint is checked for equality first; after that, {@code target < nums[middle]} (and the
     * corresponding strict lower comparison) means the midpoint cannot be the answer, so it is
     * safe to remove it with {@code middle - 1} or {@code middle + 1}. Using {@code <=} for the
     * sorted-half test and for the outer endpoint checks keeps values exactly at an endpoint in
     * the retained range.
     *
     * @param nums ascending distinct values rotated at an unknown pivot; an empty array is valid
     * @param target value to locate
     * @return the index of {@code target}, or {@code -1} if it is absent
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        // This version uses [left, right] as an inclusive candidate range, but stops when
        // only one candidate remains. Therefore the loop condition is left < right: when
        // left == right, the loop has narrowed the search to that final index and the check
        // after the loop examines it. A usual exact-match binary search that checks every
        // midpoint inside the loop would use left <= right instead, because left == right
        // still represents one unchecked candidate. In that form, removing a checked middle
        // with middle - 1 or middle + 1 makes the interval empty when the target is absent.
        while (left < right) {
            int middle = left + (right - left) / 2;

            if (nums[middle] == target) {
                return middle;
            }

            // Equality is possible when the range has two elements. It still means the left
            // half is sorted, so keep the <= test; a strict < would misclassify that case.
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

        return nums[left] == target ? left : -1;
    }
}
