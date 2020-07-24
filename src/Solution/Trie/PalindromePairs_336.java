package Solution.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of unique words, find all pairs of (i, j) that the concatenation of the two words is a palindrome.
 * i.e. words[i] + words[j] is a palindrome.
 *
 * @author BorisMirage
 * Time: 2020/02/02 17:22
 * Created with IntelliJ IDEA
 */
public class PalindromePairs_336 {
    /**
     * Use a modified trie to reduce searching time complexity.
     * The specification of this trie:
     * 1. It starts at the end of word (to better search palindrome).
     * 2. Use the index of word to replace the end of word mark.
     * When searching, there are several cases that concatenation of the two words is a palindrome.
     * 1. If reaches the end of word in trie, and the rest of search word is palindrome.
     * 2. If reaches the end of search word, and rest of word in trie is palindrome.
     * 3. Both reaches the end, and their concatenation is a palindrome.
     * Therefore, the problem can be solved by build a reversed trie.
     * Each node in trie has a list contains all word that the rest part is palindrome.
     * When searching in trie, if reaches the end of one word, check if rest of word is palindrome.
     * If two words have same length, then both words will end at same time.
     *
     * @param words given words list
     * @return all pairs of (i, j) that the concatenation of the two words is a palindrome
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> out = new ArrayList<>();

        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            search(words, i, root, out);
        }

        return out;
    }

    /**
     * Build the trie.
     * The difference between the normal trie is:
     * 1. It starts at the end of word (to better search palindrome).
     * 2. Use the index of word to replace the end of word mark.
     *
     * @param root  root of trie
     * @param word  word to be added into trie
     * @param index index of word
     */
    private void addWord(TrieNode root, String word, int index) {

        for (int i = word.length() - 1; i >= 0; i--) {
            int id = word.charAt(i) - 'a';

            if (root.next[id] == null) {
                root.next[id] = new TrieNode();
            }

            if (isPalindrome(word, 0, i)) {     // if rest of word is palindrome
                root.list.add(index);       // if a searching word ends here, the concatenation of them is a palindrome
            }

            root = root.next[id];
        }

        root.list.add(index);
        root.index = index;     // end of the word
    }

    /**
     * There are several cases that concatenation of the two words is a palindrome.
     * 1. If reaches the end of word in trie, and the rest of search word is palindrome.
     * 2. If reaches the end of search word, and rest of word in trie is palindrome.
     * 3. Both reaches the end, and their concatenation is a palindrome.
     *
     * @param words search word
     * @param id    index of current word in list
     * @param root  root of trie
     * @param out   output list
     */
    private void search(String[] words, int id, TrieNode root, List<List<Integer>> out) {

        for (int i = 0; i < words[id].length(); i++) {

            if (root.index >= 0 && root.index != id && isPalindrome(words[id], i, words[id].length() - 1)) {
                out.add(Arrays.asList(id, root.index));
            }

            root = root.next[words[id].charAt(i) - 'a'];

            if (root == null) {     // no palindrome found under current word
                return;             // end current searching process
            }
        }

        for (int i : root.list) {
            if (id != i) {
                out.add(Arrays.asList(id, i));
            }
        }
    }

    /**
     * Check if part of word is palindrome.
     *
     * @param word given word
     * @param i    start index
     * @param j    end index
     * @return if given word is palindrome within the given range
     */
    private boolean isPalindrome(String word, int i, int j) {

        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Modified trie node.
     */
    private static class TrieNode {
        TrieNode[] next;
        int index;      // index in given list, add index at the end of word to avoid duplicate
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;     // initially, index is -1, and will be set if reaches the end of word
            list = new ArrayList<>();
        }
    }
}

