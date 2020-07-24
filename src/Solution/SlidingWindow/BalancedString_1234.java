package Solution.SlidingWindow;

/**
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 * Return the minimum length of the substring that can be replaced with any string with same length to make s balanced.
 * Return 0 if the string is already balanced.
 *
 * @author BorisMirage
 * Time: 2019/10/20 11:54
 * Created with IntelliJ IDEA
 */

public class BalancedString_1234 {
    /**
     * Sliding window. Note that substring has to be continuous.
     * Therefore, the goal of this problem is to find the min window that can make this string balanced.
     * The window has to be continuous.
     *
     * @param s given string
     * @return minimum length of the substring that can be replaced with any string with same length to make s balanced
     */
    public int balancedString(String s) {
        int[] count = new int[26];
        int start = 0, n = s.length(), ave = n / 4, min = n;

        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'A']++;
        }

        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'A']--;
            while (start < n && count['Q' - 'A'] <= ave && count['W' - 'A'] <= ave && count['E' - 'A'] <= ave && count['R' - 'A'] <= ave) {
                min = Math.min(min, i - start + 1);
                count[s.charAt(start++) - 'A']++;
            }
        }

        return min;
    }
}
