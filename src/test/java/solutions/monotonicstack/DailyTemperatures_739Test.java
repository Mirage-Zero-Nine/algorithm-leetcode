package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import java.util.Random;

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

    // --- NEW TESTS ---

    @Test public void testEmptyArray() {
        assertArrayEquals(new int[]{}, d.dailyTemperatures(new int[]{}));
    }

    @Test public void testStrictlyIncreasingLonger() {
        assertArrayEquals(new int[]{1, 1, 1, 1, 0}, d.dailyTemperatures(new int[]{30, 31, 32, 33, 34}));
    }

    @Test public void testStrictlyDecreasingLonger() {
        assertArrayEquals(new int[]{0, 0, 0, 0, 0}, d.dailyTemperatures(new int[]{100, 99, 98, 97, 96}));
    }

    @Test public void testPlateauThenJump() {
        assertArrayEquals(new int[]{3, 2, 1, 0}, d.dailyTemperatures(new int[]{70, 70, 70, 80}));
    }

    @Test public void testEqualMaxFollowedByWarmer() {
        // [80, 80, 81] -> first 80 waits 2 days, second 80 waits 1 day, 81 waits 0
        assertArrayEquals(new int[]{2, 1, 0}, d.dailyTemperatures(new int[]{80, 80, 81}));
    }

    @Test public void testLargeRandomCrossCheckBruteForce() {
        Random rng = new Random(42L);
        int[] temps = new int[1000];
        for (int i = 0; i < 1000; i++) temps[i] = 30 + rng.nextInt(71); // [30, 100]

        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = i + 1; j < 1000; j++) {
                if (temps[j] > temps[i]) { expected[i] = j - i; break; }
            }
        }

        assertArrayEquals(expected, d.dailyTemperatures(temps));
    }

    @Test public void testPropertyResultNonNegativeAndLengthMatch() {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = d.dailyTemperatures(temps);
        assertEquals(temps.length, result.length);
        for (int r : result) assertTrue(r >= 0, "result[i] must be >= 0");
    }

    @Test public void testPropertyWarmerDayCorrectness() {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = d.dailyTemperatures(temps);
        for (int i = 0; i < result.length; i++) {
            if (result[i] > 0) {
                assertTrue(temps[i + result[i]] > temps[i],
                        "T[i+result[i]] must be > T[i] when result[i] > 0");
            }
        }
    }

    @Test public void testAllSameLonger() {
        int[] temps = new int[10];
        int[] expected = new int[10];
        java.util.Arrays.fill(temps, 50);
        assertArrayEquals(expected, d.dailyTemperatures(temps));
    }

    @Test public void testLeetCodeExample() {
        // Explicit LeetCode example verification
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                d.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }
}
