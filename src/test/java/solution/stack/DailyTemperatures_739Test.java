package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DailyTemperatures_739Test {
    private final DailyTemperatures_739 d = new DailyTemperatures_739();

    @Test public void testSingle() { assertArrayEquals(new int[]{0}, d.dailyTemperatures(new int[]{30})); }
    @Test public void testIncreasing() { assertArrayEquals(new int[]{1, 1, 0}, d.dailyTemperatures(new int[]{30, 31, 32})); }
    @Test public void testDecreasing() { assertArrayEquals(new int[]{0, 0, 0}, d.dailyTemperatures(new int[]{32, 31, 30})); }
    @Test public void testClassic() {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, d.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }
    @Test public void testAllSame() { assertArrayEquals(new int[]{0, 0, 0}, d.dailyTemperatures(new int[]{30, 30, 30})); }

    // Additional happy cases
    @Test public void testTwoElements() { assertArrayEquals(new int[]{1, 0}, d.dailyTemperatures(new int[]{30, 31})); }
    @Test public void testVShape() { assertArrayEquals(new int[]{2, 1, 0}, d.dailyTemperatures(new int[]{50, 40, 60})); }

    // Negative case: no warmer day exists for any
    @Test public void testAllMax() { assertArrayEquals(new int[]{0, 0, 0, 0}, d.dailyTemperatures(new int[]{100, 100, 100, 100})); }

    // Edge cases
    @Test public void testWarmerAtEnd() { assertArrayEquals(new int[]{4, 3, 2, 1, 0}, d.dailyTemperatures(new int[]{30, 30, 30, 30, 31})); }
    @Test public void testAlternating() { assertArrayEquals(new int[]{0, 1, 0, 1, 0}, d.dailyTemperatures(new int[]{50, 40, 50, 40, 50})); }

    // Giant test case
    @Test public void testGiant() {
        int[] temps = new int[10000];
        for (int i = 0; i < 10000; i++) temps[i] = 30 + (i % 71);
        int[] result = d.dailyTemperatures(temps);
        assertEquals(10000, result.length);
    }
}
