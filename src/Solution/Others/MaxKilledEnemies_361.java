package Solution.Others;

/**
 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero).
 * Return the maximum enemies you can kill using one bomb.
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall.
 * Bumb can only be put at an empty cell.
 *
 * @author BorisMirage
 * Time: 2018/10/05 20:53
 * Created with IntelliJ IDEA
 */

public class MaxKilledEnemies_361 {
    /**
     * Traverse whole array and use a counter and an array to record each row and column's max.
     *
     * @param grid given 2D array
     * @return max E can be removed
     */
    public int maxKilledEnemies(char[][] grid) {

        int max = 0;
        int rowHits = 0;
        int n = grid.length == 0 ? 0 : grid[0].length;        // Avoid empty matrix
        int[] columnHit = new int[n];       // column will be used repeatedly, so use array to record possible 'E'

        /* Traverse each element in grid */
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                /* If it is first row or current cell is wall */
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowHits = 0;        // counter and traverse is from left to right, so if encounter 'W', reset it
                    for (int k = j; k < grid[0].length && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            rowHits += 1;       // find all 'E'
                        }
                    }
                }

                /* If it is first column or current cell is wall */
                if (i == 0 || grid[i - 1][j] == 'W') {
                    columnHit[j] = 0;       // counter and traverse is downside, so if encounter 'W', reset it
                    for (int k = i; k < grid.length && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            columnHit[j] += 1;
                        }
                    }
                }

                /* If current cell is able to put bomb */
                if (grid[i][j] == '0') {
                    max = Math.max(max, rowHits + columnHit[j]);
                }
            }
        }
        return max;
    }
}
