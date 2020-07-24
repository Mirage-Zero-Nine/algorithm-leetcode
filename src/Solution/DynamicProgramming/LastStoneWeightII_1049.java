package Solution.DynamicProgramming;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * Each turn, we choose any two rocks and smash them together.
 * Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 * 1. If x == y, both stones are totally destroyed;
 * 2. If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.
 * Return the smallest possible weight of this stone (the weight is 0 if there are no stones left.)
 *
 * @author BorisMirage
 * Time: 2019/07/13 13:18
 * Created with IntelliJ IDEA
 */

public class LastStoneWeightII_1049 {
    /**
     * Knapsack problem.
     *
     * @param stones given int array
     * @return min sum difference of two sub array
     */
    public int lastStoneWeightII(int[] stones) {

        /* Corner case */
        if (stones.length == 1) {
            return stones[0];
        }

        int sum = 0, s1 = 0;
        for (int s : stones) {
            sum += s;       // find total sum of array
        }

        boolean[][] dp = new boolean[sum / 2 + 1][stones.length + 1];

        for (int i = 0; i <= stones.length; i++) {
            dp[0][i] = true;
        }

        for (int i = 1; i <= sum / 2; i++) {
            for (int j = 1; j <= stones.length; j++) {
                if (dp[i][j - 1] || (i >= stones[j - 1]) && dp[i - stones[j - 1]][j - 1]) {
                    dp[i][j] = true;
                    s1 = Math.max(s1, i);
                }
            }
        }
        return sum - s1 * 2;
    }
}
