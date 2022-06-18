package solution.datastructure;

import java.util.LinkedList;

/**
 * Implement the StreamChecker class as follows:
 * 1. StreamChecker(words): Constructor, init the data structure with the given words.
 * 2. query(letter): returns true iff for some k >= 1, the last k characters queried spell one of the words in the list.
 * (in order from oldest to newest, including this letter just queried)
 *
 * @author BorisMirage
 * Time: 2019/08/09 20:40
 * Created with IntelliJ IDEA
 */

public class StreamChecker_1032 {
    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean isEnd;
    }

    private final LinkedList<Character> temp = new LinkedList<>();        // save part of stream
    private final TrieNode root = new TrieNode();
    private int max = 0;        // max size of list, which is the longest word in given array

    /**
     * Init of class, build a trie based on given words.
     *
     * @param words given words list
     */
    public StreamChecker_1032(String[] words) {
        buildTire(words);
    }

    /**
     * Returns true iff for some k >= 1, the last k characters queried spell one of the words in the given list.
     * (in order from oldest to newest, including this letter just queried)
     * Search from back of list to the beginning, if there is an end in trie under current char, return true.
     * Otherwise, return false.
     * Use reversed searching to assure it fits the last k chars rule.
     *
     * @param letter given letter
     * @return true if last k characters queried spell one of the words in the given list, false otherwise
     */
    public boolean query(char letter) {
        temp.add(letter);
        if (temp.size() > max) {
            temp.removeFirst();     // keep list size not larger than longest word in list to save memory
        }

        TrieNode n = root;

        for (int i = temp.size() - 1; i >= 0 && n != null; i--) {
            n = n.next[temp.get(i) - 'a'];      // find next node in trie
            if (n != null && n.isEnd) {     // if next node exist and is one word end
                return true;
            }
        }
        return false;
    }

    /**
     * Build trie. Note that it is build in reverse order, and so it is to the traversal process in query.
     *
     * @param words given word list
     */
    private void buildTire(String[] words) {
        for (String w : words) {
            max = Math.max(w.length(), max);

            TrieNode n = root;      // get root node to build trie with current word
            for (int i = w.length() - 1; i >= 0; i--) {
                char c = w.charAt(i);
                if (n.next[c - 'a'] == null) {
                    n.next[c - 'a'] = new TrieNode();
                }
                n = n.next[c - 'a'];
            }
            n.isEnd = true;
        }
    }

    public static void main(String[] args) {
        StreamChecker_1032 test = new StreamChecker_1032(new String[]{"cd", "f", "kl"});
        System.out.println(test.query('c'));
        System.out.println(test.query('d'));
        System.out.println(test.query('d'));
        System.out.println(test.query('f'));
    }
}
