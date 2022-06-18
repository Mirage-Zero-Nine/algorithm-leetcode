package solution.others;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' '.
 * Return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 * @author BorisMirage
 * Time: 2018/07/04 15:48
 * Created with IntelliJ IDEA
 */

public class LengthOfLastWord_58 {
    /**
     * Traverse from last to start of s and use a int to record start position.
     * When find second space, return length.
     *
     * @param s input string
     * @return length of last word
     */
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int position = s.length() - 1;
        while (position > -1 && s.charAt(position) == ' ') {
            position--;
        }
        int start = position;
        if (position > -1) {
            while (position > -1 && s.charAt(position) != ' ') {
                position--;
            }
            return start - position;
        } else {
            return 0;
        }
    }
}
