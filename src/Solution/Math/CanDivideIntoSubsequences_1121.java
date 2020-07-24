package Solution.Math;

/**
 * Given a non-decreasing array of positive integers nums and an integer K.
 * Find out if this array can be divided into one or more disjoint increasing subsequences of length at least K.
 *
 * @author BorisMirage
 * Time: 2020/05/15 10:52
 * Created with IntelliJ IDEA
 */

public class CanDivideIntoSubsequences_1121 {
    /**
     * Count the quantity of the most occurred element.
     * Then check whether the length of given array is larger than K * max.
     * The quantity of most frequent element represents the number of groups.
     * Since each element should be divided into a group.
     *
     * @param nums given array
     * @param K    increasing subsequences of length at least K
     * @return if this array can be divided into one or more disjoint increasing subsequences of length at least K
     */
    public boolean canDivideIntoSubsequences(int[] nums, int K) {
        int count = 1, max = 1;

        for (int i = 1; i < nums.length; i++) {
            count = nums[i - 1] < nums[i] ? 1 : count + 1;      // new element found in sorted array
            max = Math.max(count, max);                         // finding most frequent element
        }

        return nums.length >= max * K;
    }
}
