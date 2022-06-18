package solution.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given two strings str1 and str2 with same length, determine if str1 can be transformed into str2 by doing 0 or more conversions.
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 * Return true if and only if you can transform str1 into str2.
 *
 * @author BorisMirage
 * Time: 2019/08/10 15:13
 * Created with IntelliJ IDEA
 */

public class CanConvert_1153 {
    /**
     * Construct a graph to store the mapping function from str1 to str2.
     * If there is a node that has more than 1 outdegree, then return false.
     * Note that there should be at least one char that is not used in str1. Otherwise, the conversion is not available.
     * The reason is that if all 26 letters are used in str1, there must be a condition that convert 2 char at 1 time.
     *
     * @param s1 first given string
     * @param s2 second given string
     * @return true if and only if you can transform str1 into str2, false otherwise
     */
    public boolean canConvert(String s1, String s2) {

        /* Corner case */
        if (s1.equals(s2)) {
            return true;
        }

        HashMap<Character, Character> m = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            if (m.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i)) {
                return false;
            }
            m.put(s1.charAt(i), s2.charAt(i));
        }

        return new HashSet<>(m.values()).size() < 26;       // at least one char unused to make conversion available
    }
}
