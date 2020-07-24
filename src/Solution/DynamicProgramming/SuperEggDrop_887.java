package Solution.DynamicProgramming;

/**
 * Given K eggs, and the access to a building with N floors from 1 to N.
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * There exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break
 * And any egg dropped at or below floor F will not break.
 * Each move, take an egg (if there is an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * Goal is to know with certainty what the value of F is.
 * What is the minimum number of moves that need to know with certainty what F is, regardless of the initial value of F?
 *
 * @author BorisMirage
 * Time: 2019/07/14 10:17
 * Created with IntelliJ IDEA
 */

public class SuperEggDrop_887 {
    /**
     * Dynamic programming.
     * dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1,
     * Assume dp[m-1][k-1] = n0, dp[m-1][k] = n1
     * First floor to check is n0+1.
     * If egg breaks, F must be in [1,n0] floors, use m-1 moves and k-1 eggs to find out exact F.
     * If egg doesn't breaks and F is in [n0+2, n0+n1+1] floors, use m-1 moves and k eggs to find out exact F.
     * In this way, with m moves and k eggs, F can be found in n0+n1+1 floors.
     *
     * @param K K eggs
     * @param N building with N floors from 1 to N
     * @return minimum number of moves that need to know with certainty what F is, regardless of the initial value of F
     */
    public int superEggDrop(int K, int N) {

        int[][] dp = new int[N + 1][K + 1];     // dp[M][K]: the maximum number of floor can be checked given M moves and K eggs

        int m = 0;      // minimum number of moves

        while (dp[m][K] < N) {      // search from 1 move to n moves, and 1 egg to K eggs
            m++;     // add a move (drop an egg)

            /*
             * There are two conditions:
             * 1. Egg breaks: now only k - 1 eggs can be used and use m-1 moves and k-1 eggs to find out exact F.
             * 2. Egg remains safe: F is in upper floor, with m-1 moves and k eggs can find final F.
             * The sum of conditions are the maximum number of floor can be checked. */
            for (int k = 1; k <= K; ++k) {
                dp[m][K] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
            }
        }

        return m;
    }

    public static void main(String[] args) {
        System.out.println(new SuperEggDrop_887().superEggDrop(2, 10));
    }
}
