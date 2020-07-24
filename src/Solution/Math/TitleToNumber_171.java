package Solution.Math;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *
 * @author BorisMirage
 * Time: 2019/10/22 21:36
 * Created with IntelliJ IDEA
 */

public class TitleToNumber_171 {
    /**
     * Base-26 to decimal.
     * c1 * 26^(n - 1) + c2 * 26^(n - 2) + ... + cn * 26^(0), where n is the length of given string.
     *
     * @param s given base-26 string
     * @return representing decimal number
     */
    public int titleToNumber(String s) {
        int n = s.length();
        int out = 0;
        for (int i = 0; i < n; i++) {
            out += ((s.charAt(i) - 'A' + 1) * (int) Math.pow(26, n - i - 1));
        }
        return out;
    }
}
