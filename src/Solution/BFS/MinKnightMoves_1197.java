package Solution.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 * A knight has 8 possible moves it can make, as illustrated below.
 * Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 * Return the minimum number of steps needed to move the knight to the square [x, y].
 * It is guaranteed the answer exists.
 *
 * @author BorisMirage
 * Time: 2019/09/21 18:21
 * Created with IntelliJ IDEA
 */

public class MinKnightMoves_1197 {
    /**
     * BFS in first quadrant, since the movement of knight is symmetric.
     * The normal BFS has 4 directions, while knight move has 8.
     * The moves are symmetric. Hence, it can be assumed that the problem to be in the first quadrant.
     * Maintaining the visited state in the count matrix itself.
     * If it is visited, record the minimum number of steps taken to reach that cell.
     *
     * @param x coord x
     * @param y coord y
     * @return minimum number of steps needed to move the knight to the square [x, y]
     */
    public int minKnightMoves(int x, int y) {

        if (x == 0 && y == 0) {
            return 0;
        }

        x = Math.abs(x);
        y = Math.abs(y);
        int n = 300;
        int[][] matrix = new int[n][n];
        for (int[] arr : matrix) {
            Arrays.fill(arr, -1);
        }

        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2}, dy = {-1, 1, -2, 2, -2, 2, -1, 1};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tx = tmp[0];
            int ty = tmp[1];

            if (matrix[x][y] != -1) {
                return matrix[x][y] + 1;
            }

            for (int i = 0; i < 8; i++) {
                int xx = tx + dx[i];
                int yy = ty + dy[i];
                if (xx >= 0 && xx < n && yy >= 0 && yy < n && matrix[xx][yy] == -1) {
                    matrix[xx][yy] = matrix[tx][ty] + 1;
                    q.add(new int[]{xx, yy});
                }
            }
        }

        return matrix[x][y] + 1;
    }

    /**
     * A formula to solve this problem.
     *
     * @param x coord x
     * @param y coord y
     * @return minimum number of steps needed to move the knight to the square [x, y]
     */
    public int formula(int x, int y) {

        x = Math.abs(x);        // symmetry for axes
        y = Math.abs(y);

        if (x < y) {        // symmetry for diagonal
            int temp = x;
            x = y;
            y = temp;
        }

        if (x == 1 && y == 0) {
            return 3;
        }
        if (x == 2 && y == 2) {
            return 4;
        }

        int delta = x - y;
        if (y > delta) {
            return (int) (delta - 2 * Math.floor((float) (delta - y) / 3));
        } else {
            return (int) (delta - 2 * Math.floor((delta - y) / 4));
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinKnightMoves_1197().minKnightMoves(1, 1));
        System.out.println(new MinKnightMoves_1197().minKnightMoves(2, 1));
        System.out.println(new MinKnightMoves_1197().minKnightMoves(5, 5));
    }
}
