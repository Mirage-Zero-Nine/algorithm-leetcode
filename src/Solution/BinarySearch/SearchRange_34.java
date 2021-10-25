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
     * First round find the lower bound of target, and second bound find upper bound of target.
     * Note that in second round, since the "/" operation is rounded to the lowest integer, add 1 to find the upper bound.
     *
     * @param nums   input int array
     * @param target target int
     * @return starting and ending position, return [-1, -1] if target is not found
     */
    public int[] searchRange(int[] nums, int target) {
        int[] out = new int[]{-1, -1};
        if (nums.length == 0) {
            return out;
        }

        out[0] = findStart(nums, target);
        out[1] = findEnd(nums, target);

        return out;
    }

    /**
     * Find the beginning position of the range by implementing the binary search.
     *
     * @param nums   given array
     * @param target target number
     * @return starting position of the given target value
     */
    private int findStart(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) { // nums[mid] < target: go right, since nums[mid] was excluded
                left = mid + 1;
            } else { // otherwise, go left with mid as right boundary, since it could be the starting position
                right = mid;
            }
        }

        return nums[left] == target ? left : -1;
    }

    /**
     * Find the ending position of the range by implementing the binary search.
     * Note that when calculating mid, add one to the normal method since "/" will always round it to the smaller value.
     * Therefore, need to add 1 to the mid.
     *
     * @param nums   given array
     * @param target target number
     * @return ending position of the given target value
     */
    private int findEnd(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1; // / 2 is rounded to the lowest integer, move to the next right
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }

        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        SearchRange_34 searchRangeTest = new SearchRange_34();
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 6, 6, 6, 6, 8, 9, 12, 30};
        System.out.println(Arrays.toString(searchRangeTest.searchRange(nums, 4)));
    }
}
