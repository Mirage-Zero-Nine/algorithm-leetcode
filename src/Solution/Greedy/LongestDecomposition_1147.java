package Solution.Greedy;

/**
 * Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:
 * Each a_i is a non-empty string;
 * Their concatenation a_1 + a_2 + ... + a_k is equal to text;
 * For all 1 <= i <= k,  a_i = a_{k+1 - i}.
 *
 * @author BorisMirage
 * Time: 2019/08/03 23:07
 * Created with IntelliJ IDEA
 */

public class LongestDecomposition_1147 {
    /**
     * Greedy.
     * Found each chunk that follows a(i) == a(k+1-i).
     * Note that the # of chunk can be even or odd. Hence, count each chunk respectively instead of multiply it by 2.
     *
     * @param text given text
     * @return largest possible k
     */
    public int longestDecomposition(String text) {
        int max = 0, n = text.length();
        StringBuilder l = new StringBuilder();
        StringBuilder r = new StringBuilder();

        for (int i = 0; i < n; i++) {
            l.append(text.charAt(i));
            r.insert(0, text.charAt(n - i - 1));

            if (l.toString().equals(r.toString())) {        // if found a chunk, reset string builder
                l = new StringBuilder();
                r = new StringBuilder();
                max++;
            }
        }

        return max;
    }
}
