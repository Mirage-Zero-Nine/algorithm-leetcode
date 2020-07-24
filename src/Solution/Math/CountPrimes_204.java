package Solution.Math;

/**
 * Count the number of prime numbers less than a non-negative number, n.
 *
 * @author BorisMirage
 * Time: 2019/09/01 10:13
 * Created with IntelliJ IDEA
 */

public class CountPrimes_204 {
    /**
     * Count prime number.
     * First, filter out all even number (n / 2).
     * Then filter out all odd composite #.
     * Since even number has been filter out, only odd composite # should be checked.
     * Started from min odd prime # 3, and multiple 3 (even # has been filtered).
     * Each time, add 2 * i, the inner loop will actually be j = i * i + 2 * i * n.
     * In this way to filter out all composite #.
     *
     * @param n less than a non-negative number, n
     * @return number of prime numbers less than a non-negative number, n
     */
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }

        boolean[] f = new boolean[n];
        int count = n / 2;      // filter out all even numbers

        for (int i = 3; i * i < n; i += 2) {        // filter out multiple of odd # from 3 to sqrt(n)
            for (int j = i * i; j < n && !f[i]; j += 2 * i) {       // j = i * i + 2 * i * n, i will be the factor
                if (!f[j]) {        // odd # + even # = odd #, filter out odd composite #
                    count--;
                    f[j] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountPrimes_204 test = new CountPrimes_204();
        System.out.println(test.countPrimes(1000));     // 168
        System.out.println(test.countPrimes(5000));     // 699
        System.out.println(test.countPrimes(10000));    // 1229
        System.out.println(test.countPrimes(100000));   // 9592
    }
}
