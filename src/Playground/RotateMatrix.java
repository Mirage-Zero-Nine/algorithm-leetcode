package Playground;

/**
 * Rotate a 2D matrix in clockwise or anti clockwise.
 *
 * @author BorisMirage
 * Date: 2018/06/24 23:59
 * Created with IntelliJ IDEA
 */

public class RotateMatrix {

    /**
     * Rotate input matrix 90 degrees clockwise.
     *
     * @param matrix input matrix
     * @return rotated matrix
     */
    public int[][] rotateClockwise(int[][] matrix) {

        /* Flip each row along with mid row of matrix */
        for (int i = 0; i < matrix.length / 2; i++) {

            /* matrix[i]: row[i] */
            int[] temp = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = matrix[i];
            matrix[i] = temp;
        }

        /* Flip each element along with diagonal from up-left to down-right */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }

    /**
     * Rotate input matrix 90 degrees anti-clockwise.
     *
     * @param matrix input matrix
     * @return rotated matrix
     */
    public int[][] rotateAntiClockwise(int[][] matrix) {

        /* Flip each column along with mid column of matrix (matrix[0].length / 2) */
        for (int i = 0; i < matrix.length; i++) {
            for (int a = 0, b = matrix[0].length - 1; a < b; a++, b--) {
                int temp = matrix[i][a];
                matrix[i][a] = matrix[i][b];
                matrix[i][b] = temp;
            }
        }

        /* Flip each element along with diagonal from up-left to down-right */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
        return matrix;
    }
}
