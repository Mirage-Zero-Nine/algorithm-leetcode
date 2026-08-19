package solutions.math;

/**
 * Calculates the perimeter of the land cells in a rectangular binary grid.
 *
 * <p>A land cell initially contributes four sides. When two land cells share
 * an edge, that edge was counted once for each cell, so two sides must be
 * removed from the total. Each shared edge is checked only from its lower or
 * right endpoint by looking at the cell above and the cell to the left.
 *
 * <p>The method does not mutate the input grid and also works when the grid
 * contains multiple disconnected land regions or enclosed water regions.
 *
 * @author BorisMirage
 * Time: 2026/08/18 22:50
 * Created with IntelliJ IDEA
 */

public class IslandPerimeter_463 {
    /**
     * Returns the total perimeter of all land cells.
     *
     * <p>For every land cell, four sides are added. A shared edge with the
     * cell above or to the left is then subtracted twice because it was
     * counted once from each of its two land cells. Checking only those two
     * directions prevents the same shared edge from being processed twice.
     * Water and grid boundaries therefore remain in the result as perimeter.
     *
     * <p>The input is assumed to be rectangular, as required by the problem.
     * Null or empty grids have perimeter zero.
     *
     * @param grid a rectangular grid where {@code 1} is land and {@code 0} is
     *             water
     * @return the perimeter of the land, or {@code 0} for a null or empty grid
     * @implNote Runs in {@code O(rows * columns)} time and {@code O(1)} extra
     * space.
     */
    public int islandPerimeter(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4;

                    // The shared vertical edge with the land cell above was counted twice
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimeter -= 2;
                    }

                    // The shared horizontal edge with the land cell to the left was counted twice
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter;
    }
}
