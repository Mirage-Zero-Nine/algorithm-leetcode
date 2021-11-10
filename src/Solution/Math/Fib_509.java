package Solution.Math;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence.
 * Each number is the sum of the two preceding ones, starting from 0 and 1. That is:
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 * @author BorisMirage
 * Time: 2021/11/09 22:47
 * Created with IntelliJ IDEA
 */

public class Fib_509 {
    /**
     * Follow the formula. Set the base case (n == 0 and n == 1), the implement the recursion.
     *
     * @param n nth Fibonacci number
     * @return nth Fibonacci number
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }
}
