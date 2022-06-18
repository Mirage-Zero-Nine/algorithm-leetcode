package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board.
 * Directions are alternating each row.
 * For example, for a 6 x 6 board, the numbers are written as follows:
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,35,-1,-1,13,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,15,-1,-1,-1,-1]
 * You start on square 1 of the board (which is always in the last row and first column).
 * Each move, starting from square x, consists of the following:
 * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
 * If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
 * A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c].
 * Note that you only take a snake or ladder at most once per move:
 * If the destination to a snake or ladder is the start of another snake or ladder, you do not continue moving.
 * Return the least number of moves required to reach square N*N.
 * If it is not possible, return -1.
 *
 * @author BorisMirage
 * Time: 2019/10/24 13:35
 * Created with IntelliJ IDEA
 */
public class SnakesAndLadders_909 {
    /**
     * BFS. Each time, add all possible next cell into queue (as layer in BFS).
     * If any node reaches the target, return the layer number.
     *
     * @param board given 2D board
     * @return least number of moves required to reach square N*N. If it is not possible, return -1
     */
    public int snakesAndLadders(int[][] board) {
        int r = board.length, c = board[0].length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[r * c + 1];
        q.add(1);
        visited[1] = true;
        int step = 1, size = q.size(), end = r * c;

        while (!q.isEmpty()) {
            for (int i = 0; i < size; i++) {
                int tmp = q.poll();
                for (int j = 0; j < 6; j++) {       // x + 1, x + 2, ..., x + 6
                    int next = tmp + j + 1;
                    int[] position = numberToPosition(board, next);
                    if (board[position[0]][position[1]] > 0) {
                        next = board[position[0]][position[1]];
                    }
                    if (next == end) {
                        return step;
                    }
                    if (!visited[next]) {
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }
            size = q.size();
            step++;
        }

        return -1;
    }

    /**
     * Convert the number of cell to 2D board position.
     * The last row, which is the start position, will always
     *
     * @param board given 2D board
     * @param num   number of current cell
     * @return position on board based on # of cell
     */
    private int[] numberToPosition(int[][] board, int num) {
        int n = board.length;

        int row = (num - 1) / n, col = (num - 1) % n;   // find the row and col #
        int x = n - 1 - row;
        int y = row % 2 == 0 ? col : n - 1 - col;       // directions are alternating each row

        return new int[]{x, y};
    }

    /**
     * Although this method is not used in the problem, it is still worthy to record the conversion.
     * Convert 2D board position to the number of cell.
     *
     * @param board    given 2D board
     * @param position position of cell on board
     * @return number of cell
     */
    private int positionToNumber(int[][] board, int[] position) {
        int n = board.length;

        int row = (n - 1 - position[0]);
        int y = row % 2 == 0 ? position[1] + 1 : n - position[1];

        return row * n + y;
    }
}
