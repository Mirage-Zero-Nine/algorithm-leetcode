package solution.others;

/**
 * Implement atoi which converts a string to an integer.
 * First, discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, takes an optional initial plus or minus sign followed by as many numerical digits as possible.
 * Interprets them as a numerical value.
 * The string can contain additional characters after those that form the integral number.
 * They will be ignored and have no effect on the behavior of this function.
 * If the first sequence of non-whitespace characters in str is not a valid digit, or not +/-, no conversion is performed.
 * If no valid conversion could be performed, return 0.
 *
 * @author BorisMirage
 * Time: 2018/03/26 15:28
 * Created with IntelliJ IDEA
 */

public class MyAtoi_8 {
    /**
     * Corner cases: (return 0)
     * 1. The whole string only contains white space.
     * 2. String like "+-1".
     *
     * @param str input string
     * @return integer that is converted
     */
    public int myAtoi(String str) {

        /* Corner case */
        if (str == null || str.length() == 0) {
            return 0;
        }

        int p = 0, sign = 1;

        while (p < str.length() && str.charAt(p) == ' ') {
            p++;        // move to first non-space char
        }

        if (p == str.length()) {
            return 0;
        }

        if (!Character.isDigit(str.charAt(p))) {
            if (str.charAt(p) != '+' && str.charAt(p) != '-') {     // if first non-space char is not +, -, or digit
                return 0;
            }
            sign = (str.charAt(p++) == '-') ? -1 : 1;       // negative or positive
        }

        long tmp = 0;

        while (p < str.length() && Character.isDigit(str.charAt(p))) {      // end loop at first non-digit char
            tmp = tmp * 10 + str.charAt(p++) - '0';
            if (tmp * sign > Integer.MAX_VALUE) {       // avoid overflow
                return Integer.MAX_VALUE;
            }
            if (tmp * sign < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) tmp * sign;
    }

    public static void main(String[] args) {

        MyAtoi_8 test = new MyAtoi_8();
        System.out.println(test.myAtoi("   - 321"));      // 0
        System.out.println(test.myAtoi("  -0012a42"));    // -12
        System.out.println(test.myAtoi("   +0 123"));     // 0
        System.out.println(test.myAtoi("    010"));       // 10
        System.out.println(test.myAtoi("9223372036854775808"));       // 2147483647 (larger than long)
    }
}
