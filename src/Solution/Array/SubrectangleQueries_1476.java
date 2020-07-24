package Solution.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement the class SubrectangleQueries_1476 which receives a matrix of integers and supports two methods:
 * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
 * Updates all values with newValue from upper left coordinate is (row1,col1) to bottom right coordinate is (row2,col2).
 * 2. getValue(int row, int col)
 * Returns the current value of the coordinate (row,col) from the rectangle.
 *
 * @author BorisMirage
 * Time: 2020/06/26 19:49
 * Created with IntelliJ IDEA
 */

public class SubrectangleQueries_1476 {
    private final int[][] rectangle;
    private final List<int[]> l = new ArrayList<>();

    /**
     * 2 approaches: update corresponding value, or only save the update range and check at the get method.
     * Keep a list store all previous update.
     * When getValue() is called, check the whether cell has been updated in list.
     * Return the corresponding value.
     *
     * @param rectangle given 2D array
     */
    public SubrectangleQueries_1476(int[][] rectangle) {
        int i = 0;
        this.rectangle = new int[rectangle.length][0];
        for (int[] arr : rectangle) {
            this.rectangle[i++] = arr.clone();
        }
    }

    /**
     * Update part of the matrix.
     *
     * @param row1     start row
     * @param col1     start column
     * @param row2     end row
     * @param col2     end column
     * @param newValue new value at this position
     */
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        l.add(0, new int[]{row1, row2, col1, col2, newValue});      // insert at the head of the list
    }

    /**
     * Return the most updated value in given cell.
     * To check whether this cell has been updated, go through list from latest to earliest.
     *
     * @param row row #
     * @param col column #
     * @return value in cell.
     */
    public int getValue(int row, int col) {
        for (int[] arr : l) {
            if (arr[0] <= row && row <= arr[1] && arr[2] <= col && col <= arr[3]) {
                return arr[4];
            }
        }

        return rectangle[row][col];
    }
}
