package solution.dynamicprogramming;


/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
 * Note:
 * The length of the input string will fit in range [1, 10^5].
 * The input string will only contain the character '*' and digits '0' - '9'.
 *
 * @author BorisMirage
 * Time: 2019/02/23 13:51
 * Created with IntelliJ IDEA
 */

public class NumDecodings_639 {
    /**
     * Dynamic programming with 1D table and from bottom to top.
     * dp(i) = (dp(i-1) * oneChar(i)) + (dp(i-2) * twoChars(i-1, i))
     *
     * @param s input string
     * @return total number of ways to decode s
     */
    public int numDecodings(String s) {

        /* Corner case */
        long[] c = new long[2];     // two stages: i - 2, i - 1; i will replace i - 1 and i - 1 replace i - 2
        c[0] = oneChar(s.charAt(0));
        if (s.length() < 2) {
            return (int) c[0];
        }

        c[1] = c[0] * oneChar(s.charAt(1)) + twoChars(s.charAt(0), s.charAt(1));
        for (int i = 2; i < s.length(); i++) {
            long t = c[1];
            c[1] = (c[1] * oneChar(s.charAt(i)) + c[0] * twoChars(s.charAt(i - 1), s.charAt(i))) % 1000000007;
            c[0] = t;
        }

        return (int) c[1];
    }

    /**
     * Return the possible combination by given single char.
     * If given char is digit from 1 to 9, then there is only one combination: itself.
     * If given char is '*', then it contains 9 possible combinations, from 1 - 9.
     * If given char is '0', then it has no combination.
     *
     * @param c given char
     * @return possible combination
     */
    private int oneChar(char c) {

        return c == '*' ? 9 : (c == '0' ? 0 : 1);
    }

    /**
     * 4 conditions: both chars are '*' / contains one '*' / both are digits. Note that char a is front of char b.
     * 1: If a and b are both '*', then combinations are 15.
     * 2: If a is '*' and b is digit, then possible combinations are depend on b.
     * 2.1: b is in [0, 6] then the combinations are 2, since a can only be 1 or 2.
     * 2.2: Otherwise, a can only be 1 and the combination is 1.
     * 3: If b is '*' and a is digit, then possible combinations are depend on a.
     * 3.1: If a is 1, then b can be in [1, 9], the combination is 9.
     * 3.2: If a is 2, then b can be in [1, 6]. Combination is 6.
     * 3.3: Otherwise, no extra combination. (For instance, '7*' and '*' has same result)
     * 4. Both a and b are digits. This is same as the problem 91.
     * 4.1: If int ab in [10, 26] then only one extra combination.
     * 4.2: Otherwise, no extra combination compares to one char individually.
     *
     * @param a s.charAt(i - 1)
     * @param b s.charAt(i)
     * @return extra possible combinations compares to one char
     */
    private int twoChars(char a, char b) {
        if (a == '*' && b == '*') {     // both a and b are '*'
            return 15;
        } else if (a == '*') {      //  only b is digit

            int bb = Integer.parseInt(String.valueOf(b));
            return (bb >= 0 && bb < 7) ? 2 : 1;

        } else if (b == '*') {      // only a is digit

            int aa = Integer.parseInt(String.valueOf(a));
            return aa == 1 ? 9 : (aa == 2 ? 6 : 0);

        } else {        // both a and b are digits
            int cc = Integer.parseInt(String.valueOf(a) + b);
            return cc > 9 && cc < 27 ? 1 : 0;

        }
    }
}
