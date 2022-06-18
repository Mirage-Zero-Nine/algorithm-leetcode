package solution.array;

import java.util.Arrays;

/**
 * Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
 * Formally, we can partition the array if we can find indexes i+1 < j with
 * (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
 *
 * @author BorisMirage
 * Time: 2019/09/09 18:30
 * Created with IntelliJ IDEA
 */

public class CanThreePartsEqualSum_1013 {
    /**
     * 1. Array sum should be the multiple of 3.
     * 2. Sum of each part should be sum / 3.
     * Direct traverse the array and find if there is the possibility.
     *
     * @param A given array
     * @return return true if and only if the array can be partitioned into three non-empty parts with equal sums
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = Arrays.stream(A).sum(), part = 0, count = 0;

        /* Corner case */
        if (sum % 3 != 0) {
            return false;
        }

        for (int a : A) {
            part += a;

            if (part == sum / 3) {
                if (++count == 3) {
                    return true;
                }
                part = 0;
            }
        }
        return false;
    }
}
