package solution.slidingwindow;

/**
 * You are given two strings s and t of the same length. You want to change s to t.
 * Changing the i-th character of s to i-th character of t costs |s[i] - t[i]|.
 * You are also given an integer maxCost.
 * Return the maximum length of a substring of s that can be changed to t with a cost less than or equal to maxCost.
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 *
 * @author BorisMirage
 * Time: 2019/09/29 15:11
 * Created with IntelliJ IDEA
 */

public class EqualSubstring_1208 {
    /**
     * Sliding window.
     * Move window forward if there has the cost to be spend.
     * Otherwise, shrink window size until the total cost in window is smaller than max cost.
     * If total cost is less than 0, reset to 0.
     *
     * @param s       given string
     * @param t       given string
     * @param maxCost max cost of string
     * @return maximum length of a substring of s that can be changed to t with a cost less than or equal to maxCost
     */
    public int equalSubstring(String s, String t, int maxCost) {

        int max = 0, left = 0, current = 0;

        for (int i = 0; i < s.length(); i++) {
            current += Math.abs(s.charAt(i) - t.charAt(i));

            if (current <= maxCost) {
                max = Math.max(i - left + 1, max);
            } else {
                while (current > maxCost) {
                    current -= Math.abs(s.charAt(left) - t.charAt(left++));
                }
                if (current < 0) {
                    current = 0;
                }
            }
        }

        return max;
    }
}
