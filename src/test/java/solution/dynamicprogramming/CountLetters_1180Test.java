package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountLetters_1180Test {

    private final CountLetters_1180 test = new CountLetters_1180();

    @Test
    public void testHappyCases() {
        assertEquals(8, test.countLetters("aaaba"));
        assertEquals(10, test.countLetters("aaaa"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.countLetters("a"));
        assertEquals(2, test.countLetters("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.countLetters("aaaaaaaaaa"));
    }

    @Test
    public void testAllDistinct() {
        assertEquals(3, test.countLetters("abc"));
        assertEquals(5, test.countLetters("abcde"));
    }

    @Test
    public void testAllSame() {
        assertEquals(6, test.countLetters("aaa"));   // 1+2+3
        assertEquals(10, test.countLetters("bbbb")); // 1+2+3+4
    }

    @Test
    public void testAlternating() {
        assertEquals(4, test.countLetters("abab"));
        assertEquals(5, test.countLetters("ababa"));
    }

    @Test
    public void testConsecutiveGroups() {
        assertEquals(6, test.countLetters("aabb"));  // 1+2+1+2=6
        assertEquals(9, test.countLetters("aaabb")); // 1+2+3+1+2=9
    }

    @Test
    public void testSingleRepeatedAtEnd() {
        assertEquals(4, test.countLetters("abb"));   // a(1)+b(1)+b(2) = 4
    }

    @Test
    public void testSingleRepeatedAtStart() {
        assertEquals(4, test.countLetters("aab"));   // a(1)+a(2)+b(1) = 4
    }

    @Test
    public void testGiantCase() {
        // 1000 'a' chars -> sum 1+2+...+1000 = 500500
        String s = "a".repeat(1000);
        assertEquals(500500, test.countLetters(s));
    }
}
