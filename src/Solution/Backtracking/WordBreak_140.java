package Solution.Backtracking;

import java.util.*;

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
     * @param s   string
     * @param dic given word dictionary
     * @param m   hash map store previous result
     * @return all possible sentences
     */
    private List<String> backtracking(String s, List<String> dic, HashMap<String, List<String>> m) {

        if (m.containsKey(s)) {
            return m.get(s);        // avoid duplication
        }

        List<String> out = new ArrayList<>();       // save all combination under current result

        for (String w : dic) {

            if (s.startsWith(w)) {
                if (s.substring(w.length()).length() == 0) {
                    out.add(w);     // if it is last word in given string
                } else {
                    for (String subWord : backtracking(s.substring(w.length()), dic, m)) {
                        out.add(w + " " + subWord);     // find all words in substring and add to current result
                    }
                }
            }
        }

        m.put(s, out);
        return out;
    }

    public static void main(String[] args) {
        String[] wordDict = {"apple", "pen", "applepen", "pine", "pineapple"};
        List<String> l = new ArrayList<>(Arrays.asList(wordDict));

        WordBreak_140 test = new WordBreak_140();
        System.out.println(test.wordBreak("pineapplepenapple", l));
    }
}
