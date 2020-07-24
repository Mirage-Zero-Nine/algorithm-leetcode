package Solution.Math;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * @author BorisMirage
 * Time: 2019/10/22 21:25
 * Created with IntelliJ IDEA
 */

public class ConvertToTitle_168 {
    /**
     * Decimal to base-26.
     * Note that the mapping rule starts at 1, instead of 0 in normal conversion.
     *
     * @param n given column # in Excel
     * @return corresponding column title as appear in an Excel sheet
     */
    public String convertToTitle(int n) {
        if (n == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int tmp = (n - 1) % 26;
            sb.append((char) (tmp + 'A'));
            n = (n - 1) / 26;
        }

        return sb.reverse().toString();
    }
}
