package Solution.BinarySearch;

/**
 * Given a sorted integer array where every element appears twice, except for one element which appears exactly once.
 * Find this single element that appears only once.
 *
 * @author BorisMirage
 * Time: 2019/11/05 13:08
 * Created with IntelliJ IDEA
 */

public class SingleNonDuplicate_540 {
    /**
     * Binary search with only even index.
     * If all elements appeared in array twice, then each pair (0, 1), (2, 3), ..., (n - 2, n - 1) will be equal.
     * The single element will only be exist in even index, since all element before this single element appear twice.
     * They have a total of even number, and the next one that is single element is even index.
     * Do the binary search on even element.
     * Each time, if nums[mid] == nums[mid + 1], then the single element is at right part.
     * Otherwise, it will be at left part, since number of total elements in left part is odd.
     *
     * @param nums given int array
     * @return single element that appears only once
     */
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
