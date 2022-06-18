package solution.palindrome;

/**
 * Find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * If there does not exist a palindromic substring, then return first letter.
 *
 * @author BorisMirage
 * Time: 2018/05/12 16:43
 * Created with IntelliJ IDEA
 */

public class LongestPalindromicSubstring_5 {
    /**
     * Manacher algorithm.
     * First of all, preprocess string into a array. For instance, cbcbc -> ^#c#b#c#b#c#$.
     * Then use a array to count length of longest palindromic substring at center of each char.
     * Traverse the array, count the center of longest palindromic substring (C) and its radius (R).
     * The initially value is 0.
     * During the traverse, arr[i] equals to arr[iMirror]. iMirror is symmetric to center.
     * Three conditions arr[i] != arr[iMirror]:
     * 1. i + arr[i] > R. The max symmetric length is C + R. Check if there is update by expending from C + R.
     * 2. arr[iMirror] reaches the left bound of string. arr[iMirror] may be smaller than actual palindrome length.
     * 3. i == R. Start at 0 and expend at i to find max palindromic substring.
     *
     * @param s given string
     * @return longest palindromic substring in s
     */
    public String longestPalindrome(String s) {
        String processed = preProcess(s);         // insert char into original string to make it like "^#c#b#c#b#c#$"
        int n = processed.length();
        int[] palindromicLength = new int[n];       // length of longest palindromic substring at center of each char
        int center = 0;                     // pointing at center of palindromic substring
        int radius = 0;                     // radius of palindromic substring

        for (int i = 1; i < n - 1; i++) {
            int iMirror = 2 * center - i;

            if (radius > i) {       // max length should be smaller than radius and center, otherwise it will update
                palindromicLength[i] = Math.min(radius - i, palindromicLength[iMirror]);
            } else {
                palindromicLength[i] = 0;
            }

            while (processed.charAt(i + 1 + palindromicLength[i]) == processed.charAt(i - 1 - palindromicLength[i])) {
                palindromicLength[i]++;     // find max palindromic substring by 1 each time (naive searching)
            }

            if (i + palindromicLength[i] > radius) {        // if new palindromic substring radius is found
                center = i;                                 // update center
                radius = i + palindromicLength[i];          // update radius
            }
        }

        int maxLength = 0, centerIndex = 0;
        for (int i = 1; i < n - 1; i++) {       // find max palindromic substring center and length
            if (palindromicLength[i] > maxLength) {
                maxLength = palindromicLength[i];
                centerIndex = i;
            }
        }
        int start = (centerIndex - maxLength) / 2;

        return s.substring(start, start + maxLength);
    }

    /**
     * Preprocess string into format like "^#c#b#c#b#c#$".
     *
     * @param s given string
     * @return processed string
     */
    public String preProcess(String s) {
        int n = s.length();

        if (n == 0) {
            return "^$";
        }

        StringBuilder out = new StringBuilder("^");
        for (int i = 0; i < n; i++) {
            out.append("#").append(s.charAt(i));
        }

        out.append("#$");

        return out.toString();
    }

    /**
     * There are two conditions of palindromic substring.
     * First is that the center only contains one letter, such as "aba".
     * Second is that the center contains two letters, such as "abba".
     * Hence, Each one or two adjacent letters could be center of palindromic substring.
     *
     * @param s given string
     * @return longest palindromic substring in s
     */
    public String longestPalindromeNaive(String s) {
        if (s.length() < 2) {
            return s;
        }

        int maxStart = 0;       // return first letter if there is no palindromic substring
        int maxEnd = 0;

        for (int i = 0; i < s.length() - 1; i++) {      // traverse s to find center of Palindromic Substring

            int oddCenter = findLength(s, i, i);        // center has one letter
            int evenCenter = findLength(s, i, i + 1);       // center has two letters

            int currentLength = Math.max(oddCenter, evenCenter);

            if (currentLength > maxEnd - maxStart) {        // set max length's start and end for slicing when loop is over
                maxStart = i - (currentLength - 1) / 2;
                maxEnd = i + currentLength / 2;
            }
        }

        return s.substring(maxStart, maxEnd + 1);
    }

    /**
     * Find center of palindromic substring.
     *
     * @param s     given string
     * @param left  left bound of palindromic substring
     * @param right right bound of palindromic substring
     * @return length of palindromic substring
     */
    private int findLength(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * Dynamic programming.
     * This solution is slower than brute force approach.
     * dp[i][j]: string(j, i) is palindromic.
     * dp[i][j] = ((i - j) <= 2 || dp[i - 1][j + 1]) && s.charAt(i) == s.charAt(j)
     * 1. Only one char
     * 2. Two bound chars are equal, and string(j+1, i-1) is palindromic.
     *
     * @param s given string
     * @return longest palindromic substring in s
     */
    public String longestPalindromeDynamicProgramming(String s) {

        /* Corner case */
        if (s.length() < 2) {
            return s;
        }

        int n = s.length(), start = 0, maxLength = 1;
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                dp[i][j] = ((i - j) <= 2 || dp[i - 1][j + 1]) && s.charAt(i) == s.charAt(j);

                if (dp[i][j] && maxLength < (i - j + 1)) {
                    start = j;                  // max palindromic substring start position
                    maxLength = i - j + 1;      // max palindromic substring length
                }
            }
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 test = new LongestPalindromicSubstring_5();
        System.out.println(test.longestPalindrome("cbcbc"));
    }
}
