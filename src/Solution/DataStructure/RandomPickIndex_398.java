package Solution.DataStructure;

import java.util.Random;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number.
 * The given target number must exist in the array.
 * Note:
 * 1. The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * @author BorisMirage
 * Time: 2019/07/01 16:40
 * Created with IntelliJ IDEA
 */

public class RandomPickIndex_398 {

    int[] nums;
    Random random;

    /**
     * Initialization.
     *
     * @param nums given array
     */
    public RandomPickIndex_398(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    /**
     * Randomly return index of target.
     *
     * @param target given target to be found
     * @return random index in target
     */
    public int pick(int target) {
        int result = -1;
        int count = 0;
        for (int i = 0; i < this.nums.length; i++) {

            /*
             * If only one target in array, then return its index.
             * If there are multiple targets in array, then random int range enlarged with traverse.
             * Later index may replace previous index and repeat this process. But first index may be kept as well. */
            if (this.nums[i] == target && this.random.nextInt(++count) == 0) {
                result = i;
            }
        }
        return result;
    }
}
