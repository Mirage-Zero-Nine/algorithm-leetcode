package Solution.Others;


/**
 * An n x n 2D matrix representing an image is given.
 * Rotate_48 the image by 90 degrees (clockwise).
 * <p>
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 *
 * @author BorisMirage
 * Time: 2018/06/24 20:07
 * Created with IntelliJ IDEA
 */

public class Rotate_48 {
    /**
     * Rotate image in clockwise:
     * 1. Flip each row along with mid row of matrix (matrix.length / 2).
     * 2. Flip each element along with diagonal from up-left to down-right.
     * Rotate image in anti-clockwise:
     * 1. Flip each column along with mid column of matrix (matrix[0].length / 2).
     * 2. Flip each element along with diagonal from up-left to down-right.
     *
     * @param matrix input 2D array
     */
    public void rotate(int[][] matrix) {

        if (matrix.length == 0) {
            return;
        }

        /* Flip each row along with mid row of matrix */
        for (int i = 0; i < matrix.length / 2; i++) {

            /* matrix[i]: row[i] */
            int[] temp = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = matrix[i];
            matrix[i] = temp;
        }

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
    }
}
