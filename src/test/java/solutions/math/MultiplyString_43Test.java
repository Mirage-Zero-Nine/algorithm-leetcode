package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class MultiplyString_43Test {

    private final MultiplyString_43 test = new MultiplyString_43();

    @Test
    public void testHappyCases() {
        assertEquals("6", test.multiply("2", "3"));
        assertEquals("56088", test.multiply("123", "456"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("0", test.multiply("0", "123"));
        assertEquals("0", test.multiply("0", "0"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("121932631112635269", test.multiply("123456789", "987654321"));
    }

    @Test
    public void testMultiplyByOne() {
        assertEquals("999", test.multiply("999", "1"));
    }

    @Test
    public void testSingleDigits() {
        assertEquals("72", test.multiply("9", "8"));
    }

    @Test
    public void testZeroFirst() {
        assertEquals("0", test.multiply("0", "999999"));
    }

    @Test
    public void testZeroSecond() {
        assertEquals("0", test.multiply("999999", "0"));
    }

    @Test
    public void testSymmetric() {
        assertEquals(test.multiply("12", "34"), test.multiply("34", "12"));
    }

    @Test
    public void testPowersOfTen() {
        assertEquals("100", test.multiply("10", "10"));
    }

    @Test
    public void testGiantCase() {
        String num1 = "9".repeat(100);
        String num2 = "9".repeat(100);
        String result = test.multiply(num1, num2);
        // 10^100 - 1 squared = 10^200 - 2*10^100 + 1
        // result should be 199 digits or 200 digits
        assertEquals(200, result.length());
        // Should end with ...0...01
        assertEquals('1', result.charAt(result.length() - 1));
    }

    @Test
    public void testZeroTimesAnything() {
        assertEquals("0", test.multiply("0", "98765432109876543210"));
        assertEquals("0", test.multiply("98765432109876543210", "0"));
    }

    @Test
    public void testOneTimesX() {
        assertEquals("98765432109876543210", test.multiply("1", "98765432109876543210"));
        assertEquals("98765432109876543210", test.multiply("98765432109876543210", "1"));
    }

    @Test
    public void testTwoDigitMultiplication() {
        assertEquals("5304", test.multiply("52", "102"));
        assertEquals("462", test.multiply("21", "22"));
        assertEquals("9801", test.multiply("99", "99"));
    }

    @Test
    public void testVeryLargeNumbersCrossCheckBigInteger() {
        String a = "12345678901234567890123456789012345678901234567890123";
        String b = "98765432109876543210987654321098765432109876543210987";
        String expected = new BigInteger(a).multiply(new BigInteger(b)).toString();
        assertEquals(expected, test.multiply(a, b));
    }

    @Test
    public void testNumbersEndingInZeros() {
        assertEquals("120000", test.multiply("400", "300"));
        assertEquals("100000000", test.multiply("10000", "10000"));
    }

    @Test
    public void testRepunitAllNines() {
        // 999 * 999 = 998001
        assertEquals("998001", test.multiply("999", "999"));
        // 99999 * 99999 = 9999800001
        assertEquals("9999800001", test.multiply("99999", "99999"));
    }

    @Test
    public void testCommutativityProperty() {
        assertEquals(test.multiply("1357", "2468"), test.multiply("2468", "1357"));
        assertEquals(test.multiply("999999999", "123456789"), test.multiply("123456789", "999999999"));
        String big1 = "9".repeat(50);
        String big2 = "12345678901234567890";
        assertEquals(test.multiply(big1, big2), test.multiply(big2, big1));
    }

    @Test
    public void testNoLeadingZerosInResult() {
        String result = test.multiply("11", "11");
        assertEquals("121", result);
        assertFalse(result.length() > 1 && result.startsWith("0"));

        String result2 = test.multiply("101", "101");
        assertEquals("10201", result2);
        assertFalse(result2.length() > 1 && result2.startsWith("0"));
    }
}
