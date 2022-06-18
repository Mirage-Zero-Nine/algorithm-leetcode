package solution.others;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * @author BorisMirage
 * Time: 2018/03/26 12:39
 * Created with IntelliJ IDEA
 */

public class Reverse_7 {
    /**
     * Keep a list to store every digit of given int.
     *
     * @param x integer to be reversed
     * @return reversed number
     * if reverse result is larger than Integer.MAX_VALUE, return 0
     */
    public int reverse(int x) {

        /* Corner case */
        if (x == 0) {
            return 0;
        }

        List<Integer> numArray = new ArrayList<>();

        long result = 0;
        int count = 0;

        while (x != 0) {
            numArray.add(x % 10);
            x = x / 10;
        }
        while (numArray.size() > 0) {
            result = (long) (result + numArray.get(numArray.size() - 1) * Math.pow(10, count));
            count += 1;
            numArray.remove(numArray.size() - 1);
        }

        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) result;
    }

    public static void main(String[] args) {

        Reverse_7 reverseIntegerTest = new Reverse_7();
        System.out.println(reverseIntegerTest.reverse(1534236469));
    }
}