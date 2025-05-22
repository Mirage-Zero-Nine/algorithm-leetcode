package solution.binarysearch;

/**
 * Given a ascending order integer array nums of n elements and a target value.
 * Search target in nums.
 * If target exists, then return its index, otherwise return -1.
 *
 * @author BorisMirage
 * Time: 2019/06/02 23:37
 * Created with IntelliJ IDEA
 */

public class Search_704 {
    /**
     * Basic binary search.
     * If the target is greater than nums[mid], it searches the right half by updating left = mid + 1.
     * If the target is less than nums[mid], it searches the left half by updating right = mid - 1.
     *
     * @param nums   given array
     * @param target target number
     * @return if target exists, return its index, otherwise return -1
     */
    public int search(int[] nums, int target) {

        // corner case
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        while (left <= right) {

            // avoid index overflow
            int mid = left + (right - left) / 2;

            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            }
        }
        return -1;
    }
}
