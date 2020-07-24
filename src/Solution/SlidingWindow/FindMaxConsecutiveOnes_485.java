package Solution.SlidingWindow;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 *
 * @author BorisMirage
 * Time: 2019/10/22 21:45
 * Created with IntelliJ IDEA
 */

public class FindMaxConsecutiveOnes_485 {
    /**
     * Normal sliding window (actually, one pointer) problem.
     *
     * @param nums given array
     * @return maximum number of consecutive 1s in this array
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, tmp = 0;

        for (int i : nums) {
            tmp = (i == 1) ? tmp + 1 : 0;
            max = Math.max(max, tmp);
        }

        return max;
    }
}
