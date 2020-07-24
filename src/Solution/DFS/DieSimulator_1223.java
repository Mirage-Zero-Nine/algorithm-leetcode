package Solution.DFS;

/**
 * A die simulator generates a random number from 1 to 6 for each roll.
 * The generator cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
 * Given an array of integers rollMax and an integer n.
 * Return the number of distinct sequences that can be obtained with exact n rolls.
 * Two sequences are considered different if at least one element differs from each other.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2019/10/14 11:41
 * Created with IntelliJ IDEA
 */

public class DieSimulator_1223 {
    private int MOD = 1000000000 + 7;

    /**
     * DFS with memorization.
     * The memorization array stores value under specific remaining dices, roll limit, and dice value.
     *
     * @param n       n dices
     * @param rollMax max consecutive rolls of one dice value
     * @return the number of distinct sequences that can be obtained with exact n rolls
     */
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] mem = new int[n][16][6];      // n remaining dices, at most roll 16 times, dice has 6 facet

        int out = 0;
        for (int i = 0; i < 6; i++) {
            out = (out + dfs(mem, rollMax, n - 1, 1, i)) % MOD;     // first dice roll
        }

        return out;
    }

    /**
     * DFS with memorization.
     * The constraint of DFS is that, first, if remaining dice is 0, then current dice has only one combination.
     * Second, if consecutive roll has reached the limit, current dice roll can not be the limited value.
     * A memorization array is used to store the previous result of specific remaining dice, roll limit and dice value.
     *
     * @param mem         memory array
     * @param max         max consecutive rolls of one dice value
     * @param remaining   remaining dice roll
     * @param consecutive previous consecutive roll
     * @param i           previous dice value
     * @return the number of distinct sequences that can be obtained with exact n rolls
     */
    private int dfs(int[][][] mem, int[] max, int remaining, int consecutive, int i) {

        if (remaining == 0) {
            return 1;       // last dice has only one combination
        }

        if (mem[remaining][consecutive][i] != 0) {
            return mem[remaining][consecutive][i];      // memorization
        }

        int out = 0;
        for (int j = 0; j < 6; j++) {
            if (j != i) {
                out = (out + dfs(mem, max, remaining - 1, 1, j)) % MOD;
            } else if (consecutive < max[i]) {
                out = (out + dfs(mem, max, remaining - 1, consecutive + 1, j)) % MOD;
            }
        }

        mem[remaining][consecutive][i] = out;

        return out;
    }
}
