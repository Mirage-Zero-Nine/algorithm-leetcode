package Solution.DynamicProgramming;

/**
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid.
 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way to rescue the princess.
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 * Some of the rooms knight loses health (negative integers); other rooms are either 0 or positive integers.
 * In order to reach the princess ASAP, the knight decides to move only rightward or downward in each step.
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 *
 * @author BorisMirage
 * Time: 2019/08/27 23:19
 * Created with IntelliJ IDEA
 */

public class CalculateMinimumHP_174 {
    /**
     * Dynamic programming.
     * Note that this problem can only be solved by bottom-up, since the initial health is unknown.
     * State transition:
     * dp[i][j] = minimum health level required to reach the princess when entering (i, j)
     * dp[i + 1][j + 1] = max(min(dp[i][j + 1], dp[i + 1][j]) - dungeon[i + 1][j + 1], 0)
     *
     * @param dungeon given map
     * @return minimum initial health so that he is able to rescue the princess
     */
    public int calculateMinimumHP(int[][] dungeon) {

        int m = dungeon.length;
        int n = dungeon[0].length;

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            dp[i][n] = Integer.MAX_VALUE;       // avoid out of bound
        }
        for (int j = 0; j < n + 1; j++) {
            dp[m][j] = Integer.MAX_VALUE;
        }

        dp[m][n - 1] = 0;       // these two points is "virtual" and will not cost health
        dp[m - 1][n] = 0;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 0);
            }
        }

        return dp[0][0] + 1;
    }
}
