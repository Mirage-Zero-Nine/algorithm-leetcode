package Solution.BinarySearch;

import java.util.Arrays;

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
     * Two rounds of binary search.
     * First round find the lower bound of target, and second bound find higher bound of target.
     *
     * @param nums   input int array
     * @param target target int
     * @return starting and ending position, return [-1, -1] if target is not found
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirstLast(nums, target, true);
        result[1] = findFirstLast(nums, target, false);
        return result;
    }

    /**
     * Find boundary of target.
     *
     * @param nums   given array
     * @param target target
     * @param first  is finding first element
     * @return index of start or end of target
     */
    private int findFirstLast(int[] nums, int target, boolean first) {
        int index = -1;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target < nums[mid] || (first && target == nums[mid])) {     // find lower bound in first binary search
                right = mid - 1;
            } else {
                left = mid + 1;
            }

            if (nums[mid] == target) {
                index = mid;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        SearchRange_34 searchRangeTest = new SearchRange_34();
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 8, 9, 12, 30};
        System.out.println(Arrays.toString(searchRangeTest.searchRange(nums, 4)));
    }
}
