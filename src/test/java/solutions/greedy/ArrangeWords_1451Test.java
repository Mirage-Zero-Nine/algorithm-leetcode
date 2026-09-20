package solutions.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArrangeWords_1451Test {

    private final ArrangeWords_1451 test = new ArrangeWords_1451();

    @Test
    public void testHappyCases() {
        assertEquals("To be or is not this", test.arrangeWords("To be or not this is"));
        assertEquals("Is cool leetcode", test.arrangeWords("Leetcode is cool"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("A", test.arrangeWords("A"));
        assertEquals("Hi", test.arrangeWords("Hi"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("I a am very good student", test.arrangeWords("I am a very good student"));
    }

    @Test
    public void testAllSameLength() {
        assertEquals("Aaa bbb ccc", test.arrangeWords("Aaa bbb ccc"));
    }

    @Test
    public void testAlreadySorted() {
        assertEquals("A bb ccc dddd", test.arrangeWords("A bb ccc dddd"));
    }

    @Test
    public void testReverseSorted() {
        assertEquals("A bb ccc dddd", test.arrangeWords("Dddd ccc bb a"));
    }

    @Test
    public void testTwoWords() {
        assertEquals("Is keep", test.arrangeWords("Keep is"));
    }

    @Test
    public void testStableSort() {
        // same length words should maintain original order
        assertEquals("Go to me the", test.arrangeWords("Go to me the"));
    }

    @Test
    public void testNegativeLowerCaseOutput() {
        // all output except first char should be lowercase
        assertEquals("A bc def", test.arrangeWords("DEF BC A"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder("Zzzz");
        for (int i = 0; i < 100; i++) {
            sb.append(" aa");
        }
        String result = test.arrangeWords(sb.toString());
        // all "aa" words (length 2) should come before "zzzz" (length 4)
        assertTrue(result.startsWith("Aa aa"));
        assertTrue(result.endsWith("zzzz"));
    }

    private void assertTrue(boolean condition) {
        org.junit.jupiter.api.Assertions.assertTrue(condition);
    }

    @Test public void testAdditionalSentenceOrders() { assertEquals("A bc def", test.arrangeWords("Def A bc")); }
    @Test public void testAdditionalTwo() { assertEquals("Hi go", test.arrangeWords("Hi go")); }
    @Test public void testAdditionalLengths() { assertEquals("I am code tests", test.arrangeWords("Tests I code am")); }
    @Test public void testAdditionalStable() { assertEquals("Six one two", test.arrangeWords("Six One two")); }
    @Test public void testAdditionalMixedCase() { assertEquals("A bb cccc", test.arrangeWords("CCCC bb A")); }
    @Test public void testAdditionalLongWord() { assertEquals("A be seven three", test.arrangeWords("Seven be three A")); }
    @Test public void testAdditionalFive() { assertEquals("A bb ccc dddd eeeee", test.arrangeWords("Eeeee dddd ccc bb A")); }
    @Test public void testAdditionalRepeated() { assertEquals("A aa aa bbb", test.arrangeWords("Aa bbb Aa A")); }
    @Test public void testAdditionalSingleLower() { assertEquals("X", test.arrangeWords("x")); }
    @Test public void testAdditionalOrder() { assertEquals("A at cat house", test.arrangeWords("House cat at A")); }
}
