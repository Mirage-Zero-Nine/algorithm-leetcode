package solutions.greedy;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * Input array length will not be 0.
 *
 * @author BorisMirage
 * Time: 2018/06/29 20:09
 * Created with IntelliJ IDEA
 */

public class CanJump_55 {
    /**
     * Checks reachability by keeping the farthest index reachable from the
     * prefix processed so far. At index {@code i}, the transition is
     * {@code farthest = max(farthest, i + nums[i])}; an index beyond the
     * previous range proves that the end cannot be reached. Null and empty
     * arrays return {@code true} as defined by this implementation.
     *
     * @param nums non-negative maximum jump lengths
     * @return whether the last index is reachable
     * @implNote Runs in {@code O(n)} time and uses {@code O(1)} auxiliary
     * space. The input array is not modified.
     */
    public boolean canJump(int[] nums) {
        // The implementation treats invalid or empty input as already solved.
        if (nums == null || nums.length == 0) {
            return true;
        }

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // Every index visited must lie inside the reach established by
            // earlier positions; otherwise this gap cannot be crossed.
            if (max < i) {
                return false;
            }

            // Any jump from the current reachable prefix may extend its
            // boundary. Keeping only the largest boundary is sufficient
            // because all positions up to it are reachable.
            max = Math.max(nums[i] + i, max);
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
