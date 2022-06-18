package solution.slidingwindow;

import java.util.Arrays;

/**
 * Find the length of the longest substring T in a given string such that every character in T appears at least k times.
 *
 * @author BorisMirage
 * Time: 2020/04/11 16:24
 * Created with IntelliJ IDEA
 */

public class LongestSubstring_395 {
    /**
     * Sliding window. However, this is not a normal sliding window problem.
     * The window depends on the number of unique chars in the current window.
     * There are 26 letters in total, so the window contains the number of unique char i from 1 to 26
     * If the window contains less than i, then enlarge window.
     * If the window contains more than i, shrink the window.
     * If the window contains i unique chars and each repeats at least k times, one substring is found.
     * Finally, find the max window.
     * Time complexity: O(26n)
     *
     * @param s given string
     * @param k appears at least k times
     * @return the length of the longest substring that every character appears at least k times
     */
    public int longestSubstring(String s, int k) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] count = new int[26];
        int max = 0;

        for (int i = 1; i <= 26; ++i) {     // how many unique char in the sliding window
            Arrays.fill(count, 0);
            int left = 0, right = 0, unique = 0, repeat = 0;

            while (right < s.length()) {

                if (unique <= i) {      // if current window does not contains k unique character
                    int index = s.charAt(right) - 'a';

                    count[index]++;
                    if (count[index] == 1) {
                        unique++;
                    }
                    if (count[index] == k) {
                        repeat++;
                    }
                    right++;        // enlarge window
                } else {
                    int index = s.charAt(left) - 'a';

                    count[index]--;
                    if (count[index] == 0) {
                        unique--;
                    }
                    if (count[index] == k - 1) {
                        repeat--;
                    }
                    left++;     // shrink window
                }

                if (unique == i && repeat == unique) {      // window with i unique char and appears at least k times
                    max = Math.max(max, right - left);
                }
            }
        }

        return max;
    }
}
