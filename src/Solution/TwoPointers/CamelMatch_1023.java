package Solution.TwoPointers;

import java.util.LinkedList;
import java.util.List;

/**
 * A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query.
 * Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.
 *
 * @author BorisMirage
 * Time: 2019/07/13 10:41
 * Created with IntelliJ IDEA
 */

public class CamelMatch_1023 {
    /**
     * Use two pointers to point at pattern and string. If not matched, then return false;
     *
     * @param queries given string queries
     * @param pattern given pattern
     * @return if each string is matched with pattern
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        /* Corner case */
        if (queries.length == 0) {
            return new LinkedList<>();
        }

        LinkedList<Boolean> out = new LinkedList<>();

        for (String query : queries) {
            out.add(isMatch(query, pattern));
        }
        return out;
    }

    /**
     * Check if given string is matched to pattern.
     *
     * @param s given string
     * @param p given pattern
     * @return if they are matched
     */
    private boolean isMatch(String s, String p) {

        int j = 0;

        for (int i = 0; i < s.length(); i++) {
            if (j < p.length() && s.charAt(i) == p.charAt(j)) {
                j++;
            } else if (!Character.isLowerCase(s.charAt(i))) {       // if chars do not match and is not lowercase, return false
                return false;
            }
        }

        return j == p.length();     // avoid string has a matched part, but is longer than pattern could match
    }

    public static void main(String[] args) {
        System.out.println(new CamelMatch_1023().camelMatch(new String[]{"FooBar", "FooBarTest"}, "FB"));
    }
}
