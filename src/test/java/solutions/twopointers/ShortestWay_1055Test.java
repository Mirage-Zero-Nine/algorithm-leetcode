package solutions.twopointers;

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

    @Test public void testTwoPassReordered() { assertEquals(2,test.shortestWay("abc","acb")); }
    @Test public void testRepeatedSingleSource() { assertEquals(3,test.shortestWay("ab","ababab")); }
    @Test public void testMissingAtEnd() { assertEquals(-1,test.shortestWay("abc","abz")); }
    @Test public void testOneCharacterTarget() { assertEquals(1,test.shortestWay("abc","b")); }
    @Test public void testTargetEmptyAgain() { assertEquals(0,test.shortestWay("abc","")); }
    @Test public void testAlternatingTarget() { assertEquals(3,test.shortestWay("ab","baba")); }
    @Test public void testSourceLonger() { assertEquals(3,test.shortestWay("abcdef","fed")); }
    @Test public void testManyPasses() { assertEquals(10,test.shortestWay("a","a".repeat(10))); }
    @Test public void testInterleavedSource() { assertEquals(3,test.shortestWay("xyz","xzyxz")); }
    @Test public void testImpossibleSourceEmpty() { assertEquals(-1,test.shortestWay("","a")); }
}
