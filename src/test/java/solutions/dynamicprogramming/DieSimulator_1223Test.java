package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DieSimulator_1223Test {

    private final DieSimulator_1223 test = new DieSimulator_1223();

    @Test
    public void testHappyCases() {
        assertEquals(34, test.dieSimulator(2, new int[]{1, 1, 2, 2, 2, 3}));
        assertEquals(30, test.dieSimulator(2, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(6, test.dieSimulator(1, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(181, test.dieSimulator(3, new int[]{1, 1, 1, 2, 2, 3}));
    }

    @Test
    public void testSingleRollNoLimit() {
        // n=1, any rollMax, always 6 outcomes
        assertEquals(6, test.dieSimulator(1, new int[]{6, 6, 6, 6, 6, 6}));
    }

    @Test
    public void testTwoRollsNoLimit() {
        // n=2, rollMax all >= 2, no constraint active -> 6*6 = 36
        assertEquals(36, test.dieSimulator(2, new int[]{2, 2, 2, 2, 2, 2}));
    }

    @Test
    public void testThreeRollsAllMaxOne() {
        // n=3, rollMax all 1: no consecutive same -> 6 * 5 * 5 = 150
        assertEquals(150, test.dieSimulator(3, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testFourRollsAllMaxOne() {
        // n=4, rollMax all 1: 6 * 5 * 5 * 5 = 750
        assertEquals(750, test.dieSimulator(4, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testTwoRollsHighMax() {
        // n=2, rollMax all 15 (effectively no constraint) -> 36
        assertEquals(36, test.dieSimulator(2, new int[]{15, 15, 15, 15, 15, 15}));
    }

    @Test
    public void testThreeRollsMixedMax() {
        assertEquals(216, test.dieSimulator(3, new int[]{3, 3, 3, 3, 3, 3}));
    }

    @Test
    public void testNegativeLikeConstraint() {
        // All max=1 with n=2: can't repeat -> 6*5 = 30
        assertEquals(30, test.dieSimulator(2, new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        // Large n to test performance and modular arithmetic
        int result = test.dieSimulator(20, new int[]{8, 8, 8, 8, 8, 8});
        // Just verify it returns a positive value within mod range
        assertEquals(true, result > 0 && result < 1000000007);
    }
}
