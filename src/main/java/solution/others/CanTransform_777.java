package solution.others;

/**
 * In a string composed of 'L', 'R', and 'X' characters, a move has two options:
 * 1. Replacing one occurrence of "XL" with "LX".
 * 2. Replacing one occurrence of "RX" with "XR".
 * Given the starting string start and the ending string end.
 * Return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * @author BorisMirage
 * Time: 2020/05/04 11:02
 * Created with IntelliJ IDEA
 */

public class CanTransform_777 {
    /**
     * Three conditions:
     * 1. R and L has same occurrence and order in both start and end.
     * 2. The corresponding index R in start and end (representing result of swap) i and j should follow i <= j.
     * 3. The corresponding index L in start and end (representing result of swap) i and j should follow i >= j.
     * The reason of condition 2 and 3 is that the swap rule keeps move R shift right before next L and R at its right.
     * Same reason for L, which will only shift left before next L and R at its left.
     *
     * @param start original string
     * @param end   convert string
     * @return whether there exists a sequence of moves to transform one string to the other
     */
    public boolean canTransform(String start, String end) {

        /* Corner case */
        if (start.length() != end.length() || !start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }

        int p1 = 0, p2 = 0;
        char[] s = start.toCharArray(), e = end.toCharArray();

        while (p1 < start.length()) {

            while (p1 < s.length && s[p1] == 'X') {     // start with first non-X position
                p1++;
            }
            while (p2 < e.length && e[p2] == 'X') {
                p2++;
            }

            if (p1 == s.length || p2 == e.length) {
                return true;      // if one reach the end, the other one must reach the end as well
            }
            if ((s[p1] == 'R' && p1 > p2) || (s[p1] == 'L' && p1 < p2)) {       // if they point at different char
                return false;
            }

            p1++;
            p2++;
        }

        return true;
    }
}
