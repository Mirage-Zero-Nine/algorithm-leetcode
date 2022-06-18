package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * Surrounded regions shouldn’t be on the border, any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 *
 * @author BorisMirage
 * Time: 2019/05/01 14:28
 * Created with IntelliJ IDEA
 */

public class Solve_130 {
    /**
     * Find all 'O' on boundary of given board and relating 'O', set them to a third value.
     * Use BFS to searching relating 'O'.
     * These 'O's will not be flipped.
     * After such 'O's are all found, flip the rest 'O's.
     *
     * @param board given board
     */
    public void solve(char[][] board) {

        /* Corner case */
        if (board.length == 0) {
            return;
        }

        int[] d = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        int row = board.length - 1;
        int col = board[0].length - 1;

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if ((i == 0 || j == 0 || i == row || j == col) && board[i][j] == 'O') {
                    bfs(board, i, j, d);        // do BFS to find all 'O' on board
                }
            }
        }

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * BFS to find all cells connected to 'O' on board.
     *
     * @param board given board
     * @param i     'O' index
     * @param j     'O' index
     * @param d     directions array
     */
    private void bfs(char[][] board, int i, int j, int[] d) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        board[i][j] = '-';      // mark first
        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            for (int k = 0; k < 4; k++) {
                int xx = tmp[0] + d[k];
                int yy = tmp[1] + d[k + 4];

                if (xx >= 0 && xx < board.length && yy >= 0 && yy < board[0].length && board[xx][yy] == 'O') {
                    board[xx][yy] = '-';
                    q.add(new int[]{xx, yy});
                }
            }
        }
    }
}
