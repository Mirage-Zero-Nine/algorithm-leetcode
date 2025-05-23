package solution.binarysearch;

/**
 * Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
 * Note that 0 is neither positive nor negative.
 *
 * @author BorisMirage
 * Time: 2025/05/22 14:05
 * Created with IntelliJ IDEA
 */

public class MaximumCount_2529 {
    /**
     * Binary search.
     * For negative integer, find the first index with a number >= 0 (non-negative elements).
     * For positive integer, find the first index with a number >= 1 (positive elements).
     *
     * @param nums given array
     * @return the maximum between the number of positive integers and the number of negative integers
     */
    public int maximumCount(int[] nums) {

        // corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int negativeCount = binarySearch(nums, 0);
        int positiveCount = nums.length - binarySearch(nums, 1);
        return Math.max(negativeCount, positiveCount);
    }

    /**
     * Binary search to find the first position where the value is ≥ target.
     *
     * @param nums   given array
     * @param target target number
     * @return index to insert target element
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // if target > nums[mid], then all elements in the left are smaller than target, exclude all
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                // otherwise, it's possible that there are other elements that are equal to the target, hence, only remove mid
                right = mid - 1;
            }
        }

        return left;
    }
}
