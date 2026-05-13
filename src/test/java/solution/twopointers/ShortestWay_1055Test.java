package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestWay_1055Test {

    private final ShortestWay_1055 test = new ShortestWay_1055();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.shortestWay("abc", "abcbc"));
        assertEquals(-1, test.shortestWay("abc", "acdbc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(3, test.shortestWay("xyz", "xzyxz"));
        assertEquals(1, test.shortestWay("abc", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.shortestWay("ab", "ababababab"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(2, test.shortestWay("abc", "abcabc"));
        assertEquals(3, test.shortestWay("abc", "cbcabc"));
        assertEquals(2, test.shortestWay("xyz", "xzy"));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.shortestWay("abc", ""));
        assertEquals(1, test.shortestWay("aaaaa", "aaa"));
        assertEquals(-1, test.shortestWay("abc", "d"));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(8, test.shortestWay("abcd", "abcd".repeat(8)));
    }

    @Test
    public void testSingleCharacterSourceRequiresManyPasses() {
        assertEquals(6, test.shortestWay("a", "aaaaaa"));
    }

    @Test
    public void testImpossibleWhenTargetContainsMissingCharacterMidway() {
        assertEquals(-1, test.shortestWay("ab", "aabca"));
    }

    @Test
    public void testInterleavedPatternAcrossMultipleSubsequences() {
        assertEquals(4, test.shortestWay("abc", "cabcabca"));
    }

    @Test
    public void testLargeUnbalancedPattern() {
        assertEquals(50, test.shortestWay("ab", "a".repeat(50)));
    }
}
