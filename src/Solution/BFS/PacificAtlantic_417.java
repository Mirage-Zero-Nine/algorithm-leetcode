package Solution.BFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent.
 * The "Pacific" touches the left and top edges of the matrix and the "Atlantic" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic.
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 *
 * @author BorisMirage
 * Time: 2019/05/28 12:54
 * Created with IntelliJ IDEA
 */

public class PacificAtlantic_417 {
    /**
     * Add all points next to edge and find all possible accessed point from edge using BFS.
     * Use two boolean 2D array to record corresponding visit.
     * The start point of each BFS is the edge point.
     * Finally, compare both diagonal visited map, if a point can be accessed in both visit map, then add to result.
     *
     * @param matrix given grid
     * @return all points that could move to diagonal edges
     */
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> out = new LinkedList<>();

        /* Corner case */
        if (matrix.length == 0) {
            return out;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        boolean[][] visitedPacific = new boolean[row][col], visitedAtlantic = new boolean[row][col];
        Queue<int[]> Pacific = new LinkedList<>(), Atlantic = new LinkedList<>();

        for (int i = 0; i < row; i++) {       // mark all edges to corresponding map as visited
            Pacific.add(new int[]{i, 0});
            Atlantic.add(new int[]{i, col - 1});
            visitedPacific[i][0] = true;
            visitedAtlantic[i][col - 1] = true;
        }
        for (int i = 0; i < col; i++) {        // mark all edges to corresponding map as visited
            Pacific.add(new int[]{0, i});
            Atlantic.add(new int[]{row - 1, i});
            visitedPacific[0][i] = true;
            visitedAtlantic[row - 1][i] = true;
        }

        bfs(visitedPacific, matrix, Pacific);       // find all points can be accessed from upper and left edge
        bfs(visitedAtlantic, matrix, Atlantic);     // find all points can be accessed from right and bottom edge

        for (int i = 0; i < row; i++) {       // find points that is both accessible
            for (int j = 0; j < col; j++) {
                if (visitedPacific[i][j] && visitedAtlantic[i][j]) {
                    out.add(new int[]{i, j});
                }
            }
        }

        return out;
    }

    /**
     * BFS to find all points that is accessible based on given point queue.
     *
     * @param isVisited 2D array record which point has been visited
     * @param matrix    given 2D array record value
     * @param q         queue
     */
    private void bfs(boolean[][] isVisited, int[][] matrix, Queue<int[]> q) {
        int[] xy = {1, -1, 0, 0, 0, 0, 1, -1};
        int row = matrix.length;
        int col = matrix[0].length;

        while (!q.isEmpty()) {
            int[] c = q.remove();
            int x = c[0];
            int y = c[1];
            for (int i = 0; i < 4; i++) {
                int xx = x + xy[i];
                int yy = y + xy[i + 4];
                if (xx > -1 && xx < row && yy > -1 && yy < col && !isVisited[xx][yy] && matrix[xx][yy] >= matrix[x][y]) {
                    q.add(new int[]{xx, yy});
                    isVisited[xx][yy] = true;
                }
            }
        }
    }
}
