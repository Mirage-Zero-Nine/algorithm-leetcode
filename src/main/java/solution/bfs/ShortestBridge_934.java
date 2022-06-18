package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a given 2D binary array grid, there are two islands.
 * An island is a 4-directionally connected group of 1s not connected to any other 1s.
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 * @author BorisMirage
 * Time: 2021/11/14 22:10
 * Created with IntelliJ IDEA
 */

public class ShortestBridge_934 {

    private final static int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int m;
    private static int n;

    /**
     * Implement a DFS to find the first island.
     * Then implement BFS to find the shortest distance from first island to second island.
     *
     * @param grid given grid
     * @return the smallest number of 0s that must be flipped
     */
    public int shortestBridge(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean found = false;
        for (int i = 0; i < m && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, q);
                    found = true;
                }
            }
        }

        return bfs(grid, q);
    }

    /**
     * BFS to find the shortest distance from first island to second island.
     *
     * @param grid given matrix
     * @param q    queue stores all the cells of first island
     * @return minimum length from first island to second island
     */
    private int bfs(int[][] grid, Queue<int[]> q) {
        int distance = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] current = q.poll();
                for (int[] d : directions) {
                    int xx = current[0] + d[0];
                    int yy = current[1] + d[1];
                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 1) {
                        return distance;
                    }
                    if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 0) {
                        q.add(new int[]{xx, yy});
                        grid[xx][yy] = -1;
                    }
                }
            }
            distance++;
        }

        return distance;
    }


    /**
     * DFS to find all cells in first island.
     *
     * @param grid given grid
     * @param x    current coord x
     * @param y    current coord y
     * @param q    queue stores all elements in first island
     */
    private void dfs(int[][] grid, int x, int y, Queue<int[]> q) {
        grid[x][y] = -1;
        q.add(new int[]{x, y});

        for (int[] d : directions) {
            int xx = x + d[0];
            int yy = y + d[1];
            if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 1) {
                dfs(grid, xx, yy, q);
            }
        }
    }
}
