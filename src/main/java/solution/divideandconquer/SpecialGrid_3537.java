package solution.divideandconquer;

import java.util.Arrays;

/**
 * You are given a non-negative integer n representing a 2n x 2n grid.
 * You must fill the grid with integers from 0 to 2^n-1 to make it special.
 * A grid is special if it satisfies all the following conditions:
 * - All numbers in the top-right quadrant are smaller than those in the bottom-right quadrant.
 * - All numbers in the bottom-right quadrant are smaller than those in the bottom-left quadrant.
 * - All numbers in the bottom-left quadrant are smaller than those in the top-left quadrant.
 * - Each of its quadrants is also a special grid.
 * Return the special 2^n x 2^n grid.
 * Note: Any 1x1 grid is special.
 *
 * @author BorisMirage
 * Time: 2025/05/05 13:36
 * Created with IntelliJ IDEA
 */


public class SpecialGrid_3537 {

    // globe variable when counting value
    private int value = 0;

    /**
     * Divide and conquer.
     * Each grid with size 2^n can be divided into 4 sub-grids with size of 2^n-1.
     * Hence, divide it into 4 sub-grid following the order (top right, bottom right, bottom left, top left).
     * When the grid size is 1, put the current value into output grid (and add 1).
     *
     * @param n size of the output grid (2^n-1)
     * @return grid following the rule
     */
    public int[][] specialGrid(int n) {
        int size = 1 << n;
        int[][] output = new int[size][size];

        // started from (0, 0) with original size
        divide(output, 0, 0, size);

        return output;
    }

    /**
     * Divide and fill value. Flow:
     * - Divide the current grid into 4 sub-grids with half of the length.
     * - When the size equals 1, fill the value.
     *
     * @param grid   given grid
     * @param row    current row
     * @param column current column
     * @param size   size of the grid
     */
    private void divide(int[][] grid, int row, int column, int size) {
        if (size == 1) {
            grid[row][column] = value++;
            return;
        }

        // divide into 4 sub-grids with half of the size
        int nextSize = size / 2;
        divide(grid, row, column + nextSize, nextSize);
        divide(grid, row + nextSize, column + nextSize, nextSize);
        divide(grid, row + nextSize, column, nextSize);
        divide(grid, row, column, nextSize);
    }
}
