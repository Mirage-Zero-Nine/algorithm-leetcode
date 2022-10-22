package solution.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words.
 * Add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * Note:
 * 1. The same word in the dictionary may be reused multiple times in the segmentation.
 * 2. You may assume the dictionary does not contain duplicate words.
 *
 * @author BorisMirage
 * Time: 2019/06/06 16:23
 * Created with IntelliJ IDEA
 */

public class WordBreak_140 {
    /**
     * Backtracking + HashMap.
     * Use hash map to store results during backtracking.
     * The key of hash map is string, and the value is all result can be formed under current given string.
     *
     * @param s        given string
     * @param wordDict given word dictionary
     * @return all possible sentences
     */
    public List<String> wordBreak(String s, List<String> wordDict) {

        return backtracking(s, wordDict, new HashMap<>());
    }

    /**
     * Backtracking with hash map to store previous result.
     * Use hash map to store previous result to avoid TLE.
     *
     * @param s    string
     * @param dict given word dictionary
     * @param map  hash map store previous result
     * @return all possible sentences
     */
    private List<String> backtracking(String s, List<String> dict, HashMap<String, List<String>> map) {

        if (map.containsKey(s)) {
            return map.get(s);        // avoid duplication
        }

        List<String> out = new ArrayList<>();       // save all combination under current result

        for (String word : dict) {

            if (s.startsWith(word)) {
                if (s.substring(word.length()).length() == 0) {
                    out.add(word);     // if it is last word in given string
                } else {
                    for (String substring : backtracking(s.substring(word.length()), dict, map)) {
                        out.add(word + " " + substring);     // find all words in substring and add to current result
                    }
                }
            }
        }

        map.put(s, out);
        return out;
    }
}
