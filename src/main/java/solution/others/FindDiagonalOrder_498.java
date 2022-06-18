package solution.others;

/**
 * Given a matrix of M x N elements (M rows, N columns).
 * Return all elements of the matrix in diagonal order.
 *
 * @author BorisMirage
 * Time: 2020/02/01 11:43
 * Created with IntelliJ IDEA
 */

public class FindDiagonalOrder_498 {
    /**
     * Diagonal direction: (-1, 1), (1, -1).
     * Keep same direction until reaches the end of current diagonal line.
     *
     * @param matrix given matrix
     * @return all elements of the matrix in diagonal order
     */
    public int[] findDiagonalOrder(int[][] matrix) {

        /* Corner case */
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        int m = matrix.length, n = matrix[0].length, total = m * n, row = 0, column = 0, d = 0;
        int[] out = new int[total];
        int[][] directions = {{-1, 1}, {1, -1}};

        for (int i = 0; i < total; i++) {
            out[i] = matrix[row][column];
            row += directions[d][0];
            column += directions[d][1];

            if (row >= m) {     // if reaches the end of row, switch direction
                row = m - 1;
                column += 2;
                d = 1 - d;
            }
            if (column >= n) {      // if reaches the end of column, switch direction
                column = n - 1;
                row += 2;
                d = 1 - d;
            }
            if (row < 0) {      // if reaches the top of matrix, switch direction
                row = 0;
                d = 1 - d;
            }
            if (column < 0) {       // if reaches the left bound of matrix, switch direction
                column = 0;
                d = 1 - d;
            }
        }

        return out;
    }
}
