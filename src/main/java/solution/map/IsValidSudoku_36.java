package solution.map;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9x9 Sudoku board is valid.
 * Only the filled cells need to be validated according to the following rules:
 * 1. Each row must contain the digits 1-9 without repetition.
 * 2. Each column must contain the digits 1-9 without repetition.
 * 3. Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * 4. The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Note:
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 *
 * @author BorisMirage
 * Time: 2018/06/21 17:09
 * Created with IntelliJ IDEA
 */

public class IsValidSudoku_36 {
    /**
     * Use a hash set to store column(char), (char)row, block(char)block as key.
     *
     * @param board input 9x9 char array as board
     * @return whether this board is valid
     */
    public boolean isValidSudoku(char[][] board) {

        /* Corner case */
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        Set<String> boardSet = new HashSet<>();     // use set to store each int in board's position

        for (int i = 0; i < 9; i++) {       // traverse each element in board
            for (int j = 0; j < 9; j++) {

                if (board[i][j] != '.') {       // check every position in board

                    String current = "(" + board[i][j] + ")";       // board record format(in String)
                    if (!boardSet.add(i + current) || !boardSet.add(current + j) || !boardSet.add(i / 3 + current + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        /* Valid Sudoku */
        IsValidSudoku_36 isValidSudoku = new IsValidSudoku_36();
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};
        System.out.println(isValidSudoku.isValidSudoku(board));
    }
}
