package solution.backtracking;

/**
 * In a gold mine grid of size m * n, each cell has an integer representing the amount of gold, 0 if it is empty.
 * Return the maximum amount of gold you can collect under the conditions:
 * 1. Every time you are located in a cell you will collect all the gold in that cell.
 * 2. From your position you can walk one step to the left, right, up or down.
 * 3. You can't visit the same cell more than once.
 * 4. Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 * @author BorisMirage
 * Time: 2019/10/05 19:53
 * Created with IntelliJ IDEA
 */

public class GetMaximumGold_1219 {
    private int max = 0;
    private final int[] direction = new int[]{1, -1, 0, 0, 0, 0, 1, -1};

    /**
     * Backtracking with pruning.
     *
     * @param grid given grid
     * @return maximum amount of gold can be collected
     */
    public int getMaximumGold(int[][] grid) {
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    isVisited[i][j] = true;
                    backtracking(i, j, grid, isVisited, grid[i][j]);
                    isVisited[i][j] = false;
                }
            }
        }

        return max;
    }

    /**
     * Backtracking to find the max value.
     *
     * @param x         coord x
     * @param y         coord y
     * @param grid      given grid
     * @param isVisited record cells visited
     * @param sum       currently total value
     */
    private void backtracking(int x, int y, int[][] grid, boolean[][] isVisited, int sum) {
        max = Math.max(sum, max);

        for (int i = 0; i < 4; i++) {
            int xx = x + direction[i];
            int yy = y + direction[i + 4];

            if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length && !isVisited[xx][yy] && grid[xx][yy] != 0) {
                isVisited[xx][yy] = true;
                backtracking(xx, yy, grid, isVisited, sum + grid[x][y]);
                isVisited[xx][yy] = false;
            }
        }
    }
}
