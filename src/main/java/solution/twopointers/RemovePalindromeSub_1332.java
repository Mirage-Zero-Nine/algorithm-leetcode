package solution.twopointers;

/**
 * Given a string s consisting only of letters 'a' and 'b'.
 * In a single step you can remove one palindromic subsequence from s.
 * Return the minimum number of steps to make the given string empty.
 * A string is a subsequence of a given string.
 * If it is generated by deleting some characters of a given string without changing its order.
 * A string is called palindrome if is one that reads the same backward as well as forward.
 *
 * @author BorisMirage
 * Time: 2020/02/04 15:32
 * Created with IntelliJ IDEA
 */

public class RemovePalindromeSub_1332 {
    /**
     * Note: remove subsequence and this string only contains a and b.
     * Therefore, if the string is not palindromic, remove all a in string, then remove all b in string.
     * All a and all b is always palindromic and it is subsequence.
     * This means the max move will always be 2.
     *
     * @param s given string
     * @return minimum number of steps to make the given string empty
     */
    public int removePalindromeSub(String s) {
        int n = s.length();

        /* Corner case */
        if (n == 0) {
            return 0;
        }
        int i = 0, j = n - 1;

        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {       // if string is not palindromic
                return 2;                               // then first remove all a, then remove all b
            }
        }

        return 1;       // original string is palindromic
    }
}