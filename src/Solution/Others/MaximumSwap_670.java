package Solution.Others;

/**
 * @author BorisMirage
 * Time: 2019/07/13 14:30
 * Created with IntelliJ IDEA
 */

public class MaximumSwap_670 {
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
