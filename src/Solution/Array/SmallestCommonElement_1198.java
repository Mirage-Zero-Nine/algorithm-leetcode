package Solution.Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
 * If there is no common element, return -1.
 *
 * @author BorisMirage
 * Time: 2019/09/21 09:58
 * Created with IntelliJ IDEA
 */

public class SmallestCommonElement_1198 {
    /**
     * Sort the matrix by row. The search each row by each element from left to right in first row.
     * If the element in first row contains in all other rows, this element is the smallest common value.
     *
     * @param mat given matrix
     * @return smallest common element in all rows
     */
    public int smallestCommonElement(int[][] mat) {
        int n = mat[0].length;
        Arrays.sort(mat, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 0; i < n; i++) {
                    if (o1[i] != o2[i]) {
                        return o1[0] - o2[0];
                    }
                }
                return o1[n - 1] - o2[n - 1];
            }
        });

        for (int i = 0; i < n; i++) {
            int min = mat[0][i];
            int j = 1;
            while (j < mat.length && Arrays.binarySearch(mat[j], min) >= 0) {
                j++;
            }
            if (j == mat.length) {
                return min;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] m = new int[][]{{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {3, 3, 7, 9, 11}, {1, 3, 5, 7, 9}};
        System.out.println(new SmallestCommonElement_1198().smallestCommonElement(m));
    }
}
