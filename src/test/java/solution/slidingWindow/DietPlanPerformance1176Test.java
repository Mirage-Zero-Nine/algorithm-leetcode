package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DietPlanPerformance1176Test {

    private final DietPlanPerformance_1176 test = new DietPlanPerformance_1176();

    @Test
    public void testHappyCases() {
        assertEquals(0, test.dietPlanPerformance(new int[]{1, 2, 3, 4, 5}, 1, 3, 3));
        assertEquals(1, test.dietPlanPerformance(new int[]{3, 2}, 2, 0, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.dietPlanPerformance(new int[]{6, 5, 0, 0}, 2, 1, 5));
        assertEquals(0, test.dietPlanPerformance(new int[]{3}, 1, 3, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.dietPlanPerformance(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 3, 10, 15));
    }

    @Test
    public void testAllBelowLower() {
        assertEquals(-5, test.dietPlanPerformance(new int[]{1, 1, 1, 1, 1}, 1, 5, 10));
    }

    @Test
    public void testAllAboveUpper() {
        assertEquals(5, test.dietPlanPerformance(new int[]{10, 10, 10, 10, 10}, 1, 1, 5));
    }

    @Test
    public void testKEqualsLength() {
        assertEquals(1, test.dietPlanPerformance(new int[]{5, 5, 5}, 3, 1, 10));
    }

    @Test
    public void testNegativeScore() {
        assertEquals(-5, test.dietPlanPerformance(new int[]{1, 1, 1, 1, 1}, 1, 2, 10));
    }

    @Test
    public void testMixedResults() {
        // k=2: sums are 3, 5, 7 -> lower=4, upper=6: -1, 0, +1 = 0
        assertEquals(0, test.dietPlanPerformance(new int[]{1, 2, 3, 4}, 2, 4, 6));
    }

    @Test
    public void testSingleDay() {
        assertEquals(0, test.dietPlanPerformance(new int[]{5}, 1, 5, 5));
        assertEquals(-1, test.dietPlanPerformance(new int[]{4}, 1, 5, 10));
        assertEquals(1, test.dietPlanPerformance(new int[]{11}, 1, 5, 10));
    }

    @Test
    public void testGiantCase() {
        int[] calories = new int[100000];
        for (int i = 0; i < 100000; i++) {
            calories[i] = 1;
        }
        // k=100, sum always 100, lower=200, upper=300 -> always below -> -(100000-100+1) = -99901
        assertEquals(-99901, test.dietPlanPerformance(calories, 100, 200, 300));
    }
}
