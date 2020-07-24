package Solution.BinarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string S, find out the length of the longest repeating substring(s).
 * Return 0 if no repeating substring exists.
 * The occurrences may overlap.
 *
 * @author BorisMirage
 * Time: 2019/08/09 16:14
 * Created with IntelliJ IDEA
 */

public class LongestRepeatingSubstring_1062 {
    /**
     * Two sub tasks. First sub task is to find out max length of duplicated substring by binary search.
     * Initially, the left bound and right bound is 0 and length of string. And the mid value is search length.
     * Search for half of length first, if there is a substring, then increase the search length left bound to mid + 1.
     * Otherwise, if there is no duplicated substring, reduce right bound to mid - 1.
     * Keep this process until left > right, right will be the last possible length, since left is last edited value.
     * Second sub task is to find if there is a duplicated substring in string.
     * Calculate hash code by rolling hash to reduce time to O(1) for each string, and O(N) for whole string.
     * Time complexity : O(NlogN). O(logN) for the binary search and O(N) for Rabin-Karp algorithm.
     *
     * @param S given string
     * @return length of the longest repeating substring(s), or 0 if no repeating substring exists
     */
    public int longestRepeatingSubstring(String S) {

        /* Corner case */
        if (S.length() < 2) {
            return 0;
        }

        int base = 26, n = S.length();
        int mod = 1 << 31 - 1;

        int[] arr = new int[n];

        for (int i = 0; i < S.length(); i++) {
            arr[i] = S.charAt(i) - 'a';
        }

        int left = 0, right = n - 1;
        while (left <= right) {
            int length = left + (right - left) / 2;
            int c = hasRepeating(S, length, base, arr, mod);

            if (c == -1) {
                right--;
            } else {
                left++;
            }
        }

        int start = hasRepeating(S, right, base, arr, mod);
        return (start == -1) ? 0 : start;
    }

    /**
     * Rabin-Karp with polynomial rolling hash.
     * Search a substring of given length that occurs at least 2 times.
     * Return start position if the substring exits. If no such position, return -1.
     *
     * @param s      given string
     * @param length length of duplicated string to be found
     * @param base   base number
     * @param arr    string array
     * @param mod    module number
     * @return length of duplicated string if the duplicated substring exits, or -1 otherwise
     */
    private int hasRepeating(String s, int length, int base, int[] arr, int mod) {
        long hash = 0, al = 1;

        for (int i = 0; i < length; i++) {
            hash = (hash * base % mod + arr[i]) % mod;
            al = al * base % mod;
        }

        HashMap<Long, List<Integer>> m = new HashMap<>();
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0);       // hash code represents string starts at 0
        m.put(hash, l);

        for (int i = 1; i < s.length() - length + 1; i++) {
            hash = ((hash * base % mod - arr[i - 1] * al % mod + mod) % mod + arr[i + length - 1]) % mod;

            if (m.containsKey(hash)) {
                for (Integer h : m.get(hash)) {
                    if (s.substring(h, h + length).equals(s.substring(i, i + length))) {
                        return length;      // length of duplicated string
                    }
                }
            } else {
                m.put(hash, new ArrayList<>());
            }

            m.get(hash).add(i);
        }

        return -1;
    }
}
