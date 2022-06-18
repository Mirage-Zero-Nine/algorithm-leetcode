package solution.array;

/**
 * There are some chips, and the i-th chip is at position chips[i].
 * You can perform any of the two following types of moves any number of times (possibly zero) on any chip:
 * Move the i-th chip by 2 units to the left or to the right with a cost of 0.
 * Move the i-th chip by 1 unit to the left or to the right with a cost of 1.
 * There can be two or more chips at the same position initially.
 * Return the minimum cost needed to move all the chips to the same position (any position).
 *
 * @author BorisMirage
 * Time: 2019/10/05 19:32
 * Created with IntelliJ IDEA
 */

public class MinCostToMoveChips_1217 {
    /**
     * Count the number of odd numbers and even numbers. Return the min one.
     *
     * @param chips given int array
     * @return minimum cost needed to move all the chips to the same position (any position)
     */
    public int minCostToMoveChips(int[] chips) {
        int even = 0, odd = 0;
        for (int i : chips) {
            if (i % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return Math.min(odd, even);
    }
}
