package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

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

    @Test
    public void testAllZeros() {
        assertEquals(0, test.maxArea(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testSymmetricPalindromic() {
        // [1,3,5,7,5,3,1] -> best is min(3,3)*4=12 or min(5,5)*2=10 or min(1,7)*3=3... actually min(3,3)*4=12
        assertEquals(12, test.maxArea(new int[]{1, 3, 5, 7, 5, 3, 1}));
    }

    @Test
    public void testSingleTallPoleAtStart() {
        // [100, 1, 1, 1, 1] -> min(100,1)*4=4
        assertEquals(4, test.maxArea(new int[]{100, 1, 1, 1, 1}));
    }

    @Test
    public void testTwoTallPolesAtExtremes() {
        // [50, 1, 1, 1, 1, 1, 1, 1, 1, 50] -> min(50,50)*9=450
        assertEquals(450, test.maxArea(new int[]{50, 1, 1, 1, 1, 1, 1, 1, 1, 50}));
    }

    @Test
    public void testMonotonicWithOutlierInMiddle() {
        // [1,2,3,4,100,5,6,7,8] -> best: min(100,8)*(8-4)=32
        assertEquals(32, test.maxArea(new int[]{1, 2, 3, 4, 100, 5, 6, 7, 8}));
    }

    @Test
    public void testPropertyReversedSameResult() {
        int[] original = {3, 1, 2, 4, 5, 2, 6};
        int[] reversed = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        assertEquals(test.maxArea(original), test.maxArea(reversed));
    }

    @Test
    public void testLargeRandomCrossCheck() {
        Random rand = new Random(42L);
        int n = 500; // small enough for brute force O(n^2)
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = rand.nextInt(10000);
        }
        // brute force
        int expected = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                expected = Math.max(expected, Math.min(heights[i], heights[j]) * (j - i));
            }
        }
        assertEquals(expected, test.maxArea(heights));
    }

    @Test
    public void testLargeRandom10000Runs() {
        Random rand = new Random(42L);
        int[] heights = new int[10000];
        for (int i = 0; i < 10000; i++) {
            heights[i] = rand.nextInt(100000);
        }
        int result = test.maxArea(heights);
        assertTrue(result > 0, "Result should be positive for non-zero random heights");
    }

    @Test
    public void testAllSameHeightLarge() {
        int[] heights = new int[100];
        java.util.Arrays.fill(heights, 7);
        // max area = 7 * 99 = 693
        assertEquals(693, test.maxArea(heights));
    }
}
