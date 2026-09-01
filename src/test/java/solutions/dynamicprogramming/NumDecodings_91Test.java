package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumDecodings_91Test {

    private final NumDecodings_91 test = new NumDecodings_91();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.numDecodings("12"));
        assertEquals(3, test.numDecodings("226"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDecodings("0"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.numDecodings("1021"));
    }

    @Test
    public void testSingleDigit() {
        assertEquals(1, test.numDecodings("1"));
        assertEquals(1, test.numDecodings("9"));
    }

    @Test
    public void testLeadingZero() {
        assertEquals(0, test.numDecodings("01"));
        assertEquals(0, test.numDecodings("00"));
    }

    @Test
    public void testAllOnes() {
        // "111" -> 1,1,1 | 11,1 | 1,11 = 3
        assertEquals(3, test.numDecodings("111"));
    }

    @Test
    public void testContainsZero() {
        assertEquals(1, test.numDecodings("10"));
        assertEquals(1, test.numDecodings("20"));
        assertEquals(0, test.numDecodings("30")); // 30 is invalid
    }

    @Test
    public void testLargerNumbers() {
        // "27" -> only 2,7 (27 > 26)
        assertEquals(1, test.numDecodings("27"));
        // "26" -> 2,6 | 26 = 2
        assertEquals(2, test.numDecodings("26"));
    }

    @Test
    public void testMiddleZero() {
        assertEquals(1, test.numDecodings("101"));
        assertEquals(1, test.numDecodings("2010"));
    }

    @Test
    public void testGiantCase() {
        // "1111111111" (10 ones) -> Fibonacci-like: f(10) = 89
        assertEquals(89, test.numDecodings("1111111111"));
    }

    @Test
    public void testExhaustiveShortDigitStrings() {
        // Exhaustively cover lengths 1..6 over a compact alphabet that includes
        // zero, valid two-digit codes, and two-digit values greater than 26.
        assertAllDigitStrings("", 6);
    }

    private void assertAllDigitStrings(String prefix, int remainingLength) {
        if (!prefix.isEmpty()) {
            assertEquals(decodeCount(prefix, 0), test.numDecodings(prefix), prefix);
        }
        if (remainingLength == 0) {
            return;
        }

        for (char digit : "0126789".toCharArray()) {
            assertAllDigitStrings(prefix + digit, remainingLength - 1);
        }
    }

    /** Independent recursive oracle based directly on the 1..26 encoding rule. */
    private int decodeCount(String s, int index) {
        if (index == s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }

        int count = decodeCount(s, index + 1);
        if (index + 1 < s.length()) {
            int value = (s.charAt(index) - '0') * 10 + s.charAt(index + 1) - '0';
            if (value <= 26) {
                count += decodeCount(s, index + 2);
            }
        }
        return count;
    }
}
