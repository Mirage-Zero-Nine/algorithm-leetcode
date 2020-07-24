package Solution.DynamicProgramming;

/**
 * Given a string S, return the number of substrings that have only one distinct letter.
 *
 * @author BorisMirage
 * Time: 2019/09/08 16:29
 * Created with IntelliJ IDEA
 */

public class CountLetters_1180 {
    /**
     * Similar to max sub array sum problem.
     * dp[i] = (s[i] == s[i + 1]) ? dp[i + 1] + 1 : 1
     * @param S given string
     * @return number of substrings that have only one distinct letter
     */
    public int countLetters(String S) {
        char pre = S.charAt(0);
        int count = 1, sum = 1;

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == pre) {
                count++;
            } else {
                pre = S.charAt(i);
                count = 1;
            }

            sum += count;
        }

        return sum;
    }
}
