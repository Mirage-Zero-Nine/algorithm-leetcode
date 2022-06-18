package solution.array;

/**
 * Given a string S and a character C.
 * Return an array of integers representing the shortest distance from the character C in the string.
 *
 * @author BorisMirage
 * Time: 2020/03/05 20:55
 * Created with IntelliJ IDEA
 */

public class ShortestToChar_821 {
    /**
     * Two-passes.
     * First pass, assume the target char is at the end of string and count the position distance.
     * The target char is exist in string. Hence, the position of target character will finally be updated.
     * The second pass is to update the incorrect position in first pass.
     *
     * @param s given string
     * @param c given target char
     * @return an array of integers representing the shortest distance from the character c in the string
     */
    public int[] shortestToChar(String s, char c) {
        int n = s.length(), position = -n;      // initially assume c is at the last of string
        int[] out = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                position = i;
            }
            out[i] = i - position;
        }

        for (int i = position - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                position = i;
            }
            out[i] = Math.min(position - i, out[i]);
        }

        return out;
    }
}
