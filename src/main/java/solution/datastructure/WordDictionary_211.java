package solution.datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or '.'.
 * A . means it can represent any one letter.
 *
 * @author BorisMirage
 * Time: 2019/07/03 17:17
 * Created with IntelliJ IDEA
 */

public class WordDictionary_211 {

    private final TrieNode root;

    /**
     * Constructor.
     */
    public WordDictionary_211() {
        this.root = new TrieNode();
    }

    /**
     * Adds a word into the data structure.
     *
     * @param word given word to be added
     */
    public void addWord(String word) {
        TrieNode temp = root;

        for (int i = 0; i < word.length(); i++) {
            if (!temp.containsChild(word.charAt(i))) {
                temp.addChild(word.charAt(i));
            }
            temp = temp.getChild(word.charAt(i));
        }

        temp.setEnd(true);
    }

    /**
     * Returns if the word is in the data structure.
     * A word could contain the dot character '.' to represent any one letter.
     *
     * @param word given word
     * @return if the word is in dictionary
     */
    public boolean search(String word) {
        return find(word.toCharArray(), 0, root);
    }

    /**
     * @param word  given word in char array
     * @param index current index in word
     * @param n     root node
     * @return if given word can be found in trie
     */
    private boolean find(char[] word, int index, TrieNode n) {

        if (index == word.length) {
            return n.isEnd();

        } else if (word[index] == '.') {

            List<TrieNode> temp = n.getAllChildren();
            for (TrieNode node : temp) {
                if (find(word, index + 1, node)) {
                    return true;
                }
            }

        } else if (n.containsChild(word[index])) {
            return find(word, index + 1, n.getChild(word[index]));
        }
        return false;
    }


    /**
     * Nodes in trie.
     */
    static class TrieNode {
        private char val;       // value of current node
        private final HashMap<Character, TrieNode> m = new HashMap<>();       // save children of current trie
        private boolean end = false;

        /**
         * Constructor.
         */
        TrieNode() {
        }

        /**
         * Set current node's value.
         *
         * @param val value to be set
         */
        void setVal(char val) {
            this.val = val;
        }

        /**
         * Add child to current node.
         *
         * @param val children value
         */
        void addChild(char val) {
            this.m.put(val, new TrieNode());
            this.m.get(val).setVal(val);
        }

        /**
         * Mark current node as end of word.
         *
         * @param b boolean to be set
         */
        void setEnd(boolean b) {
            this.end = b;
        }

        /**
         * Check if current node is an end of a word.
         *
         * @return true if current node is an end of a word, false otherwise
         */
        boolean isEnd() {
            return this.end;
        }

        /**
         * Return the child based on given value.
         *
         * @param val value of child
         * @return child node
         */
        TrieNode getChild(char val) {
            return m.get(val);
        }

        /**
         * Get value of current node.
         *
         * @return value in current node
         */
        char getVal() {
            return this.val;
        }

        /**
         * Check if given char is contained in current node's child.
         *
         * @param val given value
         * @return true if given char is in child
         */
        boolean containsChild(char val) {
            return m.containsKey(val);
        }

        List<TrieNode> getAllChildren() {
            return new ArrayList<>(m.values());
        }
    }

    public static void main(String[] args) {
        WordDictionary_211 test = new WordDictionary_211();
        test.addWord("bad");
        test.addWord("dad");
        test.addWord("mad");
        System.out.println(test.search("bad."));
        System.out.println(test.search("b.."));
        System.out.println(test.search("..."));
        System.out.println(test.search("bad"));
        System.out.println(test.search(".ad"));
        System.out.println(test.search("dap"));
    }
}

