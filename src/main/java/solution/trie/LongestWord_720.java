package solution.trie;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of strings words representing an English Dictionary.
 * Return the longest word in words that can be built one character at a time by other words in words.
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 * If there is no answer, return the empty string.
 *
 * @author BorisMirage
 * Time: 2021/11/02 00:15
 * Created with IntelliJ IDEA
 */

public class LongestWord_720 {
    /**
     * Build a trie based on given words array.
     * Then implement BFS to search the longest word, started from root.
     * There is a hidden condition in the question, the length of initial word for a valid longest word should be 1.
     * Hence, BFS worked.
     *
     * @param words given array
     * @return the longest word in words that can be built one character at a time by other words in words
     */
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for (String s : words) {
            TrieNode tmp = root;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (tmp.children[c - 'a'] == null) {
                    tmp.children[c - 'a'] = new TrieNode();
                }
                tmp = tmp.children[c - 'a'];
            }
            tmp.isEnd = true;
            tmp.word = s;
        }

        Queue<TrieNode> q = new LinkedList<>();
        q.add(root);
        String out = "";
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TrieNode n = q.poll();
                for (int j = 25; j >= 0; j--) {
                    if (n.children[j] != null && n.children[j].isEnd) {
                        q.add(n.children[j]);
                        out = n.children[j].word;
                    }
                }
            }
        }

        return out;
    }

    /**
     * TrieNode class.
     */
    static class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
        }
    }
}
