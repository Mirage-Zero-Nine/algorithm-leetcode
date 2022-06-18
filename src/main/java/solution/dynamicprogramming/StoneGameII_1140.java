package solution.dynamicprogramming;

/**
 * Alex and Lee continue their games with piles of stones.
 * There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 * The objective of the game is to end with the most stones.
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.
 * Then, set M = max(M, X).
 * The game continues until all the stones have been taken.
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 *
 * @author BorisMirage
 * Time: 2019/08/03 18:26
 * Created with IntelliJ IDEA
 */

public class StoneGameII_1140 {
    /**
     * Dynamic programming with memorization to find min-max value.
     *
     * @param piles given array represents stone piles
     * @return the maximum number of stones Alex can get
     */
    public int stoneGameII(int[] piles) {

        /* Corner case */
        if (piles.length == 0) {
            return 0;
        }

        int l = piles.length;
        int[] sum = new int[l];
        sum[l - 1] = piles[l - 1];

        for (int i = l - 2; i > -1; i--) {      // sum of rest stones that could be taken in one time
            sum[i] = sum[i + 1] + piles[i];
        }
        int[][] dp = new int[l][l];
        return helper(piles, 0, 1, sum, dp);
    }

    /**
     * Dynamic programming with memorization.
     *
     * @param piles given array represents stone piles
     * @param i     pile index start to be taken
     * @param m     init piles that player could take (players could take from i to i + 2m plies)
     * @param sum   sum of piles from n to 1.
     * @param dp    dp array
     * @return the maximum number of stones first player can get
     */
    private int helper(int[] piles, int i, int m, int[] sum, int[][] dp) {

        if (i == piles.length) {        // reaches the end of piles
            return 0;
        }
        if (m * 2 >= piles.length - i) {        // the other plays can take all rest stones
            return sum[i];
        }
        if (dp[i][m] != 0) {        // previous memorization
            return dp[i][m];        // dp[i][m]: max stones current player could take
        }

        int min = Integer.MAX_VALUE;        // init min value
        for (int x = 1; x <= 2 * m; x++) {

            /*
             * Find min value that next player could take based on this turn from 1 to 2m.
             * The reason to find min value is to maximize next turn.
             * i + x: take x piles and the next turn start position is i + x
             * max(m, x): m = max(m, x), set by game rules. */
            min = Math.min(min, helper(piles, i + x, Math.max(m, x), sum, dp));     // find min for next player
        }

        dp[i][m] = sum[i] - min;        // max stones = all the left stones - the min stones next player can get

        return dp[i][m];
    }
}
