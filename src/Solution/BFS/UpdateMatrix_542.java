package Solution.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * @author BorisMirage
 * Time: 2019/05/05 19:07
 * Created with IntelliJ IDEA
 */

public class UpdateMatrix_542 {
    /**
     * Mark each 1 to Integer.MAX_VALUE.
     *
     * @param matrix given 2D int array
     * @return matrix with distance
     */
    public int[][] updateMatrix(int[][] matrix) {

        /* Corner case */
        if (matrix == null) {
            return matrix;
        }
        if (matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }

        Queue<int[]> q = new LinkedList<>();
        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    q.add(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int[] d = new int[]{1, -1, 0, 0, 0, 0, 1, -1};
        while (!q.isEmpty()) {
            int[] tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int xx = tmp[0] + d[i];
                int yy = tmp[1] + d[i + 4];

                if (xx >= 0 && xx < row && yy >= 0 && yy < col && matrix[xx][yy] > matrix[tmp[0]][tmp[1]] + 1) {
                    matrix[xx][yy] = matrix[tmp[0]][tmp[1]] + 1;
                    q.add(new int[]{xx, yy});
                }
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] r = {{0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        UpdateMatrix_542 test = new UpdateMatrix_542();
        System.out.println(Arrays.deepToString(test.updateMatrix(r)));
    }
}
