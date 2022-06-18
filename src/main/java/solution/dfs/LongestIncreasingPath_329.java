package solution.dfs;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * @author BorisMirage
 * Time: 2019/06/08 16:08
 * Created with IntelliJ IDEA
 */

public class LongestIncreasingPath_329 {
    /**
     * DFS with a 2D array for pruning.
     * Use a 2D array to store the max length from current position.
     * In this way to save time when later iteration comes to current location.
     *
     * @param matrix given matrix
     * @return length of the longest increasing path
     */
    public int longestIncreasingPath(int[][] matrix) {

        /* Corner case */
        if (matrix.length == 0) {
            return 0;
        }

        int max = 1;
        int[][] temp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(dfs(matrix, i, j, temp), max);
            }
        }
        return max;
    }

    /**
     * DFS with a 2D table for pruning.
     *
     * @param matrix given matrix
     * @param i      i
     * @param j      j
     * @param temp   store max path length in previous iteration
     * @return length of the longest increasing path on current cell
     */
    private int dfs(int[][] matrix, int i, int j, int[][] temp) {

        if (temp[i][j] != 0) {
            return temp[i][j];      // if cell is visited more than one time, directly return the max value to save time
        }

        int max = 1;
        final int[] d = {1, -1, 0, 0, 0, 0, 1, -1};

        for (int k = 0; k < 4; k++) {
            int xx = i + d[k];
            int yy = j + d[k + 4];

            if (xx > -1 && xx < matrix.length && yy > -1 && yy < matrix[0].length && matrix[xx][yy] > matrix[i][j]) {
                max = Math.max(max, 1 + dfs(matrix, xx, yy, temp));
            }
        }
        temp[i][j] = max;

        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingPath_329 test = new LongestIncreasingPath_329();
        System.out.println(test.longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
    }
}
