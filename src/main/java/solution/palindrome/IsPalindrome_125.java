package solution.palindrome;

/**
 * Determine if a string is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Empty string considered as palindrome string.
 *
 * @author BorisMirage
 * Time: 2019/03/22 18:40
 * Created with IntelliJ IDEA
 */

public class IsPalindrome_125 {
    /**
     * Traverse string with 2 indexes from start and end.
     *
     * @param s given string
     * @return is s a palindrome string
     */
    public boolean isPalindrome(String s) {

        /* Corner case*/
        if (s.length() == 0) {
            return true;
        }

        int start = 0, end = s.length() - 1;

        while (start < end) {

            if (Character.isLetterOrDigit(s.charAt(start)) && Character.isLetterOrDigit(s.charAt(end))) {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                }
                start += 1;
                end -= 1;
            } else {
                if (!Character.isLetterOrDigit(s.charAt(start))) {
                    start += 1;
                }
                if (!Character.isLetterOrDigit(s.charAt(end))) {
                    end -= 1;
                }
            }
        }
        return true;
    }
}
