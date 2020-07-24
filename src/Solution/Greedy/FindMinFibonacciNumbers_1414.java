package Solution.Greedy;

/**
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k.
 * A Fibonacci number could be used multiple times.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 *
 * @author BorisMirage
 * Time: 2020/04/28 12:21
 * Created with IntelliJ IDEA
 */

public class FindMinFibonacciNumbers_1414 {
    /**
     * Greedy with recursive approach.
     * Subtract the largest Fibonacci number of current k, and find the largest Fibonacci number in the remainder.
     * Keep doing this until the remainder is smaller than 2 or equal to 0.
     *
     * @param k target number
     * @return he minimum number of Fibonacci numbers whose sum is equal to k
     */
    public int findMinFibonacciNumbers(int k) {

        /* Corner case */
        if (k < 2) {
            return k;
        }

        int first = 1, second = 1;      // f(n - 1), f(n - 2)
        while (second <= k) {           // find the largest Fibonacci number less than k, f(n) = f(n - 1) + f(n - 2)
            second += first;            // new f(n)
            first = second - first;     // convert f(n - 2) to f(n - 1)
        }

        return 1 + findMinFibonacciNumbers(k - first);      // one Fibonacci number is found, find next in remainder
    }
}
