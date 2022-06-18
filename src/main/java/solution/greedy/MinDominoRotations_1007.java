package solution.greedy;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 *
 * @author BorisMirage
 * Time: 2019/09/08 21:43
 * Created with IntelliJ IDEA
 */

public class MinDominoRotations_1007 {
    /**
     * If all the values in A are the same, then A[0] must be in A or B.
     * It is same to B if it is possible to turn all the values in B are the same.
     * Therefore, check if A can be converted to same value by second array.
     * If not, swap A and B and check again.
     * If still can not be converted, return -1.
     *
     * @param A given array
     * @param B given array
     * @return min number of rotations so that all the values in A are the same, or all the values in B are the same
     */
    public int minDominoRotations(int[] A, int[] B) {
        int min = counter(A, B);
        return (min == -1) ? counter(B, A) : min;
    }

    /**
     * Check if value in first array can be converted to a[0], or turn second array to same value to a[0].
     *
     * @param a first array
     * @param b second array
     * @return min number of rotations so that all the values in A are the same
     */
    private int counter(int[] a, int[] b) {
        int n = a.length, p1 = 0, p2 = 0;
        for (int i = 0; i < n && (a[i] == a[0] || b[i] == a[0]); i++) {
            p1 = (a[i] != a[0]) ? p1 + 1 : p1;
            p2 = (b[i] != a[0]) ? p2 + 1 : p2;
            if (i == n - 1) {
                return Math.min(p1, p2);
            }
        }
        return -1;
    }
}
