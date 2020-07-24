package Solution.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Placing n queens on an n × n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement.
 * 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * @author BorisMirage
 * Time: 2018/07/10 22:26
 * Created with IntelliJ IDEA
 */

public class SolveNQueens_51 {

    /**
     * Based on special property of queen, search can be done column by column (or row by row).
     *
     * @param n placing n queens
     * @return list contains all solutions that distinct board configuration of the n-queens' placement
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] chessBoard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessBoard[i][j] = '.';
            }
        }

        backtracking(res, chessBoard, 0);
        return res;
    }

    /**
     * Use backtracking to traverse each possible place in chess board
     *
     * @param res        result list
     * @param chessBoard char array that represent chess board
     * @param c          column number of current position
     */
    private void backtracking(List<List<String>> res, char[][] chessBoard, int c) {
        if (c == chessBoard.length) {
            res.add(output(chessBoard));
        }
        for (int i = 0; i < chessBoard.length; i++) {
            if (isValid(chessBoard, i, c)) {
                chessBoard[i][c] = 'Q';
                backtracking(res, chessBoard, c + 1);
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
     *
     * @param temp chess board
     * @param r    current row
     * @param c    current column
     * @return true if valid, false otherwise
     */
    private boolean isValid(char[][] temp, int r, int c) {
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < c; j++) {
                if (temp[i][j] == 'Q' && (r + j == c + i || r + c == i + j || i == r)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Convert 2D char array into string list for final output.
     *
     * @param board chess board
     * @return ListNode contains output chess board
     */
    private List<String> output(char[][] board) {
        List<String> res = new LinkedList<>();
        for (char[] aBoard : board) {
            String s = new String(aBoard);
            res.add(s);
        }
        return res;
    }
}
