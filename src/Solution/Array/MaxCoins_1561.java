package Solution.Array;

import java.util.Arrays;

/**
 * There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
 * - In each step, you will choose any 3 piles of coins (not necessarily consecutive).
 * - Of your choice, Alice will pick the pile with the maximum number of coins.
 * - You will pick the next pile with maximum number of coins.
 * - Your friend Bob will pick the last pile.
 * - Repeat until there are no more piles of coins.
 * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
 * Return the maximum number of coins which you can have.
 *
 * @author BorisMirage
 * Time: 2020/08/27 20:48
 * Created with IntelliJ IDEA
 */

public class MaxCoins_1561 {
    /**
     * In each pile, Alice will always take the largest, and Bob will always take smallest.
     * Hence, always pick the largest element in array (Alice), second largest (you), smallest (Bob).
     * This is actually to divide array into 3 parts, smallest 1/3 is for Bob, the 2/3 for you and Alice.
     * You and Alice will take it alternately, Alice take the largest and you take second largest.
     *
     * @param piles given piles
     * @return the maximum number of coins which you can have
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int out = 0;
        for (int i = piles.length / 3; i < piles.length; i += 2) {      // the smallest 1/3 belongs to Bob
            out += piles[i];
        }
        return out;
    }
}
