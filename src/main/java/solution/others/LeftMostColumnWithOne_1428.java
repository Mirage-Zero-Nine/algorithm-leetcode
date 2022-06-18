package solution.others;

import library.BinaryMatrix;

import java.util.List;

/**
 * A binary matrix means that all elements are 0 or 1.
 * For each individual row of the matrix, this row is sorted in non-decreasing order.
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index with at least a 1 in it.
 * If such index doesn't exist, return -1.
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 * 1. BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * 2. BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 * For custom testing purposes you're given the binary matrix mat as input in the following four examples.
 * You will not have access the binary matrix directly.
 *
 * @author BorisMirage
 * Time: 2020/05/23 13:27
 * Created with IntelliJ IDEA
 */

public class LeftMostColumnWithOne_1428 {
    /**
     * Note that each row in the binary matrix is sorted. And the matrix only contains 0 and 1.
     * Therefore, if a cell contains 0, then all element in same row at its left will be 0.
     * To find the left most column contains 1, starts at the top right of the matrix.
     * If current cell is 0, then all the cell in same row at its left are 0. No need to search current row, move down.
     * Otherwise, there could be 1 at its left, move to left cell and check if there is a 1.
     * Time complexity: O(m + n).
     *
     * @param binaryMatrix given matrix object
     * @return leftmost column index with at least a 1 in it
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> para = binaryMatrix.dimensions();
        int m = para.get(0), n = para.get(1);       // row and column of the matrix

        int leftMost = -1, row = 0, column = n - 1;

        while (row < m && column >= 0) {
            int current = binaryMatrix.get(row, column);
            if (current == 0) {             // row is sorted, left side will only contains 0
                row++;
            } else {                        // try left column to find the leftmost column contains at least one 1
                leftMost = column--;        // leftmost column could be current column, and try at its left
            }
        }

        return leftMost;
    }
}
