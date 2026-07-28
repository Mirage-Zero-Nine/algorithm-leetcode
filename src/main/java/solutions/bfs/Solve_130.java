package solutions.bfs;

import java.util.ArrayDeque;
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
     * Captures every {@code 'O'} region that is not connected to the board boundary.
     * An {@code 'O'} is safe from capture if it is on the boundary or is connected
     * horizontally or vertically to another safe {@code 'O'}. Instead of searching each
     * interior region to determine whether it is surrounded, the algorithm starts from the
     * boundary, where every safe region is guaranteed to have at least one cell.
     * The board is modified in place as follows:
     * <ol>
     *     <li>Find every {@code 'O'} on the boundary.</li>
     *     <li>Run BFS from each unvisited boundary {@code 'O'}, marking it and every connected
     *     {@code 'O'} as {@code '-'} so those cells cannot be captured.</li>
     *     <li>Scan the entire board. Any remaining {@code 'O'} is not connected to the boundary,
     *     so change it to {@code 'X'}. Change each temporary {@code '-'} mark back to
     *     {@code 'O'}.</li>
     * </ol>
     * Each cell is processed a constant number of times, so the time complexity is
     * {@code O(rows * columns)}. The BFS queue uses {@code O(rows * columns)} auxiliary space
     * in the worst case.
     *
     * @param board rectangular board to modify
     */
    public void solve(char[][] board) {

        /* Corner case */
        if (board.length == 0) {
            return;
        }

        int[] d = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        int row = board.length - 1, column = board[0].length - 1;

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= column; j++) {
                if ((i == 0 || j == 0 || i == row || j == column) && board[i][j] == 'O') {
                    bfs(board, i, j, d);        // do BFS to find all 'O' on board
                }
            }
        }

        captureUnmarkedRegions(board);
    }

    /**
     * Captures every surrounded region using recursive depth-first search.
     * <p>
     * The key observation is the same as in the BFS solution: only {@code 'O'} cells connected
     * to the boundary are safe. DFS starts from every boundary {@code 'O'} and changes all
     * reachable {@code 'O'} cells to {@code '-'}. After these safe regions are marked, every
     * remaining {@code 'O'} must be surrounded and is changed to {@code 'X'}. Finally, the
     * temporary marks are restored to {@code 'O'}.
     * <p>
     * Each cell is processed a constant number of times, so the time complexity is
     * {@code O(rows * columns)}. The recursive call stack uses
     * {@code O(rows * columns)} space in the worst case and may overflow for a very large
     * connected region; the iterative BFS solution avoids that recursion risk.
     *
     * @param board rectangular board to modify
     */
    public void solveDfs(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        int lastRow = board.length - 1;
        int lastColumn = board[0].length - 1;

        for (int row = 0; row <= lastRow; row++) {
            dfs(board, row, 0);
            dfs(board, row, lastColumn);
        }

        for (int column = 0; column <= lastColumn; column++) {
            dfs(board, 0, column);
            dfs(board, lastRow, column);
        }

        captureUnmarkedRegions(board);
    }

    /**
     * Marks the connected component containing {@code (i, j)} as boundary-safe.
     *
     * <p>Every reachable {@code 'O'} is changed to {@code '-'} before it enters the queue, which
     * prevents the same cell from being enqueued more than once.
     *
     * @param board board being searched
     * @param i     starting row
     * @param j     starting column
     * @param d     encoded offsets for the four orthogonal directions
     */
    private void bfs(char[][] board, int i, int j, int[] d) {
        Queue<int[]> q = new ArrayDeque<>();
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

    /**
     * Marks every {@code 'O'} connected to {@code (row, column)} as boundary-safe.
     *
     * @param board  board being searched
     * @param row    current row
     * @param column current column
     */
    private void dfs(char[][] board, int row, int column) {
        if (row < 0
                || row >= board.length
                || column < 0
                || column >= board[0].length
                || board[row][column] != 'O') {
            return;
        }

        board[row][column] = '-';
        dfs(board, row + 1, column);
        dfs(board, row - 1, column);
        dfs(board, row, column + 1);
        dfs(board, row, column - 1);
    }

    /**
     * Captures unmarked regions and restores cells marked as boundary-safe.
     *
     * @param board board to update
     */
    private void captureUnmarkedRegions(char[][] board) {
        for (char[] row : board) {
            for (int column = 0; column < row.length; column++) {
                if (row[column] == 'O') {
                    row[column] = 'X';
                } else if (row[column] == '-') {
                    row[column] = 'O';
                }
            }
        }
    }
}
