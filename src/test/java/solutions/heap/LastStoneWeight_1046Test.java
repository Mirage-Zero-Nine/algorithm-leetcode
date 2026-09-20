package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LastStoneWeight_1046Test {

    private final LastStoneWeight_1046 test = new LastStoneWeight_1046();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        assertEquals(2, test.lastStoneWeight(new int[]{10, 4, 2, 10}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.lastStoneWeight(new int[]{1, 1}));
        assertEquals(9, test.lastStoneWeight(new int[]{9}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.lastStoneWeight(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
    }

    @Test
    public void testTwoStonesDifferent() {
        assertEquals(3, test.lastStoneWeight(new int[]{5, 2}));
    }

    @Test
    public void testAllSameWeight() {
        assertEquals(0, test.lastStoneWeight(new int[]{4, 4, 4, 4}));
    }

    @Test
    public void testOddCountSameWeight() {
        assertEquals(5, test.lastStoneWeight(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testDescendingWeights() {
        assertEquals(1, test.lastStoneWeight(new int[]{10, 5, 3, 1}));
    }

    @Test
    public void testAscendingWeights() {
        assertEquals(1, test.lastStoneWeight(new int[]{1, 3, 5, 10}));
    }

    @Test
    public void testGiantCase() {
        int[] stones = new int[1000];
        for (int i = 0; i < 1000; i++) stones[i] = 1;
        assertEquals(0, test.lastStoneWeight(stones));
    }

    @Test
    public void testLargeWeights() {
        assertEquals(0, test.lastStoneWeight(new int[]{1000, 1000}));
    }

    @Test public void testEmptyInput() { assertEquals(0, test.lastStoneWeight(new int[]{})); }
    @Test public void testThreeStones() { assertEquals(1, test.lastStoneWeight(new int[]{2, 3, 4})); }
    @Test public void testUnequalPair() { assertEquals(1, test.lastStoneWeight(new int[]{1, 2})); }
    @Test public void testZeros() { assertEquals(0, test.lastStoneWeight(new int[]{0, 0, 0})); }
    @Test public void testOneLargeRemaining() { assertEquals(8, test.lastStoneWeight(new int[]{10, 1, 1})); }
    @Test public void testBalancedPairs() { assertEquals(0, test.lastStoneWeight(new int[]{1, 2, 3, 4})); }
    @Test public void testRepeatedInvocation() { test.lastStoneWeight(new int[]{1, 2}); assertEquals(9, test.lastStoneWeight(new int[]{9})); }
    @Test public void testMaximumSingleWeight() { assertEquals(10000, test.lastStoneWeight(new int[]{10000})); }
    @Test public void testOddCountPairing() { assertEquals(1, test.lastStoneWeight(new int[]{7, 5, 3, 1, 1})); }
    @Test public void testManyDistinctWeights() { assertEquals(1, test.lastStoneWeight(new int[]{1, 2, 4, 8, 16})); }
}
