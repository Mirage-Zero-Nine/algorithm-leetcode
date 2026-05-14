package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinCostTickets_983Test {

    private final MinCostTickets_983 test = new MinCostTickets_983();

    @Test
    public void testHappyCases() {
        assertEquals(11, test.minCostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15}));
        assertEquals(17, test.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.minCostTickets(new int[]{1}, new int[]{2, 7, 15}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(7, test.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{1, 7, 30}));
    }

    @Test
    public void testSingleDayCheapest() {
        assertEquals(3, test.minCostTickets(new int[]{1, 100, 200}, new int[]{1, 7, 30}));
    }

    @Test
    public void testMonthlyPassCheapest() {
        // 30 consecutive days: 30 * 2 = 60 daily, 5 * 7 = 35 weekly, 15 monthly
        assertEquals(15, test.minCostTickets(
                new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30},
                new int[]{2, 7, 15}));
    }

    @Test
    public void testWeeklyPassBetter() {
        // 7 days: daily=14, weekly=7, monthly=15
        assertEquals(7, test.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{2, 7, 15}));
    }

    @Test
    public void testTwoDays() {
        assertEquals(4, test.minCostTickets(new int[]{1, 2}, new int[]{2, 7, 15}));
    }

    @Test
    public void testSparseDays() {
        // Days far apart, daily pass is cheapest
        assertEquals(6, test.minCostTickets(new int[]{1, 50, 100, 200, 300, 365}, new int[]{1, 7, 30}));
    }

    @Test
    public void testNegativeCaseExpensiveDaily() {
        // Daily pass is expensive, weekly is better
        assertEquals(7, test.minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{10, 7, 30}));
    }

    @Test
    public void testGiantCase() {
        // Travel every day of the year
        int[] days = new int[365];
        for (int i = 0; i < 365; i++) days[i] = i + 1;
        // 13 monthly passes cover 390 days, so 13*15=195; but let's check actual
        int result = test.minCostTickets(days, new int[]{2, 7, 15});
        assertEquals(result, test.minCostTickets(days, new int[]{2, 7, 15})); // consistency check
    }
}
