package solution.binarysearch;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 1. Integers in each row are sorted in ascending from left to right.
 * 2. Integers in each column are sorted in ascending from top to bottom.
 *
 * @author BorisMirage
 * Time: 2019/06/23 17:23
 * Created with IntelliJ IDEA
 */

public class SearchMatrix_240 {

    /**
     * Binary search row and column to narrow the search range.
     *
     * @param matrix given 2D matrix
     * @param target target number
     * @return if target value in matrix
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        return search(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    /**
     * Binary search.
     *
     * @param m  given matrix
     * @param t  target value
     * @param r1 left index of row
     * @param r2 right index of row
     * @param c1 left index of column
     * @param c2 right index of column
     * @return if target exist in given range
     */
    private boolean search(int[][] m, int t, int r1, int r2, int c1, int c2) {

        if (r2 < r1 || c2 < c1 || r1 >= m.length || c1 > m[0].length || r2 < 0 || c2 < 0) {
            return false;
        }

        int rowMid = r1 + (r2 - r1) / 2;
        int colMid = c1 + (c2 - c1) / 2;

        if (m[rowMid][colMid] == t) {
            return true;
        } else if (t > m[rowMid][colMid]) {
            return (search(m, t, r1, r2, colMid + 1, c2)) || (search(m, t, rowMid + 1, r2, c1, colMid));
        } else if (t < m[rowMid][colMid]) {
            return (search(m, t, r1, rowMid - 1, colMid, c2) || (search(m, t, r1, r2, c1, colMid - 1)));
        }
        return false;
    }
}
