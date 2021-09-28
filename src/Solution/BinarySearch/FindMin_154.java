package Solution.BinarySearch;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * The array may contain duplicates.
 *
 * @author BorisMirage
 * Time: 2019/06/22 13:52
 * Created with IntelliJ IDEA
 */

public class FindMin_154 {
    /**
     * Modified binary search, idea is almost the same when array contains no duplicated elements.
     * - Escape condition: left == right (and it's the min value)
     * - If nums[mid] > nums[right], then go right and excluded mid.
     * - If nums[mid] < nums[right], go left and included mid, since it could be the min value (start of the rotation).
     * - If nums[mid] == nums[right], there is a duplication, shrink the right boundary.
     *
     * @param nums given array
     * @return minimum value in array
     */
    public int findMin(int[] nums) {

        /* Corner case */
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;
        while (left < right) { // escape when left == right
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) { // go right, excluded nums[mid] since it's already larger than nums[right]
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid; // go left, mid could be the minimum, so included
            } else {
                right--; // excluded duplicated element
            }
        }

        return nums[right]; // return left or right does not make a difference
    }

    public static void main(String[] args) {
        FindMin_154 test = new FindMin_154();

        System.out.println(test.findMin(new int[]{2, 5, 6, 7, 0, 0, 1, 2}));
        System.out.println(test.findMin(new int[]{1, 2, 3, 4, 5}));
        System.out.println(test.findMin(new int[]{0, 0, 0, 0, 0, 2}));
    }
}
