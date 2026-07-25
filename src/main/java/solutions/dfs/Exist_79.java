package solutions.dfs;

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
    private final int[][] DIRECTIONS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * Tries every matching start cell and uses DFS with backtracking to find
     * {@code word}. Visited cells are marked with {@code '*'} and then restored.
     *
     * <p>Time: {@code O(m * n * 4^L)}. Space: {@code O(L)}, where {@code m * n}
     * is the board size and {@code L} is the word length.
     *
     * @param board a rectangular character grid
     * @param word  the word to search for
     * @return {@code true} if the word exists; otherwise {@code false}
     */
    public boolean exist(char[][] board, String word) {

        // corner case
        if (word == null || word.isEmpty() || board == null || board.length == 0) {
            return false;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && dfs(i, j, board, 0, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Matches {@code word[index]} at the current cell, searches its four neighbors, restores the cell before returning.
     *
     * @param i     current row
     * @param j     current column
     * @param board board being searched
     * @param index current index in {@code word}
     * @param word  the target word
     * @return {@code true} if the remaining word is found
     */
    private boolean dfs(int i, int j, char[][] board, int index, String word) {

        // check if current index is valid, and if current char matches the board
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(index) != board[i][j]) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        char current = board[i][j];
        board[i][j] = '*';
        boolean found = Arrays.stream(DIRECTIONS).anyMatch(direction -> dfs(i + direction[0], j + direction[1], board, index + 1, word));
        board[i][j] = current;

        return found;
    }
}
