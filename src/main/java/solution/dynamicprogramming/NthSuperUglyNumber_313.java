package solution.dynamicprogramming;

/**
 * Write a program to find the nth super ugly number.
 * Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.
 * Example:
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers
 * Given primes = [2,7,13,19] of size 4.
 * Note:
 * 1 is a super ugly number for any given primes.
 * The given numbers in primes are in ascending order.
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 *
 * @author BorisMirage
 * Time: 2019/08/22 19:05
 * Created with IntelliJ IDEA
 */

public class NthSuperUglyNumber_313 {
    /**
     * Dynamic programming. Almost same as Nth ugly number.
     * Every subsequence is the ugly-sequence itself (1, 2, 3, 4, 5, ...) multiply primes in given array.
     * Each time, choose the smallest one among 3 of the factors.
     * Multiply each with given prime factor as next number.
     * Note that if use heap in this problem may cause TLE.
     *
     * @param n      given n
     * @param primes array of prime numbers
     * @return nth super ugly number
     */
    public int nthSuperUglyNumber(int n, int[] primes) {

        int length = primes.length;
        int[] index = new int[length], out = new int[n];
        out[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < length; j++) {
                min = Math.min(out[index[j]] * primes[j], min);
            }

            out[i] = min;

            for (int j = 0; j < length; j++) {
                if (out[i] % primes[j] == 0) index[j]++;
            }
        }

        return out[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new NthSuperUglyNumber_313().nthSuperUglyNumber(6, new int[]{2, 11, 13}));
    }
}

