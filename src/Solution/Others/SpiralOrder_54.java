package Solution.Others;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * @author BorisMirage
 * Time: 2018/07/09 09:52
 * Created with IntelliJ IDEA
 */

public class SpiralOrder_54 {
    /**
     * Add elements into result list controlled by four boundary element.
     *
     * @param matrix input 2D array
     * @return elements in spiral order that store in list
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> out = new ArrayList<>();

        /* Corner case */
        if (matrix.length == 0) {
            return out;
        }

        int left = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;

        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                out.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                out.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    out.add(matrix[bottom][i]);
                }
            }
            bottom--;

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    out.add(matrix[i][left]);
                }
            }
            left++;
        }

        return out;
    }
}
