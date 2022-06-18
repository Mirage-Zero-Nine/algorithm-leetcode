package solution.greedy;

import java.util.*;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 * There is only lower case English letters in both s and t.
 * t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
 * A subsequence of a string is string which is formed from the original string.
 * It is formed by deleting 0 or more characters without disturbing the relative positions of the remaining characters.
 * i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * @author BorisMirage
 * Time: 2019/06/30 13:57
 * Created with IntelliJ IDEA
 */

public class IsSubsequence_392 {
    /**
     * Greedy. Each time check if current char is char in string s (in order).
     * If t contains all char in s and in s's order, then return true.
     *
     * @param s given subsequence
     * @param t given string
     * @return if s is subsequence of t
     */
    public boolean isSubsequence(String s, String t) {

        /* Corner case */
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0 || s.length() > t.length()) {
            return false;
        }

        int p = 0;

        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(p)) {
                p++;
            }
            if (p == s.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Use buckets to store the index and for later checking.
     * If s[i] matches t[j], then s[i+1] should match a character in t with index bigger than j.
     * This can be reduced to find the first element larger than a value in an sorted array (find upper bound).
     * Can be achieved by binary search.
     *
     * @param s given subsequence
     * @param t given string
     * @return if s is subsequence of t
     */
    @SuppressWarnings("unchecked")
    public boolean bucket(String s, String t) {

        List<Integer>[] index = new List[256];
        for (int i = 0; i < t.length(); i++) {
            if (index[t.charAt(i)] == null) {
                index[t.charAt(i)] = new ArrayList<>();
            }
            index[t.charAt(i)].add(i);
        }

        int previous = 0;
        for (int i = 0; i < s.length(); i++) {
            if (index[s.charAt(i)] == null) {
                return false;
            }
            int j = Collections.binarySearch(index[s.charAt(i)], previous);     // binary search to find insert position
            if (j < 0) {
                j = -j - 1;
            }
            if (j == index[s.charAt(i)].size()) {
                return false;
            }
            previous = index[s.charAt(i)].get(j) + 1;
        }

        return true;
    }
}
