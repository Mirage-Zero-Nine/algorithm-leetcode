package solution.array;

/**
 * @author BorisMirage
 * Time: 2023/04/16 00:42
 * Created with IntelliJ IDEA
 */

public class MinFallingPathSum_931 {
    /**
     * Each cell in the matrix can only be from its upper 3 cells (2 if it's in the first/last col).
     * Iterate each cell in the matrix except first row to calculate the min sum at current cell.
     * Then, iterate the last row to find the min path sum.
     *
     * @param matrix given matrix
     * @return the minimum sum of any falling path through matrix
     */
    public int minFallingPathSum(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int minSum = Integer.MAX_VALUE;

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = findMin(matrix, i, j);
            }
        }

        // all path sum stores at last column, find min sum by loop the last column
        for (int j = 0; j < col; j++) {
            minSum = Math.min(minSum, matrix[row - 1][j]);
        }

        return minSum;
    }

    /**
     * Find min path sum at the given cell.
     * Cell in between the column has 3 possible paths for the min sum path (upper left, upper, upper right).
     * If it's the first or last cell in the column, then the path can only be derived from 2 cells, instead of 3.
     *
     * @param matrix given matrix
     * @param i      row index
     * @param j      column index
     * @return min sum from all possible paths
     */
    private int findMin(int[][] matrix, int i, int j) {

        int minFromPossible =
                (j == 0) ? Math.min(matrix[i - 1][j], matrix[i - 1][j + 1]) :
                        (j == matrix[0].length - 1) ? Math.min(matrix[i - 1][j - 1], matrix[i - 1][j]) :
                                Math.min(Math.min(matrix[i - 1][j - 1], matrix[i - 1][j + 1]), matrix[i - 1][j]);

        return minFromPossible + matrix[i][j];
    }
}
