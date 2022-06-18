package solution.bfs;

import java.util.PriorityQueue;

/**
 * Given a matrix with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].
 * The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.
 * A path moves from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions.
 *
 * @author BorisMirage
 * Time: 2020/08/22 11:19
 * Created with IntelliJ IDEA
 */

public class MaximumMinimumPath_1102 {
    /**
     * BFS with priority queue (max heap). The priority queue replaces the FIFO queue in normal BFS.
     * The priority queue will assure the next element in path will always be the maximum value in all available cells.
     * This way, it can assure BFS traverse all possible largest elements in matrix before reaches the end.
     * If reaches the end, immediately return the min value in max path.
     *
     * @param A given matrix
     * @return the maximum score of a path starting at [0,0] and ending at [R-1,C-1]
     */
    public int maximumMinimumPath(int[][] A) {

        /* Corner case */
        if (A == null || A.length == 0 || A[0].length == 0) {
            return -1;
        }

        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = A.length - 1, column = A[0].length - 1, out = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[row + 1][column + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[2] - o1[2]);       // max heap
        pq.add(new int[]{0, 0, A[0][0]});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();      // next largest available element
            int x = current[0], y = current[1], tmp = current[2];
            visited[x][y] = true;
            out = Math.min(out, tmp);       // min value in current path
            if (current[0] == row && current[1] == column) {
                return out;
            }

            for (int[] d : direction) {
                int xx = x + d[0], yy = y + d[1];
                if (xx >= 0 && xx <= row && yy >= 0 && yy <= column && !visited[xx][yy]) {
                    pq.add(new int[]{xx, yy, A[xx][yy]});
                }
            }
        }

        return out;
    }
}
