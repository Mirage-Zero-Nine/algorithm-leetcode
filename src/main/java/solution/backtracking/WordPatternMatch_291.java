package solution.backtracking;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Follow means a full match, that there is a bijection between a letter in pattern and a non-empty substring in str.
 *
 * @author BorisMirage
 * Time: 2019/09/10 21:00
 * Created with IntelliJ IDEA
 */
public class WordPatternMatch_291 {
    /**
     * Backtracking with hash map and hash set as pruning.
     * Each char in pattern should be mapping to one or more consecutive substring in string.
     * Therefore, each char in pattern can be mapped into a substring, if if str follows the same pattern.
     * The length is not fixed, therefore, use backtracking to try every substring and check if they can match.
     *
     * @param pattern given pattern string
     * @param str     given string
     * @return if str follows the same pattern
     */
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> m = new HashMap<>();     // pattern char -> matched substring
        HashSet<String> s = new HashSet<>();                // previous matched pattern
        return backtracking(str, pattern, m, s, 0, 0);
    }

    /**
     * Basically, each char in pattern should be mapping to one or more consecutive substring in string.
     * Therefore, backtracking all possible substring and check if they can be matched into pattern.
     * Each time in backtracking, check if current pattern char can be found in hash map.
     * If current char in pattern has been mapped, check if current string starts with this pattern.
     * Otherwise, enlarge one char in string each time and mapping it to pattern char.
     *
     * @param s   given string
     * @param p   given pattern string
     * @param m   hash map stores pattern char and corresponding string
     * @param set hash set stores each char in pattern's corresponding string
     * @param i   start index of string
     * @param j   start index of pattern
     * @return if str follows the same pattern
     */
    private boolean backtracking(String s, String p, HashMap<Character, String> m, HashSet<String> set, int i, int j) {

        /*
         * Exit point.
         * If reaches the end of string or pattern, check if both string and pattern reaches the end.
         * If so, then the string and pattern is matched.
         * If only string or only pattern reaches the end, then this is a mismatch. */
        if (i == s.length() || j == p.length()) {
            return (i == s.length() && j == p.length());
        }

        char c = p.charAt(j);

        /*
         * Check if map contains current pattern char.
         * If map contains the current pattern, then it was previously mapped to a certain substring. */
        if (m.containsKey(c)) {

            /*
             * Map stores pattern char and its corresponding substring.
             * Note that each char in pattern should be mapped to same substring, if string follows the same pattern.
             * If pattern char is found in map, then check if current string starts with previous mapped substring.
             * If so, continue backtracking and return final result (matched? Not matched?).
             * Otherwise, current pattern is not matched. Directly return false. */
            String previousPattern = m.get(c);
            return (s.startsWith(previousPattern, i)) && backtracking(s, p, m, set, i + previousPattern.length(), j + 1);
        }

        for (int k = i; k < s.length(); k++) {      // backtracking

            String matched = s.substring(i, k + 1);
            if (set.add(matched)) {     // if matched string is found in set, then it should be in map and handled
                m.put(c, matched);

                /*
                 * The string start position in backtracking should be string without current pattern.
                 * The pattern start position in backtracking should be next char in pattern. */
                if (backtracking(s, p, m, set, k + 1, j + 1)) {
                    return true;
                }
                set.remove(matched);
                m.remove(c);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new WordPatternMatch_291().wordPatternMatch("abab", "redblueredblue"));
        System.out.println(new WordPatternMatch_291().wordPatternMatch("aaaa", "asdasdasdasd"));
        System.out.println(new WordPatternMatch_291().wordPatternMatch("aabb", "xyzabcxzyabc"));
    }
}
