package Solution.DynamicProgramming;

/**
 * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
 * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
 * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
 * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * Return the length of a maximum size turbulent subarray of A.
 *
 * @author BorisMirage
 * Time: 2019/07/26 10:42
 * Created with IntelliJ IDEA
 */

public class MaxTurbulenceSize_978 {
    /**
     * Each time, if (A[i-1] - A[i]) * (A[i] - A[i+1]) < 0, then turbulent subarray + 1.
     * If (A[i-1] - A[i]) * (A[i] - A[i+1]) >= 0, then there are two conditions:
     * 1. If (A[i - 1] == A[i]) && (A[i] == A[i + 1]), then reset turbulent subarray is 1.
     * 2. Otherwise reset to 2.
     * Each time, find max length and finally return it.
     *
     * @param A given array
     * @return length of a maximum size turbulent subarray of A
     */
    public int maxTurbulenceSize(int[] A) {
        if (A.length < 2) {
            return A.length;
        }

        int max = (A[0] == A[1]) ? 1 : 2;
        int count = (A[0] == A[1]) ? 1 : 2;

        for (int i = 1; i < A.length - 1; i++) {
            if ((long) (A[i - 1] - A[i]) * (A[i] - A[i + 1]) < 0) {     // avoid overflow
                count++;
                max = Math.max(count, max);
            } else {
                count = (A[i - 1] == A[i] && A[i] == A[i + 1]) ? 1 : 2;
            }
        }

        return max;
    }
}
