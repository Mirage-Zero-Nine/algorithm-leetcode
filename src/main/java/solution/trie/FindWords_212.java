package solution.trie;

import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Note:
 * 1. All inputs are consist of lowercase letters a-z.
 * 2. The values of words are distinct.
 *
 * @author BorisMirage
 * Time: 2019/07/03 22:05
 * Created with IntelliJ IDEA
 */

public class FindWords_212 {
    /**
     * Use a simplified trie that store nodes by array instead of hash map to store given words.
     * Search in board to see if board contains words from list.
     *
     * @param board given board
     * @param words words list
     * @return all words in list
     */
    public List<String> findWords(char[][] board, String[] words) {

        List<String> out = new ArrayList<>();
        TrieNode root = trie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, out);
            }
        }

        return out;
    }

    /**
     * DFS search in board.
     *
     * @param board given board
     * @param i     row
     * @param j     column
     * @param p     trie node
     * @param out   output list
     */
    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> out) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) {
            return;
        }
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            out.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) {
            dfs(board, i - 1, j, p, out);
        }
        if (j > 0) {
            dfs(board, i, j - 1, p, out);
        }
        if (i < board.length - 1) {
            dfs(board, i + 1, j, p, out);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j + 1, p, out);
        }
        board[i][j] = c;
    }

    /**
     * Simplified trie constructed by array.
     *
     * @param words given words list
     * @return new trie
     */
    private TrieNode trie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = w;
        }
        return root;
    }

    /**
     * Node in trie.
     */
    static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
