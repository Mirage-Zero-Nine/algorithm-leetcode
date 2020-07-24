package Solution.Array;

/**
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 * 1. B.length >= 3
 * 2. There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 * Given an array A of integers, return the length of the longest mountain.
 * Return 0 if there is no mountain.
 *
 * @author BorisMirage
 * Time: 2020/03/05 16:54
 * Created with IntelliJ IDEA
 */

public class LongestMountain_845 {
    /**
     * One-pass traverse. Two counters: increasing counter and decreasing counter.
     * If previous subarray is decreasing subarray and a new increasing subarray is found, then reset counters.
     * If A[i] == A[i - 1], then no mountain is found, reset counters.
     * Otherwise, count the increasing subarray or decreasing subarray and compare to max length.
     *
     * @param A given array
     * @return the length of the longest mountain
     */
    public int longestMountain(int[] A) {
        int out = 0, up = 0, down = 0;

        for (int i = 1; i < A.length; i++) {

            /*
             * Two cases:
             * 1. If A[i - 1] < A[i] and down > 0, then it means a new subarray is found. Reset up and down.
             * 2. If A[i - 1] == A[i], then no mountain is found, reset count. */
            if ((down > 0 && A[i - 1] < A[i]) || A[i - 1] == A[i]) {
                up = 0;     // reset up and down
                down = 0;
            }
            if (A[i - 1] < A[i]) {      // increasing subarray
                up++;
            }
            if (A[i - 1] > A[i]) {      // decreasing subarray
                down++;
            }

            if (up > 0 && down > 0) {
                out = Math.max(out, up + down + 1);
            }
        }

        return out;
    }
}
