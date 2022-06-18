package solution.slidingwindow;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 *
 * @author BorisMirage
 * Time: 2019/10/22 23:10
 * Created with IntelliJ IDEA
 */

public class FindMaxConsecutiveOnes_487 {
    /**
     * Sliding window problem.
     * Each time, if current window contains more than one 0, then shrink the window until there is only at most one 0.
     *
     * @param nums given array
     * @return maximum number of consecutive 1s in this array if you can flip at most one 0
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, n = nums.length, max = 0, count = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != 1) {
                count++;
            }
            while (count > 1) {
                if (nums[left] != 1) {
                    count--;
                }
                left++;
            }
            max = Math.max(i - left + 1, max);
        }

        return max;
    }
}
