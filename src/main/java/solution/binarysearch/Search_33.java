package solution.binarysearch;

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
     * Binary search.
     * The key is to find the middle position, and compare it to pivot position.
     * If pivot is at right of mid, then left sub array is normal array.
     * If target is not in left then it will be in right, or not exist.
     * If pivot is at left of mid, then right sub array is normal array.
     * If target is not in right then it will be in left, or not exist.
     * In this way, only normal part of half array will be searched and avoid the complicated pivot case.
     *
     * @param nums   input int array
     * @param target value to search
     * @return target index in array
     */
    public int search(int[] nums, int target) {

        /* Corner case */
        if (nums.length == 0) {
            return -1;
        }

        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) { // pivot at right of mid, left sub-array is normal ascending array
                if (nums[left] <= target && target < nums[mid]) { // nums[left] <= target <  nums[mid]
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

        return nums[left] == target ? left : -1;
    }

    public static void main(String[] args) {
        Search_33 test = new Search_33();
        System.out.println(test.search(new int[]{1, 2, 3, 4, 5, 6}, 4));
        System.out.println(test.search(new int[]{1, 2, 3, 4, 5, 6}, 1));
        System.out.println(test.search(new int[]{1, 2, 3, 4, 5, 6}, 6));
        System.out.println(test.search(new int[]{1, 2}, 1));
        System.out.println(test.search(new int[]{1, 2}, 2));
        System.out.println(test.search(new int[]{2, 1}, 2));
        System.out.println(test.search(new int[]{2, 1}, 1));
        System.out.println(test.search(new int[]{1}, 4));
        System.out.println(test.search(new int[]{1}, 1));
    }
}
