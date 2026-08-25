package solutions.design;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * A trie-backed dictionary that supports adding words and searching for literal or wildcard patterns.
 *
 * <p>Each trie node represents one character in a word. The {@code isEnd} flag distinguishes a
 * complete word from a prefix shared by a longer word. During a wildcard search, the queue stores
 * all trie nodes that match the pattern prefix processed so far.
 *
 * @author BorisMirage
 * Time: 2019/07/03 17:17
 * Created with IntelliJ IDEA
 */

public class WordDictionary_211 {
    private final TrieNode root;
    private int depth = 0;

    /**
     * Creates an empty word dictionary.
     */
    public WordDictionary_211() {
        root = new TrieNode('-', false);
    }

    /**
     * Adds a word to the dictionary.
     *
     * <p>The final node is marked after the complete path is traversed so that a word can also be
     * a prefix of another word, regardless of which word is added first.
     *
     * @param word lowercase word to add; null and empty values are ignored defensively
     */
    public void addWord(String word) {
        // LeetCode supplies non-empty words, but ignoring invalid values keeps insertion safe.
        if (word == null || word.isEmpty()) {
            return;
        }

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode next = current.map.get(ch);

            if (next == null) {
                // Reuse existing shared prefixes and create only the missing suffix.
                next = new TrieNode(ch, false);
                current.map.put(ch, next);
            }
            current = next;
        }

        // This is intentionally outside the loop: an existing prefix may itself be a word.
        current.isEnd = true;
        depth = Math.max(depth, word.length());
    }

    /**
     * Searches for a word or wildcard pattern.
     *
     * <p>A dot ({@code .}) matches exactly one lowercase letter. The search is breadth-first over
     * the trie: each queue entry is one possible node after matching the current pattern prefix.
     * The root represents the empty prefix, not a character at index {@code -1}. Therefore, when
     * {@code index == 0}, the queue contains the root and {@code word.charAt(0)} is the first
     * character to match. After processing a character, {@code index} advances and the queue holds
     * nodes reached after matching {@code word[0, index)}.
     * If the queue becomes empty before all pattern characters are processed, no trie path matches
     * the pattern prefix and the method returns {@code false}. Once the loop finishes, the queue is
     * the final set of nodes matching the entire pattern; the result is {@code true} only if at
     * least one of those nodes is marked as the end of a stored word.
     *
     * @param word lowercase search pattern containing zero or more dots
     * @return true if at least one stored word matches the pattern; false otherwise
     */
    public boolean search(String word) {
        // corner case
        if (word.length() > depth) {
            return false;
        }

        TrieNode current = root;
        Queue<TrieNode> q = new ArrayDeque<>();
        q.add(current);
        int index = 0, size = q.size();

        // the index is from 0 to word.length() - 1, while the root node is actually started from -1.
        // hence, after each loop under the index, q is storing all matching result of word[0, index]
        while (index < word.length()) {
            if (q.isEmpty()) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                current = q.poll();
                char c = word.charAt(index);
                TrieNode next = current.map.get(c);

                q.addAll(c == '.' ?
                        current.map.values() :
                        next == null ?
                                List.of() :
                                List.of(next));
            }

            size = q.size();
            index++;
        }

        // after the loop, the queue contains all possible nodes that match the last char.
        // only return true if any of the node is the end of the word.
        return q.stream().anyMatch(node -> node.isEnd);
    }

    /**
     * A trie node representing one character and all following characters.
     */
    private static class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        boolean isEnd;

        /**
         * Character represented by this node; useful when inspecting the trie.
         */
        char current;

        /**
         * Creates a trie node.
         */
        TrieNode(char current, boolean isEnd) {
            this.current = current;
            this.isEnd = isEnd;
        }
    }
}
