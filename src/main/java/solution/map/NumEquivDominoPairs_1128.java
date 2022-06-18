package solution.map;

import java.util.HashMap;

/**
 * Given a list of dominoes, dominoes[i] = [a, b] == dominoes[j] = [c, d] iff either (a==c && b==d) || (a==d && b==c).
 * One domino can be rotated to be equal to another domino.
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 *
 * @author BorisMirage
 * Time: 2019/07/27 13:06
 * Created with IntelliJ IDEA
 */

public class NumEquivDominoPairs_1128 {
    /**
     * Hash map to store the pair. The key is made up with number of dominoes, min * 10 + max.
     * Value is the count of appearance of this dominoes.
     * When calculating total pairs, it should be C(n,2) for each pair count (n same dominoes and each 2 as a pair).
     *
     * @param dominoes given dominoes list
     * @return pairs of dominoes[i] is equivalent to dominoes[j]
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int c = 0;
        for (int[] d : dominoes) {
            int k = Math.min(d[0], d[1]) * 10 + Math.max(d[0], d[1]);
            m.put(k, m.getOrDefault(k, 0) + 1);
        }

        for (int i : m.values()) {
            c += i * (i - 1) / 2;       // count total pairs
        }

        return c;
    }
}
