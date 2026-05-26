package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class Trap_42Test {

    private final Trap_42 test = new Trap_42();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(9, test.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trap(new int[]{1, 2}));
        assertEquals(0, test.trap(new int[]{3, 2, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 0, 2}));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(2, test.trap(new int[]{2, 0, 2}));
        assertEquals(7, test.trap(new int[]{3, 0, 2, 0, 4}));
        assertEquals(1, test.trap(new int[]{1, 0, 1}));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.trap(new int[]{}));
        assertEquals(0, test.trap(new int[]{5}));
        assertEquals(0, test.trap(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(43, test.trap(new int[]{5, 0, 0, 0, 5, 0, 1, 0, 4, 0, 2, 0, 5}));
    }

    @Test
    public void testFlat() {
        assertEquals(0, test.trap(new int[]{3, 3, 3, 3}));
    }

    @Test
    public void testValley() {
        assertEquals(8, test.trap(new int[]{4, 0, 0, 4}));
    }

    @Test
    public void testMultipleValleys() {
        assertEquals(6, test.trap(new int[]{3, 0, 3, 0, 3}));
    }

    @Test
    public void testGiantStaircase() {
        int[] height = new int[200];
        for (int i = 0; i < 100; i++) height[i] = i;
        for (int i = 100; i < 200; i++) height[i] = 199 - i;
        // symmetric mountain, no water trapped
        assertEquals(0, test.trap(height));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.trap(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testStrictlyDecreasing() {
        assertEquals(0, test.trap(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testPlateauValley() {
        // [3,0,0,0,3] -> 3*3 = 9
        assertEquals(9, test.trap(new int[]{3, 0, 0, 0, 3}));
    }

    @Test
    public void testVShapeUnequalWalls() {
        // [5,0,3] -> min(5,3)-0 = 3
        assertEquals(3, test.trap(new int[]{5, 0, 3}));
    }

    @Test
    public void testWShape() {
        // [4,0,2,0,4] -> valley1: min(4,2)*1=2 water at idx1 (4-0=4 actually min(4,4)... let me compute)
        // idx1: leftMax=4, rightMax=4, water=4-0=4; idx2: leftMax=4, rightMax=4, water=4-2=2; idx3: leftMax=4, rightMax=4, water=4-0=4 => total=10
        assertEquals(10, test.trap(new int[]{4, 0, 2, 0, 4}));
    }

    @Test
    public void testLargeInputKnownTotal() {
        // [10, 0,0,...,0 (998 zeros), 10] -> 10 * 998 = 9980
        int[] height = new int[1000];
        height[0] = 10;
        height[999] = 10;
        assertEquals(9980, test.trap(height));
    }

    @Test
    public void testWaterLessThanOrEqualSumOfHeights() {
        int[] height = {4, 2, 0, 3, 2, 5, 1, 0, 3, 2, 1};
        int water = test.trap(height);
        int sum = IntStream.of(height).sum();
        assertTrue(water <= sum, "Water trapped should be <= sum of heights");
    }

    @Test
    public void testReversalInvariance() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] reversed = new int[height.length];
        for (int i = 0; i < height.length; i++) reversed[i] = height[height.length - 1 - i];
        assertEquals(test.trap(height), test.trap(reversed));
    }

    @Test
    public void testRandomReversalInvariance() {
        Random rng = new Random(42L);
        for (int trial = 0; trial < 50; trial++) {
            int n = rng.nextInt(100) + 3;
            int[] height = new int[n];
            for (int i = 0; i < n; i++) height[i] = rng.nextInt(100);
            int[] reversed = new int[n];
            for (int i = 0; i < n; i++) reversed[i] = height[n - 1 - i];
            assertEquals(test.trap(height), test.trap(reversed),
                    "Reversal invariance failed on trial " + trial);
        }
    }
}
