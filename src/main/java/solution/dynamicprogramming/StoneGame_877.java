package solution.dynamicprogramming;

/**
 * Alex and Lee play a game with piles of stones.
 * There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 * Alex and Lee take turns, with Alex starting first.
 * Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.
 * This continues until there are no more piles left, at which point the person with the most stones wins.
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 * Note:
 * 1. 2 <= piles.length <= 500
 * 2. piles.length is even.
 * 3. 1 <= piles[i] <= 500
 * 4. sum(piles) is odd.
 *
 * @author BorisMirage
 * Time: 2019/09/16 17:37
 * Created with IntelliJ IDEA
 */

public class StoneGame_877 {
    /**
     * Min-max problem can be solved by dynamic programming.
     * The key of this problem is to correctly define dp[i][j].
     * State transition:
     * dp[i][j]: how much more scores that the first-in-action player will get from i to j than the second player.
     * dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1], where 0 <= i < j < n and i + j < n.
     * Base case:
     * dp[i][i] = i
     * At each dp[i][j], the max dp[i][j] is actually to find the max result that last player can get.
     * The reason is that under the rule of game, each player will try the strategy that max its score.
     * Therefore, to find if player 1 can win, check the max points player 2 can reach and decide if player 1 can win.
     * However, this problem is actually meaningless.
     * Since piles.length is even, the first player can always choose all odd or all even elements in array.
     * sum(piles) is odd, then sum of odd elements and sum of even elements will not be equal.
     * Therefore, player 1 will always win by selecting all odd/even elements, judge by sum of all odd/even elements.
     *
     * @param piles given int array
     * @return rue if and only if Alex wins the game
     */
    public boolean stoneGame(int[] piles) {

        /* Corner case */
        if (piles.length <= 2) {
            return true;
        }

        int n = piles.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        for (int length = 1; length < n; length++) {
            for (int i = 0; i < n - length; i++) {
                int j = i + length;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] > 0;
    }
}
