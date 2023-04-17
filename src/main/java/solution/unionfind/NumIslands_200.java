package solution.unionfind;

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

    private static final char one = '1';
    private static final char two = '2';
    private static final int[] direction = new int[]{0, 1, 0, -1, 0};

    /**
     * DFS.
     * When reaches a '1' then do DFS start at this point and mark all accessible point as visited.
     * Iterate all cells in grid.
     *
     * @param grid given grid
     * @return number of connected '1'
     */
    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length, col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == one) {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * DFS to access all accessible points based on current point.
     *
     * @param grid given grid
     * @param x    coord x
     * @param y    coord y
     */
    private void dfs(char[][] grid, int x, int y) {
        grid[x][y] = two;

        for (int i = 0; i < direction.length - 1; i++) {
            int xx = x + direction[i];
            int yy = y + direction[i + 1];
            if (xx >= 0 && xx < grid.length && yy >= 0 && yy < grid[0].length && grid[xx][yy] == one) {
                dfs(grid, xx, yy);
            }
        }
    }

    /**
     * Implement union find to connect all nodes that are connected and count all islands.
     *
     * @param grid given grid
     * @return number of islands
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
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == one) {
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
     * Union find class specially implemented for this problem.
     */
    static class UnionFind {
        int[] father;
        int count = 0;

        /**
         * Count father of each node. Initially, father of each node is itself.
         *
         * @param grid given grid
         */
        UnionFind(char[][] grid) {
            father = new int[grid.length * grid[0].length];     // contains all nodes in 2D array
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == one) {
                        int id = i * grid[0].length + j;
                        father[id] = id;
                        count++;
                    }
                }
            }
        }

        /**
         * Union two nodes that are both '1'.
         * If two nodes are connected, then the # of islands reduced by 1.
         *
         * @param node1 first node
         * @param node2 second node
         */
        void union(int node1, int node2) {
            int find1 = find(node1), find2 = find(node2);
            if (find1 != find2) {
                father[find1] = find2;
                count--;
            }
        }

        /**
         * Find root of given node.
         *
         * @param node given node
         * @return root of given node
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
