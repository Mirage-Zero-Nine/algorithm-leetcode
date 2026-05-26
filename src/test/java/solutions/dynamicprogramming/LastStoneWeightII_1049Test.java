package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastStoneWeightII_1049Test {

    private final LastStoneWeightII_1049 test = new LastStoneWeightII_1049();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.lastStoneWeightII(new int[]{2, 7, 4, 1, 8, 1}));
        assertEquals(5, test.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.lastStoneWeightII(new int[]{1}));
        assertEquals(0, test.lastStoneWeightII(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.lastStoneWeightII(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    public void testTwoEqualStones() {
        assertEquals(0, test.lastStoneWeightII(new int[]{5, 5}));
    }

    @Test
    public void testTwoDifferentStones() {
        assertEquals(1, test.lastStoneWeightII(new int[]{3, 4}));
    }

    @Test
    public void testThreeStones() {
        assertEquals(0, test.lastStoneWeightII(new int[]{1, 2, 3}));
    }

    @Test
    public void testAllSameStones() {
        assertEquals(0, test.lastStoneWeightII(new int[]{4, 4, 4, 4}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(0, test.lastStoneWeightII(new int[]{10, 10}));
    }

    @Test
    public void testGiantCase() {
        assertEquals(0, test.lastStoneWeightII(new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89}));
    }

    @Test
    public void testLargeWeightDifference() {
        assertEquals(89, test.lastStoneWeightII(new int[]{1, 100, 10}));
    }

    @Test
    public void testGiantCase2() {
        int[] stones = new int[30];
        for (int i = 0; i < 30; i++) {
            stones[i] = i + 1;
        }
        // sum = 465, half = 232, best subset sum = 232 or 233 -> result = 465 - 2*232 = 1
        assertEquals(1, test.lastStoneWeightII(stones));
    }
}
