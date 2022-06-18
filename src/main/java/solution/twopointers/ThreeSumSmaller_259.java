package solution.twopointers;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target.
 * Find the number of index triplets i, j, k that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * @author BorisMirage
 * Time: 2019/06/19 11:56
 * Created with IntelliJ IDEA
 */

public class ThreeSumSmaller_259 {
    /**
     * Three sum problem variant.
     * Idea is same as three sum, use two pointers to point at the next element and the end of array.
     *
     * @param nums   given array
     * @param target target number that nums[i] + nums[j] + nums[k] < target
     * @return number of triplets satisfy the condition nums[i] + nums[j] + nums[k] < target
     */
    public int threeSumSmaller(int[] nums, int target) {

        /* Corner case */
        if (nums.length < 3) {
            return 0;
        }

        int count = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                if (nums[left] + nums[right] + nums[i] < target) {
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }
}
