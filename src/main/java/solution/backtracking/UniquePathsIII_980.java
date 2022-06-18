package solution.backtracking;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 * - 1 represents the starting square.  There is exactly one starting square.
 * - 2 represents the ending square.  There is exactly one ending square.
 * - 0 represents empty squares we can walk over.
 * - -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 * @author BorisMirage
 * Time: 2020/01/09 13:40
 * Created with IntelliJ IDEA
 */
public class UniquePathsIII_980 {
    /**
     * Naive backtracking.
     * Note that each empty cell should be walked over exactly only once (walked once and only once).
     * It will change the backtracking compare to normal maze path finding.
     * This problem is actually asking of how many Hamiltonian path can be found in given grid.
     *
     * @param grid given grid
     * @return the number path from start to end, that walk over every non-obstacle square exactly once
     */
    public int uniquePathsIII(int[][] grid) {

        /* Corner case */
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length, n = grid[0].length, empty = 0;

        int[] start = new int[2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                } else if (grid[i][j] == 0) {
                    empty++;
                }
            }
        }

        int[] count = new int[]{0};
        boolean[][] isVisited = new boolean[m][n];
        backtracking(grid, start[0], start[1], count, empty + 1, isVisited);

        return count[0];
    }

    /**
     * Naive backtracking.
     * Note that if the destination position is reached, check if all empty cells have been traversed.
     * If so, then one new path is found. Otherwise, this path is not valid (not a Hamiltonian path).
     *
     * @param grid      given grid
     * @param i         # of row
     * @param j         # of column
     * @param count     counting # of path
     * @param empty     count # of empty cell
     * @param isVisited save states of whether cell has been visited before
     */
    private void backtracking(int[][] grid, int i, int j, int[] count, int empty, boolean[][] isVisited) {

        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == -1 || isVisited[i][j]) {
            return;
        }

        if (grid[i][j] == 2) {
            if (empty == 0) {       // if all empty cells have been traversed only once, then path is found
                count[0]++;
            }

            return;
        }

        isVisited[i][j] = true;

        for (int[] d : directions) {
            int xx = d[0] + i;
            int yy = d[1] + j;

            backtracking(grid, xx, yy, count, empty - 1, isVisited);
        }

        isVisited[i][j] = false;
    }
}
