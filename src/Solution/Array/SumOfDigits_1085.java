package Solution.Array;

import java.util.Arrays;

/**
 * Given an array A of positive integers, let S be the sum of the digits of the minimal element of A.
 * Return 0 if S is odd, otherwise return 1.
 *
 * @author BorisMirage
 * Time: 2019/09/09 18:26
 * Created with IntelliJ IDEA
 */

public class SumOfDigits_1085 {
    /**
     * Direct approach.
     * Find min value in array, then calculate sum of min value.
     *
     * @param A given array
     * @return return 0 if S is odd, otherwise return 1
     */
    public int sumOfDigits(int[] A) {

        int out = 0;
        if (Arrays.stream(A).min().isPresent()) {
            int min = Arrays.stream(A).min().getAsInt();
            out = 0;
            while (min > 0) {
                out += min % 10;
                min /= 10;
            }
        }

        return 1 - out % 2;
    }
}
