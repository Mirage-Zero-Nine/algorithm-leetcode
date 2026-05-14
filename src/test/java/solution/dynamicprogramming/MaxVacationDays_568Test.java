package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxVacationDays_568Test {

    private final MaxVacationDays_568 test = new MaxVacationDays_568();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.maxVacationDays(new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}, new int[][]{{1, 3, 1}, {6, 0, 3}, {3, 3, 3}}));
        assertEquals(3, test.maxVacationDays(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][]{{1, 1, 1}, {7, 7, 7}, {7, 7, 7}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maxVacationDays(new int[][]{}, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(21, test.maxVacationDays(new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}}, new int[][]{{7, 7, 7}, {7, 7, 7}, {7, 7, 7}}));
    }

    @Test
    public void testSingleCitySingleWeek() {
        assertEquals(5, test.maxVacationDays(new int[][]{{0}}, new int[][]{{5}}));
    }

    @Test
    public void testSingleCityMultipleWeeks() {
        assertEquals(14, test.maxVacationDays(new int[][]{{0}}, new int[][]{{7, 7}}));
    }

    @Test
    public void testNoFlightsStayInCity0() {
        assertEquals(6, test.maxVacationDays(new int[][]{{0, 0}, {0, 0}}, new int[][]{{3, 3}, {7, 7}}));
    }

    @Test
    public void testCanFlyToCity1() {
        assertEquals(14, test.maxVacationDays(new int[][]{{0, 1}, {1, 0}}, new int[][]{{3, 3}, {7, 7}}));
    }

    @Test
    public void testZeroVacationDays() {
        assertEquals(0, test.maxVacationDays(new int[][]{{0, 1}, {1, 0}}, new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void testOneWayFlight() {
        // Can fly from 0 to 1 but not back
        assertEquals(14, test.maxVacationDays(new int[][]{{0, 1}, {0, 0}}, new int[][]{{0, 0}, {7, 7}}));
    }

    @Test
    public void testGiantCase() {
        int n = 10, k = 10;
        int[][] flights = new int[n][n];
        int[][] days = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flights[i][j] = 1;
            }
            flights[i][i] = 0;
            for (int j = 0; j < k; j++) {
                days[i][j] = i; // city i has i vacation days each week
            }
        }
        // Best: always go to city 9 (9 days/week * 10 weeks = 90)
        assertEquals(90, test.maxVacationDays(flights, days));
    }
}
