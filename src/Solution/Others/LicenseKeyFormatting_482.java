package Solution.Others;

/**
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes.
 * The string is separated into N+1 groups by N dashes.
 * Given a number K, reformat the strings such that each group contains exactly K characters, except first group.
 * Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 *
 * @author BorisMirage
 * Time: 2019/09/09 13:22
 * Created with IntelliJ IDEA
 */

public class LicenseKeyFormatting_482 {
    /**
     * Format string from the end to the beginning, note that if current char is at factor of k, add a "-" to result.
     *
     * @param s given license key
     * @param k each group contains exactly K characters
     * @return formatted string
     */
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                if (sb.length() % (k + 1) == k) {
                    sb.append("-");
                }
                sb.append(s.charAt(i));
            }
        }
        return sb.reverse().toString().toUpperCase();
    }
}
