package Solution.DynamicProgramming;

/**
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 * A move consists of merging exactly K consecutive piles into one pile.
 * And the cost of this move is equal to the total number of stones in these K piles.
 * Find the minimum cost to merge all piles of stones into one pile. If it is impossible, return -1.
 * Note:
 * 1. 1 <= stones.length <= 30
 * 2. 2 <= K <= 30
 * 3. 1 <= stones[i] <= 100
 *
 * @author BorisMirage
 * Time: 2019/08/07 14:28
 * Created with IntelliJ IDEA
 */

public class MergeStones_1000 {
    /**
     * stones[i] ~ stones[j] will merge as much as possible.
     * State transition:
     * dp[i][j] = min(dp[i][j], dp[i][m] + dp[m+1][j])
     * if (j - i) % (K - 1) == 0, then stone(i) to stone(j) can be merged, add merge value to dp[i][j].
     *
     * @param stones given stones array
     * @param K      each move merging exactly K consecutive piles into one pile
     * @return minimum cost to merge all piles of stones into one pile, if it is impossible, return -1
     */
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;

        /* Corner case */
        if ((n - 1) % (K - 1) > 0) {
            return -1;
        }

        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }

        int[][] dp = new int[n][n];

        for (int r = K; r <= n; r++) {      // traverse in stones from K to n
            for (int i = 0; i + r <= n; i++) {       // find each start index in stones
                int j = i + r - 1;                  // end position is based on
                dp[i][j] = Integer.MAX_VALUE;
                for (int m = i; m < j; m += K - 1) {        // find min merge position from i to j
                    dp[i][j] = Math.min(dp[i][j], dp[i][m] + dp[m + 1][j]);     // fill dp
                }

                if ((j - i) % (K - 1) == 0) {       // if current position is mergeable
                    dp[i][j] = dp[i][j] + sum[j + 1] - sum[i];
                }
            }
        }

        return dp[0][n - 1];
    }
}
