package solution.math;

/**
 * Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)
 * Since the answer may be large, return the answer modulo 10^9 + 7.
 *
 * @author BorisMirage
 * Time: 2019/08/31 19:35
 * Created with IntelliJ IDEA
 */

public class NumPrimeArrangements_1175 {

    /**
     * Count # of prime number and do the factorial from 1 to # of prime number, and from 1 to # of composite number.
     *
     * @param n permutations of 1 to n
     * @return number of permutations of 1 to n that prime numbers are at prime indices (1-indexed)
     */
    public int numPrimeArrangements(int n) {

        /* Corner case */
        if (n <= 2) {
            return 1;
        }

        int count = countPrimes(n + 1);

        long total = 1;

        for (int i = 1; i <= count; i++) {
            total = total * i % ((int) Math.pow(10, 9) + 7);
        }
        for (int i = 1; i <= n - count; i++) {
            total = total * i % ((int) Math.pow(10, 9) + 7);
        }

        return (int) total;
    }

    /**
     * Count prime number.
     * First, filter out all even number (n / 2).
     * Then filter out all odd composite #.
     * Since even number has been filter out, only odd composite # should be checked.
     * Started from min odd prime # 3, and multiple 3 (even # has been filtered).
     * Each time, add 2 * i, the inner loop will actually be j = i * i + 2 * i * n.
     * In this way to filter out all composite #.
     *
     * @param n count # of prime number from 1 to n
     * @return # of prime numbers
     */
    public int countPrimes(int n) {

        if (n < 2) {
            return 0;
        }

        boolean[] f = new boolean[n];
        int count = n / 2;      // filter out all even numbers

        for (int i = 3; i * i < n; i += 2) {       // filter out multiple of odd # from 3 to sqrt(n)

            for (int j = i * i; j < n && !f[i]; j += 2 * i) {        // j = i * i + 2 * i * n, i will be the factor
                if (!f[j]) {        // odd # + even # = odd #, filter out odd composite #
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumPrimeArrangements_1175 test = new NumPrimeArrangements_1175();
        System.out.println(test.numPrimeArrangements(100));
    }
}
