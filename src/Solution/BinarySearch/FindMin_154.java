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
     * Modified binary search.
     * Originally, to find min value in rotate sorted array is to find the lower part each time.
     * To avoid the duplicated value, simply shrink the right bound when mid of array is equal to right.
     * In this situation, the min value will either be in the right, or the duplicated one is the min value.
     * Either way, the right duplicated one can be ignored.
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

        while (left < right) {

            int mid = left + (right - left) / 2;

            /*
             * Rightmost should be larger than middle value in array if in normal ascending array.
             * Therefore, if mid value is larger than rightmost value in array, then the minimum element is at right. */
            if (nums[right] < nums[mid]) {      // abnormal sub array
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;        // duplicate value from middle to the end of array, narrow the searching range
            }
        }

        return nums[left];      // after the while loop, left == right
    }

    public static void main(String[] args) {
        FindMin_154 test = new FindMin_154();

        System.out.println(test.findMin(new int[]{2, 5, 6, 7, 0, 0, 1, 2}));
        System.out.println(test.findMin(new int[]{1, 2, 3, 4, 5}));
        System.out.println(test.findMin(new int[]{0, 0, 0, 0, 0, 2}));
    }
}
