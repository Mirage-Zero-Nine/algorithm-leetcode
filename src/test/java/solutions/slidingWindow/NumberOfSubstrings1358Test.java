package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfSubstrings1358Test {

    private final NumberOfSubstrings_1358 test = new NumberOfSubstrings_1358();

    @Test
    public void testHappyCases() {
        assertEquals(10, test.numberOfSubstrings("abcabc"));
        assertEquals(3, test.numberOfSubstrings("aaacb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numberOfSubstrings("abc"));
        assertEquals(0, test.numberOfSubstrings("aab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(28, test.numberOfSubstrings("abcabcabc"));
    }

    @Test
    public void testOnlyTwoChars() {
        assertEquals(0, test.numberOfSubstrings("aabb"));
        assertEquals(0, test.numberOfSubstrings("ab"));
    }

    @Test
    public void testSingleChar() {
        assertEquals(0, test.numberOfSubstrings("a"));
        assertEquals(0, test.numberOfSubstrings("aaaa"));
    }

    @Test
    public void testMinimalWithAllThree() {
        assertEquals(1, test.numberOfSubstrings("bca"));
        assertEquals(1, test.numberOfSubstrings("cab"));
    }

    @Test
    public void testRepeatedPattern() {
        // "abcabc" = 10, already tested; "abcab" -> substrings containing all: abc(1), abca(2), abcab(3), bcab(4)... 
        // Let's just verify
        assertEquals(6, test.numberOfSubstrings("abcab"));
    }

    @Test
    public void testLongSameCharWithAbc() {
        // "aaabcaa" -> first abc at index 2,3,4. substrings containing all three:
        assertEquals(11, test.numberOfSubstrings("aaabcaa"));
    }

    @Test
    public void testAllThreeAtEnd() {
        // "aaaabc" -> only substrings ending at or after index 5 that include all three
        assertEquals(4, test.numberOfSubstrings("aaaabc"));
    }

    @Test
    public void testGiantCase() {
        // "abc" repeated 10000 times = 30000 chars
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) sb.append("abc");
        int result = test.numberOfSubstrings(sb.toString());
        // Should be a large positive number
        assertEquals(true, result > 0);
    }
}
