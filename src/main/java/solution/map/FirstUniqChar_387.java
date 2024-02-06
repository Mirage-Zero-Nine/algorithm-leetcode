package solution.map;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * @author BorisMirage
 * Time: 2019/07/19 17:01
 * Created with IntelliJ IDEA
 */

public class FirstUniqChar_387 {
    /**
     * Use a char array as map.
     *
     * @param s given string
     * @return first non-repeating character's index
     */
    public int firstUniqChar(String s) {

        int[] map = new int[26];

        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Use a linked hash map to store char and its frequency.
     * If a char occurred twice, remove it from map.
     *
     * @param s given string
     * @return first non-repeating character's index
     */
    public int linkedHashMap(String s) {
        if (s == null) {
            return -1;
        }

        if (s.length() == 1) {
            return 0;
        }

        Map<Character, Integer> m = new LinkedHashMap<>();
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                m.put(s.charAt(i), i);
            } else {
                m.remove(s.charAt(i));
            }
        }

        return (!m.isEmpty()) ? m.entrySet().iterator().next().getValue() : -1;
    }

    public static void main(String[] args) {
        FirstUniqChar_387 test = new FirstUniqChar_387();
        System.out.println(test.firstUniqChar("statistics"));
    }
}
