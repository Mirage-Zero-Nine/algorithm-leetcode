package Solution.Others;

import java.util.HashMap;

/**
 * Given a pattern and a string str, find if string follows the same pattern.
 *
 * @author BorisMirage
 * Time: 2019/07/06 20:59
 * Created with IntelliJ IDEA
 */

public class WordPattern_290 {
    /**
     * Use a hash map to store pattern - string map.
     * During the traverse process, if the map does not contain patter of current string and the string is in map, return false.
     * This is to avoid string like "bbb" and has a pattern of "abb", which is obvious incorrect.
     *
     * @param pattern pattern string
     * @param str     given string to find if pattern is matched
     * @return if string follows the same pattern
     */
    public boolean wordPattern(String pattern, String str) {

        /* Corner case */
        String[] list = str.split(" ");
        if (pattern.length() != list.length) {
            return false;
        }

        HashMap<Character, String> m = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {

            if (m.containsKey(pattern.charAt(i))) {     // if found a previous pattern
                if (!m.get(pattern.charAt(i)).equals(list[i])) {
                    return false;
                }
            } else {
                if (m.containsValue(list[i])) {
                    return false;
                }
                m.put(pattern.charAt(i), list[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        WordPattern_290 test = new WordPattern_290();
        System.out.println(test.wordPattern("abba", "dog cat cat dog"));
    }
}
