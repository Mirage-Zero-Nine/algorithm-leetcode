package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShortestDistance243Test {

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
}
