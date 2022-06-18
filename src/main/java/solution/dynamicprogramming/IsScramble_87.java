package solution.dynamicprogramming;

/**
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * Note that s1 and s2 has same length.
 *
 * @author BorisMirage
 * Time: 2019/02/12 22:56
 * Created with IntelliJ IDEA
 */

public class IsScramble_87 {
    /**
     * Recursion.
     * Check if two given strings are anagram, then split string into substring and repeat this process.
     * If two original string is not scrambled, then it will finally has a different substring.
     *
     * @param s1 first string
     * @param s2 second string
     * @return if s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {

        /* Corner case */
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        int[] alphabet = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            alphabet[s1.charAt(i) - 'a']++;
            alphabet[s2.charAt(i) - 'a']--;
        }

        for (int i : alphabet) {
            if (i != 0) {
                return false;       // if two strings has different # of char, then it can not be scrambled
            }
        }

        for (int i = 1; i < s1.length(); i++) {

            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dynamic programming with 3D table.
     * i  i+q  i+k-1
     * j  j+q  j+k-1
     * k  j+k-q  j+k-1
     *
     * @param s1 first string
     * @param s2 second string
     * @return if s2 is a scrambled string of s1
     */
    public boolean dp(String s1, String s2) {
        /* Corner case */
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }

        int l = s1.length();

        boolean[][][] temp = new boolean[l][l][l + 1];

        /* Check each single char in string */
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                temp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        /* Fixed split string position */
        for (int k = 2; k <= l; k++) {

            /* Find start position in s1 */
            for (int i = 0; i <= l - k; i++) {

                /* Find start position in s2 */
                for (int j = 0; j <= l - k; j++) {

                    for (int p = 1; p < k; p++) {
                        if (((temp[i][j][p] && temp[i + p][j + p][k - p])) || ((temp[i][j + k - p][p] && temp[i + p][j][k - p]))) {
                            temp[i][j][k] = true;
                        }
                    }
                }
            }
        }

        return temp[0][0][l];
    }
}
