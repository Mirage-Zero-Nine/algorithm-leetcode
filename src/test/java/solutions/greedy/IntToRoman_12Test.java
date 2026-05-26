package solutions.greedy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:53
 * Created with IntelliJ IDEA
 */

public class IntToRoman_12Test {
    private final IntToRoman_12 test = new IntToRoman_12();

    /**
     * Reference table for Roman numerals 1..100, used as ground truth for the
     * parameterized 1..100 sweep. Hardcoded to avoid testing the algorithm
     * against itself.
     */
    private static final String[] ROMAN_1_TO_100 = new String[]{
            "",            // index 0 (unused)
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };

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

    /**
     * Iterable sweep: every integer in [1, 100] must produce the canonical
     * Roman numeral. This exercises every combination of subtractive forms
     * (IV, IX, XL, XC) that appears below 100 plus all repeated-symbol runs.
     */
    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("oneToHundred")
    public void testEveryValueFromOneToOneHundred(int input, String expected) {
        assertEquals(expected, test.intToRoman(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToHundred() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(i -> arguments(i, ROMAN_1_TO_100[i]));
    }

    /**
     * Spot-check selected larger values to cover 100..3999 (which would be too
     * verbose to enumerate but cannot be ignored).
     */
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({
            "101, CI",
            "150, CL",
            "199, CXCIX",
            "246, CCXLVI",
            "444, CDXLIV",
            "555, DLV",
            "789, DCCLXXXIX",
            "888, DCCCLXXXVIII",
            "999, CMXCIX",
            "1000, M",
            "1666, MDCLXVI",
            "2024, MMXXIV",
            "2999, MMCMXCIX",
            "3000, MMM",
            "3888, MMMDCCCLXXXVIII",
            "3999, MMMCMXCIX"
    })
    public void testLargerValuesSpotCheck(int input, String expected) {
        assertEquals(expected, test.intToRoman(input));
    }
}
