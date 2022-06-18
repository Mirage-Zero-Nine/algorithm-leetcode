package solution.math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * @author BorisMirage
 * Time: 2020/02/03 09:57
 * Created with IntelliJ IDEA
 */

public class TrailingZeroes_172 {
    /**
     * 1. Only 2 * 5 can generate a 0.
     * 2. 5 is less than 2 in factorial.
     * Therefore, find all 5 in factorization of n!.
     * For instance, 10! = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10, 10 = 2 * 5, therefore, two (2 * 5) can be found.
     * Finally, it can be concluded to f(n) = f(n / 5) + n / 5.
     *
     * @param n given number
     * @return the number of trailing zeroes in n!
     */
    public int trailingZeroes(int n) {

        if (n < 5) {
            return 0;
        }
        if (n < 10) {
            return 1;
        }

        return trailingZeroes(n / 5) + n / 5;
    }

    /**
     * Iterative approach, idea is the same.
     *
     * @param n given number
     * @return the number of trailing zeroes in n!
     */
    public static int iterative(int n) {
        int result = 0;
        while (n > 0) {
            result += n / 5;

            n /= 5;
        }

        return result;
    }
}
