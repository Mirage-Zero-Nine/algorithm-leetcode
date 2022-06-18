package solution.datastructure;

import java.util.HashMap;

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
     * Initialize of Trie.
     */
    public Trie_208() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     * Iterate each char in word, if current char does not exist in children, create a new node.
     * Finally, mark this word as the end of the string for searching.
     *
     * @param word word to be inserted to trie
     */
    public void insert(String word) {
        TrieNode tmp = root;

        for (int i = 0; i < word.length(); i++) {
            if (tmp.child.get(word.charAt(i)) == null) {
                TrieNode n = new TrieNode();
                tmp.child.put(word.charAt(i), n);
            }
            tmp = tmp.child.get(word.charAt(i));
        }

        tmp.isEnd = true;
    }

    /**
     * Returns true if the word is in the trie.
     *
     * @param word given word
     * @return if word is in trie
     */
    public boolean search(String word) {
        TrieNode tmp = findEnd(word);
        return tmp != null && tmp.isEnd;
    }

    /**
     * Returns true if there exist a word in the trie that starts with the given prefix.
     *
     * @param prefix given prefix
     * @return if prefix is in trie
     */
    public boolean startsWith(String prefix) {
        return findEnd(prefix) != null;
    }

    /**
     * Find longest reachable TrieNode in trie.
     *
     * @param w given string
     * @return longest reachable TrieNode, or null if given string contains char that is not exist in trie.
     */
    private TrieNode findEnd(String w) {
        TrieNode tmp = root;

        for (int i = 0; i < w.length(); i++) {
            if (tmp.child.get(w.charAt(i)) == null) {
                return null;
            }
            tmp = tmp.child.get(w.charAt(i));
        }

        return tmp;
    }

    /**
     * Nodes in trie.
     */
    static class TrieNode {
        public HashMap<Character, TrieNode> child = new HashMap<>();
        boolean isEnd = false;

        /**
         * Constructor of TrieNode.
         */
        TrieNode() {
        }
    }

    public static void main(String[] args) {
        Trie_208 test = new Trie_208();
        test.insert("app");
        test.insert("apple");
        test.insert("beer");
        test.insert("add");
        test.insert("jam");
        test.insert("rental");
        System.out.println(test.search("app"));
        System.out.println(test.search("apps"));
        System.out.println(test.startsWith("beer"));
        test.insert("app");
        System.out.println(test.search("app"));
    }
}
