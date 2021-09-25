package Solution.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a string, s, and a list of words, words, that are all of the SAME LENGTH.
 * Find all starting indices of substring(s) in s
 * These indices are a concatenation of each word in words exactly once and without any intervening characters.
 *
 * @author BorisMirage
 * Time: 2018/06/17 17:55
 * Created with IntelliJ IDEA
 */

public class FindSubstring_30 {

    private final TrieNode root = new TrieNode();
    private final HashMap<String, Integer> wordCount = new HashMap<>();

    /**
     * Keep a trie to store all words.
     * Traverse the string, if found a char in the matches any first word in trie, search the string if it is a word.
     * If current substring is a word, check if it has been found too many in string.
     * If all words in substring was found with correct count, put index to the output list.
     * The time complexity is O(n) * k, where k is the length of each word (all words have same length).
     * Not sure why it runs slow on LeetCode.
     *
     * @param s     given string
     * @param words list of words
     * @return all starting indices of substring(s) in s
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> output = new ArrayList<>();
        if (s == null || words == null || s.length() == 0 || words.length == 0) {
            return output;
        }

        for (String w : words) {
            wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
        }

        buildTrie(words);

        for (int i = 0; i < s.length(); i++) {
            if (root.next[s.charAt(i) - 'a'] != null) {
                if (isConcatenation(s, i, words)) {
                    output.add(i);
                }
            }
        }

        return output;
    }

    /**
     * Check if substring is a valid concatenation.
     * A valid concatenation:
     * 1. Substring contains all words, without any intervening characters.
     * 2. Concatenation of each word in words exactly once.
     *
     * @param s     given string
     * @param index start index
     * @param words given words array
     * @return if substring is a valid concatenation
     */
    private boolean isConcatenation(String s, int index, String[] words) {
        HashMap<String, Integer> foundCount = new HashMap<>();
        for (int i = 0; i < words.length; i++) { // try to find each word in substring
            TrieNode current = root;
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < words[0].length(); j++) { // each word has same length

                if (index >= s.length() || current.next[s.charAt(index) - 'a'] == null) {
                    return false;
                }
                current = current.next[s.charAt(index) - 'a'];
                sb.append(s.charAt(index++));
            }
            String word = sb.toString();

            int found = foundCount.getOrDefault(word, 0) + 1;
            foundCount.put(word, found);
            if (found > wordCount.get(word)) { // if too many words were found, then it is not a valid concatenation
                return false;
            }
        }

        return true;
    }

    /**
     * Trie node.
     */
    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
    }

    /**
     * Build trie with given words array.
     *
     * @param words given array
     */
    private void buildTrie(String[] words) {
        for (String w : words) {
            TrieNode current = root;
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (current.next[c - 'a'] == null) {
                    current.next[c - 'a'] = new TrieNode();
                }
                current = current.next[c - 'a'];
            }
        }
    }

    public static void main(String[] args) {

        /* Substring with Concatenation of All Words */
        String s = "barfoothefoobarman";
        String[] words = {"bar", "foo"};

        final long startTime = System.currentTimeMillis();  // Timer

        FindSubstring_30 findSubstringTest = new FindSubstring_30();
        System.out.println(findSubstringTest.findSubstring(s, words));

        final long endTime = System.currentTimeMillis();    // Timer
        System.out.println("Time: " + (endTime - startTime) + "ms");

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word", "good", "best", "word"};
        System.out.println(findSubstringTest.findSubstring(s, words));
    }
}
