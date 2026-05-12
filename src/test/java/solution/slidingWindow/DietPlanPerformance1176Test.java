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
}
