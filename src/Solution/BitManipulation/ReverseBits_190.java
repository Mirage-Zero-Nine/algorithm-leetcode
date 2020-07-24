package Solution.BitManipulation;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * @author BorisMirage
 * Time: 2019/10/12 17:25
 * Created with IntelliJ IDEA
 */

public class ReverseBits_190 {
    /**
     * Shift bit one by one.
     *
     * @param n given int
     * @return reversed int
     */
    public int reverseBits(int n) {

        /* Corner case */
        if (n == 0 || n == -1) {
            return n;
        }

        int out = 0;

        for (int i = 0; i < 32; i++) {
            if ((n & 1) > 0) {
                out |= 1 << (32 - i - 1);
            }

            n >>>= 1;       // avoid signed influence
        }

        return out;
    }
}
