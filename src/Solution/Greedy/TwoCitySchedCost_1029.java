package Solution.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are 2N people a company is planning to interview.
 * The cost of flying the i-th person to city A is costs[i][0].
 * The cost of flying the i-th person to city B is costs[i][1].
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 *
 * @author BorisMirage
 * Time: 2019/07/18 18:04
 * Created with IntelliJ IDEA
 */

public class TwoCitySchedCost_1029 {
    /**
     * Greedy.
     * Sort the array by difference for person fly a or fly to b by (a[0] - a[1]) - (b[0] - b[1]).
     * The higher priority has less total cost send to city A.
     * And less priority has less total cost send to city B.
     *
     * @param costs cost list
     * @return minimum cost to fly every person to a city such that exactly N people arrive in each city
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {

                /*
                 * The higher priority has less total cost send to city A.
                 * And less priority has less total cost send to city B. */
                return (a[0] - a[1]) - (b[0] - b[1]);
            }
        });

        int total = 0;
        for (int i = 0; i < costs.length / 2; i++) {
            total += costs[i][0] + costs[costs.length - i - 1][1];
        }

        return total;
    }

    /**
     * For (i+j)th people, it can be assigned either to A city or B city.
     * The min cost if assigned to A city: dp[i-1][j]+costs[i+j-1][0]. (Fly to A, so use i-1)
     * The min cost if assigned to B city: dp[i][j-1]+costs[i+j-1][1]; (Fly to B, so use j-1)
     * dp[i][j] = Math.min(dp[i-1][j]+costs[i+j-1][0] , dp[i][j-1]+costs[i+j-1][1])
     *
     * @param costs cost list
     * @return minimum cost to fly every person to a city such that exactly N people arrive in each city
     */
    public int dynamicProgramming(int[][] costs) {
        int N = costs.length / 2;
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
        }

        for (int j = 1; j <= N; j++) {
            dp[0][j] = dp[0][j - 1] + costs[j - 1][1];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }

        return dp[N][N];
    }
}
