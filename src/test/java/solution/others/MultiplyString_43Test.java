package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
