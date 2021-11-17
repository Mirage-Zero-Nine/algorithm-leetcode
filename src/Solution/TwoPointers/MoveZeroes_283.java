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
     * Traverse the array. Keep a pointer started at 0 since the non-zero elements needs to keep original order.
     * Each time, if a non-zero element is found, swap it to the pointer's position and fill current position to 0.
     * Note that the swap only take place when two elements are in different positions.
     *
     * @param nums given array
     */
    public void moveZeroes(int[] nums) {

        /* Corner case */
        if (nums == null || nums.length < 2) {
            return;
        }

        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) { // found a non-zero element, swap to the beginning of the array
                if (index != i) {
                    nums[index] = nums[i];
                    nums[i] = 0;
                }
                index++;
            }
        }
    }
}
