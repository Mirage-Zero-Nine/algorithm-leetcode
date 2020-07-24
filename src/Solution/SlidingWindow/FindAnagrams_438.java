package Solution.SlidingWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s.
 * The order of output does not matter.
 *
 * @author BorisMirage
 * Time: 2019/11/14 21:58
 * Created with IntelliJ IDEA
 */
public class FindAnagrams_438 {
    /**
     * Sliding window.
     * The window size is fixed, which is the length of pattern.
     * Each time, move right pointer first. If the new included char is in pattern, one char is found.
     * If all chars in pattern are found, one anagram is found and the start index can be added.
     * Then move the left pointer if window size is equal to pattern length.
     * If left point previously points at one char in pattern, one more char required to be found in later string.
     *
     * @param s given string
     * @param p given pattern
     * @return all the start indices of p's anagrams in s
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> out = new ArrayList<>();

        /* Corner case */
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return out;
        }

        int[] hash = new int[26];
        for (char c : p.toCharArray()) {
            hash[c - 'a']++;
        }

        int left = 0, count = p.length(), n = s.length();

        for (int i = 0; i < n; i++) {

            int leftIndex = s.charAt(left) - 'a';
            int rightIndex = s.charAt(i) - 'a';

            if (hash[rightIndex] >= 1) {        // if include a new char in pattern
                count--;        // find a new char in pattern
            }
            hash[rightIndex]--;

            if (count == 0) {       // one anagram is found
                out.add(left);
            }

            if (i - left + 1 == p.length()) {       // resize window
                if (hash[leftIndex] >= 0) {     // if found a char in pattern
                    count++;        // one more char requires to be found
                }
                hash[leftIndex]++;
                left++;     // resize window
            }
        }

        return out;
    }

    public static void main(String[] args) {
        System.out.println(new FindAnagrams_438().findAnagrams("cbaebabacd", "abc"));
        System.out.println(new FindAnagrams_438().findAnagrams("abab", "ab"));
    }
}
