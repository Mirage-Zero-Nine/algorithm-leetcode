package Solution.Others;

import java.util.ArrayList;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * @author BorisMirage
 * Time: 2018/03/26 12:39
 * Created with IntelliJ IDEA
 */

public class ReverseInteger_7 {
    /**
     * Reverse digits of an integer.
     *
     * @param x integer to be reversed
     * @return reversed number
     * if reverse result is larger than Integer.MAX_VALUE, return 0
     */
    public int reverseInteger(int x) {
        ArrayList<Integer> numArray = new ArrayList<>();

        if (x == 0) {
            return 0;
        }

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

        /* Reverse int Test */
        ReverseInteger_7 reverseIntegerTest = new ReverseInteger_7();
        System.out.println(reverseIntegerTest.reverseInteger(1534236469));
    }
}