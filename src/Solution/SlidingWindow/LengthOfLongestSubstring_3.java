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
     * Sliding window problem.
     * The begin of sliding window should be the next index of last duplicated character.
     * Each time, right pointer is trying to enlarge window size by one.
     * If it contains a duplicated char, then the left bound should be set to largest index without duplicated char.
     * The largest index of current char is saved in map, and the window without duplicated one should be next of it.
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
        int last = -1;      // index of latest duplicated character
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (m.containsKey(s.charAt(i))) {       // if current char is duplicated
                last = Math.max(last, m.get(s.charAt(i)));      // update sliding window to later one of duplicated char
            }

            m.put(s.charAt(i), i);
            max = Math.max(i - last, max);
        }

        return max;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring_3 test = new LengthOfLongestSubstring_3();
        System.out.println(test.lengthOfLongestSubstring("abcabcbb"));       // 3
        System.out.println(test.lengthOfLongestSubstring("tmmzuxt"));       // 5
        System.out.println(test.lengthOfLongestSubstring("a"));
        System.out.println(test.lengthOfLongestSubstring("dvdf"));      // 3
    }
}


