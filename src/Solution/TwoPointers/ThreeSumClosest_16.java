package Solution.TwoPointers;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target.
 * Find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 *
 * @author BorisMirage
 * Time: 2019/06/19 14:36
 * Created with IntelliJ IDEA
 */

public class ThreeSumClosest_16 {
    /**
     * Similar to 3Sum problem, the difference is the bound when selecting elements from array.
     * And the compare number that is found in each round is not -nums[i], it is target - nums[i].
     *
     * @param nums   input numbers
     * @param target target number
     * @return the SUM that is closest to target
     */
    public int threeSumClosest(int[] nums, int target) {

        /* Corner case */
        if (nums.length < 3) {
            return 0;
        }

        int closet = Integer.MAX_VALUE;     // final return closet sum
        int min = Integer.MAX_VALUE;        // temp sum that is closest to target

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int p1 = i + 1, p2 = nums.length - 1;

            while (p1 < p2) {

                if (Math.abs(nums[p1] + nums[p2] + nums[i] - target) < min) {       // found new cloest sum
                    min = Math.abs(nums[p1] + nums[p2] + nums[i] - target);
                    closet = nums[p1] + nums[p2] + nums[i];
                }

                if (nums[p1] + nums[p2] + nums[i] < target) {
                    p1++;
                } else if (nums[p1] + nums[p2] + nums[i] > target) {
                    p2--;
                } else {
                    return target;      // a + b + c == target
                }
            }
        }
        return closet;
    }
}
