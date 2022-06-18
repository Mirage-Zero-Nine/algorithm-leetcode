package solution.others;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * Substrings with different start or end indexes are counted as different substrings even consist of same characters.
 *
 * @author BorisMirage
 * Time: 2020/03/05 10:32
 * Created with IntelliJ IDEA
 */

public class CountSubstrings_647 {
    /**
     * Extend from center to find palindromic substrings at each character.
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     *
     * @param s given string
     * @return count number of palindromic substrings in this string
     */
    public int countSubstrings(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += extend(s, i, i);                 // odd length palindromic substring center
            count += extend(s, i, i + 1);       // even length palindromic substring center
        }

        return count;
    }

    /**
     * Extend from current index to find palindromic substrings.
     * If left == right, then count number of odd length palindromic substrings.
     * If left == right - 1, then count number of even length palindromic substrings
     *
     * @param s     given string
     * @param left  left start position
     * @param right right start position
     * @return number of palindromic substrings
     */
    private int extend(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left--) == s.charAt(right++)) {
            count++;
        }

        return count;
    }

    /**
     * Almost same as finding longest palindromic substrings.
     * The difference is that, if found a palindromic substring, then add 1 to result.
     *
     * @param s given string
     * @return count number of palindromic substrings in this string
     */
    public int countSubstringsDP(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length(), count = 1;
        boolean[][] dp = new boolean[n][n];

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((i - j) <= 2 || dp[i - 1][j + 1]);
                if (dp[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Manacher algorithm. The idea is the same.
     * First of all, preprocess string into a array. For instance, cbcbc -> ^#c#b#c#b#c#$.
     * Then use a array to count length of longest palindromic substring at center of each char.
     * Traverse the array, count the center of longest palindromic substring (C) and its radius (R).
     * After finding all number of palindromic substrings, traverse the int array again.
     * If current char has a palindromic substring with length of n, then the total # will be n / 2.
     *
     * @param s given string
     * @return count number of palindromic substrings in this string
     */
    public int countSubstringsManacher(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] array = new char[2 * s.length() + 3];        // + 3 is to allow special beginning and end
        array[0] = '@';
        array[1] = '#';
        array[array.length - 1] = '$';
        int index = 2;
        for (char c : s.toCharArray()) {        // preprocess string
            array[index++] = c;
            array[index++] = '#';
        }

        int[] palindromicLength = new int[array.length];
        int center = 0, right = 0;
        for (int i = 1; i < palindromicLength.length - 1; ++i) {
            if (i < right) {
                palindromicLength[i] = Math.min(right - i, palindromicLength[2 * center - i]);
            }
            while (array[i + palindromicLength[i] + 1] == array[i - palindromicLength[i] - 1]) {
                palindromicLength[i]++;
            }
            if (i + palindromicLength[i] > right) {
                center = i;
                right = i + palindromicLength[i];
            }
        }

        int count = 0;
        for (int c : palindromicLength) {       // count number of palindromic substrings in s
            count += (c + 1) / 2;               // each palindromic substrings contains n / 2 palindromic substrings
        }

        return count;
    }
}