package Solution.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

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
     * 0/1 Knapsack problem.
     * State transition:
     * dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j]
     * dp[i - 1][j - nums[i - 1]]: choose nums[i], and if j - nums[i] can make up sum, dp[i][j] can.
     * dp[i - 1][j]: leave nums[i] and if from nums[0] to nums[i-1] can make up, then dp[i][j] can.
     *
     * @param nums given array
     * @return if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal
     */
    public boolean canPartition(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();

        if (sum % 2 == 1) {
            return false;       // odd number can not be spilt to equal parts
        }

        sum /= 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];     // dp[i][j]: if 0 to i in nums can reach sum of j

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                if (j - nums[i - 1] >= 0) {     // choose current nums[i]
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][sum];
    }

    /**
     * DFS with pruning, idea is same as the DP solution, select or not select.
     * Keep a boolean array or a hash map to prune.
     *
     * @param nums given array
     * @return if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal
     */
    public boolean canPartitionDFS(int[] nums) {

        /* Corner case */
        if (nums.length < 2) {
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
    private boolean dfs(int[] nums, int index, int sum, HashMap<Integer, Boolean> m) {

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
        visited[sum] = dfs(nums, index + 1, sum - nums[index], visited) || dfs(nums, index + 1, sum, visited);

        return visited[sum];
    }

    public static void main(String[] args) {
        System.out.println(new CanPartition_416().canPartitionDFS(new int[]{6, 4, 4, 3, 1}));       // true
        System.out.println(new CanPartition_416().canPartitionDFS(new int[]{1, 3, 4, 4, 6}));       // true
        System.out.println(new CanPartition_416().canPartitionDFS(new int[]{0, 0, 0, 0}));       // true
        System.out.println(new CanPartition_416().canPartitionDFS(new int[]{10, 5, 4, 1}));       // true
    }
}
