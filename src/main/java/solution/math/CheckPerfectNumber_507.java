package solution.math;

/**
 * Define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
 * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
 *
 * @author BorisMirage
 * Time: 2019/07/13 20:49
 * Created with IntelliJ IDEA
 */

public class CheckPerfectNumber_507 {
    /**
     * Find factor from 1 to n.
     *
     * @param num given number
     * @return whether this number is perfect number
     */
    public boolean checkPerfectNumber(int num) {

        /* Corner case */
        if (num == 1) {
            return false;
        }

        int sum = 0;
        double limit = Math.sqrt(num);
        for (int i = 2; i <= limit; i++) {
            if (num % i == 0) {
                sum += i + num / i;
            }
        }
        sum++;

        return sum == num;
    }
}
