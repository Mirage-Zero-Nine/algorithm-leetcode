package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumDecodings_639Test {

    private final NumDecodings_639 test = new NumDecodings_639();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.numDecodings("*"));
        assertEquals(18, test.numDecodings("1*"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDecodings("0"));
        assertEquals(1, test.numDecodings("1"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(96, test.numDecodings("**"));
    }

    @Test
    public void testHappyDigitsOnly() {
        // "12" -> "1","2" or "12" -> 2 ways
        assertEquals(2, test.numDecodings("12"));
    }

    @Test
    public void testHappyThreeDigits() {
        // "226" -> "2,2,6", "22,6", "2,26" -> 3 ways
        assertEquals(3, test.numDecodings("226"));
    }

    @Test
    public void testNegativeLeadingZero() {
        // "00" -> no valid decoding
        assertEquals(0, test.numDecodings("00"));
    }

    @Test
    public void testEdgeStarZero() {
        // "*0" -> star can be 1 or 2 (10 or 20), so 2 ways
        assertEquals(2, test.numDecodings("*0"));
    }

    @Test
    public void testEdgeZeroStar() {
        // "0*" -> starts with 0, no valid single decode for first char
        assertEquals(0, test.numDecodings("0*"));
    }

    @Test
    public void testHappyStarDigit() {
        // "*1" -> one char: 9 ways for *, 1 way for 1 = 9; two chars: *1 where *=1->11, *=2->21 = 2
        // total = 9*1 + 2 = 11
        assertEquals(11, test.numDecodings("*1"));
    }

    @Test
    public void testGiantCase() {
        // Long string of "1" repeated 100 times
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) sb.append('1');
        int result = test.numDecodings(sb.toString());
        // Should be a positive number (Fibonacci-like growth mod 10^9+7)
        assertEquals(true, result > 0);
    }
}
