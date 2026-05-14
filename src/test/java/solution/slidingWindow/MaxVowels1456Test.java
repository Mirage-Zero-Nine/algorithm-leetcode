package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxVowels1456Test {

    private final MaxVowels_1456 test = new MaxVowels_1456();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.maxVowels("abciiidef", 3));
        assertEquals(2, test.maxVowels("aeiou", 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxVowels("", 1));
        assertEquals(0, test.maxVowels("bcd", 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.maxVowels("leetcode", 3));
    }

    @Test
    public void testAllVowels() {
        assertEquals(5, test.maxVowels("aeiou", 5));
        assertEquals(3, test.maxVowels("aeiou", 3));
    }

    @Test
    public void testNoVowels() {
        assertEquals(0, test.maxVowels("bcdfg", 3));
        assertEquals(0, test.maxVowels("xyz", 2));
    }

    @Test
    public void testKEqualsStringLength() {
        assertEquals(3, test.maxVowels("aei", 3));
        assertEquals(1, test.maxVowels("abc", 3));
    }

    @Test
    public void testKEqualsOne() {
        assertEquals(1, test.maxVowels("abcde", 1));
        assertEquals(0, test.maxVowels("bcd", 1));
    }

    @Test
    public void testSingleChar() {
        assertEquals(1, test.maxVowels("a", 1));
        assertEquals(0, test.maxVowels("b", 1));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, test.maxVowels(null, 1));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("aeiou");
        }
        assertEquals(1000, test.maxVowels(sb.toString(), 1000));
    }
}
