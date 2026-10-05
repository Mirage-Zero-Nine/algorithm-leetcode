package solutions.dynamicprogramming;

import java.util.Arrays;

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
     * Uses a one-dimensional 0/1-knapsack table to find a subset summing to
     * half the total. For each value {@code n}, the state transition is
     * {@code dp[s] = dp[s] || dp[s - n]}, with {@code dp[0]} initially true.
     *
     * @param nums positive input values; the array is not mutated
     * @return whether the values can be split into two equal-sum subsets
     * <p>Complexity: O(n * totalSum) time and O(totalSum) auxiliary space.
     */
    public boolean canPartition(int[] nums) {
        // corner cases
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

        for (int n : nums) {
            // Before this iteration, dp[s] describes sums reachable from the
            // prefix before n. The update either skips n (dp[i]) or takes it
            // from a previously reachable sum (dp[i - n]).
            for (int i = target; i >= n; i--) {
                // Descending ensures dp[i - n] still belongs to the previous
                // prefix, so this 0/1 transition cannot reuse n. The bounds
                // avoid negative indexes and ignore sums above the target.
                dp[i] = dp[i] || dp[i - n];
            }
        }

        return dp[target];
    }

    /**
     * Explores the include/exclude decision tree with memoization. The state
     * transition is {@code F(i, r) = F(i + 1, r - nums[i]) || F(i + 1, r)}.
     *
     * @param nums positive input values; the array is not mutated
     * @return whether a subset reaches half of the total sum
     * <p>Complexity: O(n * totalSum) worst-case time plus O(n log n) sorting,
     * with O(n * totalSum) auxiliary memo space and O(n) recursion depth.
     */
    public boolean canPartitionDFS(int[] nums) {

        // The implementation defines null and shorter inputs as having no
        // valid partition.
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();

        if (sum % 2 == 1) {
            return false;       // odd number cannot be split into equal parts
        }
        // Sorting enables the recursive remaining-sum prune without mutating
        // the caller's array: every later value is at least sorted[index].
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        // The index is part of the key: the same remaining sum can have a
        // different answer when different suffixes remain available.
        Boolean[][] memo = new Boolean[sorted.length][sum / 2 + 1];
        return dfs(sorted, 0, sum / 2, memo);
    }

    /**
     * Evaluates one memoized DFS state. A state succeeds when its remaining sum
     * is zero and fails when no sorted value remains small enough to use.
     *
     * @param nums  given array
     * @param index current index
     * @param sum   current remaining sum
     * @param memo  memoization table indexed by index and remaining sum
     * @return whether the suffix can form the remaining sum
     */
    private boolean dfs(int[] nums, int index, int sum, Boolean[][] memo) {
        if (sum == 0) {
            return true;
        }
        if (index == nums.length || sum < nums[index]) {
            // With positive sorted values, neither taking a later value nor
            // skipping the current one can make a too-large remaining sum.
            return false;
        }
        if (memo[index][sum] != null) {
            return memo[index][sum];
        }

        // Try taking the current value, then skipping it. Short-circuiting
        // avoids exploring the second branch after a complete subset is found.
        boolean found = dfs(nums, index + 1, sum - nums[index], memo)
                || dfs(nums, index + 1, sum, memo);
        memo[index][sum] = found;
        return found;
    }
}
