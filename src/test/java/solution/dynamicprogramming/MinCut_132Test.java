package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCut_132Test {

    private final MinCut_132 test = new MinCut_132();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minCut("aab"));
        assertEquals(0, test.minCut("a"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minCut(null));
        assertEquals(0, test.minCut("aa"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.minCut("abcba"));
    }

    @Test
    public void testHappyMultipleCuts() {
        assertEquals(1, test.minCut("ab"));
    }

    @Test
    public void testHappyLongerString() {
        // "cabababcbc" needs cuts
        assertEquals(3, test.minCut("cabababcbc"));
    }

    @Test
    public void testEdgeEmptyString() {
        assertEquals(0, test.minCut(""));
    }

    @Test
    public void testEdgeSingleChar() {
        assertEquals(0, test.minCut("z"));
    }

    @Test
    public void testHappyAllDistinct() {
        // "abcde" -> each char is a palindrome, need 4 cuts
        assertEquals(4, test.minCut("abcde"));
    }

    @Test
    public void testEdgeEntirePalindrome() {
        assertEquals(0, test.minCut("racecar"));
    }

    @Test
    public void testNegativeWorstCase() {
        // All different chars
        assertEquals(5, test.minCut("abcdef"));
    }

    @Test
    public void testGiantCase() {
        // 500 'a's -> entire string is palindrome, 0 cuts
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 500; i++) sb.append('a');
        assertEquals(0, test.minCut(sb.toString()));
    }
}
