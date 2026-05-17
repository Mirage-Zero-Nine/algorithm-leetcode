package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxVowels_1456Test {

    private final MaxVowels_1456 solution = new MaxVowels_1456();

    @Test
    public void testBasicCase() {
        assertEquals(3, solution.maxVowels("abciiidef", 3));
    }

    @Test
    public void testAllVowels() {
        assertEquals(5, solution.maxVowels("aeiou", 5));
    }

    @Test
    public void testVowelsAtEnd() {
        assertEquals(2, solution.maxVowels("bcdfae", 2));
    }

    @Test
    public void testSingleCharVowel() {
        assertEquals(1, solution.maxVowels("a", 1));
    }

    @Test
    public void testWindowSmallerThanVowelCluster() {
        assertEquals(2, solution.maxVowels("aeiou", 2));
    }

    @Test
    public void testNoVowels() {
        assertEquals(0, solution.maxVowels("bcdfg", 3));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, solution.maxVowels("", 1));
    }

    @Test
    public void testNullString() {
        assertEquals(0, solution.maxVowels(null, 1));
    }

    @Test
    public void testKEqualsStringLength() {
        assertEquals(2, solution.maxVowels("aebcd", 5));
    }

    @Test
    public void testSingleConsonant() {
        assertEquals(0, solution.maxVowels("b", 1));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(i % 2 == 0 ? 'a' : 'b');
        }
        // pattern: ababab... in window of 10, 5 vowels
        assertEquals(5, solution.maxVowels(sb.toString(), 10));
    }
}
