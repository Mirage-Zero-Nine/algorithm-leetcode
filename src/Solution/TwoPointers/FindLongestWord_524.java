package Solution.TwoPointers;

import java.util.List;

/**
 * Given a string and a string dictionary.
 * Find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 * If there are more than one possible results, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 *
 * @author BorisMirage
 * Time: 2020/04/06 19:48
 * Created with IntelliJ IDEA
 */

public class FindLongestWord_524 {
    /**
     * Two pointers.
     * One pointer points at the word in dictionary, and the other points the given string.
     * Note that if there is a match, then the word should be a substring in s, not a subset.
     *
     * @param s given string
     * @param d given dictionary
     * @return the longest string in the dictionary that can be formed by deleting some characters of the given string
     */
    public String findLongestWord(String s, List<String> d) {

        /* Corner case */
        if (s == null || s.length() == 0) {
            return "";
        }

        String out = "";

        for (String w : d) {
            int i = 0;
            for (char c : s.toCharArray()) {
                if (i < w.length() && c == w.charAt(i)) {
                    i++;
                }
            }

            if (i == w.length() && w.length() >= out.length()) {
                if (w.length() > out.length() || w.compareTo(out) < 0) {
                    out = w;
                }
            }
        }

        return out;
    }
}
