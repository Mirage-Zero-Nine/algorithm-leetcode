package Solution.BinarySearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,0,1,2,2,5,6] might become [5,6,0,0,1,2,2]).
 * A target value is given to search.
 * If found in the array return true, otherwise return false.
 *
 * @author BorisMirage
 * Time: 2018/08/15 15:42
 * Created with IntelliJ IDEA
 */

public class Search_81 {
    /**
     * Before find the middle value in normal binary search, remove all duplicated elements at left-most or right-most.
     * After that, the approach is almost same as search in array without duplicated elements.
     *
     * @param nums   input int array
     * @param target search int
     * @return true if target is in nums, otherwise return false
     */
    public boolean search(int[] nums, int target) {

        /* Corner case */
        if (nums.length == 0) {
            return false;
        }

        int left = 0, right = nums.length - 1;

        while (left <= right) {     // assure to check last element in array

            while (left < right && nums[left] == nums[right]) {     // excludes duplicated elements
                left++;
            }

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {      // after excludes duplicated elements, it is same as rotated ascending array
                return true;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {       // target can be at left or right, but not mid
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Search_81 test = new Search_81();
        System.out.println(test.search(new int[]{1, 1}, 0));
        System.out.println(test.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(test.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        System.out.println(test.search(new int[]{0}, 0));
        System.out.println(test.search(new int[]{0, 0, 0}, 0));
        System.out.println(test.search(new int[]{0, 1, 2, 3, 4, 5, 90}, 0));
        System.out.println(test.search(new int[]{1, 2, 3, 4, 5, 90}, 0));
        System.out.println(test.search(new int[]{1, 3, 1, 1, 1}, 3));
    }
}
