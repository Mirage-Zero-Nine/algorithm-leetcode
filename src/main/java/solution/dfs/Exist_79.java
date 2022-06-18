package solution.dfs;

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

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Similar to path searching in maze, entry is the word's first char in board, exit is the last char (if exist).
     * Hence, recursively traverse each possible cell in board, if found available cell then continue searching.
     * In the end, if traverse to the end of string, return true, otherwise return false.
     *
     * @param board input 2D char board
     * @param word  input word string
     * @param i     current cell row
     * @param j     current cell column
     * @param l     current char index
     * @return true if word is found, false otherwise
     */
    private boolean dfs(char[][] board, String word, int i, int j, int l) {

        /* Check availability */
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(l)) {
            return false;
        }

        if (l == word.length() - 1 && board[i][j] == word.charAt(l)) {
            return true; // at the end of the string
        }

        char temp = board[i][j];
        board[i][j] = 0;        // marked current char as visited

        boolean exist = dfs(board, word, i + 1, j, l + 1)
                || dfs(board, word, i, j + 1, l + 1)
                || dfs(board, word, i - 1, j, l + 1)
                || dfs(board, word, i, j - 1, l + 1);

        board[i][j] = temp;     // recover char for next backtracking

        return exist;
    }

    public static void main(String[] args) {
        Exist_79 existTest = new Exist_79();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(existTest.exist(board, "ABCZZ"));
    }
}
