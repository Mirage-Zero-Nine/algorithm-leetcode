package solution.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 * In one shift operation:
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 *
 * @author BorisMirage
 * Time: 2020/02/02 10:05
 * Created with IntelliJ IDEA
 */

public class ShiftGrid_1260 {
    /**
     * 2D array can be regarded as 1D array. Then this problem can be considered as rotate 1D array.
     * Reverse whole array first, then reverse first k element in array, finally reverse the rest of array.
     *
     * @param grid given grid
     * @param k    shift k times
     * @return the 2D grid after applying shift operation k times
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, total = m * n;
        k = k % total;

        reverse(grid, 0, total - 1);
        reverse(grid, 0, k - 1);
        reverse(grid, k, total - 1);

        List<List<Integer>> out = new ArrayList<>();
        for (int[] g : grid) {
            out.add(Arrays.stream(g).boxed().collect(Collectors.toList()));
        }

        return out;
    }

    /**
     * Reverse 2D array within given range.
     *
     * @param grid  given grid
     * @param start start # of element
     * @param end   end # of element
     */
    private void reverse(int[][] grid, int start, int end) {
        int n = grid[0].length;

        while (start < end) {
            int r = start / n, c = start % n, i = end / n, j = end % n, tmp = grid[r][c];
            grid[r][c] = grid[i][j];
            grid[i][j] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ShiftGrid_1260().shiftGrid(new int[][]{{1}, {2}, {3}, {4}, {7}, {6}, {5}}, 2));
    }
}
