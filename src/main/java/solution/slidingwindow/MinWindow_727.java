package solution.slidingwindow;

/**
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such minimum-length windows, return the one with the left-most starting index.
 *
 * @author BorisMirage
 * Time: 2020/04/05 11:19
 * Created with IntelliJ IDEA
 */

public class MinWindow_727 {
    /**
     * Sliding window.
     * First, find the start position of window in s, which matches the first char in t.
     * Then find the end position of window.
     * After the window is found, try to narrow it by traverse the t and window from right to left.
     * If a smaller window is found, then set the start position of window to the smaller window's start.
     * Then start the next traverse until reaches the end of string.
     *
     * @param s given string to find substring
     * @param t given string try to be a subsequence of the substring in s
     * @return minimum-length of substring in s that t is the substring's subsequence
     */
    public String minWindow(String s, String t) {

        /* Corner case */
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int m = s.length(), n = t.length(), startS = -1, endS = 0, endT, minWindow = Integer.MAX_VALUE;
        String out = "";

        while (endS < m) {
            endT = 0;
            endS = startS + 1;

            while (endS < m && s.charAt(endS) != t.charAt(endT)) {      // find the start position of window
                endS++;
            }
            startS = endS;

            while (endS < m && endT <= n - 1) {     // find window
                if (s.charAt(endS++) == t.charAt(endT)) {
                    endT++;
                }
            }

            if (endT == n) {     // try to narrow down the window from right to left
                endT--;
                int tmp = endS - 1;

                while (endT >= 0 && tmp >= startS) {
                    if (s.charAt(tmp--) == t.charAt(endT)) {
                        endT--;
                    }
                }

                if (endT == -1) {
                    tmp++;
                }

                startS = Math.max(startS, tmp);     // set start position to the window with smaller size

                if (minWindow > (endS - startS + 1)) {      // replace only when finding a minor window
                    minWindow = (endS - startS + 1);
                    out = s.substring(startS, endS);
                }
            }
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new MinWindow_727().minWindow("cnhczmccqouqadqtmjjzl", "ccouq"));      // ccqouq
        System.out.println(new MinWindow_727().minWindow("cnhczmccqouqadqtmjjzl", "cm"));      // czm
        System.out.println(new MinWindow_727().minWindow("abcdebdde", "bde"));      // bcde
    }
}
