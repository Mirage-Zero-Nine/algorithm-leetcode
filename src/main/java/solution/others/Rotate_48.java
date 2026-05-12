package solution.others;


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

        /* Transpose along the main diagonal. */
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        /* Reverse each row to complete a clockwise rotation. */
        for (int i = 0; i < matrix.length; i++) {
            for (int left = 0, right = matrix[0].length - 1; left < right; left++, right--) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
            }
        }
    }
}
