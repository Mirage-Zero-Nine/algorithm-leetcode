package solution.backtracking;

/**
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * @author BorisMirage
 * Time: 2018/08/05 16:36
 * Created with IntelliJ IDEA
 */

public class TotalNQueens_52 {
    /**
     * Based on special property of queen, search can be done column by column (or row by row).
     *
     * @param n placing n queens
     * @return list contains all solutions that distinct board configuration of the n-queens' placement
     */
    public int totalNQueens(int n) {

        int[] count = {0};
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = '.';
            }
        }

        backtracking(chessBoard, 0, count);
        return count[0];
    }

    /**
     * Use backtracking to traverse each possible place in chess board
     *
     * @param chessBoard char array that represent chess board
     * @param c          column number of current position
     * @param count      counter
     */
    private void backtracking(char[][] chessBoard, int c, int[] count) {

        if (c == chessBoard.length) {       // end point
            count[0]++;
            return;
        }

        for (int i = 0; i < chessBoard.length; i++) {
            if (isValid(chessBoard, i, c)) {
                chessBoard[i][c] = 'Q';
                backtracking(chessBoard, c + 1, count);
                chessBoard[i][c] = '.';
            }
        }
    }

    /**
     * Check if current position is valid to put queen.
     * If two queen is at same diagram, then there row num and column num will:
     * 1. r1 + c2 == r2 + c1
     * 2. r1 + c1 == r2 + c2
     * And also, two position should not be in same row and column.
     * The column will not be duplicated since the backtracking process is started column by column.
     *
     * @param board chess board
     * @param r     current row
     * @param c     current column
     * @return true if valid, false otherwise
     */
    private boolean isValid(char[][] board, int r, int c) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 'Q' && (r + j == c + i || r + c == i + j || i == r)) {
                    return false;
                }
            }
        }
        return true;
    }
}
