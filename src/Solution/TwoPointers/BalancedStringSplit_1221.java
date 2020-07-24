package Solution.TwoPointers;

/**
 * Balanced strings are those who have equal quantity of 'L' and 'R' characters.
 * Given a balanced string s split it in the maximum amount of balanced strings.
 * Return the maximum amount of split balanced strings.
 *
 * @author BorisMirage
 * Time: 2019/10/12 21:15
 * Created with IntelliJ IDEA
 */

public class BalancedStringSplit_1221 {
    /**
     * Two pointers. Traverse string from left to right.
     * Each time, if left and right are equal, new sub string is found.
     *
     * @param s given string
     * @return maximum amount of split balanced strings
     */
    public int balancedStringSplit(String s) {

        int n = 0, left = 0, right = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                left++;
            }
            if (s.charAt(i) == 'R') {
                right++;
            }

            if (left == right) {
                n++;
                left = 0;
                right = 0;
            }
        }
        return n;
    }
}
