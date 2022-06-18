package solution.array;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order.
 * i.e., sorted in ascending order.
 * The replacement must be in-place and use only constant extra memory.
 *
 * @author BorisMirage
 * Time: 2018/06/18 16:55
 * Created with IntelliJ IDEA
 */

public class NextPermutation_31 {
    /**
     * This problem is actually to find the next larger number based on given number.
     * Start at last digit of number, and find the increasing order of digits from right to left.
     * The increasing order means this sequence is the largest number and no larger sequence can be found.
     * Find the minimum value in increasing array that is larger than left value next to increasing array.
     * Swap these two elements and reverse the increasing array.
     * The reason of reversing is that, the order of permutation is from small to large.
     * Hence, a new permutation is the smallest value that is larger than current value.
     * For instance, given a array = {1,2,3,2,1}, swap 2 and 3. Then reverse subarray {2,2,1}.
     * Finally, result is {1,3,1,2,2}.
     *
     * @param nums input int array
     */
    public void nextPermutation(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return;
        }

        int i = nums.length - 2;
        while (i > -1 && nums[i] >= nums[i + 1]) {
            i--;        // find first decreasing (from right to left) value in array
        }

        if (i > -1) {       // if there exist an decreasing element
            int j = nums.length - 1;
            while (j > 0 && nums[i] >= nums[j]) {
                j--;        // find smallest value in increasing array that is larger than decreasing element
            }
            swap(nums, i, j);       // swap them
        }

        reverse(nums, i + 1, nums.length - 1);
    }

    /**
     * Swap two elements in array.
     *
     * @param nums given array
     * @param i    first index
     * @param j    second index
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Reverse array based on given array and range.
     *
     * @param nums given array
     * @param i    start index
     * @param j    end index
     */
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        NextPermutation_31 nextPermutationTest = new NextPermutation_31();
        int[] a = {1, 4, 6, 4, 7, 4, 4, 5, 6, 7, 8, 2};
//        int[] a = {1,1};
        nextPermutationTest.nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }
}
