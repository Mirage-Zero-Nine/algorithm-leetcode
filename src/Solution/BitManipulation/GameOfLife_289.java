package Solution.BitManipulation;

/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors using the following four rules:
 * 1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * 2. Any live cell with two or three live neighbors lives on to the next generation.
 * 3. Any live cell with more than three live neighbors dies, as if by over-population..
 * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * @author BorisMirage
 * Time: 2019/07/01 13:19
 * Created with IntelliJ IDEA
 */

public class GameOfLife_289 {
    /**
     * Use 2 bits as state transition. First bit represents second state and second bit representing current state.
     * For instance, 01 means first state is live and second state is died.
     * Count the neighbors of current cell and find next state. Fill with number of these states.
     * Finally, traverse board and right shift one bit to remove first state, as first state is the last bit of number.
     *
     * @param board given board
     */
    public void gameOfLife(int[][] board) {
        if (board.length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = neighbors(board, i, j);

                /*
                 * According to the game rules:
                 * Case 1: board[i][j] == 1
                 * 1. neighbors are less than 2 or larger than 3 -> 2nd state is 0, which is 01, nothing changed.
                 * 2. neighbors are equal to 2 or 3 -> 2nd state is 1, which is 11, fill this cell with 3.
                 * Case 2: board[i][j] == 1
                 * 1. neighbors are equal to 3 -> 2nd state is 1, fill current cell with 2.
                 * 2. neighbors are not equal to 3 -> 2nd state is 0, nothing changed. */
                if (board[i][j] == 1 && (count == 2 || count == 3)) {
                    board[i][j] = 3;
                } else if (board[i][j] == 0 && count == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] >>= 1;      // right shift 1 bit to remove first state (last bit)
            }
        }
    }

    /**
     * Count # of live neighbors of current cell.
     *
     * @param board given boards
     * @param i     row
     * @param j     column
     * @return # of live neighbors
     */
    private int neighbors(int[][] board, int i, int j) {
        int c = 0;
        for (int k = Math.max(0, i - 1); k < Math.min(board.length, i + 2); k++) {
            for (int l = Math.max(0, j - 1); l < Math.min(board[0].length, j + 2); l++) {

                /*
                 * Count live neighbors in current state.
                 * If digits last bit is 0, then this number is converted to 1.
                 * Otherwise, & 1 convert it to 0. */
                c += board[k][l] & 1;       // // AND operation with last bit, remove all other bits
            }
        }

        return c - board[i][j];
    }
}
