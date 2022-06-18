package solution.dynamicprogramming;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * @author BorisMirage
 * Time: 2019/02/23 13:20
 * Created with IntelliJ IDEA
 */

public class NumDecodings_91 {
    /**
     * Dynamic programming with 1D table.
     * Traverse s from back to front to avoid initial '0'.
     * During the traverse, if current char is '0' then continue, otherwise check if i, i + 1 is larger than 26.
     * If larger than 26, add dp[i + 2] only, otherwise add dp[i + 1] and dp[i + 2].
     *
     * @param s given int string
     * @return total number of ways to decode it
     */
    public int numDecodings(String s) {

        /* Corner case */
        if (s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];

        dp[s.length()] = 1;
        dp[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;        // in case last digit is 0

        for (int i = s.length() - 2; i > -1; i--) {
            if (s.charAt(i) != '0') {

                /* (i ,i + 1), two digits */
                dp[i] = (Integer.parseInt(s.substring(i, i + 2)) < 27) ? dp[i + 1] + dp[i + 2] : dp[i + 1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new NumDecodings_91().numDecodings("1021"));
    }
}
