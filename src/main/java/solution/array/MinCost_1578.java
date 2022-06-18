package solution.array;

/**
 * Given a string s and an array of integers cost where cost[i] is the cost of deleting the character i in s.
 * Return the minimum cost of deletions such that there are no two identical letters next to each other.
 * Notice that you will delete the chosen characters at the same time.
 * After deleting a character, the costs of deleting other characters will not change.
 *
 * @author BorisMirage
 * Time: 2020/09/07 20:17
 * Created with IntelliJ IDEA
 */

public class MinCost_1578 {
    /**
     * Delete all duplicated elements until there is one left. This element should owns the largest cost in subarray.
     *
     * @param s    given string
     * @param cost the cost of deleting elements in string
     * @return the minimum cost of deletions such that there are no two identical letters next to each other
     */
    public int minCost(String s, int[] cost) {

        int out = 0, max = 0, group = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) != s.charAt(i - 1)) {
                out += (group - max);
                max = 0;
                group = 0;
            }
            group += cost[i];
            max = Math.max(max, cost[i]);
        }
        out += (group - max);

        return out;
    }
}
