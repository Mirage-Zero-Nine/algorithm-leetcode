package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land.
 * Find a water cell such that its distance to the nearest land cell is maximized and return the distance.
 * The distance used in this problem is the Manhattan distance.
 * If no land or water exists in the grid, return -1.
 *
 * @author BorisMirage
 * Time: 2019/08/17 19:43
 * Created with IntelliJ IDEA
 */

public class MaxDistance_1162 {
    /**
     * BFS to find max reachable level.
     *
     * @param grid given grid
     * @return distance to the nearest land cell is maximized
     */
    public int maxDistance(int[][] grid) {

        /* Corner case */
        if (grid.length == 1) {
            return -1;
        }

        int n = grid.length, level = 1, max = 0;
        int[] d = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        Queue<int[]> q = new LinkedList<>();
        boolean[][] isVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    q.add(new int[]{i, j});
                    isVisited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] tmp = q.poll();
                if (tmp != null) {
                    int x = tmp[0];
                    int y = tmp[1];

                    for (int j = 0; j < 4; j++) {
                        int xx = x + d[j];
                        int yy = y + d[j + 4];

                        if (xx >= 0 && xx < n && yy >= 0 && yy < n && !isVisited[xx][yy]) {
                            isVisited[xx][yy] = true;
                            q.add(new int[]{xx, yy});
                            max = Math.max(max, level);
                        }
                    }
                }
            }
            level++;
        }
        return (max == 0) ? -1 : max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxDistance_1162().maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}));
//        System.out.println(new MaxDistance().maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    }
}
