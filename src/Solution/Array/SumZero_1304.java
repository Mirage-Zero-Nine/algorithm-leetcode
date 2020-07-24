package Solution.Array;

/**
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 *
 * @author BorisMirage
 * Time: 2020/01/13 19:31
 * Created with IntelliJ IDEA
 */

public class SumZero_1304 {
    /**
     * Math solution: out[i] = n - 1 - 2 * i
     * The target is to find a array with n unique numbers, and its sum is 0.
     * This is to find a arithmetic sequence that its sum is 0.
     * Arithmetic sequence sum: (a[0] + a[n - 1]) * n / 2 = 0 => a[0] + a[n-1] = 0.
     * Delta in arithmetic sequence is d = (a[m] - a[n]) / (m - n)
     * Set m and n to n - 1 and 0, it will be (n - 1) * d = a[n - 1] - a[0], and a[n - 1]  = -a[0]
     * It can be found that d * (n - 1) = -2 * a[0].
     * n is the given number, therefore, to satisfied this equation, simply set d to -2, and a[0] to n - 1.
     * Each item in arithmetic sequence can be constructed by a[i] = a[0] - 2 * i, note that sequence starts from 0.
     * Finally, out[i] can be derived that out[i] = n - 1 - 2 * i.
     * Note that d can be set to 2 and a[0] can be set to n - 1 as well.
     *
     * @param n n unique integers
     * @return any array containing n unique integers such that they add up to 0
     */
    public int[] sumZero(int n) {
        int[] out = new int[n];

        /* Corner case */
        if (n <= 1) {
            return out;
        }

        for (int i = 0; i < out.length; i++) {
            out[i] = n - 1 - 2 * i;
        }

        return out;
    }
}
