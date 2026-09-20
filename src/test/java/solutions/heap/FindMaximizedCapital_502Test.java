package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaximizedCapital_502Test {

    private final FindMaximizedCapital_502 test = new FindMaximizedCapital_502();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        assertEquals(11, test.findMaximizedCapital(3, 1, new int[]{2, 3, 5}, new int[]{1, 1, 2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findMaximizedCapital(2, 0, new int[]{1, 2}, new int[]{1, 2}));
        assertEquals(5, test.findMaximizedCapital(0, 5, new int[]{1, 2}, new int[]{0, 0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(23, test.findMaximizedCapital(5, 0, new int[]{1, 2, 3, 4, 5, 10}, new int[]{0, 1, 1, 2, 3, 5}));
    }

    @Test
    public void testSingleProject() {
        assertEquals(1, test.findMaximizedCapital(1, 0, new int[]{1}, new int[]{0}));
    }

    @Test
    public void testKLargerThanProjects() {
        assertEquals(6, test.findMaximizedCapital(10, 0, new int[]{1, 2, 3}, new int[]{0, 0, 0}));
    }

    @Test
    public void testAllProjectsTooExpensive() {
        assertEquals(0, test.findMaximizedCapital(3, 0, new int[]{5, 6, 7}, new int[]{1, 2, 3}));
    }

    @Test
    public void testHighInitialCapital() {
        assertEquals(108, test.findMaximizedCapital(2, 100, new int[]{5, 3, 2}, new int[]{0, 0, 0}));
    }

    @Test
    public void testGreedyChoice() {
        // With W=0, can only do project with capital=0 (profit=1), then W=1, can do capital<=1 (profit=3)
        assertEquals(4, test.findMaximizedCapital(2, 0, new int[]{1, 3, 2}, new int[]{0, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int n = 1000;
        int[] profits = new int[n];
        int[] capital = new int[n];
        for (int i = 0; i < n; i++) {
            profits[i] = 1;
            capital[i] = 0;
        }
        assertEquals(100, test.findMaximizedCapital(100, 0, profits, capital));
    }

    @Test
    public void testIncrementalUnlock() {
        // Each project unlocks the next: profit=1 each, capital=0,1,2,3,4
        assertEquals(5, test.findMaximizedCapital(5, 0, new int[]{1, 1, 1, 1, 1}, new int[]{0, 1, 2, 3, 4}));
    }

    @Test public void testNoProjects() { assertEquals(7, test.findMaximizedCapital(3, 7, new int[]{}, new int[]{})); }
    @Test public void testZeroProfitProjects() { assertEquals(3, test.findMaximizedCapital(5, 3, new int[]{0, 0}, new int[]{0, 0})); }
    @Test public void testExactCapitalThreshold() { assertEquals(8, test.findMaximizedCapital(1, 5, new int[]{3}, new int[]{5})); }
    @Test public void testProjectUnlockedAfterProfit() { assertEquals(6, test.findMaximizedCapital(2, 0, new int[]{2, 4}, new int[]{0, 2})); }
    @Test public void testChooseLargestImmediateProfit() { assertEquals(12, test.findMaximizedCapital(2, 0, new int[]{1, 10, 2}, new int[]{0, 0, 0})); }
    @Test public void testKOne() { assertEquals(15, test.findMaximizedCapital(1, 10, new int[]{5, 4}, new int[]{0, 0})); }
    @Test public void testCapitalOrderingUnsorted() { assertEquals(8, test.findMaximizedCapital(3, 0, new int[]{2, 5, 1}, new int[]{4, 0, 2})); }
    @Test public void testNegativeProfitStillAtMostK() { assertEquals(7, test.findMaximizedCapital(2, 3, new int[]{-1, 5}, new int[]{0, 3})); }
    @Test public void testRepeatedIndependentInvocation() { test.findMaximizedCapital(1, 0, new int[]{1}, new int[]{0}); assertEquals(4, test.findMaximizedCapital(1, 2, new int[]{2}, new int[]{2})); }
    @Test public void testCapitalExactlyUnlocksMultiple() { assertEquals(8, test.findMaximizedCapital(2, 2, new int[]{1, 5}, new int[]{2, 2})); }
}
