package solution.backtracking;

import java.util.HashMap;
import java.util.Map;

/**
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10.
 * Player who first causes the running total to reach/exceed 100 wins.
 * Now rule changes to not able to re-use integers.
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement.
 * The first one reaching or exceeding 100 wins.
 * Given an integer <code>maxChoosableInteger</code> and another integer desiredTotal
 * Determine if the first player to move can force a win.
 * <code>maxChoosableInteger</code> will not be larger than 20 and desiredTotal will not be larger than 300.
 *
 * @author BorisMirage
 * Time: 2019/06/08 13:05
 * Created with IntelliJ IDEA
 */

public class CanIWin_464 {

    private final Map<Integer, Boolean> m = new HashMap<>();       // hash map store backtrack temp result

    /**
     * Backtracking with pruning to reduce time.
     *
     * @param maxChoosableInteger max choosable integer
     * @param desiredTotal        total to reach
     * @return if the first player to move can force a win
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        /* Corner case */
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        if (maxChoosableInteger + maxChoosableInteger * (maxChoosableInteger - 1) / 2 < desiredTotal) {
            return false;
        }

        return backtracking(maxChoosableInteger + 1, new boolean[maxChoosableInteger + 1], desiredTotal);
    }

    /**
     * Backtracking with a hash map for pruning.
     *
     * @param max          max choosable integer
     * @param chosen       integers has been choosen
     * @param currentTotal current remaining total
     * @return if the first player to move can force a win
     */
    private boolean backtracking(int max, boolean[] chosen, int currentTotal) {

        if (currentTotal <= 0) {        // previous step has won
            return false;
        }

        int current = transfer(chosen);
        if (m.containsKey(current)) {
            return m.get(current);      // reuse previous result to reduce time
        }

        for (int i = 1; i < max; i++) {

            if (!chosen[i]) {       // choose a new number for result tree
                chosen[i] = true;

                /* If next player lose, then current player wins */
                if (!backtracking(max, chosen, currentTotal - i)) {
                    m.put(current, true);
                    chosen[i] = false;      // flip back to previous situation for next iteration
                    return true;            // only one winning situation will suffice
                }
                chosen[i] = false;      // flip back to previous situation for next iteration
            }
        }
        m.put(current, false);      // no winning situation for current player

        return false;
    }


    /**
     * Regard bit array as a integer and transfer it.
     *
     * @param arr given boolean array
     * @return integer that this array representing
     */
    private int transfer(boolean[] arr) {
        int num = 0;
        for (boolean x : arr) {
            num <<= 1;      // left shift one bit
            if (x) {
                num |= 1;       // turn false to 0
            }
        }
        return num;
    }
}
