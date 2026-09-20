package solutions.dfs;

import java.util.ArrayDeque;

/**
 * Given two m x n binary matrices grid1 and grid2 containing only 0's and 1's.
 * An island is a group of 1's connected 4-directionally (horizontal or vertical).
 * Any cells outside of the grid are considered water cells.
 * An island in grid2 is a sub-island if there is an island in grid1 contains all the cells making up in grid2.
 * Return the number of islands in grid2 that are considered sub-islands.
 *
 * @author BorisMirage
 * Time: 2021/06/22 22:03
 * Created with IntelliJ IDEA
 */

public class CountSubIslands_1905 {
    /**
     * DFS in second grid. If found an island in grid2, then check if it is a sub-island in grid1.
     * Visited land cells in {@code grid2} are changed to water; {@code grid1} is not modified.
     * The traversal takes O(mn) time and uses O(mn) auxiliary space in the worst case.
     *
     * @param grid1 first grid
     * @param grid2 second grid
     * @return number of islands in grid2 that are considered sub-islands
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length, n = grid1[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // search based on the other map
                if (grid2[i][j] == 1) {
                    if (isSubisland(grid1, grid2, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /**
     * Iterative DFS to reach every cell of the current island in grid2.
     * Search will start at each valid (within grid2 boundary and grid2[i][j] == 1).
     * If current position is invalid, then current search ends.
     * Otherwise, search 4 other directions.
     * Finally, if grid1[i][j] == 1, then current position is a valid part of subisland.
     * The explicit stack keeps the traversal within the problem's 500 x 500 limit
     * without depending on the Java call stack for a large connected island.
     *
     * @param grid1 first grid
     * @param grid2 second grid
     * @param i     index i of current cell
     * @param j     index j of current cell
     * @return if current position is a valid part of subisland
     * @implNote The supplied {@code grid2} is consumed by changing every visited land cell to 0.
     */
    private boolean isSubisland(int[][] grid1, int[][] grid2, int i, int j) {
        int m = grid1.length, n = grid1[0].length;

        // search starts at a valid position for both grids
        // if current position is invalid (out of boundary or not a land in grid2, then current search end)
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] == 0) {
            return true;
        }

        boolean isSubisland = true;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        stack.push(new int[]{i, j});
        grid2[i][j] = 0;
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int row = cell[0];
            int column = cell[1];
            // Every grid2 land cell must also be land in grid1.
            if (grid1[row][column] != 1) {
                isSubisland = false;
            }
            for (int[] direction : directions) {
                int nextRow = row + direction[0];
                int nextColumn = column + direction[1];
                if (nextRow >= 0 && nextRow < m && nextColumn >= 0 && nextColumn < n
                        && grid2[nextRow][nextColumn] == 1) {
                    // Mark on push so each cell enters the stack once.
                    grid2[nextRow][nextColumn] = 0;
                    stack.push(new int[]{nextRow, nextColumn});
                }
            }
        }

        return isSubisland;
    }
}
