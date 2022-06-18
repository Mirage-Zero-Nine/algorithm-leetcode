package solution.findkth;

import java.util.*;

/**
 * A 2D grid of values 0 or 1 is given.
 * Find a point that from all 1 to this point, where the total Manhattan distance is minimized.
 * Manhattan distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * @author BorisMirage
 * Time: 2019/05/05 23:07
 * Created with IntelliJ IDEA
 */

public class MinTotalDistance_296 {
    /**
     * The travel distance is Manhattan distance.
     * Therefore, to find the min distance, collect all position's row and column #.
     * To reach the min best meeting point, both people has to travel the Manhattan distance.
     * Hence, calculate the Manhattan distance by calculating the Manhattan distance in row and column after sorting.
     *
     * @param grid given grid
     * @return minimize Manhattan distance
     */
    public int minTotalDistance(int[][] grid) {
        int r = grid.length, c = grid[0].length;

        List<Integer> row = new ArrayList<>();      // total value in each row
        List<Integer> col = new ArrayList<>();      // total value in each column

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        return minSum(row) + minSum(col);       // convert 2D to 1D
    }

    /**
     * Calculate the min Manhattan distance based on row # and column #.
     *
     * @param list list
     * @return minimize sum of given list
     */
    private int minSum(List<Integer> list) {

        int sum = 0, left = 0, right = list.size() - 1;
        Collections.sort(list);

        while (left < right) {
            sum = sum + list.get(right--) - list.get(left++);
        }

        return sum;
    }
}
