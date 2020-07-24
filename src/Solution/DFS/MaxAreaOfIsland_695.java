package Solution.DFS;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's connected 4-directionally.
 * You may assume all four edges of the grid are surrounded by water.
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * @author BorisMirage
 * Time: 2020/03/19 11:13
 * Created with IntelliJ IDEA
 */

public class MaxAreaOfIsland_695 {
    /**
     * If reaches a island, DFS on this island to find the area.
     * Find the max area among all islands.
     *
     * @param grid given grid
     * @return max area of island
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length, maxArea = 0;
        int[] area = new int[1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area[0] = 0;
                    dfs(grid, i, j, area);
                    maxArea = Math.max(area[0], maxArea);
                }
            }
        }

        return maxArea;
    }

    /**
     * DFS to find area of current island.
     *
     * @param grid given grid
     * @param i    current row index
     * @param j    current column index
     * @param area area of current island
     */
    private void dfs(int[][] grid, int i, int j, int[] area) {
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        area[0]++;
        grid[i][j] = 0;

        for (int[] d : directions) {
            int xx = d[0] + i;
            int yy = d[1] + j;
            if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length && grid[xx][yy] == 1) {
                dfs(grid, xx, yy, area);
            }
        }
    }
}
