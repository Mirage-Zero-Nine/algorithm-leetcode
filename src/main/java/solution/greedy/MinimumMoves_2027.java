package solution.greedy;

/**
 * You are given a string s consisting of n characters which are either 'X' or 'O'.
 * A move is defined as selecting three consecutive characters of s and converting them to 'O'.
 * Note that if a move is applied to the character 'O', it will stay the same.
 * Return the minimum number of moves required so that all the characters of s are converted to 'O'.
 *
 * @author BorisMirage
 * Time: 2021/10/04 18:09
 * Created with IntelliJ IDEA
 */

public class MinimumMoves_2027 {
    /**
     * If current char is X, flip current char.
     * Move to 3 chars later since no matter what next 3 chars are, they will be flipped to O.
     *
     * @param s given string
     * @return the minimum number of moves required so that all the characters of s are converted to 'O'
     */
    public int minimumMoves(String s) {

        /* Corner case */
        if (s == null || s.length() < 3) {
            return 0;
        }

        int p = 0, count = 0;

        while (p < s.length()) {
            if (s.charAt(p) == 'X') {
                p += 3;
                count++;
            } else {
                p++;
            }
        }

        return count;
    }
}
