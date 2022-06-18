package solution.others;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
 *
 * @author BorisMirage
 * Time: 2020/03/21 13:35
 * Created with IntelliJ IDEA
 */

public class GenerateMatrix_59 {
    /**
     * Generate a n row and n column spiral matrix.
     *
     * @param n n row and column
     * @return matrix with n row and n column filled with element from 1 to n^2
     */
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];

        /* Corner case */
        if (n == 0) {
            return matrix;
        }

        int rowStart = 0;
        int rowEnd = n - 1;
        int colStart = 0;
        int colEnd = n - 1;
        int num = 1;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                matrix[rowStart][i] = num++;
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++;
            }
            colEnd--;

            for (int i = colEnd; i >= colStart; i--) {
                if (rowStart <= rowEnd)
                    matrix[rowEnd][i] = num++;
            }
            rowEnd--;

            for (int i = rowEnd; i >= rowStart; i--) {
                if (colStart <= colEnd)
                    matrix[i][colStart] = num++;
            }
            colStart++;
        }

        return matrix;
    }
}