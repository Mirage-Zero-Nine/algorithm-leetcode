package solution.binarysearch;

/**
 * Suppose an non-empty array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 * Assume no duplicate exists in the array.
 *
 * @author BorisMirage
 * Time: 2019/02/20 22:26
 * Created with IntelliJ IDEA
 */

public class FindMin_153 {
    /**
     * Binary search. The minimum element will be at left part, or right part if rotated.
     * - Escape condition: left == right (and it's the min value)
     * - If nums[mid] >= nums[right] (actually since no duplication in array, equal will not happen), then go right and excluded mid.
     * - Otherwise, go left. However, mid will be included, since it could be the min value (start of the rotation).
     *
     * @param nums given int array
     * @return min value in array
     */
    public int findMin(int[] nums) {

        /* Corner case */
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0, right = nums.length - 1;
        while (left < right) { // escape when left == right
            int mid = left + (right - left) / 2;

            if (nums[mid] >= nums[right]) { // go right, excluded nums[mid] since it's already larger than nums[right]
                left = mid + 1;
            } else {
                right = mid; // go left, mid could be the minimum, so included
            }
        }

        return nums[left]; // return left or right does not make a difference
    }

    public static void main(String[] args) {
        FindMin_153 test = new FindMin_153();
        int[] t = {2, 3, 4, 5, 1};
        System.out.println(test.findMin(t));
        System.out.println(test.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }
}
