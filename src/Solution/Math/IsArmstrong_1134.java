package Solution.Math;

/**
 * The k-digit number N is an Armstrong number if and only if the k-th power of each digit sums to N.
 * Given a positive integer N, return true if and only if it is an Armstrong number.
 *
 * @author BorisMirage
 * Time: 2019/08/16 16:37
 * Created with IntelliJ IDEA
 */

public class IsArmstrong_1134 {
    /**
     * Note that count the length of integer is by log function.
     *
     * @param N given integer
     * @return true if and only if it is an Armstrong number
     */
    public boolean isArmstrong(int N) {
        int length = (int) Math.log10(N) + 1;
        int out = 0, tmp = N;
        while (tmp > 0) {
            out += Math.pow(tmp % 10, length);
            tmp = tmp / 10;
        }
        return out == N;
    }
}
