package playground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Generate anagram of given string.
 *
 * @author BorisMirage
 * Time: 6/26/18 17:10
 * Created with IntelliJ IDEA
 */

public class Anagram {
    private final String find;

    /**
     * Find anagrams based on given string s.
     *
     * @param s given string
     */
    public Anagram(String s) {
        find = s;
    }

    /**
     * Find anagrams via backtracking.
     *
     * @return anagrams store in list
     */
    public List<String> findAnagram() {
        List<String> res = new LinkedList<>();

        /* Corner case */
        if (find.length() < 2) {
            return res;
        }
        char[] charArray = find.toCharArray();
        Arrays.sort(charArray);
        backtracking(charArray, new ArrayList<>(), res, new boolean[charArray.length]);

        return res;
    }

    /**
     * Use backtracking to find all anagrams of given string.
     *
     * @param charArray given string into char array
     * @param cache     temp list
     * @param res       result anagram list
     * @param used      positions have been visited
     */
    private void backtracking(char[] charArray, List<Character> cache, List<String> res, boolean[] used) {
        if (cache.size() == charArray.length) {
            StringBuilder temp = new StringBuilder();
            for (Character ch : cache) {
                temp.append(ch);
            }
            res.add(temp.toString());
        } else {
            for (int i = 0; i < charArray.length; i++) {
                if (used[i] || i > 0 && charArray[i] == charArray[i - 1] && !used[i - 1]) {
                    continue;
                }
                cache.add(charArray[i]);
                used[i] = true;
                backtracking(charArray, cache, res, used);
                used[i] = false;
                cache.remove(cache.size() - 1);
            }
        }
    }
}
