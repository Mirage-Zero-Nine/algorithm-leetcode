package solution.others;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:53
 * Created with IntelliJ IDEA
 */

public class IntToRoman_12Test {
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

    @Test
    public void testSingleDigitSubtractiveCases() {
        assertEquals("IV", test.intToRoman(4));
        assertEquals("IX", test.intToRoman(9));
    }

    @Test
    public void testTensSubtractiveCases() {
        assertEquals("XL", test.intToRoman(40));
        assertEquals("XC", test.intToRoman(90));
    }

    @Test
    public void testHundredsSubtractiveCases() {
        assertEquals("CD", test.intToRoman(400));
        assertEquals("CM", test.intToRoman(900));
    }

    @Test
    public void testCompositeHappyCases() {
        assertEquals("XII", test.intToRoman(12));
        assertEquals("XXVII", test.intToRoman(27));
        assertEquals("MMDCCLXVI", test.intToRoman(2766));
    }

    @Test
    public void testRepeatedSymbols() {
        assertEquals("XXX", test.intToRoman(30));
        assertEquals("CCC", test.intToRoman(300));
        assertEquals("MMM", test.intToRoman(3000));
    }

    @Test
    public void testBoundaryMinimum() {
        assertEquals("I", test.intToRoman(1));
    }

    @Test
    public void testBoundaryMaximumGiantCase() {
        assertEquals("MMMCMXCIX", test.intToRoman(3999));
    }

    @Test
    public void testCurrentImplementationForZero() {
        assertEquals("", test.intToRoman(0));
    }

    @Test
    public void testCurrentImplementationForNegativeInput() {
        assertEquals("", test.intToRoman(-1));
    }
}
