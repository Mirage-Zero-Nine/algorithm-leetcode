package solution.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's connected 4-directionally.
 * You may assume all four edges of the grid are surrounded by water.
 * Count the number of distinct islands.
 * An island is considered to be distinct iff one island can be translated (not rotated or reflected) to the other.
 *
 * @author BorisMirage
 * Time: 2020/02/22 10:07
 * Created with IntelliJ IDEA
 */

public class NumDistinctIslands_694 {
    /**
     * Modified DFS. To find the distinct islands, record each DFS move by saving directions in each move.
     * Use a hash set to save all islands with move.
     * If two islands are distinct, then the move will be same, since the order of DFS move is identical.
     *
     * @param grid given grid
     * @return the number of distinct islands
     */
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<String> set = new HashSet<>();
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        char[] directionChar = new char[]{'u', 'd', 'l', 'r'};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("o");                         // original start point
                    dfs(grid, i, j, sb, directions, directionChar);
                    set.add(sb.toString());
                }
            }
        }

        return set.size();
    }

    /**
     * DFS search to find all distinct islands.
     *
     * @param grid          given grid
     * @param i             current cell row
     * @param j             current cell column
     * @param sb            string builder to record each move during DFS
     * @param directions    directions integer array
     * @param directionChar directions character (u for upper, d for down, etc.)
     */
    private void dfs(int[][] grid, int i, int j, StringBuilder sb, int[][] directions, char[] directionChar) {

        int m = grid.length, n = grid[0].length;
        grid[i][j] = 0;
        for (int k = 0; k < directions.length; k++) {
            int xx = i + directions[k][0];
            int yy = j + directions[k][1];
            if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == 1) {
                sb.append(directionChar[k]);
                dfs(grid, xx, yy, sb, directions, directionChar);
                sb.append("b");     // move back should be recorded
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0}
        }));      // 2
        System.out.println(new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {0, 0, 1},
                {0, 0, 1},
                {1, 1, 0}
        }));      // 2
        System.out.println(new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        }));      // 1
        System.out.println(new NumDistinctIslands_694().numDistinctIslands(new int[][]{
                {0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                {0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}
        }));      // 15
    }
}
