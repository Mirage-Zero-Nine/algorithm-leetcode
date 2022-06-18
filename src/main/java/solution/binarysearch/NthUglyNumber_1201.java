package solution.binarysearch;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive integers which are divisible by a or b or c.
 * Constraints:
 * 1. 1 <= n, a, b, c <= 10^9
 * 2. 1 <= a * b * c <= 10^18
 * 3. It's guaranteed that the result will be in range [1, 2 * 10^9]
 *
 * @author BorisMirage
 * Time: 2019/09/21 19:33
 * Created with IntelliJ IDEA
 */

public class NthUglyNumber_1201 {
    /**
     * Calculate how many numbers from 1 to NUM are divisible by either a, b or c:
     * (NUM / a) + (NUM / b) + (NUM / c) – (NUM / lcm(a, b)) – (NUM / lcm(b, c)) – (NUM / lcm(a, c)) + (NUM / lcm(a, b, c))
     * In this problem, NUM can be 2*(10^9) (or Integer.MAX_VALUE).
     * Then use binary search to find nth ugly number (under this definition).
     *
     * @param n nth ugly number
     * @param a first prime number
     * @param b second prime number
     * @param c third prime number
     * @return n-th ugly number
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        int low = 1, high = Integer.MAX_VALUE, mid;

        while (low < high) {
            mid = low + (high - low) / 2;

            if (count(a, b, c, mid) < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        /*
         * Return the number of previous binary search's right bound.
         * From this range [1, high], there are exactly n ugly numbers and the range can not be shrunk. */
        return high;
    }

    /**
     * Count how many numbers from 1 to NUM are divisible by either a, b or c by this formula:
     * (NUM / a) + (NUM / b) + (NUM / c) – (NUM / lcm(a, b)) – (NUM / lcm(b, c)) – (NUM / lcm(a, c)) + (NUM / lcm(a, b, c))
     * Also, lcm(a,b,c) = lcm(a,lcm(b,c)).
     *
     * @param a   number a
     * @param b   number b
     * @param c   number c
     * @param num upper bound of searching range
     * @return LCM of a, b and c
     */
    private int count(long a, long b, long c, long num) {
        return (int) ((num / a) + (num / b) + (num / c) - (num / lcm(a, b)) - (num / lcm(b, c)) - (num / lcm(a, c)) + (num / lcm(a, lcm(b, c))));
    }

    /**
     * Calculate GCD (Greatest Common Divisor) of a and b.
     *
     * @param a first number
     * @param b second number
     * @return GCD of a and b
     */
    private long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }

        return gcd(b % a, a);
    }

    /**
     * Calculate the LCM (Least Common Multiple) of a and b.
     *
     * @param a first number
     * @param b second number
     * @return LCM (Least Common Multiple) of a and b
     */
    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        System.out.println(new NthUglyNumber_1201().nthUglyNumber(8, 2, 3, 5));
        System.out.println(new NthUglyNumber_1201().nthUglyNumber(4, 2, 3, 4));
        System.out.println(new NthUglyNumber_1201().nthUglyNumber(5, 2, 11, 13));
        System.out.println(new NthUglyNumber_1201().nthUglyNumber(1000000000, 2, 217983653, 336916467));
    }
}
