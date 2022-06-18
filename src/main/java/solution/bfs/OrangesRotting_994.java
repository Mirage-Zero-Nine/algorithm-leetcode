package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1 instead.
 *
 * @author BorisMirage
 * Time: 2019/11/21 20:16
 * Created with IntelliJ IDEA
 */

public class OrangesRotting_994 {
    /**
     * BFS flooding.
     * Add all rotten orange into queue, and add neighbor fresh orange into queue.
     * Finally, check if all fresh orange is rotten.
     * Corner case: all cells are 0 or all cells are 1.
     * If all cells are 0, then return 0.
     * If all cells are 1, then return -1.
     *
     * @param grid given grid
     * @return the minimum number of minutes that must elapse until no cell has a fresh orange
     */
    public int orangesRotting(int[][] grid) {

        /* Corner case */
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        int m = grid.length, n = grid[0].length, count = 0, size, fresh = 0;

        int[] dir = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) {
            return 0;
        }

        while (!q.isEmpty()) {
            size = q.size();

            while (size-- > 0) {
                int[] tmp = q.poll();
                for (int i = 0; i < 4; i++) {
                    int xx = tmp[0] + dir[i];
                    int yy = tmp[1] + dir[i + 4];

                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 1) {
                        q.add(new int[]{xx, yy});
                        fresh--;
                        grid[xx][yy] = 2;
                    }
                }
            }

            count++;
        }

        return fresh == 0 ? count - 1 : -1;
    }
}
