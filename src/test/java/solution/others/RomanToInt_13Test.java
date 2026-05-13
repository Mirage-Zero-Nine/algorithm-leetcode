package solution.others;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:08
 * Created with IntelliJ IDEA
 */

public class RomanToInt_13Test {
    private final RomanToInt_13 test = new RomanToInt_13();

    @Test
    public void test() {
        for (int i = 0; i < romanGenerator().size(); i++) {
            Assertions.assertEquals(i + 1, test.romanToInt(romanGenerator().get(i)));
        }
    }

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

    private List<String> romanGenerator() {
        return Lists.newArrayList(
                "I",
                "II",
                "III",
                "IV",
                "V"
        );
    }
}
