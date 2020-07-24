package Solution.BitManipulation;

/**
 * Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * @author BorisMirage
 * Time: 2018/08/07 13:53
 * Created with IntelliJ IDEA
 */

public class AddBinary_67 {
    /**
     * Traverse all char in string. Use XOR as addition.
     * Be aware of carry.
     *
     * @param a first binary string
     * @param b second binary string
     * @return sum of a and b
     */
    public String addBinary(String a, String b) {

        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();
        StringBuilder sb = new StringBuilder();

        int i = aArray.length - 1;
        int j = bArray.length - 1;
        int aByte, bByte;
        int carry = 0, sum;

        while (i > -1 || j > -1 || carry == 1) {
            aByte = (i > -1) ? Character.getNumericValue(aArray[i--]) : 0;      // get current digit
            bByte = (j > -1) ? Character.getNumericValue(bArray[j--]) : 0;      // get current digit

            sum = aByte ^ bByte ^ carry;        // XOR as addition

            carry = ((aByte + bByte + carry) > 1) ? 1 : 0;
            sb.append(sum);
        }

        return sb.reverse().toString();
    }
}
