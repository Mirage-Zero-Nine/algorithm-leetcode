package solution.stack;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LargestRectangleArea_84Test {
    private final LargestRectangleArea_84 l = new LargestRectangleArea_84();

    @Test public void testClassic() { assertEquals(10, l.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3})); }
    @Test public void testSingle() { assertEquals(1, l.largestRectangleArea(new int[]{1})); }
    @Test public void testEmpty() { assertEquals(0, l.largestRectangleArea(new int[]{})); }
    @Test public void testIncreasing() { assertEquals(12, l.largestRectangleArea(new int[]{1, 2, 3, 4, 5, 6})); }
    @Test public void testDecreasing() { assertEquals(12, l.largestRectangleArea(new int[]{6, 5, 4, 3, 2, 1})); }
    @Test public void testAllSame() { assertEquals(15, l.largestRectangleArea(new int[]{3, 3, 3, 3, 3})); }
    @Test public void testTwoBars() { assertEquals(4, l.largestRectangleArea(new int[]{2, 4})); }
    @Test public void testValley() { assertEquals(5, l.largestRectangleArea(new int[]{5, 1, 5})); }
    @Test public void testLargeFirst() { assertEquals(10000, l.largestRectangleArea(new int[]{10000, 2, 3, 4, 5})); }
    @Test public void testGiant() {
        int[] heights = new int[10000];
        java.util.Arrays.fill(heights, 1);
        assertEquals(10000, l.largestRectangleArea(heights));
    }

    // --- New tricky/happy/negative/large tests ---

    @Test
    public void testTwoEqualBars() {
        assertEquals(10, l.largestRectangleArea(new int[]{5, 5}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, l.largestRectangleArea(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testSinglePeak() {
        // [1,2,3,2,1]: height 2 spans indices 1-3 (width 3) -> area 6; height 3 spans 1 -> area 3
        assertEquals(6, l.largestRectangleArea(new int[]{1, 2, 3, 2, 1}));
    }

    @Test
    public void testSingleValley() {
        // [3,1,3]: min height 1 spans all 3 -> area 3; height 3 spans 1 -> area 3; max = 3
        assertEquals(3, l.largestRectangleArea(new int[]{3, 1, 3}));
    }

    @Test
    public void testAllMaxValue() {
        int n = 100;
        int[] heights = new int[n];
        java.util.Arrays.fill(heights, 10000);
        assertEquals(n * 10000, l.largestRectangleArea(heights));
    }

    @Test
    public void testSingleTallBarAmongZeros() {
        assertEquals(5, l.largestRectangleArea(new int[]{0, 0, 5, 0, 0}));
    }

    @Test
    public void testPlateau() {
        // [4,4,4,2,2,2]: height 4 spans 3 -> 12; height 2 spans 6 -> 12; max = 12
        assertEquals(12, l.largestRectangleArea(new int[]{4, 4, 4, 2, 2, 2}));
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rand = new Random(42L);
        int n = 200; // small enough for O(n^2) brute-force
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = rand.nextInt(100) + 1;
        }
        int expected = bruteForce(heights);
        assertEquals(expected, l.largestRectangleArea(heights));
    }

    private int bruteForce(int[] heights) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int minH = heights[i];
            for (int j = i; j < heights.length; j++) {
                minH = Math.min(minH, heights[j]);
                max = Math.max(max, minH * (j - i + 1));
            }
        }
        return max;
    }
}
