package Solution.Math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero.
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assuming environment could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 *
 * @author BorisMirage
 * Time: 2018/06/16 23:24
 * Created with IntelliJ IDEA
 */

public class Divide_29 {
    /**
     * Binary search.
     * Each time, shift divisor to right for x + 1 bits.
     * Dividend should reduce divisor each time.
     * Time complexity: O(logN^2).
     *
     * @param dividend int dividend
     * @param divisor  int divisor
     * @return quotient
     */
    public int divide(int dividend, int divisor) {

        /* Corner case */
        if (dividend == 1 << 31 && divisor == -1) {
            return (1 << 31) - 1;       // 2^31 / -1 cause overflow, excepted return MAX_VALUE
        }

        int a = Math.abs(dividend), b = Math.abs(divisor), res = 0, x;

        while (a - b >= 0) {
            x = 0;
            while (a - (b << x << 1) >= 0) {        // a - (b * 2^x * 2^1), shift b to reach a as close as possible
                x++;
            }

            res += 1 << x;      // res += 1 * 2^x
            a -= b << x;
        }
        return (dividend < 0) ^ (divisor < 0) ? -res : res;     // check negative
    }
}
