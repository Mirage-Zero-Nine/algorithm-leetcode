package solution.orderedmap;

import java.util.TreeSet;

/**
 * Given a string s, return the last substring of s in lexicographical order.
 *
 * @author BorisMirage
 * Time: 2020/05/05 18:58
 * Created with IntelliJ IDEA
 */

public class LastSubstring_1163 {
    /**
     * Find the largest suffix of given string in lexicographical order.
     * Encoding suffix:
     * 1. Find the number of distinct chars.
     * 2. Sort the distinct chars.
     * 3. Encode the suffix by current char's index in sorted distinct char.
     * The key of encoding is to normalize the encode value.
     * For example, string "abc"'s result should be "c". If not normalized then the output will be "abc".
     *
     * @param s given string
     * @return last substring of s in lexicographical order
     */
    public String lastSubstring(String s) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return "";
        }

        TreeSet<Character> ts = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) {
            ts.add(s.charAt(i));
        }

        int base = ts.size();       // total number of distinct char
        int start = 0;              // start of largest suffix
        double max = 0, current = 0;

        for (int i = s.length() - 1; i >= 0; i--) {     // get suffices of string
            current += ts.headSet(s.charAt(i)).size();      // encode current suffix

            if (current >= max) {       // new larger suffix is found
                max = current;
                start = i;
            }

            current /= base;        // normalize the encode value to avoid longer substring has larger encode value
        }

        return s.substring(start);
    }
}
