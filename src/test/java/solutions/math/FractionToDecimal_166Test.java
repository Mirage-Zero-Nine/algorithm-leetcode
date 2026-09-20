package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FractionToDecimal_166Test {

    private final FractionToDecimal_166 test = new FractionToDecimal_166();

    @Test
    public void testHappyCases() {
        assertEquals("0.5", test.fractionToDecimal(1, 2));
        assertEquals("2", test.fractionToDecimal(2, 1));
        assertEquals("0.(6)", test.fractionToDecimal(2, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("0", test.fractionToDecimal(0, 1));
        assertEquals("-0.5", test.fractionToDecimal(1, -2));
    }

    @Test
    public void testLargeCase() {
        assertEquals("0.(142857)", test.fractionToDecimal(1, 7));
        assertEquals("0.1(6)", test.fractionToDecimal(1, 6));
    }

    @Test
    public void testWholeNumber() {
        assertEquals("10", test.fractionToDecimal(10, 1));
    }

    @Test
    public void testBothNegative() {
        assertEquals("0.5", test.fractionToDecimal(-1, -2));
    }

    @Test
    public void testNegativeNumerator() {
        assertEquals("-2", test.fractionToDecimal(-2, 1));
    }

    @Test
    public void testRepeatingDecimal() {
        assertEquals("0.(012345679)", test.fractionToDecimal(1, 81));
    }

    @Test
    public void testTerminatingDecimal() {
        assertEquals("0.25", test.fractionToDecimal(1, 4));
        assertEquals("0.125", test.fractionToDecimal(1, 8));
    }

    @Test
    public void testIntMinValue() {
        // Integer.MIN_VALUE / -1 would overflow, but numerator = MIN_VALUE, denominator = 1
        assertEquals("-2147483648", test.fractionToDecimal(Integer.MIN_VALUE, 1));
    }

    @Test
    public void testGiantDenominator() {
        // 1/11 = 0.(09)
        assertEquals("0.(09)", test.fractionToDecimal(1, 11));
        // 1/17 = 0.(0588235294117647)
        assertEquals("0.(0588235294117647)", test.fractionToDecimal(1, 17));
    }

    @Test
    public void testAdditional1() {
        assertEquals("0", test.fractionToDecimal(0, 3));
    }

    @Test
    public void testAdditional2() {
        assertEquals("0.5", test.fractionToDecimal(1, 2));
    }

    @Test
    public void testAdditional3() {
        assertEquals("2", test.fractionToDecimal(2, 1));
    }

    @Test
    public void testAdditional4() {
        assertEquals("-0.5", test.fractionToDecimal(-1, 2));
    }

    @Test
    public void testAdditional5() {
        assertEquals("-0.5", test.fractionToDecimal(1, -2));
    }

    @Test
    public void testAdditional6() {
        assertEquals("0.5", test.fractionToDecimal(-1, -2));
    }

    @Test
    public void testAdditional7() {
        assertEquals("0.(3)", test.fractionToDecimal(1, 3));
    }

    @Test
    public void testAdditional8() {
        assertEquals("0.1(6)", test.fractionToDecimal(1, 6));
    }

    @Test
    public void testAdditional9() {
        assertEquals("3.(142857)", test.fractionToDecimal(22, 7));
    }

    @Test
    public void testAdditional10() {
        assertEquals("2147483647", test.fractionToDecimal(2147483647, 1));
    }
}
