package Solution.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 *
 * @author BorisMirage
 * Time: 2019/07/17 12:45
 * Created with IntelliJ IDEA
 */
public class ShortestPathBinaryMatrix_1091 {
    /**
     * The start location and end location is fixed, and the direction is fixed. Hence, use BFS instead of DFS.
     *
     * @param grid given grid
     * @return shortest path length
     */
    public int shortestPathBinaryMatrix(int[][] grid) {

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};

        if (grid[0][0] == 1 || grid[grid.length - 1][grid.length - 1] == 1) {
            return -1;
        }

        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int[] pop = queue.remove();

                if (pop[0] == pop[1] && pop[1] == grid.length - 1) {
                    return ans + 1;
                }

                for (int j = 0; j < 8; j++) {
                    int xx = directions[j][0] + pop[0];
                    int yy = directions[j][1] + pop[1];

                    if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid.length && !visited[xx][yy] && grid[xx][yy] == 0) {
                        queue.add(new int[]{xx, yy});
                        visited[xx][yy] = true;
                    }
                }
            }
            ans++;
        }
        return -1;
    }
}
