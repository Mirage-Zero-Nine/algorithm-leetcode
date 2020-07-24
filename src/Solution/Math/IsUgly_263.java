package Solution.Math;

/**
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * @author BorisMirage
 * Time: 2019/08/22 18:52
 * Created with IntelliJ IDEA
 */

public class IsUgly_263 {
    /**
     * If there are any other prime factor than 2, 3, 5, then return false.
     * Any num < 1 is not ugly number.
     *
     * @param num given number
     * @return if given number is an ugly number
     */
    public boolean isUgly(int num) {

        /* Corner case */
        if (num < 1) {
            return false;
        }

        while (num % 2 == 0) {
            num = num / 2;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }

        while (num % 5 == 0) {
            num = num / 5;
        }

        return num == 1;
    }
}
