package Solution.Others;

import java.util.Arrays;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Each element in the array contain a single digit.
 * Integer does not contain any leading zero, except the number 0 itself.
 *
 * @author BorisMirage
 * Time: 2018/08/06 14:09
 * Created with IntelliJ IDEA
 */

public class PlusOne_66 {
    /**
     * Be careful about carry of last digit. If last digit is not 9, then directly return input array.
     * Otherwise, calculate each digit if carry is required.
     * Finally, add a new int at front of array as carry.
     *
     * @param digits input int array
     * @return array after plus one.
     */
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {        // if no carry is found in current position, then return array with one added
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }


        int[] out = new int[digits.length + 1];     // if all digits are 9, then add a carry at beginning of output
        out[0] = 1;                                 // carry at top

        return out;
    }

    public static void main(String[] args) {
        PlusOne_66 plusOneTest = new PlusOne_66();
        int[] tt = {4, 3, 2, 1};
        System.out.println(Arrays.toString(plusOneTest.plusOne(tt)));
    }
}
