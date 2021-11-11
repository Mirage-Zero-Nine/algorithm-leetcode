package Solution.SlidingWindow;

import java.util.HashMap;

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
     * The beginning of window is the earliest non-duplicated char in string.
     * If there is a char found in map, check the last appearance.
     * If the last appearance is in current substring, then set the start position to the next of duplicated char.
     * This excluded counting duplicated char to substring.
     * Otherwise, the duplicated char is outside the substring, do nothing.
     *
     * @param s input string
     * @return max sub-string length
     */
    public int lengthOfLongestSubstring(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> m = new HashMap<>();
        int lastNonDuplicatedChar = 0, maxWindow = 1;
        for (int i = 0; i < s.length(); i++) {
            if (m.containsKey(s.charAt(i))) {
                /*
                 * If there is a duplicated char, then the start position depends on the duplicated char position.
                 * If the last appearance of it is in current substring, set start to the next position of duplicated char.
                 * Otherwise, there is no impact for current substring. */
                lastNonDuplicatedChar = Math.max(lastNonDuplicatedChar, m.get(s.charAt(i)) + 1);
            }
            maxWindow = Math.max(i - lastNonDuplicatedChar + 1, maxWindow);
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
    }
}


