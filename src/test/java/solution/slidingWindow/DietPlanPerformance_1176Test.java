package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DietPlanPerformance_1176Test {

    private final DietPlanPerformance_1176 test = new DietPlanPerformance_1176();

    @Test
    public void testHappyCase1() {
        int[] calories = {1, 2, 3, 4, 5};
        assertEquals(0, test.dietPlanPerformance(calories, 1, 3, 3));
    }

    @Test
    public void testHappyCase2() {
        int[] calories = {3, 2};
        assertEquals(1, test.dietPlanPerformance(calories, 2, 0, 1));
    }

    @Test
    public void testHappyCase3() {
        int[] calories = {6, 5, 0, 0};
        assertEquals(0, test.dietPlanPerformance(calories, 2, 1, 5));
    }

    @Test
    public void testHappyCase4() {
        int[] calories = {1, 2, 3, 4, 5};
        // k=2, lower=3, upper=5
        // [1,2]=3 score: 0
        // [2,3]=5 score: 0
        // [3,4]=7 score: +1
        // [4,5]=9 score: +2
        assertEquals(2, test.dietPlanPerformance(calories, 2, 3, 5));
    }

    @Test
    public void testHappyCase5() {
        int[] calories = {10, 10, 10, 10};
        assertEquals(3, test.dietPlanPerformance(calories, 2, 10, 15));
    }

    @Test
    public void testNegativeCase() {
        int[] calories = {0, 0, 0};
        assertEquals(-3, test.dietPlanPerformance(calories, 1, 1, 5));
    }

    @Test
    public void testEdgeCaseSingleDay() {
        assertEquals(-1, test.dietPlanPerformance(new int[]{5}, 1, 10, 20));
        assertEquals(1, test.dietPlanPerformance(new int[]{25}, 1, 10, 20));
        assertEquals(0, test.dietPlanPerformance(new int[]{15}, 1, 10, 20));
    }

    @Test
    public void testEdgeCaseKEqualsLength() {
        int[] calories = {1, 2, 3};
        assertEquals(1, test.dietPlanPerformance(calories, 3, 1, 5));
        assertEquals(-1, test.dietPlanPerformance(calories, 3, 10, 15));
    }

    @Test
    public void testEdgeCaseScoreGoesBothWays() {
        int[] calories = {5, 0, 5, 0};
        // k=2, lower=3, upper=4
        // [5,0]=5 -> +1
        // [0,5]=5 -> +2
        // [5,0]=5 -> +3
        assertEquals(3, test.dietPlanPerformance(calories, 2, 3, 4));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[] calories = new int[n];
        for (int i = 0; i < n; i++) {
            calories[i] = 10;
        }
        // k=1, lower=15, upper=20. score should be -n.
        assertEquals(-n, test.dietPlanPerformance(calories, 1, 15, 20));
    }
}
