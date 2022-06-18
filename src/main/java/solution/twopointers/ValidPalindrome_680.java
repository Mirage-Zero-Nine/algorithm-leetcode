package solution.twopointers;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * @author BorisMirage
 * Time: 2020/04/05 16:15
 * Created with IntelliJ IDEA
 */

public class ValidPalindrome_680 {
    /**
     * Check if the string itself is palindrome.
     * If not, then check if remove one char at left or right, if anyone is palindrome, return true.
     *
     * @param s given string
     * @return whether a string is palindrome that at most remove one char
     */
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int left = -1, right = s.length();

        while (++left < --right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left - 1, right) || isPalindrome(s, left, right + 1);
            }
        }

        return true;
    }

    /**
     * Check if string is palindrome under given index.
     *
     * @param s given string
     * @param i start index
     * @param j end index
     * @return if string is palindrome under given index
     */
    private boolean isPalindrome(String s, int i, int j) {
        while (++i < --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
