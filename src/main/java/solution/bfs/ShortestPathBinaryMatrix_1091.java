package solution.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * 1. All the visited cells of the path are 0.
 * 2. All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * @author BorisMirage
 * Time: 2019/07/17 12:45
 * Created with IntelliJ IDEA
 */
public class ShortestPathBinaryMatrix_1091 {
    /**
     * Implement BFS to find the shortest path in matrix.
     * Each time, add all available neighbors to the queue.
     *
     * @param grid given grid
     * @return shortest path length
     */
    public int shortestPathBinaryMatrix(int[][] grid) {

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {-1, 1}, {-1, -1}, {1, 1}};

        /* Corner case */
        if (grid[0][0] == 1 || grid[grid.length - 1][grid.length - 1] == 1) {
            return -1;
        }

        int m = grid.length;
        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        int length = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] current = q.poll();

                if (current[0] == m - 1 && current[1] == m - 1) {
                    return ++length;
                }

                for (int[] d : directions) {
                    int xx = d[0] + current[0];
                    int yy = d[1] + current[1];
                    if (xx >= 0 && xx < m && yy >= 0 && yy < m && !visited[xx][yy] && grid[xx][yy] == 0) {
                        visited[xx][yy] = true;
                        q.add(new int[]{xx, yy});
                    }
                }
            }
            length++;
        }

        return -1;
    }
}
