package Solution.DataStructure;

/**
 * @author BorisMirage
 * Time: 2020/04/05 16:58
 * Created with IntelliJ IDEA
 */

public class NumMatrix_304 {
    private int[][] sum;

    /**
     * For each cell matrix[i][j], the sum cover all cells from matrix[0][0] to matrix[i][j] is as follows:
     * sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i][j]
     * sum[i][j] is the sum cover all cells from matrix[0][0] to matrix[i][j].
     * sum[i - 1][j] + sum[i][j - 1] will calculate the area of sum[i - 1][j - 1] twice, which should be reduced.
     * Finally, add value at matrix[i][j].
     *
     * @param matrix given matrix
     */
    public NumMatrix_304(int[][] matrix) {

        /* Corner case */
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int m = matrix.length, n = matrix[0].length;
        sum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    /**
     * Return the range sum (row1, col1), (row2, col2).
     *
     * @param row1 (row1, col1)
     * @param col1 (row1, col1)
     * @param row2 (row2, col2)
     * @param col2 (row2, col2)
     * @return sum in the range (row1, col1), (row2, col2)
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
    }
}
