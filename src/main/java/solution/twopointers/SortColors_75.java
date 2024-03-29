package solution.twopointers;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue.
 * Sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * 1. Do not use the library's sort function for this problem.
 *
 * @author BorisMirage
 * Time: 2019/06/20 11:18
 * Created with IntelliJ IDEA
 */

public class SortColors_75 {
    /**
     * Three pointers.
     * First pointer points p0 at 0 in array, where that index should be filled with 0.
     * The second pointer p2 points at last position of array, where it should be filled with 2.
     * The last pointer p1 is the loop index, where to swap all elements are not 1 to corresponding pointer.
     * During the traverse, only considerate p1. If nums[p1] is not 1, then swap it to 0 or 2.
     * After swap, move p1 back since it may swap 0 or 2 to the current position.
     *
     * @param nums given array
     */
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;      // two pointers point at position of 0 and 2

        for (int p1 = 0; p1 <= p2; p1++) {
            if (nums[p1] == 0 && p1 != p0) {
                swap(nums, p1, p0);      // swap 0 to front of array where it should be all with 0
                p0++;
                p1--;        // one step back to check if swapped value is 2
            } else if (nums[p1] == 2 && p1 != p2) {
                swap(nums, p1, p2);      // swap 2 to end of array where it should be all with 2
                p2--;
                p1--;        // one step back to check if swapped value is 0
            }
        }
    }

    /**
     * Swap two elements in given array based on given index.
     *
     * @param nums array
     * @param a    first index
     * @param b    second index
     */
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {

        SortColors_75 sortColorsTest = new SortColors_75();
        int[] sss = {1, 1, 2, 0, 1, 1, 1, 2};
        sortColorsTest.sortColors(sss);
        System.out.println(Arrays.toString(sss));
    }
}
