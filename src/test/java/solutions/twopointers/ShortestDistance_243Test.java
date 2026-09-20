package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestDistance_243Test {

    private final ShortestDistance_243 test = new ShortestDistance_243();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        assertEquals(1, test.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.shortestDistance(new String[]{"a", "b"}, "a", "b"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.shortestDistance(new String[]{"a", "b", "c", "d", "e"}, "a", "b"));
        assertEquals(2, test.shortestDistance(new String[]{"a", "b", "c", "d", "e"}, "a", "c"));
    }

    @Test
    public void testAdjacentWords() {
        assertEquals(1, test.shortestDistance(new String[]{"hello", "world"}, "hello", "world"));
    }

    @Test
    public void testMultipleOccurrencesPicksShortest() {
        assertEquals(1, test.shortestDistance(new String[]{"a", "c", "b", "a"}, "a", "b"));
    }

    @Test
    public void testWordsAtEnds() {
        assertEquals(4, test.shortestDistance(new String[]{"a", "x", "x", "x", "b"}, "a", "b"));
    }

    @Test
    public void testReversedOrder() {
        assertEquals(1, test.shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "practice", "makes"));
    }

    @Test
    public void testSameDistanceMultiplePairs() {
        assertEquals(1, test.shortestDistance(new String[]{"a", "b", "c", "a", "b"}, "a", "b"));
    }

    @Test
    public void testNegativeCaseWordsNotAdjacent() {
        assertEquals(3, test.shortestDistance(new String[]{"a", "x", "y", "b"}, "a", "b"));
    }

    @Test
    public void testGiantCase() {
        int size = 10000;
        String[] words = new String[size];
        for (int i = 0; i < size; i++) {
            words[i] = "filler";
        }
        words[0] = "alpha";
        words[9999] = "beta";
        words[5000] = "alpha";
        assertEquals(4999, test.shortestDistance(words, "alpha", "beta"));
    }

    @Test
    public void testMultipleOccurrencesBothWords() {
        assertEquals(1, test.shortestDistance(new String[]{"a", "b", "a", "b", "a"}, "a", "b"));
    }

    @Test public void testWordsAdjacentAtEnd() { assertEquals(1,test.shortestDistance(new String[]{"x","a","b"},"a","b")); }
    @Test public void testWordsSeparatedByOne() { assertEquals(2,test.shortestDistance(new String[]{"a","x","b"},"a","b")); }
    @Test public void testFirstWordRepeated() { assertEquals(1,test.shortestDistance(new String[]{"a","b","a","x"},"a","b")); }
    @Test public void testSecondWordRepeated() { assertEquals(1,test.shortestDistance(new String[]{"a","x","b","a","b"},"a","b")); }
    @Test public void testLongGap() { assertEquals(6,test.shortestDistance(new String[]{"a","x","x","x","x","x","b"},"a","b")); }
    @Test public void testReverseAdjacent() { assertEquals(1,test.shortestDistance(new String[]{"b","a"},"a","b")); }
    @Test public void testThreeOccurrences() { assertEquals(1,test.shortestDistance(new String[]{"a","b","x","a","x","b"},"a","b")); }
    @Test public void testUniqueWords() { assertEquals(2,test.shortestDistance(new String[]{"one","two","three"},"one","three")); }
    @Test public void testLongNames() { assertEquals(1,test.shortestDistance(new String[]{"alpha","beta"},"alpha","beta")); }
}
