package solution.dfs;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k.
 * Find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * @author BorisMirage
 * Time: 2020/03/20 11:00
 * Created with IntelliJ IDEA
 */

public class CanPartitionKSubsets_698 {
    /**
     * NP problem. Backtracking + pruning.
     * Sort the array, and starts searching at the end of array.
     *
     * @param nums given array
     * @param k    k parts with equal sum
     * @return whether it's possible to divide this array into k non-empty subsets whose sums are all equal
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {

        /* Corner case */
        if (nums == null || nums.length == 0 || k > nums.length) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        boolean[] isVisited = new boolean[nums.length];     // avoid duplicated visit
        Arrays.sort(nums);                                  // sort array for pruning

        return backtracking(nums, isVisited, nums.length - 1, 0, sum / k, k);
    }

    /**
     * Backtracking with pruning.
     * Pruning: If current subset sum is larger than target, then current subset is not valid.
     *
     * @param nums      given array
     * @param isVisited visited elements
     * @param start     start position
     * @param sum       current sum of subset
     * @param target    target value
     * @param k         k parts remaining to be found
     * @return whether it's possible to divide this array into k non-empty subsets whose sums are all equal
     */
    private boolean backtracking(int[] nums, boolean[] isVisited, int start, int sum, int target, int k) {

        if ((k == 0) || (sum == target && backtracking(nums, isVisited, nums.length - 1, 0, target, k - 1))) {
            return true;        // k == 0 means find k parts
        }

        for (int i = start; i >= 0; i--) {      // starts with largest element
            if (!isVisited[i] && sum + nums[i] <= target) {
                isVisited[i] = true;
                if (backtracking(nums, isVisited, i - 1, sum + nums[i], target, k)) {      // backtracking
                    return true;
                }
                isVisited[i] = false;
            }
        }

        return false;
    }
}
