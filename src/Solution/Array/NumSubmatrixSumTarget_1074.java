package Solution.Array;

import java.util.HashMap;

/**
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * @author BorisMirage
 * Time: 2019/09/18 22:53
 * Created with IntelliJ IDEA
 */

public class NumSubmatrixSumTarget_1074 {
    /**
     * Calculate prefix sum in matrix.
     * Calculate the prefix sum of each row first.
     * Then calculate each possible pair column's accumulative sum from 0 to matrix.length.
     * Use a hash map to store previous accumulative sum to find how many sub matrix has a sum of certain value.
     * If the certain value is target, add the corresponding value.
     *
     * @param matrix given matrix
     * @param target target number
     * @return number of non-empty submatrices that sum to target
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        int out = 0;
        for (int i = 0; i < n; i++) {

            /*
             * Order of pair:
             * 1. Start from the column itself only.
             * 2. Start from jth column - first column, from jth column - ith column, where i <= j < n.
             * In this way to include all sub matrix.
             * Use a hash map to store previous sub matrix sum in current round (each j).
             * In this way to both avoid duplication and save calculation. */
            for (int j = i; j < n; j++) {       // j >= i
                HashMap<Integer, Integer> map = new HashMap<>();
                int current = 0;
                map.put(0, 1);        // if prefix sum + current value == target, then at least 1 sub matrix is found

                for (int[] k : matrix) {

                    /*
                     * k[j]: prefix sum in row k from 0 to j.
                     * k[j] - ((i > 0) ? k[i - 1] : 0): prefix sum in row k from i to j. */
                    current += k[j] - ((i > 0) ? k[i - 1] : 0);
                    out += map.getOrDefault(current - target, 0);
                    map.put(current, map.getOrDefault(current, 0) + 1);
                }
            }
        }

        return out;
    }
}
