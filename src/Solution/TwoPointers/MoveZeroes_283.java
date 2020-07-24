package Solution.TwoPointers;

/**
 * Given an array nums.
 * Write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * @author BorisMirage
 * Time: 2020/03/28 10:01
 * Created with IntelliJ IDEA
 */

public class MoveZeroes_283 {
    /**
     * Traverse the array.
     * If current element is not 0, then try to swap it with first 0 in array. Then move the index of first 0.
     *
     * @param nums given array
     */
    public void moveZeroes(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length < 2) {
            return;
        }

        int index = 0;        // position of 0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {     // if current element is not 0, then try to swap it with 0 in previous part of array
                if (index != i) {   // if current position is same as assumed swap position, then pass it
                    nums[index] = nums[i];
                    nums[i] = 0;
                }
                index++;            // move to next potential position of 0
            }
        }
    }
}
