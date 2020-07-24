package Solution.Others;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together.
 * Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is written as IV.
 * Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 *
 * @author BorisMirage
 * Time: 2018/06/10 15:04
 * Created with IntelliJ IDEA
 */
public class IntToRoman_12 {
    /**
     * Subtracting int num from largest Roman numeral (M).
     * I can be placed before V (5) and X (10) to make 4(IV) and 9(IX).
     * X can be placed before L (50) and C (100) to make 40(XL) and 90(XC).
     * C can be placed before D (500) and M (1000) to make 400(CD) and 900(CM).
     * Started from the largest int(1000), subtract the largest that is not larger than current num.
     * Combine_77 represented Roman numerals each subtraction.
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * @param num input int number
     * @return string Roman numerals
     */
    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.length; i++) {       // subtracting int num from largest Roman numeral (M)
            while (num >= values[i]) {
                num -= values[i];
                result.append(numerals[i]);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        /* IntToRoman_12 Test */
        IntToRoman_12 intToRomanTest = new IntToRoman_12();
        System.out.println(intToRomanTest.intToRoman(100));
    }
}
