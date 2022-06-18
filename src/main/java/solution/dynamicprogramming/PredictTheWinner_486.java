package solution.dynamicprogramming;

import java.util.Arrays;

/**
 * Given an array of scores that are non-negative integers.
 * Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on.
 * Each time a player picks a number, that number will not be available for the next player.
 * This continues until all the scores have been chosen. The player with the maximum score wins.
 * Given an array of scores, predict whether player 1 is the winner.
 * You can assume each player plays to maximize his score.
 *
 * @author BorisMirage
 * Time: 2019/09/16 14:48
 * Created with IntelliJ IDEA
 */

public class PredictTheWinner_486 {
    /**
     * Min-max problem. Solved by dynamic programming.
     * The key of this problem is to correctly define dp[i][j].
     * State transition:
     * dp[i][j]: how much more scores the first-in-action player will get from i to j than the second player.
     * dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]), where 0 <= i < j < n and i + j < n.
     * Base case:
     * dp[i][i] = i
     * At each dp[i][j], the max dp[i][j] is actually to find the max result that last player can get.
     * The reason is that under the rule of game, each player will try the strategy that max its score.
     * Therefore, to find if player 1 can win, check the max points player 2 can reach and decide if player 1 can win.
     *
     * @param nums given int array
     * @return predict whether player 1 is the winner
     */
    public boolean predictTheWinner(int[] nums) {

        /* Corner case */
        if (nums.length % 2 == 0 || nums.length < 2) {
            return true;
        }

        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int length = 1; length <= n; length++) {           // iterate each length
            for (int i = 0; i < n - length; i++) {              // iterate each start index with fixed length
                int j = i + length;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] >= 0;
    }

    /**
     * DFS with pruning.
     * Find max sum of player from range [i, j].
     * Use a 2D array to store max score that current player can get as pruning.
     *
     * @param nums given int array
     * @return predict whether player 1 is the winner
     */
    public boolean predictTheWinnerDFSWithPruning(int[] nums) {
        int n = nums.length, sum = Arrays.stream(nums).sum();
        int[][] memorization = new int[n][n];
        int first = dfsPruning(memorization, nums, 0, n - 1);       // most score first player can get

        return sum - first <= first;
    }

    /**
     * DFS with pruning.
     *
     * @param mem   memorization
     * @param nums  given array
     * @param left  left bound
     * @param right right bound
     * @return max score current player can get
     */
    private int dfsPruning(int[][] mem, int[] nums, int left, int right) {

        /* Base cases */
        if (left == right) {
            return nums[left];     // if only one element left, then current player always win
        }
        if (left > right) {          // no available element
            return 0;
        }

        if (mem[left][right] != 0) {
            return mem[left][right];
        }

        /*
         * Current player can select left or right.
         * The max score is based on next round, select min score of next player. */
        int selectLeft = nums[left] + Math.min(dfsPruning(mem, nums, left + 2, right), dfsPruning(mem, nums, left + 1, right - 1));
        int selectRight = nums[right] + Math.min(dfsPruning(mem, nums, left + 1, right - 1), dfsPruning(mem, nums, left, right - 2));

        mem[left][right] = Math.max(selectLeft, selectRight);

        return mem[left][right];
    }

    /**
     * Naive DFS to find if first player can win.
     * Each player will choose best strategy.
     * Therefore, if there is a chance one player can win, then the other player will definitely lose.
     *
     * @param nums given int array
     * @return predict whether player 1 is the winner
     */
    public boolean predictTheWinnerDFS(int[] nums) {
        return first(nums, 0, 0, 0, nums.length - 1);
    }

    /**
     * First player round. If second player can win, then first player can not win.
     *
     * @param nums  given int array
     * @param sum1  total score of first player
     * @param sum2  total score of second player
     * @param left  left bound of given array
     * @param right right bound of given array
     * @return if first player can win
     */
    private boolean first(int[] nums, int sum1, int sum2, int left, int right) {
        if (left > right) {
            return sum1 >= sum2;
        }

        return !second(nums, sum1 + nums[left], sum2, left + 1, right) || !second(nums, sum1 + nums[right], sum2, left, right - 1);
    }

    /**
     * Second player round. If first player can win, then second player can not win.
     *
     * @param nums  given int array
     * @param sum1  total score of first player
     * @param sum2  total score of second player
     * @param left  left bound of given array
     * @param right right bound of given array
     * @return if second player can win
     */
    private boolean second(int[] nums, int sum1, int sum2, int left, int right) {
        if (left > right) {
            return sum1 < sum2;
        }

        return !first(nums, sum1, sum2 + nums[left], left + 1, right) || !first(nums, sum1, sum2 + nums[right], left, right - 1);
    }

    public static void main(String[] args) {
        System.out.println(new PredictTheWinner_486().predictTheWinnerDFS(new int[]{1}));
    }
}
