package solution.slidingwindow;

/**
 * Given a string S and a string T.
 * Find the minimum window in S which will contain all the characters in T in complexity O(n).
 * Note:
 * 1. If there is no such window in S that covers all characters in T, return the empty string "".
 * 2. If there is such window, it is guaranteed that there will always be only one unique minimum window in S.
 *
 * @author BorisMirage
 * Time: 2019/06/18 11:25
 * Created with IntelliJ IDEA
 */

public class MinWindow_76 {
    /**
     * Sliding window to find minimum window size.
     * Try to find the minimum window by shrinking the window when all character in S which contains all character in T.
     *
     * @param s first string
     * @param t second string
     * @return minimum window in S which will contain all the characters in T
     */
    public String minWindow(String s, String t) {

        /* Corner case */
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }

        int m = s.length(), n = t.length(), start = 0, window = Integer.MAX_VALUE, rest = n, minStart = 0;
        int[] count = new int[256];

        for (int i = 0; i < n; i++) {
            count[t.charAt(i) - 'A']++;
        }

        for (int i = 0; i < m; i++) {
            if (count[s.charAt(i) - 'A']-- > 0) { // find a char in t, one less to be found
                rest--;
            }
            while (rest == 0) { // all chars were found, try to shrink the window
                if (count[s.charAt(start++) - 'A']++ == 0) { // current char in t, stop shrinking and record window size
                    rest++;
                }

                if (i - start + 1 < window) {
                    window = i - start + 1;
                    minStart = start;
                }
            }
        }

        return (window == Integer.MAX_VALUE) ? "" : s.substring(minStart - 1, minStart + window);
    }

    public static void main(String[] args) {
        MinWindow_76 test = new MinWindow_76();
        System.out.println();
    }
}
