package solution.others;

/**
 * Given a 2D grid of 0s and 1s.
 * Return the number of elements in the largest square subgrid that has all 1s on its border.
 * Or 0 if such a sub grid doesn't exist in the grid.
 *
 * @author BorisMirage
 * Time: 2019/07/28 17:09
 * Created with IntelliJ IDEA
 */

public class Largest1BorderedSquare_1139 {
    /**
     * Use two same size 2D grid to count the consecutive 1s from 0 to current position in same row and column.
     * Then check from the bottom-right point, find if minor consecutive 1s can cover in border of square.
     *
     * @param grid given grid
     * @return number of elements in the largest square sub grid that has all 1s on its border
     */
    public int largest1BorderedSquare(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][] horizon = new int[r][c], vertical = new int[r][c];
        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] > 0) {
                    horizon[i][j] = (j > 0) ? horizon[i][j - 1] + 1 : 1;
                    vertical[i][j] = (i > 0) ? vertical[i - 1][j] + 1 : 1;
                }
            }
        }

        for (int i = r - 1; i >= 0; i--) {     // start from bottom-right
            for (int j = c - 1; j >= 0; j--) {

                int min = Math.min(horizon[i][j], vertical[i][j]);      // min of consecutive 1s, potential square length

                while (min > max) {
                    if (vertical[i][j - min + 1] >= min && horizon[i - min + 1][j] >= min) {
                        max = min;
                    }
                    min--;
                }
            }
        }

        return max * max;
    }
}
