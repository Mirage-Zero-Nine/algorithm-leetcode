package Solution.BinarySearch;

/**
 * Given an array nums sorted in non-decreasing order, a number target, return True iff target is a majority element.
 * A majority element is an element that appears more than N/2 times in an array of length N.
 * Note:
 * 1. 1 <= nums.length <= 1000
 * 2. 1 <= nums[i] <= 10^9
 * 3. 1 <= target <= 10^9
 *
 * @author BorisMirage
 * Time: 2019/08/10 14:02
 * Created with IntelliJ IDEA
 */

public class IsMajorityElement_1150 {
    /**
     * Traverse array with binary search to find majority element.
     *
     * @param nums   given array
     * @param target target number
     * @return true iff target is a majority element
     */
    public boolean isMajorityElement(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid;
        int highest = -1, lowest = -1;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                highest = mid;
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (highest == -1) {
            return false;
        }

        low = 0;
        high = nums.length - 1;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                lowest = mid;
                high = mid - 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (highest - lowest + 1) > (nums.length / 2);
    }
}
