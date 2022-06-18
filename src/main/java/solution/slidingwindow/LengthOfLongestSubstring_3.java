package solution.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * @author BorisMirage
 * Time: 2018/03/23 12:27
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstring_3 {
    /**
     * Sliding window. Keep a hash map to store the char-index pair.
     * Keep a sliding window, which its start is the last non-duplicate char, and the end is current char in for loop.
     * If current char is a duplicated char, check its last appearance in given string.
     * If its last appearance is in current substring, then set the start position to the next of duplicated char.
     * This excluded counting duplicated char to substring.
     * Otherwise, the duplicated char is outside the substring, in current substring there is no duplication.
     *
     * @param s input string
     * @return max sub-string length
     */
    public int lengthOfLongestSubstring(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> m = new HashMap<>();
        int windowStart = 0, maxWindow = 1;
        for (int i = 0; i < s.length(); i++) {
            if (m.containsKey(s.charAt(i))) {
                /*
                 * If there is a duplicated char, then the start position depends on the duplicated char position.
                 * If its last appearance is in current substring, set start to the next position of duplicated char.
                 * Otherwise, there is no impact for current substring. */
                windowStart = Math.max(windowStart, m.get(s.charAt(i)) + 1);
            }
            maxWindow = Math.max(i - windowStart + 1, maxWindow);
            m.put(s.charAt(i), i);
        }

        return maxWindow;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring_3 test = new LengthOfLongestSubstring_3();
        System.out.println(test.lengthOfLongestSubstring("abcabcbb"));       // 3
        System.out.println(test.lengthOfLongestSubstring("tmmzuxt"));       // 5
        System.out.println(test.lengthOfLongestSubstring("a"));         // 1
        System.out.println(test.lengthOfLongestSubstring("dvdf"));      // 3
        System.out.println(test.lengthOfLongestSubstring("cdd"));      // 2
        System.out.println(test.lengthOfLongestSubstring("abcd"));      // 4
    }
}


