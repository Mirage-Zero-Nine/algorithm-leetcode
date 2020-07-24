package Solution.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A 2D array that contains 0, 1, 2 is given.
 * Find a point in grid to minimize the total distance from all 1 to this point.
 * Each 0 marks pass.
 * Each 1 marks start point that requires to count distance, but it CANNOT pass through.
 * Each 2 marks an obstacle that CANNOT pass through.
 * Note:
 * 1. There will be at least one building.
 * 2. If it is not possible to build such house according to the above rules, return -1.
 *
 * @author BorisMirage
 * Time: 2019/05/05 19:47
 * Created with IntelliJ IDEA
 */

public class ShortestDistance_317 {
    /**
     * If current cell is 1, then do BFS starts at current cell, calculate distance from current cell to each 0.
     * Add the distance from each 1 to each 0.
     * This value is stored in 'distance[][]'.
     * Finally, find min distance from each possible cell.
     * Time complexity: O(number of 1) * O(number of 0) ~ O(m^2n^2)
     *
     * @param grid given grid
     * @return minimal total travel distance
     */
    public int shortestDistance(int[][] grid) {

        /* Corner case */
        if (grid == null) {
            return -1;
        }
        if ((grid.length == 0 || grid[0].length == 0) || (grid.length == 1 && grid[0].length == 1)) {
            return -1;
        }

        int row = grid.length;
        int column = grid[0].length;
        int total = 0;                                  // find total 1 on grid
        int[][] distance = new int[row][column];        // distance sum
        int[][] reach = new int[row][column];           // each 0 should be capable to reach every 1 on grid

        for (int[] ints : grid) {
            for (int j = 0; j < column; j++) {
                if (ints[j] == 1) {
                    total += 1;     // find total 1 on grid
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !bfs(grid, distance, total, reach, i, j)) {
                    return -1;      // if there is a 1 can not reach any other 1 in array, then no possibility
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0 && reach[i][j] == total) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private boolean bfs(int[][] grid, int[][] distance, int total, int[][] r, int x, int y) {

        int[] d = new int[]{1, -1, 0, 0, 0, 0, 1, -1};     // directions

        int count = 0;      // count reachable 1, if count is not equal to total 1 on grid, then return false
        int level = 0;      // distance is determined by level in bfs
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];       // mark visited point

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int s = q.size();
            level++;

            for (int i = 0; i < s; i++) {       // iterate each cell under current level
                int[] tmp = q.remove();
                for (int j = 0; j < 4; j++) {

                    int xx = tmp[0] + d[j];
                    int yy = tmp[1] + d[j + 4];

                    if (xx > -1 && yy > -1 && xx < grid.length && yy < grid[0].length && !isVisited[xx][yy]) {
                        if (grid[xx][yy] == 0) {
                            distance[xx][yy] += level;      // count sum of distance from each 1
                            r[xx][yy]++;                    // 0 can be reach by current iteration
                            isVisited[xx][yy] = true;
                            q.offer(new int[]{xx, yy});
                        } else if (grid[xx][yy] == 1) {
                            count++;
                            isVisited[xx][yy] = true;
                        }
                    }
                }
            }
        }

        return count == total;
    }
}
