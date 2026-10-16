package solutions.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
     * Finds cells that can reach both oceans by running BFS backwards from each ocean's border. A separate boolean
     * grid tracks each search, and cells marked in both grids form the result. The input is not mutated, and each
     * call uses fresh state.
     * <p>
     * Time complexity: O(mn)
     * Auxiliary space: O(mn)
     *
     * @param heights rectangular matrix of cell heights
     * @return coordinates of cells whose rainwater can reach both oceans, in unspecified order
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        // Reverse a legal downhill flow: from an ocean, a search may move to an adjacent cell whose height is
        // equal or higher. Reversing any such path gives a valid route from that cell down to the ocean.
        // Seed the Pacific search with its top and left borders; every other reachable cell is discovered from
        // these starting points.
        Queue<int[]> queue = Stream.concat(
                IntStream.range(0, heights.length)
                        .mapToObj(n -> new int[]{n, 0}),
                IntStream.range(1, heights[0].length)
                        .mapToObj(n -> new int[]{0, n})
        ).collect(Collectors.toCollection(ArrayDeque::new));
        boolean[][] visited1 = new boolean[heights.length][heights[0].length];
        // Mark seeds before BFS so a border cell cannot be rediscovered through a neighboring cell.
        queue.forEach(cell -> visited1[cell[0]][cell[1]] = true);
        bfs(queue, visited1, heights);

        // Seed the Atlantic search with its bottom and right borders. The bottom range stops before the last
        // column because the bottom-right cell is already included in the right-border seed.
        queue = Stream.concat(
                IntStream.range(0, heights.length)
                        .mapToObj(n -> new int[]{n, heights[0].length - 1}),
                IntStream.range(0, heights[0].length - 1)
                        .mapToObj(n -> new int[]{heights.length - 1, n})
        ).collect(Collectors.toCollection(ArrayDeque::new));
        boolean[][] visited2 = new boolean[heights.length][heights[0].length];
        queue.forEach(cell -> visited2[cell[0]][cell[1]] = true);
        bfs(queue, visited2, heights);

        // A cell belongs in the answer only when both reverse searches reached it: each visited grid proves a
        // route to its corresponding ocean, so their intersection proves reachability to both.
        return IntStream.range(0, heights.length)
                .boxed()
                .flatMap(i -> IntStream.range(0, heights[0].length)
                        .filter(j -> visited1[i][j] && visited2[i][j])
                        .mapToObj(j -> List.of(i, j))).toList();
    }

    /**
     * Expands one ocean's reverse-reachability frontier. Each cell is enqueued at most once and the supplied queue
     * and visited grid are used without retaining state.
     * <p>
     * Time complexity: O(mn)
     * Auxiliary space: O(mn) for the supplied queue and visited grid
     *
     * @param queue   initially seeded with one ocean's border cells
     * @param visited reachability state for that ocean
     * @param heights input height matrix
     */
    private void bfs(Queue<int[]> queue, boolean[][] visited, int[][] heights) {
        int[] directions = new int[]{1, 0, -1, 0, 1};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];
            IntStream.range(0, 4).forEach(i -> {
                int nextX = x + directions[i], nextY = y + directions[i + 1];
                if (nextX >= 0 && nextX < visited.length
                        && nextY >= 0 && nextY < visited[0].length
                        && !visited[nextX][nextY]
                        && heights[nextX][nextY] >= heights[x][y]) {
                    // Mark on enqueue, rather than dequeue: equal-height cells can point back to one another,
                    // and this prevents duplicate work and cycles while preserving the reverse-flow invariant.
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            });
        }
    }
}
