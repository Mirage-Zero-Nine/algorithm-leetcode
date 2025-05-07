package solution.dfs;

import java.util.Arrays;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The same letter cell may not be used more than once.
 * Note:
 * 1. Word can only be constructed from "adjacent" cell, which are those horizontally or vertically neighboring.
 *
 * @author BorisMirage
 * Time: 2018/08/09 15:26
 * Created with IntelliJ IDEA
 */

public class Exist_79 {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Backtracking.
     * Start from first char in word, then find adjacent cell until current cell is unavailable or all char was found.
     * Time complexity: O(m * n * 4^s).
     *
     * @param board input 2D char board
     * @param word  input word string
     * @return true if found word in board, false otherwise.
     */
    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Similar to path searching in maze, entry is the word's first char in board, exit is the last char (if exist).
     * Hence, recursively traverse each possible cell in the board, if found available cell then continue searching.
     * In the end, if traverse to the end of string, return true, otherwise return false.
     *
     * @param board input 2D char board
     * @param word  input word string
     * @param i     current cell row
     * @param j     current cell column
     * @param index current index at the given word
     * @return true if word is found, false otherwise
     */
    private boolean dfs(char[][] board, int i, int j, String word, int index) {

        if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        char tmp = board[i][j];
        board[i][j] = '#'; // mark as "visited"
        boolean found = Arrays.stream(DIRECTIONS)
                .anyMatch(dir -> dfs(board, i + dir[0], j + dir[1], word, index + 1));
        board[i][j] = tmp;
        return found;
    }
}
