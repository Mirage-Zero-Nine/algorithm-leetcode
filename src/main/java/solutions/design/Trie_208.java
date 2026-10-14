package solutions.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a trie with insert, search, and startsWith methods.
 *
 * @author BorisMirage
 * Time: 2019/07/03 15:09
 * Created with IntelliJ IDEA
 */
public class Trie_208 {
    private final TrieNode root;

    /**
     * Creates a trie whose character maps share prefix paths. Each node's end flag
     * marks an inserted word independently of its children. Maps store only used
     * edges, trading fixed child-array slots for hashing and map-entry overhead.
     *
     * <p>LeetCode inputs contain only lowercase English letters, have lengths from
     * 1 to 2,000, and make at most 30,000 calls across the three operations.</p>
     *
     * <p>Operations take expected O(L) time for input length L. Retained storage is
     * O(S + 1) for S inserted characters; shared prefixes reduce the node count.</p>
     *
     * <p>Time complexity: O(1), creating only the root and its empty child map.</p>
     * <p>Auxiliary space complexity: O(1); the empty trie also retains O(1) storage.</p>
     */
    public Trie_208() {
        root = new TrieNode();
    }

    /**
     * Reuses the word's existing prefix and creates only missing nodes. Marking the
     * final node records the word without marking its proper prefixes. Duplicate
     * insertions have no effect; null and empty words are ignored.
     *
     * <p>Time complexity: O(L) expected amortized, where L is the word length;
     * one map operation per character, including map growth.</p>
     * <p>Auxiliary space complexity: O(1) for iterative traversal, excluding up to
     * O(L) newly retained nodes and edges. The input is unchanged.</p>
     *
     * @param word word to insert
     */
    public void insert(String word) {
        // Empty input must not mark the root as a stored word.
        if (word == null || word.isEmpty()) {
            return;
        }
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // Reusing an existing edge preserves every word sharing this prefix.
            current = current.children.computeIfAbsent(c, _ -> new TrieNode());
        }
        // A prefix becomes a word only when an insertion ends at this node.
        current.isEnd = true;
    }

    /**
     * Follows the word's path and checks its end flag: an existing prefix alone is
     * not a stored word. Null and empty words return false. The trie is unchanged.
     *
     * <p>Time complexity: O(L) expected for word length L, with one map lookup per
     * character until the path ends.</p>
     * <p>Auxiliary space complexity: O(1), using iterative lookup.</p>
     *
     * @param word complete word to look up
     * @return true only if this nonempty word was previously inserted
     */
    public boolean search(String word) {
        TrieNode n = find(word);
        return n != null && n.isEnd;
    }

    /**
     * Checks whether the prefix's path exists. Every path comes from an insertion,
     * so no end flag is needed. Null and empty prefixes return false; the trie is
     * unchanged.
     *
     * <p>Time complexity: O(L) expected for prefix length L, with one map lookup per
     * character until the path ends.</p>
     * <p>Auxiliary space complexity: O(1), using iterative lookup.</p>
     *
     * @param prefix nonempty prefix to look up
     * @return true if at least one inserted word begins with the prefix
     */
    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    /**
     * Follows one edge per character, stopping when an edge is missing. Ignores end
     * flags so word and prefix queries can share traversal without changing the trie.
     *
     * <p>Time complexity: O(L) expected for string length L, with one map lookup per
     * character; null or empty input takes O(1).</p>
     * <p>Auxiliary space complexity: O(1), using a node reference and index.
     * The returned node already belongs to the trie.</p>
     *
     * @param word string whose complete path is needed
     * @return final existing node, or null for a missing path, null, or empty input
     */
    private TrieNode find(String word) {
        // The root is a structural starting point, not a match for an empty query.
        if (word == null || word.isEmpty()) {
            return null;
        }

        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.children.get(word.charAt(i));
            if (current == null) {
                // A missing edge rules out this prefix; later characters cannot repair it.
                return null;
            }
        }
        return current;
    }

    /**
     * Stores whether its root-to-node path is a word and the existing next-character
     * edges. A word ending can still have children for longer inserted words.
     */
    private static class TrieNode {
        boolean isEnd;
        Map<Character, TrieNode> children;

        /**
         * Creates a node with no children or word ending.
         *
         * <p>Time complexity: O(1), initializing an empty child map.</p>
         * <p>Auxiliary space complexity: O(1); the node and map retain O(1) storage.</p>
         */
        TrieNode() {
            children = new HashMap<>();
        }
    }
}
