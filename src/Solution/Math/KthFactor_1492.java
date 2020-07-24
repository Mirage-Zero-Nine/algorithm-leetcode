package Solution.Math;

/**
 * Given two positive integers n and k.
 * A factor of an integer n is defined as an integer i where n % i == 0.
 * Consider a list of all factors of n sorted in ascending order, return the kth factor in this list.
 * Return -1 if n has less than k factors.
 *
 * @author BorisMirage
 * Time: 2020/06/27 11:46
 * Created with IntelliJ IDEA
 */

public class KthFactor_1492 {
    /**
     * The factors of given n can be divided into two parts, they are divided by sqrt(n).
     * Hence, iterate from 1 to sqrt(n), to find the first half of all factors.
     * Then iterate back from sqrt(n) to 1 to find the second half of all factors.
     *
     * @param n given n
     * @param k kth factor
     * @return the kth factor in this list, or -1 if n has less than k factors
     */
    public int kthFactor(int n, int k) {
        int i = 1;
        while (i * i <= n) {      // first half of factors
            if (n % i == 0 && --k == 0) {
                return i;
            }
            i++;
        }

        i--;
        while (i >= 1) {        // second half of factors
            if (i * i != n && n % i == 0 && --k == 0) {     // i * i == n can not be counted as two factors
                return n / i;
            }
            i--;
        }

        return -1;
    }
}
