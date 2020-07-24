package Solution.Array;

/**
 * Given an array nums of 0s and 1s and an integer k.
 * Return True if all 1's are at least k places away from each other, otherwise return False.
 *
 * @author BorisMirage
 * Time: 2020/05/02 22:01
 * Created with IntelliJ IDEA
 */

public class KLengthApart_1437 {
    /**
     * Simply traverse the array. If find 1 then compare distance between this and previous 1.
     *
     * @param nums given array
     * @param k    minimum distance between two 1s in array
     * @return whether all 1's are at least k places away from each other
     */
    public boolean kLengthApart(int[] nums, int k) {
        int previous = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (previous != -1 && i - previous <= k) {
                    return false;
                }
                previous = i;
            }
        }

        return true;
    }
}
