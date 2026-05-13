package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxArea_11Test {

    private final MaxArea_11 test = new MaxArea_11();

    @Test
    public void testHappyCases() {
        assertEquals(49, test.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, test.maxArea(new int[]{1, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxArea(null));
        assertEquals(0, test.maxArea(new int[]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(24, test.maxArea(new int[]{1, 2, 4, 3, 5, 6, 7, 8, 9}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.maxArea(new int[]{2, 3}));
        assertEquals(5, test.maxArea(new int[]{5, 100}));
    }

    @Test
    public void testAllSameHeight() {
        assertEquals(4, test.maxArea(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testIncreasingHeights() {
        assertEquals(6, test.maxArea(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testDecreasingHeights() {
        assertEquals(6, test.maxArea(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testTallEnds() {
        assertEquals(800, test.maxArea(new int[]{100, 1, 1, 1, 1, 1, 1, 1, 100}));
    }

    @Test
    public void testSingleTallLine() {
        assertEquals(3, test.maxArea(new int[]{1, 1, 1, 100}));
    }

    @Test
    public void testGiantCase() {
        int[] heights = new int[10000];
        for (int i = 0; i < 10000; i++) {
            heights[i] = i + 1;
        }
        // Max area: min(heights[0], heights[9999]) * 9999 = 1 * 9999 = 9999? No.
        // Actually with increasing heights, best is min(h[i], h[j]) * (j-i).
        // Best pair: e.g. min(5000, 10000) * 5000 = 5000*5000=25000000. Let's just verify it runs.
        int result = test.maxArea(heights);
        assertEquals(25000000, result);
    }
}
