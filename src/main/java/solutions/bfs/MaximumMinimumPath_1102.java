package solutions.bfs;

import java.util.Arrays;
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
     * Uses a max-heap version of Dijkstra's algorithm. For each cell, {@code best} stores the
     * largest bottleneck score known for a path from the origin. Expanding the greatest score
     * first is safe because any later route to the destination cannot improve a cell after its
     * greatest bottleneck score has been removed from the heap. Stale heap entries are skipped,
     * so each relaxation is bounded by the number of grid edges and duplicate frontier entries
     * cannot cause unbounded growth.
     *
     * <p>The separate {@code reached} array is needed because {@link Integer#MIN_VALUE} is a
     * valid cell value and therefore cannot serve as an unreachable sentinel. The comparator uses
     * {@link Integer#compare(int, int)} to avoid signed subtraction overflow.</p>
     *
     * <p>Time complexity is {@code O(RC log(RC))}; auxiliary space is {@code O(RC)}.</p>
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
        int row = A.length - 1, column = A[0].length - 1;
        int[][] best = new int[row + 1][column + 1];
        for (int[] values : best) {
            Arrays.fill(values, Integer.MIN_VALUE);
        }
        boolean[][] reached = new boolean[row + 1][column + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[2], o1[2]));
        best[0][0] = A[0][0];
        reached[0][0] = true;
        pq.add(new int[]{0, 0, A[0][0]});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0], y = current[1], score = current[2];
            if (score != best[x][y]) {
                continue;
            }
            if (x == row && y == column) {
                return score;
            }

            for (int[] d : direction) {
                int xx = x + d[0], yy = y + d[1];
                if (xx >= 0 && xx <= row && yy >= 0 && yy <= column) {
                    int nextScore = Math.min(score, A[xx][yy]);
                    if (!reached[xx][yy] || nextScore > best[xx][yy]) {
                        best[xx][yy] = nextScore;
                        reached[xx][yy] = true;
                        pq.add(new int[]{xx, yy, nextScore});
                    }
                }
            }
        }

        return -1;
    }
}
