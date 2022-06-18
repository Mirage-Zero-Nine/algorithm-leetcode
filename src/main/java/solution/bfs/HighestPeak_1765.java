package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 * If isWater[i][j] == 0, cell (i, j) is a land cell.
 * If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 * The height of each cell must be non-negative.
 * If the cell is a water cell, its height must be 0.
 * Any two adjacent cells must have an absolute height difference of at most 1.
 * A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.
 *
 * @author BorisMirage
 * Time: 2021/10/17 16:56
 * Created with IntelliJ IDEA
 */

public class HighestPeak_1765 {
    /**
     * Implement BFS at each water point.
     * During BFS, at each point, all its neighbors' height is 1 higher than current point.
     *
     * @param isWater given matrix
     * @return integer matrix height of size m x n where height[i][j] is cell (i, j)'s height
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    isWater[i][j] = 0;
                } else {
                    isWater[i][j] = -1;
                }
            }
        }
        int[] direction = new int[]{0, 1, 0, -1, 0};
        while (!q.isEmpty()) {

            int[] current = q.poll();
            int row = current[0], column = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = current[0] + direction[d];
                int ny = current[1] + direction[d + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && isWater[nx][ny] == -1) {
                    isWater[nx][ny] = isWater[row][column] + 1;
                    q.add(new int[]{nx, ny});
                }
            }

        }

        return isWater;
    }
}
