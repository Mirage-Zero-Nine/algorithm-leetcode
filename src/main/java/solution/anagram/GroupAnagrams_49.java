package solution.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * strs[i] consists of lowercase English letters.
 *
 * @author BorisMirage
 * Time: 2018/06/25 10:14
 * Created with IntelliJ IDEA
 */

public class GroupAnagrams_49 {
    /**
     * Convert each word in given array to char array, then sort the char array.
     * Next, convert the char array into string. This is the key of each word in hash map.
     * If two words are anagram, then they will have same key after sorting.
     *
     * @param strs input words array
     * @return linked list that contains each group of anagrams
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        /* Corner case */
        if (strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> m = new HashMap<>();

        for (String s : strs) {

            char[] w = s.toCharArray();        // convert to char array to sort
            Arrays.sort(w);
            String key = String.valueOf(w);          // use sorted char as key in hash map

            m.putIfAbsent(key, new ArrayList<>());
            m.get(key).add(s);
        }

        return new ArrayList<>(m.values());
    }
}
