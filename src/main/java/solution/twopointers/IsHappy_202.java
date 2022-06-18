package solution.twopointers;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle.
 * Those numbers for which this process ends in 1 are happy numbers.
 *
 * @author BorisMirage
 * Time: 2019/10/22 11:37
 * Created with IntelliJ IDEA
 */

public class IsHappy_202 {
    /**
     * It's like find a meeting point on two linked list. Use two pointers, fast and slow.
     * Fast pointer convert to next int as happy number rules, while slow pointer only follow once.
     * They will eventually meet, since it will all end into an endless loop.
     * If the meeting value is 1, return true.
     *
     * @param n given int
     * @return if a number is "happy"
     */
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        boolean stop = false;

        while (!stop) {
            fast = helper(fast);
            fast = helper(fast);
            slow = helper(slow);
            if (fast == slow) {
                stop = true;
            }
        }

        return slow == 1;
    }

    /**
     * Calculate number's each digit square sum.
     *
     * @param n given int
     * @return digit square sum
     */
    private int helper(int n) {
        int sum = 0;
        while (n > 0) {
            int tmp = n % 10;
            sum += (tmp * tmp);
            n /= 10;
        }
        return sum;
    }
}
