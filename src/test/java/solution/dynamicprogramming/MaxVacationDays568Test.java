package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxVacationDays568Test {

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
}
