package solution.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, we make queries on substrings of s.
 * For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right].
 * And then choose up to k of them to replace with any lowercase English letter.
 * If the substring is possible to be a palindrome string after the operations above, the result of the query is true.
 * Otherwise, the result is false.
 * Return an array answer[], where answer[i] is the result of the i-th query queries[i].
 * Note that: Each letter is counted individually for replacement.
 * For example s[left..right] = "aaa", and k = 2, we can only replace two of the letters.
 * (Also, note that the initial string s is never modified by any query.)
 *
 * @author BorisMirage
 * Time: 2019/08/31 22:40
 * Created with IntelliJ IDEA
 */

public class CanMakePaliQueries_1177 {

    /**
     * Prefix sum.
     * Traverse s, find all char appearance from 0 to i.
     * Then based on given range, count the char frequency.
     * Find all odd chars and see if they are more than replacement limit.
     *
     * @param s       given string
     * @param queries given queries
     * @return array answer[], answer[i] is the result of the i-th query queries[i]
     */
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {

        List<Boolean> out = new ArrayList<>();
        int[][] prefixSum = new int[s.length() + 1][26];
        for (int i = 1; i <= s.length(); i++) {
            prefixSum[i] = prefixSum[i - 1].clone();
            prefixSum[i][s.charAt(i - 1) - 'a']++;
        }

        for (int[] query : queries) {
            if (query[2] > 13) {
                out.add(true);
            } else {
                int count = 0;
                for (int i = 0; i < 26; i++) {
                    count += (prefixSum[query[1] + 1][i] - prefixSum[query[0]][i]) % 2;
                }

                out.add(count / 2 <= query[2]);
            }
        }

        return out;
    }

    /**
     * Count char each time directly.
     * If replacement can be taken for more than 13 times, then it will always be palindrome.
     * Otherwise, count the char that appears odd times. These char may requires replacement.
     *
     * @param s       given string
     * @param queries given queries
     * @return array answer[], answer[i] is the result of the i-th query queries[i]
     */
    public List<Boolean> countChar(String s, int[][] queries) {

        List<Boolean> out = new ArrayList<>();

        for (int[] cur : queries) {

            /*
             * If can replace more than 13 times, then all chars can be replaced.
             * Otherwise, count the total number of odd chars, which requires replacement. */
            out.add((cur[2] > 13) || Helper(s, cur[0], cur[1], cur[2]));
        }

        return out;
    }

    /**
     * Count char appearance.
     *
     * @param s       given string
     * @param start   start position
     * @param end     end position
     * @param replace replace time
     * @return if current range can be palindrome after replace and rearrange
     */
    private boolean Helper(String s, int start, int end, int replace) {
        if (start == end) {
            return true;
        }

        int[] index = new int[26];
        for (int i = start; i <= end; i++) {
            index[s.charAt(i) - 'a']++;
        }

        int check = 0;
        for (int i = 0; i < 26; i++) {
            if (index[i] % 2 != 0) {
                check++;
            }
        }

        if ((end - start + 1) % 2 == 1) {       // if range is odd, then one odd char can be at center
            check = check - 1;
        }

        return check <= replace * 2;
    }

    public static void main(String[] args) {
        CanMakePaliQueries_1177 test = new CanMakePaliQueries_1177();
        System.out.println(test.canMakePaliQueries("abcda", new int[][]{{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}}));
    }
}
