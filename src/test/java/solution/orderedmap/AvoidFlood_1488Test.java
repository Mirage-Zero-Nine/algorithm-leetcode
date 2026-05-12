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
