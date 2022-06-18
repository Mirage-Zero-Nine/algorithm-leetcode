package solution.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.
 * The occurrences may overlap.
 * Return any duplicated substring that has the longest possible length.
 * If S does not have a duplicated substring, the answer is "".
 *
 * @author BorisMirage
 * Time: 2019/08/09 10:10
 * Created with IntelliJ IDEA
 */

public class LongestDupSubstring_1044 {
    private final int base = 26;
    private final int mod = 1 << 31 - 1;      // 2^31 - 1, large prime number for module
    private int[] arr;      // pass integer array when searching instead of char

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
     * @param s given string
     * @return any duplicated substring that has the longest possible length, return "" otherwise
     */
    public String longestDupSubstring(String s) {

        int n = s.length(), left = 1, right = n - 1;
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }

        while (left <= right) {
            int length = left + (right - left) / 2;
            int startIndex = hasRepeating(s, length);

            if (startIndex == -1) {
                right = length - 1;
            } else {
                left = length + 1;
            }
        }

        int startIndex = hasRepeating(s, right);      // when while loop ended, right will be the last possible length
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + right);
    }

    /**
     * Rabin-Karp with polynomial rolling hash.
     * Search a substring of given length that occurs at least 2 times.
     * Return start position if the substring exits. If no such position, return -1.
     *
     * @param s      given string
     * @param length given length for searching
     * @return start position if the duplicated substring exits, or -1 otherwise
     */
    public int hasRepeating(String s, int length) {
        long hash = 0;         // hash code
        long aL = 1;        // a^base % modulus

        for (int i = 0; i < length; i++) {
            hash = (hash * base % mod + arr[i]) % mod;        // calculate hash code under current length for rolling hash
            aL = aL * base % mod;
        }

        HashMap<Long, List<Integer>> m = new HashMap<>();     // avoid hash collision
        m.put(hash, new ArrayList<>());
        m.get(hash).add(0);        // hash code represents string starts at 0

        for (int i = 1; i < s.length() - length + 1; i++) {
            hash = ((hash * base % mod - arr[i - 1] * aL % mod + mod) % mod + arr[i + length - 1]) % mod;     // next hash

            if (m.containsKey(hash)) {
                for (int start : m.get(hash)) {
                    if (s.substring(start, start + length).equals(s.substring(i, i + length))) {
                        return i;       // return start position if the duplicated substring exits
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
