package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestWordDistance245Test {

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
}
