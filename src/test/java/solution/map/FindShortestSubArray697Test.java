package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindShortestSubArray697Test {

    private final FindShortestSubArray_697 test = new FindShortestSubArray_697();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
        assertEquals(6, test.findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.findShortestSubArray(new int[]{1}));
        assertEquals(1, test.findShortestSubArray(new int[]{1, 2, 3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.findShortestSubArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(10, test.findShortestSubArray(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }
}
