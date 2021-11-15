package Solution.Others;

/**
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 *
 * @author BorisMirage
 * Time: 2019/07/13 14:30
 * Created with IntelliJ IDEA
 */

public class MaximumSwap_670 {
    /**
     * Use buckets to record the last position of digit 0 - 9 in the int.
     * Loop through the num array from left to right.
     * For each position, check whether there exists a larger digit in this int (start from 9 to current digit).
     * Also make sure the position of this larger digit is behind the current one.
     * If found it, swap these two digits and return the result.
     *
     * @param num given number
     * @return the maximum valued number
     */
    public int maximumSwap(int num) {

        /* Corner case */
        if (num / 10 == 0) {
            return num;
        }

        char[] digits = Integer.toString(num).toCharArray();

        int firstGreater = 1;      // find the first digit that is not in descending order

        while (firstGreater < digits.length && digits[firstGreater - 1] >= digits[firstGreater]) {
            firstGreater++;
            if (firstGreater == digits.length) {
                return num;     // all digits are in descending order
            }
        }

        char max = '0';
        int maxPosition = firstGreater;

        for (int i = firstGreater; i < digits.length; i++) {      // find max digit in remain digits
            if (max <= digits[i]) {
                max = digits[i];
                maxPosition = i;
            }
        }

        for (int i = 0; i < firstGreater; i++) {    // find first digit that smaller than max digit in the second part

            if (max > digits[i]) {
                StringBuilder s = new StringBuilder(String.valueOf(digits));
                s.setCharAt(maxPosition, digits[i]);
                s.setCharAt(i, max);
                return Integer.parseInt(s.toString());      // no need to check overflow since max value is 10^8
            }
        }
        return num;
    }
}
