package solution.datastructure;

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
        // create root trie node
        root = new TrieNode('-');
    }

    /**
     * Inserts a word into the trie.
     * Iterate each char in word, if current char does not exist in children, create a new node.
     * Finally, mark this word as the end of the string for searching.
     *
     * @param word word to be inserted to trie
     */
    public void insert(String word) {
        TrieNode current = root;  // current trie node

        for (int i = 0; i < word.length(); i++) {

            int index = word.charAt(i) - 'a';
            if (current.child[index] == null) {
                current.child[index] = new TrieNode(word.charAt(i));
            }
            current = current.child[index];
        }

        current.isLastChar = true;
    }

    /**
     * Returns true if the word is in the trie.
     *
     * @param word given word
     * @return if word is in trie
     */
    public boolean search(String word) {
        TrieNode lastNode = findLastTrieNode(word);
        return lastNode != null && lastNode.isLastChar;
    }

    /**
     * Returns true if there exist a word in the trie that starts with the given prefix.
     *
     * @param prefix given prefix
     * @return if prefix is in trie
     */
    public boolean startsWith(String prefix) {
        return findLastTrieNode(prefix) != null;
    }

    /**
     * Find longest reachable TrieNode in trie.
     *
     * @param w given string
     * @return longest reachable TrieNode, or null if given string contains char that is not exist in trie.
     */
    private TrieNode findLastTrieNode(String w) {
        TrieNode current = root;

        for (int i = 0; i < w.length(); i++) {
            int index = w.charAt(i) - 'a';
            if (current.child[index] == null) {
                return null;
            }
            current = current.child[index];
        }

        return current;
    }

//    /**
//     * Nodes in trie.
//     * Hash map can be a more generic choice, but consuming more memory.
//     */
//    static class TrieNodeWithMap {
//        public HashMap<Character, TrieNode> child = new HashMap<>();
//        boolean isLastChar = false;
//
//        TrieNodeWithMap() {
//        }
//    }

    /**
     * TrieNode with array to replace hash map. Less generic but consuming less memory.
     */
    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isLastChar = false;
        char character;

        TrieNode(char c) {
            character = c;
        }
    }
}
