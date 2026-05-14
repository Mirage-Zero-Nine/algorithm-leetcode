package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AvoidFlood_1488Test {

    private final AvoidFlood_1488 test = new AvoidFlood_1488();

    @Test
    public void testHappyCases() {
        assertValidPlan(new int[]{1, 2, 0, 0, 2, 1}, test.avoidFlood(new int[]{1, 2, 0, 0, 2, 1}));
        assertValidPlan(new int[]{1, 0, 2, 0, 2, 1}, test.avoidFlood(new int[]{1, 0, 2, 0, 2, 1}));
    }

    @Test
    public void testNegativeImpossibleCase() {
        assertEquals(0, test.avoidFlood(new int[]{1, 2, 1, 2}).length);
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertArrayEquals(new int[0], test.avoidFlood(new int[0]));

        int[] allDry = test.avoidFlood(new int[]{0, 0, 0});
        assertEquals(3, allDry.length);
        assertEquals(1, allDry[0]);
        assertEquals(1, allDry[1]);
        assertEquals(1, allDry[2]);

        assertValidPlan(new int[]{5}, test.avoidFlood(new int[]{5}));
    }

    @Test
    public void testLargeCase() {
        int[] rains = {1, 2, 3, 0, 1, 0, 2, 0, 3, 4, 0, 4};
        assertValidPlan(rains, test.avoidFlood(rains));
    }

    @Test
    public void testHappySimpleDry() {
        int[] rains = {1, 0, 1};
        assertValidPlan(rains, test.avoidFlood(rains));
    }

    @Test
    public void testHappyMultipleLakes() {
        int[] rains = {1, 2, 0, 0, 1, 2};
        assertValidPlan(rains, test.avoidFlood(rains));
    }

    @Test
    public void testNegativeImmediateFlood() {
        assertEquals(0, test.avoidFlood(new int[]{1, 1}).length);
    }

    @Test
    public void testNegativeDryTooLate() {
        // Rain on lake 1, rain on lake 2, rain on lake 1 again - dry day is after second rain on lake 1
        assertEquals(0, test.avoidFlood(new int[]{1, 2, 1, 0}).length);
    }

    @Test
    public void testEdgeSingleRain() {
        int[] result = test.avoidFlood(new int[]{3});
        assertArrayEquals(new int[]{-1}, result);
    }

    @Test
    public void testEdgeOnlyDryDays() {
        int[] result = test.avoidFlood(new int[]{0, 0});
        assertEquals(2, result.length);
        assertTrue(result[0] > 0);
        assertTrue(result[1] > 0);
    }

    @Test
    public void testGiantCase() {
        // Pattern: rain lake i, then dry, repeated
        int[] rains = new int[2000];
        for (int i = 0; i < 1000; i++) {
            rains[2 * i] = 1;
            rains[2 * i + 1] = 0;
        }
        int[] result = test.avoidFlood(rains);
        assertValidPlan(rains, result);
    }

    private static void assertValidPlan(int[] rains, int[] plan) {
        if (plan.length == 0) {
            throw new AssertionError("Expected a valid plan");
        }
        assertEquals(rains.length, plan.length);

        java.util.Map<Integer, Boolean> full = new java.util.HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] > 0) {
                assertEquals(-1, plan[i]);
                assertTrue(!full.getOrDefault(rains[i], false));
                full.put(rains[i], true);
            } else {
                assertTrue(plan[i] > 0);
                full.put(plan[i], false);
            }
        }
    }
}
