package solution.array;

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 * Return the running sum of nums.
 *
 * @author BorisMirage
 * Time: 2020/06/13 21:21
 * Created with IntelliJ IDEA
 */

public class RunningSum_1480 {
    /**
     * Basic prefix sum problem.
     *
     * @param nums given array
     * @return the running sum of array
     */
    public int[] runningSum(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        out[0] = nums[0];

        for (int i = 1; i < out.length; i++) {
            out[i] = nums[i] + out[i - 1];
        }

        return out;
    }
}
