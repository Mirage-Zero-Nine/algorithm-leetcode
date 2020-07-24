package Solution.Stack;

import java.util.Stack;

/**
 * Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].
 * The width of such a ramp is j - i.
 * Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
 *
 * @author BorisMirage
 * Time: 2020/04/04 10:34
 * Created with IntelliJ IDEA
 */

public class MaxWidthRamp_962 {
    /**
     * Decreasing stack.
     * Build the decreasing stack from left to right at first traverse.
     * Then, from right to left, pop out the elements that is smaller than current elements.
     * Find the max gap in the second traverse.
     *
     * @param A given array
     * @return maximum width of a ramp in array
     */
    public int maxWidthRamp(int[] A) {

        /* Corner case */
        if (A == null || A.length < 2) {
            return 0;
        }

        int max = 0, n = A.length;

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (s.isEmpty() || A[s.peek()] > A[i]) {
                s.push(i);       // decreasing stack
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && A[i] >= A[s.peek()]) {
                max = Math.max(max, i - s.pop());
            }
        }

        return max;
    }
}
