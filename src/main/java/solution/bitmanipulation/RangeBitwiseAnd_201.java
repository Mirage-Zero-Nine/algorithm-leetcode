package solution.bitmanipulation;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 *
 * @author BorisMirage
 * Time: 2019/07/01 11:23
 * Created with IntelliJ IDEA
 */

public class RangeBitwiseAnd_201 {
    /**
     * Right shift each bit of two numbers until they are equal.
     * Then left shift all bits and fill them all with 0s.
     * For instance, 110101 and 111011, right shift them until 11, and left shift four 0s to 110000. This is the result.
     * If n has more bits, then the left shift will make m and n both 0.
     *
     * @param m first number
     * @param n second number
     * @return bitwise AND of all numbers in this range, inclusive
     */
    public int rangeBitwiseAnd(int m, int n) {

        int i = 0;  // count left shift bits
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}
