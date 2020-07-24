package Solution.Others;

/**
 * Given two non-negative integers num1 and num2 represented as strings.
 * Return the product of num1 and num2, also represented as a string.
 * Note:
 * 1. The length of both num1 and num2 is < 110.
 * 2. Both num1 and num2 contain only digits `0-9`.
 * 3. Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * 4. Do not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * @author BorisMirage
 * Time: 2018/06/24 14:16
 * Created with IntelliJ IDEA
 */

public class MultiplyString_43 {
    /**
     * Divide multiply into steps, it can be regarded as one digit multi one digit in each time.
     * Starting at last digit of two numbers, which is the basic unit of two numbers.
     * Then move to the beginning of two strings, which is the highest digit.
     * Under each multiplication, multiply two digits and add previous carry (tens place).
     * Then calculate units place and tens place.
     * Note that the larger index in temporary array is unit place, and smaller one is tens place.
     *
     * @param num1 first input number in string format
     * @param num2 second input number in string format
     * @return multiply result in string format
     */
    public String multiply(String num1, String num2) {

        /* Corner case */
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }

        int n1 = num1.length(), n2 = num2.length(), current;
        int[] tmp = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {         // starts from the last digit of given string
            for (int j = n2 - 1; j >= 0; j--) {

                current = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');      // current two digits multiplication
                current += tmp[i + j + 1];      // add carry from previous multiplication

                tmp[i + j + 1] = current % 10;  // units place of multiplication
                tmp[i + j] += current / 10;     // tens place of multiplication
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {      // first digit in array (w/o leading 0) is the highest digit
            if (i != 0 || tmp[i] != 0) {        // avoid leading 0
                sb.append(tmp[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        MultiplyString_43 multiplyTest = new MultiplyString_43();
        String a1 = "123";
        String a2 = "456";
        System.out.println(multiplyTest.multiply(a1, a2));
    }
}
