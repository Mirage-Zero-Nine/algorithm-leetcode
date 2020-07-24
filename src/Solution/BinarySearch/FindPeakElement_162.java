package Solution.BinarySearch;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * Assume nums[-1] = nums[n] = -∞.
 *
 * @author BorisMirage
 * Time: 2019/06/22 14:24
 * Created with IntelliJ IDEA
 */

public class FindPeakElement_162 {
    /**
     * Binary search.
     * Each local maximum is one valid peak, therefore, find this local maximum will suffice.
     *
     * @param nums given array
     * @return any peak value's index
     */
    public int findPeakElement(int[] nums) {

        /* Corner case */
        if (nums.length == 1) {
            return 0;
        }

        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {        // right subarray may be ascending
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
