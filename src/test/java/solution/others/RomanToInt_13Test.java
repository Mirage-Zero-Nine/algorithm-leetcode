package solution.others;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:08
 * Created with IntelliJ IDEA
 */

public class RomanToInt_13Test {
    private final RomanToInt_13 test = new RomanToInt_13();

    /**
     * Reference Roman numerals 1..100. Identical to the table in
     * {@link IntToRoman_12Test} so the two suites cannot drift apart.
     * Index 0 is unused.
     */
    private static final String[] ROMAN_1_TO_100 = new String[]{
            "",
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
    public void testSpecialCase() {
        Assertions.assertEquals(58, test.romanToInt("LVIII"));
        Assertions.assertEquals(1994, test.romanToInt("MCMXCIV"));
    }

    @Test
    public void testHappyCases() {
        Assertions.assertEquals(6, test.romanToInt("VI"));
        Assertions.assertEquals(27, test.romanToInt("XXVII"));
        Assertions.assertEquals(49, test.romanToInt("XLIX"));
        Assertions.assertEquals(388, test.romanToInt("CCCLXXXVIII"));
    }

    @Test
    public void testEdgeAndGiantCases() {
        Assertions.assertEquals(9, test.romanToInt("IX"));
        Assertions.assertEquals(40, test.romanToInt("XL"));
        Assertions.assertEquals(90, test.romanToInt("XC"));
        Assertions.assertEquals(400, test.romanToInt("CD"));
        Assertions.assertEquals(900, test.romanToInt("CM"));
        Assertions.assertEquals(3999, test.romanToInt("MMMCMXCIX"));
    }

    @Test
    public void testSingleCharacters() {
        Assertions.assertEquals(1, test.romanToInt("I"));
        Assertions.assertEquals(5, test.romanToInt("V"));
        Assertions.assertEquals(10, test.romanToInt("X"));
        Assertions.assertEquals(50, test.romanToInt("L"));
        Assertions.assertEquals(100, test.romanToInt("C"));
        Assertions.assertEquals(500, test.romanToInt("D"));
        Assertions.assertEquals(1000, test.romanToInt("M"));
    }

    @Test
    public void testRepeatedCharacters() {
        Assertions.assertEquals(3, test.romanToInt("III"));
        Assertions.assertEquals(30, test.romanToInt("XXX"));
        Assertions.assertEquals(300, test.romanToInt("CCC"));
        Assertions.assertEquals(3000, test.romanToInt("MMM"));
    }

    @Test
    public void testSubtractivePairs() {
        Assertions.assertEquals(4, test.romanToInt("IV"));
        Assertions.assertEquals(14, test.romanToInt("XIV"));
        Assertions.assertEquals(44, test.romanToInt("XLIV"));
        Assertions.assertEquals(99, test.romanToInt("XCIX"));
    }

    @Test
    public void testMediumValues() {
        Assertions.assertEquals(621, test.romanToInt("DCXXI"));
        Assertions.assertEquals(1776, test.romanToInt("MDCCLXXVI"));
        Assertions.assertEquals(2023, test.romanToInt("MMXXIII"));
    }

    @Test
    public void testLargeValues() {
        Assertions.assertEquals(3888, test.romanToInt("MMMDCCCLXXXVIII"));
        Assertions.assertEquals(2494, test.romanToInt("MMCDXCIV"));
    }

    @Test
    public void testConsecutiveSubtractions() {
        Assertions.assertEquals(1944, test.romanToInt("MCMXLIV"));
        Assertions.assertEquals(999, test.romanToInt("CMXCIX"));
    }

    @Test
    public void testBoundaryMinimum() {
        Assertions.assertEquals(1, test.romanToInt("I"));
    }

    /**
     * Iterable sweep across the entire range [1, 100] using a hardcoded
     * reference table. Each integer's canonical Roman numeral must
     * decode back to that integer.
     */
    @ParameterizedTest(name = "{0} <- {1}")
    @MethodSource("oneToHundred")
    public void testEveryRomanFromOneToOneHundred(int expected, String roman) {
        Assertions.assertEquals(expected, test.romanToInt(roman));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToHundred() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(i -> arguments(i, ROMAN_1_TO_100[i]));
    }

    /**
     * Spot-check selected larger Roman numerals to cover 100..3999.
     */
    @ParameterizedTest(name = "{0} <- {1}")
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
    public void testLargerValuesSpotCheck(int expected, String roman) {
        Assertions.assertEquals(expected, test.romanToInt(roman));
    }

    /**
     * Roundtrip cross-check: convert int -> Roman -> int across the full
     * LeetCode range [1, 3999] and verify the original integer is
     * recovered. Catches any encode/decode asymmetry between the two
     * implementations.
     */
    @Test
    public void testRoundtripAgainstIntToRoman() {
        IntToRoman_12 encoder = new IntToRoman_12();
        for (int n = 1; n <= 3999; n++) {
            String roman = encoder.intToRoman(n);
            Assertions.assertEquals(n, test.romanToInt(roman),
                    "roundtrip failed for n=" + n + " (encoded as " + roman + ")");
        }
    }
}
