package solution.twopointers;

import java.util.Arrays;

/**
 * Given an array A of integers and integer K, return the maximum S such that there exists i < j with A[i] + A[j] = S and S < K.
 * If no i, j exist satisfying this equation, return -1.
 *
 * @author BorisMirage
 * Time: 2019/08/09 09:35
 * Created with IntelliJ IDEA
 */

public class TwoSumLessThanK_1099 {
    /**
     * Sort array first, then use two pointers from left and right to narrow the searching range.
     * Similar to 3 sum.
     *
     * @param A given array
     * @param K target sum
     * @return maximum S such that there exists i < j with A[i] + A[j] = S and S < K, return -1 if no such sum
     */
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);

        int left = 0, right = A.length - 1, out = Integer.MIN_VALUE;

        while (left < right) {
            int sum = A[left] + A[right];
            if (sum < K && sum > out) {
                out = sum;
            }
            if (sum >= K) {
                right--;
            } else {
                left++;
            }
        }

        return (out == Integer.MIN_VALUE) ? -1 : out;
    }
}
