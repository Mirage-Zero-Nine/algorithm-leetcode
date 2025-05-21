package solution.dynamicprogramming;

import java.util.Arrays;
import java.util.Map;

/**
 * Given a non-empty array containing only positive integers.
 * Find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * @author BorisMirage
 * Time: 2019/08/20 22:18
 * Created with IntelliJ IDEA
 */

public class CanPartition_416 {
    /**
     * First, check if the sum of the array is odd. Odd sum can not be divided.
     * Then it's 0/1 Knapsack problem: select items from array to have it sum equals to the target sum (total / 2).
     * Build a 1D boolean array for DP with size (total / 2) + 1.
     * dp[i] is true if it's possible to achieve a sum of i using a subset of the numbers from nums.
     * Then, loop the elements in array. From the range [target - num, target], filling the dp[i] with:
     * 1. It's already true (which means current element does not need to be selected).
     * 2. It can be obtained by adding current element to a previous subset (dp[i - num]).
     * State transition:
     * dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j]
     * dp[i - 1][j - nums[i - 1]]: choose nums[i], and if j - nums[i] can make up sum, dp[i][j] can.
     * dp[i - 1][j]: leave nums[i] and if from nums[0] to nums[i-1] can make up, then dp[i][j] can.
     *
     * @param nums given array
     * @return if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal
     */
    public boolean canPartition(int[] nums) {

        // corner case
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // considering each element
        for (int n : nums) {
            for (int i = target; i >= n; i--) {
                // either current subset sum is already reaching the current target sum
                // or by adding the current element to a previous set
                dp[i] = dp[i] || dp[i - n];
            }
        }

        return dp[target];
    }

    /**
     * DFS with pruning, idea is same as the DP solution, select or not select.
     * Keep a boolean array or a hash map to prune.
     *
     * @param nums given array
     * @return if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal
     */
    public boolean canPartitionDFS(int[] nums) {

        // corner case
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();

        if (sum % 2 == 1) {
            return false;       // odd number can not be spilt to equal parts
        }
        Arrays.sort(nums);
        Boolean[] visited = new Boolean[sum / 2 + 1];       // input only contains positive numbers, + 1 can be removed

        return dfs(nums, 0, sum / 2, visited);
    }

    /**
     * DFS to check if array can be divided into 2 subsets.
     * Idea is same to dynamic programming solution, select current element or not select.
     * Keep a hash map as pruning.
     *
     * @param nums  given array
     * @param index current index
     * @param sum   current remaining sum
     * @param m     hash map
     * @return if the array can be divided into two parts
     */
    private boolean dfs(int[] nums, int index, int sum, Map<Integer, Boolean> m) {

        if (m.containsKey(sum)) {
            return m.get(sum);
        }

        if (index == nums.length || sum < nums[index]) {
            return false;
        }
        if (sum == nums[index]) {
            return true;
        }

        m.put(sum, dfs(nums, index + 1, sum - nums[index], m) || dfs(nums, index + 1, sum, m));

        return m.get(sum);
    }

    /**
     * DFS to check if array can be divided into 2 subsets.
     * Idea is same to dynamic programming solution, select current element or not select.
     * Keep a Boolean array as pruning.
     *
     * @param nums    given array
     * @param index   current index
     * @param sum     current remaining sum
     * @param visited pruning
     * @return if the array can be divided into two parts
     */
    private boolean dfs(int[] nums, int index, int sum, Boolean[] visited) {

        if (visited[sum] != null) {
            return visited[sum];
        }

        if (index == nums.length || sum < nums[index]) {
            return false;
        }

        if (sum == nums[index]) {
            return true;
        }

        return dfs(nums, index + 1, sum - nums[index], visited) || dfs(nums, index + 1, sum, visited);
    }
}
