package Solution.Math;

import java.util.HashMap;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * @author BorisMirage
 * Time: 2019/07/06 15:42
 * Created with IntelliJ IDEA
 */

public class FractionToDecimal_166 {
    /**
     * Use a hash map for a remainder and its associated index while doing the division for same remainder.
     *
     * @param numerator   given numerator
     * @param denominator given denominator
     * @return fraction of numerator and denominator in string format
     */
    public String fractionToDecimal(int numerator, int denominator) {

        /* Corner case */
        if (numerator == 0) {
            return "0";
        }

        StringBuilder res = new StringBuilder();
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");       // positive or negative
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        res.append(num / den);      // integral part
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        res.append(".");        // fractional part
        HashMap<Long, Integer> map = new HashMap<>();
        map.put(num, res.length());

        while (num != 0) {
            map.put(num, res.length());

            num *= 10;
            res.append(num / den);
            num %= den;

            Integer remainderIndex = map.get(num);
            if (remainderIndex != null) {
                res.insert(remainderIndex, "(");
                res.append(")");
                break;
            }
        }
        return res.toString();
    }
}
