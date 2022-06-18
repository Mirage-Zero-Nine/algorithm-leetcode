package solution.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
 * We can keep "shifting" which forms the sequence:
 * "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets.
 * Group all strings that belong to the same shifting sequence.
 *
 * @author BorisMirage
 * Time: 2020/03/24 09:50
 * Created with IntelliJ IDEA
 */

public class GroupStrings_249 {
    /**
     * If two strings are under same group, then there are two conditions:
     * 1. They have same length.
     * 2. The difference between each adjacent character is identical.
     * Hence, generate a string key that contains difference between each adjacent character.
     * Use this as the key in string. The generation rule is similar to anagram.
     *
     * @param strings given list of strings
     * @return group of all strings that belong to the same shifting sequence
     */
    public List<List<String>> groupStrings(String[] strings) {

        /* Corner case */
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> m = new HashMap<>();

        for (String s : strings) {
            String key = findDiff(s);
            if (!m.containsKey(key)) {
                m.put(key, new ArrayList<>());
            }
            m.get(key).add(s);
        }

        return new ArrayList<>(m.values());
    }

    /**
     * Find the difference between each adjacent character in given string as the key of string.
     * If the difference is negative, such as z -> a, then reverse the difference to positive by adding 26.
     * Add a space as divider of difference.
     *
     * @param s given string
     * @return key of string based on difference between each adjacent character
     */
    private String findDiff(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            int diff = s.charAt(i - 1) - s.charAt(i);
            diff = (diff >= 0) ? diff : diff + 26;      // reverse difference if difference is negative
            sb.append(diff).append(" ");        // add a space as divider
        }

        return sb.toString();
    }
}
