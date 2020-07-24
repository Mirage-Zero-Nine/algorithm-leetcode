package Solution.Others;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Solve this problem in-place.
 *
 * @author BorisMirage
 * Time: 2018/08/07 15:55
 * Created with IntelliJ IDEA
 */

public class SetZeroes_73 {
    /**
     * Leave first row and column as the sign to mark which row and column needs to be set 0.
     * Two boolean value to record if first row and column needs to be set as 0.
     *
     * @param matrix given matrix
     */
    public void setZeroes(int[][] matrix) {

        boolean row0 = false;       // mark if first row should be set to 0
        boolean col0 = false;       // mark if first column should be set to 0

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row0 = (j == 0) || row0;        // if first row or column contains 0, then mark it as true
                    col0 = (i == 0) || col0;
                    matrix[0][j] = 0;               // mark row and column
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {       // set 0 except first row and column
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < matrix.length && row0; i++) {     // set first row
            matrix[i][0] = 0;
        }

        for (int i = 0; i < matrix[0].length && col0; i++) {      // set first column
            matrix[0][i] = 0;
        }
    }
}
