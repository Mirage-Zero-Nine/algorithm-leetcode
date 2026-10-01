package solutions.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 * the value 0 representing an empty cell;
 * the value 1 representing a fresh orange;
 * the value 2 representing a rotten orange.
 * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1 instead.
 * The grid has between 1 and 10 rows and columns, and every entry is 0, 1, or 2.
 *
 * @author BorisMirage
 * Time: 2019/11/21 20:16
 * Created with IntelliJ IDEA
 */

public class OrangesRotting_994 {

    /**
     * Simulates the spread of rot with a multi-source breadth-first search.
     * Every initially rotten orange is placed in the queue before the search
     * starts, so one queue layer represents all oranges that become rotten in
     * the same minute. When a fresh neighbor is discovered, it is marked
     * immediately before being queued; this both records the mutation required
     * by the simulation and prevents the same orange from being queued twice.
     * After the search, a positive fresh count means that some fresh orange
     * was separated from every rotten orange by empty cells, so the result is
     * -1. Otherwise the number of processed layers is one greater than the
     * elapsed time because the initial rotten layer is processed at minute
     * zero, and the method returns {@code count - 1}.
     *
     * <p>The grid is mutated in place: every fresh orange that can rot is
     * changed from {@code 1} to {@code 2}. The method uses O(mn) time and
     * O(mn) auxiliary space in the worst case for an m-by-n grid.</p>
     *
     * @param grid rectangular grid containing empty, fresh, and rotten cells
     * @return the minimum minutes until all fresh oranges rot, or {@code -1}
     * when at least one fresh orange cannot be reached; an empty or
     * null grid returns {@code -1}
     */
    public int orangesRotting(int[][] grid) {

        // corner cases
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    fresh++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // No fresh oranges means the goal is already satisfied; processing the
        // initially rotten queue would incorrectly add a minute to the result.
        if (fresh == 0) {
            return 0;
        }

        int count = 0;
        int[] directions = new int[]{1, 0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = current[0] + directions[j], y = current[1] + directions[j + 1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        queue.add(new int[]{x, y});
                        fresh--;
                    }
                }
            }
        }

        // The first processed layer contains the oranges rotten at minute 0,
        // so count includes one setup layer and must be reduced by one.
        return fresh == 0 ? count - 1 : -1;
    }
}
