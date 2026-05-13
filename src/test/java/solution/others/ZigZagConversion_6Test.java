package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ZigZagConversion_6Test {

    private final ZigZagConversion_6 test = new ZigZagConversion_6();

    @Test
    public void testHappyCases() {
        assertEquals("PAHNAPLSIIGYIR", test.convert("PAYPALISHIRING", 3));
        assertEquals("PINALSIGYAHRPI", test.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("A", test.convert("A", 1));
        assertEquals("AB", test.convert("AB", 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals("ABCDEFGHIJK", test.convert("ABCDEFGHIJK", 1));
    }

    @Test
    public void testNumRowsEqualsLength() {
        assertEquals("ABC", test.convert("ABC", 3));
    }

    @Test
    public void testNumRowsGreaterThanLength() {
        assertEquals("AB", test.convert("AB", 5));
    }

    @Test
    public void testTwoRows() {
        assertEquals("ACBD", test.convert("ABCD", 2));
    }

    @Test
    public void testSingleChar() {
        assertEquals("A", test.convert("A", 3));
    }

    @Test
    public void testEmptyString() {
        assertEquals("", test.convert("", 3));
    }

    @Test
    public void testNumRowsOne() {
        assertEquals("PAYPALISHIRING", test.convert("PAYPALISHIRING", 1));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append((char) ('a' + i % 26));
        String input = sb.toString();
        String result = test.convert(input, 50);
        assertEquals(1000, result.length());
        // First char of result should be first char of input (row 0 starts at index 0)
        assertEquals('a', result.charAt(0));
    }
}
