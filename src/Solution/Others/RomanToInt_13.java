package Solution.Others;

import java.util.HashMap;

/**
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * @author BorisMirage
 * Time: 2018/06/10 14:30
 * Created with IntelliJ IDEA
 */

public class RomanToInt_13 {
    /**
     * Convert Roman numerals to int
     * I can be placed before V (5) and X (10) to make 4(IV) and 9(IX).
     * X can be placed before L (50) and C (100) to make 40(XL) and 90(XC).
     * C can be placed before D (500) and M (1000) to make 400(CD) and 900(CM).
     *
     * @param s input Roman numerals
     * @return converted int
     */
    public int romanToInt(String s) {
        int result = 0;

        /* Construct Roman numerals to int map */
        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('M', 1000);
        romanMap.put('D', 500);
        romanMap.put('C', 100);
        romanMap.put('L', 50);
        romanMap.put('X', 10);
        romanMap.put('V', 5);
        romanMap.put('I', 1);

        char[] sChar = s.toCharArray();
        int i = s.length() - 1;
        while (i >= 0) {
            result += romanMap.get(sChar[i]);
            if (i > 0) {
                if (romanMap.get(sChar[i - 1]) < romanMap.get(sChar[i])) {
                    result -= romanMap.get(sChar[i - 1]);
                    i -= 2;
                } else {
                    i--;
                }
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {

        /* RomanToInt_13 Test */
        RomanToInt_13 romanIntTest = new RomanToInt_13();
        System.out.println(romanIntTest.romanToInt("MCMXCIV"));
    }
}
