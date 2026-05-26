package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfSubstrings_1358Test {

    private final NumberOfSubstrings_1358 solution = new NumberOfSubstrings_1358();

    @Test
    public void testBasicCase() {
        assertEquals(10, solution.numberOfSubstrings("abcabc"));
    }

    @Test
    public void testMinimalValid() {
        assertEquals(1, solution.numberOfSubstrings("abc"));
    }

    @Test
    public void testRepeatedPattern() {
        assertEquals(3, solution.numberOfSubstrings("aaacb"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(0, solution.numberOfSubstrings("aaa"));
    }

    @Test
    public void testTwoCharsOnly() {
        assertEquals(0, solution.numberOfSubstrings("aabb"));
    }

    @Test
    public void testSingleChar() {
        assertEquals(0, solution.numberOfSubstrings("a"));
    }

    @Test
    public void testTwoChars() {
        assertEquals(0, solution.numberOfSubstrings("ab"));
    }

    @Test
    public void testAbcAtEnd() {
        assertEquals(2, solution.numberOfSubstrings("aabc"));
    }

    @Test
    public void testLongerString() {
        // "abcbca" -> substrings containing all a,b,c
        assertEquals(7, solution.numberOfSubstrings("abcbca"));
    }

    @Test
    public void testCbaOrder() {
        assertEquals(1, solution.numberOfSubstrings("cba"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append((char) ('a' + i % 3));
        }
        int result = solution.numberOfSubstrings(sb.toString());
        // pattern "abcabc..." of length 100000, should have many valid substrings
        assert result > 0;
    }
}
