package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HIndex_274Test {

    private final HIndex_274 test = new HIndex_274();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.hIndex(new int[]{3, 0, 6, 1, 5}));
        assertEquals(1, test.hIndex(new int[]{1, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.hIndex(new int[]{0}));
        assertEquals(1, test.hIndex(new int[]{1}));
        assertEquals(0, test.hIndex(new int[]{0, 0, 0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.hIndex(new int[]{5, 5, 5, 5, 5}));
        assertEquals(5, test.hIndex(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testAllHighCitations() {
        assertEquals(3, test.hIndex(new int[]{100, 100, 100}));
    }

    @Test
    public void testSingleHighCitation() {
        assertEquals(1, test.hIndex(new int[]{100}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.hIndex(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testIncreasingSequence() {
        assertEquals(3, test.hIndex(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testDecreasingSequence() {
        assertEquals(3, test.hIndex(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testAllSameValue() {
        assertEquals(4, test.hIndex(new int[]{4, 4, 4, 4}));
        assertEquals(3, test.hIndex(new int[]{3, 3, 3, 3}));
    }

    @Test
    public void testGiantCase() {
        int[] citations = new int[1000];
        for (int i = 0; i < 1000; i++) {
            citations[i] = i + 1;
        }
        // With citations 1..1000, h-index should be 500 (500 papers with >= 500 citations)
        assertEquals(500, test.hIndex(citations));
    }
}
