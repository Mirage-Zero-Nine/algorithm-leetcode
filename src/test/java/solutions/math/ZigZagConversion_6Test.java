package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

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

    @Test
    public void testSingleCharAnyRows() {
        assertEquals("Z", test.convert("Z", 1));
        assertEquals("Z", test.convert("Z", 5));
        assertEquals("Z", test.convert("Z", 100));
    }

    @Test
    public void testNumRowsGreaterOrEqualLength() {
        assertEquals("HELLO", test.convert("HELLO", 5));
        assertEquals("HELLO", test.convert("HELLO", 10));
        assertEquals("ABCDEF", test.convert("ABCDEF", 6));
    }

    @Test
    public void testLeetCodeRows3() {
        assertEquals("PAHNAPLSIIGYIR", test.convert("PAYPALISHIRING", 3));
    }

    @Test
    public void testLeetCodeRows4() {
        assertEquals("PINALSIGYAHRPI", test.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void testTwoRowAlternating() {
        // "ABCDEF" with 2 rows: row0=ACE, row1=BDF -> "ACEBDF"
        assertEquals("ACEBDF", test.convert("ABCDEF", 2));
        // "ABCDEFG" with 2 rows: row0=ACEG, row1=BDF -> "ACEGBDF"
        assertEquals("ACEGBDF", test.convert("ABCDEFG", 2));
    }

    @Test
    public void testLongString1000CharsSeed42NumRows5() {
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append((char) ('a' + rng.nextInt(26)));
        String input = sb.toString();
        String result = test.convert(input, 5);
        assertEquals(1000, result.length());
        // First and last chars of input appear in result
        assertTrue(result.contains(String.valueOf(input.charAt(0))));
        assertTrue(result.contains(String.valueOf(input.charAt(999))));
    }

    @Test
    public void testPropertySameMultisetOfChars() {
        String[] inputs = {"PAYPALISHIRING", "ABCDEFGHIJK", "ZZZZZ", "HelloWorld"};
        int[] rows = {3, 4, 2, 5};
        for (int i = 0; i < inputs.length; i++) {
            String result = test.convert(inputs[i], rows[i]);
            char[] expected = inputs[i].toCharArray();
            char[] actual = result.toCharArray();
            Arrays.sort(expected);
            Arrays.sort(actual);
            assertEquals(new String(expected), new String(actual));
        }
    }

    @Test
    public void testPropertySameLength() {
        String[] inputs = {"", "A", "AB", "PAYPALISHIRING", "ABCDEFGHIJKLMNOP"};
        int[] rows = {3, 1, 5, 4, 7};
        for (int i = 0; i < inputs.length; i++) {
            assertEquals(inputs[i].length(), test.convert(inputs[i], rows[i]).length());
        }
    }
}
