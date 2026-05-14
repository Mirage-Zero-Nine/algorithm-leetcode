package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JobScheduling_1235Test {

    private final JobScheduling_1235 test = new JobScheduling_1235();

    @Test
    public void testHappyCases() {
        assertEquals(120, test.jobScheduling(new int[]{1, 2, 3, 3}, new int[]{3, 4, 5, 6}, new int[]{50, 10, 40, 70}));
        assertEquals(150, test.jobScheduling(new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(50, test.jobScheduling(new int[]{1}, new int[]{2}, new int[]{50}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.jobScheduling(new int[]{1, 1, 1}, new int[]{2, 3, 4}, new int[]{5, 6, 4}));
    }

    @Test
    public void testNonOverlapping() {
        assertEquals(90, test.jobScheduling(new int[]{1, 3, 5}, new int[]{2, 4, 6}, new int[]{20, 30, 40}));
    }

    @Test
    public void testAllOverlapping() {
        assertEquals(50, test.jobScheduling(new int[]{1, 1, 1}, new int[]{5, 5, 5}, new int[]{10, 50, 30}));
    }

    @Test
    public void testTwoJobs() {
        assertEquals(70, test.jobScheduling(new int[]{1, 3}, new int[]{3, 5}, new int[]{30, 40}));
    }

    @Test
    public void testAdjacentJobs() {
        // Jobs that end at time X can start another at time X
        assertEquals(100, test.jobScheduling(new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 5}, new int[]{25, 25, 25, 25}));
    }

    @Test
    public void testSingleHighProfit() {
        assertEquals(100, test.jobScheduling(new int[]{1, 1, 1, 1}, new int[]{10, 10, 10, 10}, new int[]{100, 50, 30, 20}));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int[] start = new int[n], end = new int[n], profit = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = i;
            end[i] = i + 1;
            profit[i] = 10;
        }
        assertEquals(1000, test.jobScheduling(start, end, profit));
    }

    @Test
    public void testSkipLowProfitOverlap() {
        assertEquals(250, test.jobScheduling(new int[]{1, 2, 3, 6}, new int[]{5, 5, 5, 8}, new int[]{100, 50, 60, 150}));
    }
}
