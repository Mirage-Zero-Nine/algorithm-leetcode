package Solution.BinarySearch;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * No duplication in the array.
 *
 * @author BorisMirage
 * Time: 2018/06/21 11:14
 * Created with IntelliJ IDEA
 */

public class SearchInsert_35 {
    /**
     * Binary search.
     * Find target, or next value larger than target.
     *
     * @param nums   input int array
     * @param target target int
     * @return insert position
     */
    public int searchInsert(int[] nums, int target) {

        /* Corner case */
        if (nums.length == 0 || target < nums[0]) {
            return (target > nums[nums.length - 1]) ? nums.length : 0;
        }

        int n = nums.length, left = 0, right = n;

        while (left < right) { // find target, or next value larger than target
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) { // mid is not target, and not the next element larger than target
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        SearchInsert_35 searchInsertTest = new SearchInsert_35();
        int[] nums = {1, 3};
        System.out.println(searchInsertTest.searchInsert(nums, 2));
    }
}
