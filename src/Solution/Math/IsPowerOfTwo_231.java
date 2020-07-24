package Solution.Math;

/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * @author BorisMirage
 * Time: 2019/09/02 18:28
 * Created with IntelliJ IDEA
 */

public class IsPowerOfTwo_231 {
    /**
     * 1. All numbers < 1 are not power of 2.
     * 2. If an integer is power of 2, then it will only contain one bit of 1. And this bit is the first bit.
     * Therefore, if n is the power of 2, then all bits in n - 1 will be 1.
     * n & n - 1 will convert all bits to 0 if n is power of 2.
     *
     * @param n given integer n
     * @return if n is a power of two
     */
    public boolean isPowerOfTwo(int n) {
        return (n > 0 && (n & (n - 1)) == 0);
    }
}
