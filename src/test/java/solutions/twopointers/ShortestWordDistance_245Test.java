package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestWordDistance_245Test {

    private final ShortestWordDistance_245 test = new ShortestWordDistance_245();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        assertEquals(1, test.shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
    }

    @Test
    public void testEdgeCases() {
        // word1 == word2 case
        assertEquals(3, test.shortestWordDistance(new String[]{"a", "b", "c", "a"}, "a", "a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.shortestWordDistance(new String[]{"a", "b", "c", "d", "e"}, "a", "b"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(2, test.shortestWordDistance(new String[]{"one", "x", "two", "y", "one"}, "two", "one"));
        assertEquals(1, test.shortestWordDistance(new String[]{"red", "blue", "red"}, "red", "blue"));
        assertEquals(2, test.shortestWordDistance(new String[]{"a", "x", "a", "y", "a"}, "a", "a"));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(1, test.shortestWordDistance(new String[]{"same", "same"}, "same", "same"));
        assertEquals(4, test.shortestWordDistance(new String[]{"start", "a", "b", "c", "end"}, "start", "end"));
        assertEquals(1, test.shortestWordDistance(new String[]{"p", "q", "r"}, "q", "r"));
    }

    @Test
    public void testAdditionalGiantCase() {
        String[] words = new String[100];
        for (int i = 0; i < words.length; i++) {
            words[i] = "filler";
        }
        words[10] = "alpha";
        words[70] = "beta";
        words[72] = "alpha";
        assertEquals(2, test.shortestWordDistance(words, "beta", "alpha"));
    }

    @Test
    public void testSameWordAdjacent() {
        assertEquals(1, test.shortestWordDistance(new String[]{"x", "x", "y"}, "x", "x"));
    }

    @Test
    public void testDifferentWordsAtEnds() {
        assertEquals(4, test.shortestWordDistance(new String[]{"a", "b", "c", "d", "e"}, "a", "e"));
    }

    @Test
    public void testGiantCaseSameWord() {
        String[] words = new String[1000];
        for (int i = 0; i < 1000; i++) words[i] = "noise";
        words[100] = "target";
        words[500] = "target";
        words[501] = "target";
        assertEquals(1, test.shortestWordDistance(words, "target", "target"));
    }

    @Test
    public void testMultipleOccurrencesDifferentWords() {
        assertEquals(1, test.shortestWordDistance(
            new String[]{"a", "b", "a", "b", "a"}, "a", "b"));
    }
}
