package solution.others;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:53
 * Created with IntelliJ IDEA
 */

public class IntToRoman12Test {
    private final IntToRoman_12 test = new IntToRoman_12();

    @Test
    public void test() {
        assertEquals("I", test.intToRoman(1));
        assertEquals("II", test.intToRoman(2));
        assertEquals("III", test.intToRoman(3));
        assertEquals("IV", test.intToRoman(4));
        assertEquals("V", test.intToRoman(5));
        assertEquals("VI", test.intToRoman(6));
        assertEquals("VII", test.intToRoman(7));
        assertEquals("VIII", test.intToRoman(8));
        assertEquals("IX", test.intToRoman(9));
        assertEquals("X", test.intToRoman(10));
        assertEquals("C", test.intToRoman(100));
        assertEquals("D", test.intToRoman(500));
        assertEquals("LVIII", test.intToRoman(58));
        assertEquals("MCMXCIV", test.intToRoman(1994));
    }
}