package solution.dfs;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words.
 * Determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * @author BorisMirage
 * Time: 2019/12/27 11:22
 * Created with IntelliJ IDEA
 */

public class FindAllConcatenatedWordsInADict_472 {
    /**
     * Sort the word list, then from shortest word, check if current word can be formed by shorter words.
     * The sub-problem is actually word break I.
     * Time complexity: O(n * m ^ 2), where n is the length of list, m is the length of longest word in given list.
     *
     * @param words given words list
     * @return list of words can be formed by other words in list
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        Arrays.sort(words, Comparator.comparingInt(String::length));

        List<String> out = new ArrayList<>();
        HashSet<String> dictionary = new HashSet<>();       // store all possibles words that can form longer words

        for (String w : words) {

            if (dictionary.size() != 0 && wordBreak(w, dictionary, new int[w.length()], 0)) {       // word break
                out.add(w);
            }

            dictionary.add(w);      // previous shorter word may form later longer words
        }

        return out;
    }

    /**
     * Use DFS with memorization to find if s can be segmented. Use an integer array as memorization to avoid TLE.
     * Time complexity: O(m ^ 2), where m is the length of word.
     *
     * @param s          given string
     * @param dictionary given dictionary
     * @param mem        memorization array
     * @param start      start index
     * @return if s can be segmented into a space-separated sequence of one or more dictionary words
     */
    private boolean wordBreak(String s, HashSet<String> dictionary, int[] mem, int start) {

        if (dictionary.contains(s)) {
            return true;
        }

        if (mem[start] != 0) {
            return mem[start] == 1;
        }

        for (int i = 0; i < s.length(); i++) {
            if (dictionary.contains(s.substring(i)) && wordBreak(s.substring(0, i), dictionary, mem, i)) {
                mem[i] = 1;
                return true;
            }
        }
        mem[start] = -1;

        return false;
    }
}
