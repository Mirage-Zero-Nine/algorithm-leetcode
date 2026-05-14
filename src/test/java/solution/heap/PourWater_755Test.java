package solution.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PourWater_755Test {

    private final PourWater_755 test = new PourWater_755();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 2, 2, 3, 2, 2, 2}, test.pourWater(new int[]{2, 1, 1, 2, 1, 2, 2}, 4, 3));
        assertArrayEquals(new int[]{2, 3, 3, 4}, test.pourWater(new int[]{1, 2, 3, 4}, 2, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{2, 2, 2}, test.pourWater(new int[]{2, 1, 2}, 1, 1));
        assertArrayEquals(new int[]{5}, test.pourWater(new int[]{2}, 3, 0));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{3, 3, 3, 3, 3, 3, 2}, test.pourWater(new int[]{1, 2, 3, 2, 1, 2, 2}, 7, 3));
    }

    @Test
    public void testZeroVolume() {
        assertArrayEquals(new int[]{2, 1, 2}, test.pourWater(new int[]{2, 1, 2}, 0, 1));
    }

    @Test
    public void testWaterFlowsLeft() {
        // Water should flow left since left is lower
        assertArrayEquals(new int[]{2, 2, 2}, test.pourWater(new int[]{1, 2, 2}, 1, 2));
    }

    @Test
    public void testWaterFlowsRight() {
        // Left is not lower, water flows right
        assertArrayEquals(new int[]{3, 3, 2}, test.pourWater(new int[]{3, 3, 1}, 1, 1));
    }

    @Test
    public void testWaterStaysAtK() {
        // Both sides are higher, water stays at K
        assertArrayEquals(new int[]{5, 3, 5}, test.pourWater(new int[]{5, 2, 5}, 1, 1));
    }

    @Test
    public void testLeftEdge() {
        assertArrayEquals(new int[]{2, 1, 1}, test.pourWater(new int[]{1, 1, 1}, 1, 0));
    }

    @Test
    public void testRightEdge() {
        assertArrayEquals(new int[]{1, 1, 2}, test.pourWater(new int[]{1, 1, 1}, 1, 2));
    }

    @Test
    public void testMultipleDropsFillValley() {
        assertArrayEquals(new int[]{3, 3, 3, 3, 3}, test.pourWater(new int[]{3, 1, 1, 1, 3}, 6, 2));
    }

    @Test
    public void testGiantCase() {
        int[] heights = new int[100];
        heights[0] = 100;
        heights[99] = 100;
        for (int i = 1; i < 99; i++) heights[i] = 1;
        int[] result = test.pourWater(heights, 50, 50);
        // Water should spread out in the valley
        int sum = 0;
        for (int h : result) sum += h;
        // Original sum + 50 drops
        assertEquals(348, sum);
    }
}
