package Playground;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, use hash map to store char and determine if t is an anagram of s.
 *
 * @author BorisMirage
 * Time: 2018/06/26 17:32
 * Created with IntelliJ IDEA
 */

public class AnagramCheckWithHashMap {
    /**
     * In this class the method to check anagram is to use hash map to store each char's appearance.
     * This approach is slower than only array is used. However, it is more flexiable and can be used to check Unicode.
     *
     * @param s input string
     * @param t input string
     * @return whether they are anagram.
     */
    public boolean isAnagram(String s, String t) {

        /* Corner case*/
        if (s.length() != t.length()) {
            return false;
        }
        if (s.length() == 1) {
            return s.charAt(0) == t.charAt(0);
        }

        // TODO: Solve this problem with only one map.

        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }

        if (sMap.size() != tMap.size()) {
            return false;
        }

        /* Create a new array to store all keys store in map for final iteration */
        Character[] keyArray = sMap.keySet().toArray(new Character[0]);
        for (Character key : keyArray) {
            if (!tMap.containsKey(key) || !tMap.get(key).equals(sMap.get(key))) {
                return false;
            }
            tMap.remove(key);
            sMap.remove(key);
        }

        return tMap.size() == 0;
    }
}
