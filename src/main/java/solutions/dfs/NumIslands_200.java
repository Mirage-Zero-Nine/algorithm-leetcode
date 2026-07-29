package solutions.dfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water)
 * Count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * All four edges of the grid are all surrounded by water.
 * i.e., if a land is at corner or edge of grid, it is counted as an island.
 *
 * @author BorisMirage
 * Time: 2019/06/05 11:40
 * Created with IntelliJ IDEA
 */

public class NumIslands_200 {

    /**
     * Counts islands with iterative depth-first search.
     * When an unvisited land cell is found, a LIFO stack is used to visit and
     * mark every horizontally or vertically connected land cell. Each completed
     * traversal represents one island.
     * Complexity: {@code O(rows * columns)} time and
     * {@code O(rows * columns)} auxiliary space in the worst case. This method
     * mutates {@code grid} by replacing visited land cells with
     * {@code 'x'}.
     *
     * @param grid rectangular grid containing {@code '1'} for land and
     *             {@code '0'} for water
     * @return number of islands, or {@code 0} for a null or empty grid
     */
    public int numIslands(char[][] grid) {

        // corner case
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, column = grid[0].length, count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private final static int[] DIRECTIONS = new int[]{0, 1, 0, -1, 0};

    /**
     * Marks every land cell in one island using an explicit LIFO stack.
     * Cells are marked before they are pushed so each cell is processed at most
     * once.
     *
     * @param i    starting row
     * @param j    starting column
     * @param grid grid whose starting cell is unvisited land
     */
    private void dfs(int i, int j, char[][] grid) {

        // use stack as normal DFS may risk stack overflow under deep DFS recursion
        Deque<int[]> stack = new ArrayDeque<>();

        stack.push(new int[]{i, j});
        grid[i][j] = 'x';
        while (!stack.isEmpty()) {
            int[] current = stack.pop();

            for (int k = 0; k < 4; k++) {
                int nextI = current[0] + DIRECTIONS[k], nextJ = current[1] + DIRECTIONS[k + 1];
                if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[0].length && grid[nextI][nextJ] == '1') {
                    stack.push(new int[]{nextI, nextJ});
                    grid[nextI][nextJ] = 'x';
                }
            }
        }
    }


    /**
     * Counts islands with BFS.
     * When an unvisited land cell is found, a FIFO queue is used to visit and
     * mark every horizontally or vertically connected land cell level by level.
     * Each completed traversal represents one island.
     * Complexity: {@code O(rows * columns)} time and
     * {@code O(rows * columns)} auxiliary space in the worst case. This method
     * mutates {@code grid} by replacing visited land cells with
     * {@code 'x'}.
     *
     * @param grid rectangular grid containing {@code '1'} for land and
     *             {@code '0'} for water
     * @return number of islands, or {@code 0} for a null or empty grid
     */
    public int numIslandsBFS(char[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, column = grid[0].length, count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1') {
                    bfs(i, j, grid);
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Marks every land cell in one island using a FIFO queue. Newly discovered
     * neighbors are marked when they are enqueued to avoid duplicate entries.
     *
     * @param i    starting row
     * @param j    starting column
     * @param grid grid whose starting cell is unvisited land
     */
    private void bfs(int i, int j, char[][] grid) {
        int[] direction = new int[]{0, 1, 0, -1, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            grid[current[0]][current[1]] = 'x';
            for (int k = 0; k < 4; k++) {
                int newI = current[0] + direction[k], newJ = current[1] + direction[k + 1];
                if (newI >= 0 && newI < grid.length && newJ >= 0 && newJ < grid[0].length && grid[newI][newJ] == '1') {
                    queue.add(new int[]{newI, newJ});
                    grid[newI][newJ] = 'x';
                }
            }
        }
    }

    /**
     * Counts islands with a disjoint-set (union-find) data structure.
     * Each land cell initially forms its own set. Adjacent land cells are
     * unioned, and the number of remaining disjoint land sets is the number of
     * islands.
     * Complexity: {@code O(rows * columns)} neighbor checks plus
     * amortized disjoint-set operations with path compression, and uses
     * {@code O(rows * columns)} auxiliary space. Unlike the traversal methods,
     * this method does not mutate {@code grid}.
     *
     * @param grid rectangular grid containing {@code '1'} for land and
     *             {@code '0'} for water
     * @return number of islands, or {@code 0} for a null or empty grid
     */
    public int numIslandsUnionFind(char[][] grid) {

        /* Corner case */
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int[][] distance = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        UnionFind uf = new UnionFind(grid);
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    for (int[] d : distance) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == '1') {
                            int id1 = i * cols + j;
                            int id2 = x * cols + y;
                            uf.union(id1, id2);
                        }
                    }
                }
            }
        }
        return uf.count;
    }

    /**
     * Disjoint-set structure containing one set for each land cell.
     * The component count is reduced whenever two distinct land sets are joined.
     */
    static class UnionFind {
        int[] father;
        int count = 0;

        /**
         * Initializes each land cell as its own parent and component.
         *
         * @param grid rectangular land-and-water grid
         */
        UnionFind(char[][] grid) {
            father = new int[grid.length * grid[0].length];     // contains all nodes in 2D array
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * grid[0].length + j;
                        father[id] = id;
                        count++;
                    }
                }
            }
        }

        /**
         * Joins the components containing two land cells. If the cells were in
         * different components, the component count is reduced by one.
         *
         * @param node1 flattened index of the first land cell
         * @param node2 flattened index of the second land cell
         */
        void union(int node1, int node2) {
            int find1 = find(node1), find2 = find(node2);
            if (find1 != find2) {
                father[find1] = find2;
                count--;
            }
        }

        /**
         * Finds the representative of a land cell and compresses the traversed
         * parent path.
         *
         * @param node flattened land-cell index
         * @return representative index of the cell's component
         */
        int find(int node) {
            if (father[node] == node) {
                return node;
            }
            father[node] = find(father[node]);      // recursively find father
            return father[node];
        }
    }
}
