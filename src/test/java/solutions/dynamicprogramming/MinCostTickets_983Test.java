package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

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
    public void testAllTravelDaySubsetsAndRepresentativeCosts() {
        // Every non-empty subset of this valid ten-day travel range is tested.
        int numberOfDays = 10;
        int[] validCosts = {1, 2, 3, 7, 10, 15};

        for (int subset = 1; subset < (1 << numberOfDays); subset++) {
            int[] days = daysForSubset(subset, numberOfDays);

            for (int oneDayCost : validCosts) {
                for (int sevenDayCost : validCosts) {
                    for (int thirtyDayCost : validCosts) {
                        int[] costs = {oneDayCost, sevenDayCost, thirtyDayCost};
                        assertEquals(bruteForceMinimum(days, costs), test.minCostTickets(days, costs),
                                "days=" + subset + ", costs=" + oneDayCost + "," + sevenDayCost + ","
                                        + thirtyDayCost);
                    }
                }
            }
        }
    }

    private int[] daysForSubset(int subset, int numberOfDays) {
        int[] days = new int[Integer.bitCount(subset)];
        int index = 0;
        for (int day = 1; day <= numberOfDays; day++) {
            if ((subset & (1 << (day - 1))) != 0) {
                days[index++] = day;
            }
        }
        return days;
    }

    /**
     * Independent exhaustive oracle: buy each possible pass on the first
     * uncovered travel day and recursively try all three choices. Memoization
     * only avoids recalculating an identical suffix; it does not use the
     * implementation's queue or recurrence.
     */
    private int bruteForceMinimum(int[] days, int[] costs) {
        int[] minimumFrom = new int[days.length + 1];
        Arrays.fill(minimumFrom, -1);
        minimumFrom[days.length] = 0;
        return bruteForceMinimum(days, costs, 0, minimumFrom);
    }

    private int bruteForceMinimum(int[] days, int[] costs, int firstUncovered, int[] minimumFrom) {
        if (minimumFrom[firstUncovered] != -1) {
            return minimumFrom[firstUncovered];
        }

        int day = days[firstUncovered];
        int oneDayEnd = firstUncovered + 1;
        int sevenDayEnd = firstDayOutsideCoverage(days, firstUncovered, day + 7);
        int thirtyDayEnd = firstDayOutsideCoverage(days, firstUncovered, day + 30);

        minimumFrom[firstUncovered] = Math.min(costs[0] + bruteForceMinimum(days, costs, oneDayEnd, minimumFrom),
                Math.min(costs[1] + bruteForceMinimum(days, costs, sevenDayEnd, minimumFrom),
                        costs[2] + bruteForceMinimum(days, costs, thirtyDayEnd, minimumFrom)));
        return minimumFrom[firstUncovered];
    }

    private int firstDayOutsideCoverage(int[] days, int firstUncovered, int exclusiveEndDay) {
        int next = firstUncovered;
        while (next < days.length && days[next] < exclusiveEndDay) {
            next++;
        }
        return next;
    }
}
