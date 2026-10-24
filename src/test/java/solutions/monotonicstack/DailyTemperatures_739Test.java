package solutions.monotonicstack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DailyTemperatures_739Test {
    @Test
    public void testSingle() {
        assertBoth(new int[]{0}, new int[]{30});
    }

    @Test
    public void testIncreasing() {
        assertBoth(new int[]{1, 1, 0}, new int[]{30, 31, 32});
    }

    @Test
    public void testDecreasing() {
        assertBoth(new int[]{0, 0, 0}, new int[]{32, 31, 30});
    }

    @Test
    public void testClassic() {
        assertBoth(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }

    @Test
    public void testAllSame() {
        assertBoth(new int[]{0, 0, 0}, new int[]{30, 30, 30});
    }

    // Additional happy cases
    @Test
    public void testTwoElements() {
        assertBoth(new int[]{1, 0}, new int[]{30, 31});
    }

    @Test
    public void testVShape() {
        assertBoth(new int[]{2, 1, 0}, new int[]{50, 40, 60});
    }

    // Negative case: no warmer day exists for any
    @Test
    public void testAllMax() {
        assertBoth(new int[]{0, 0, 0, 0}, new int[]{100, 100, 100, 100});
    }

    // Edge cases
    @Test
    public void testWarmerAtEnd() {
        assertBoth(new int[]{4, 3, 2, 1, 0}, new int[]{30, 30, 30, 30, 31});
    }

    @Test
    public void testAlternating() {
        assertBoth(new int[]{0, 1, 0, 1, 0}, new int[]{50, 40, 50, 40, 50});
    }

    // Giant test case
    @Test
    public void testGiant() {
        runIsolated("giant");
    }

    // --- NEW TESTS ---

    @Test
    public void testEmptyArray() {
        assertBoth(new int[]{}, new int[]{});
    }

    @Test
    public void testNullArrayExtension() {
        assertBoth(new int[0], null);
    }

    @Test
    public void testInputIsNotModifiedAndCallsDoNotShareState() {
        assertBoth(new int[]{3, 2, 1, 0}, new int[]{80, 70, 70, 90});
        assertBoth(new int[]{1, 0}, new int[]{30, 31});
        assertBoth(new int[]{0, 0}, new int[]{100, 100});

        DailyTemperatures_739 stackSolver = new DailyTemperatures_739();
        assertArrayEquals(new int[]{1, 0}, stackSolver.dailyTemperatures(new int[]{30, 31}));
        assertArrayEquals(new int[]{0, 0}, stackSolver.dailyTemperatures(new int[]{100, 100}));
        DailyTemperatures_739 arrayStackSolver = new DailyTemperatures_739();
        assertArrayEquals(new int[]{1, 0}, arrayStackSolver.dailyTemperaturesWithArrayStack(new int[]{30, 31}));
        assertArrayEquals(new int[]{0, 0}, arrayStackSolver.dailyTemperaturesWithArrayStack(new int[]{100, 100}));
    }

    @Test
    public void testExhaustiveShortInputsAgainstBruteForceOracle() {
        runIsolated("exhaustive");
    }

    @Test
    public void testStrictlyIncreasingLonger() {
        assertBoth(new int[]{1, 1, 1, 1, 0}, new int[]{30, 31, 32, 33, 34});
    }

    @Test
    public void testStrictlyDecreasingLonger() {
        assertBoth(new int[]{0, 0, 0, 0, 0}, new int[]{100, 99, 98, 97, 96});
    }

    @Test
    public void testPlateauThenJump() {
        assertBoth(new int[]{3, 2, 1, 0}, new int[]{70, 70, 70, 80});
    }

    @Test
    public void testEqualMaxFollowedByWarmer() {
        // [80, 80, 81] -> first 80 waits 2 days, second 80 waits 1 day, 81 waits 0
        assertBoth(new int[]{2, 1, 0}, new int[]{80, 80, 81});
    }

    @Test
    public void testLargeRandomCrossCheckBruteForce() {
        Random rng = new Random(42L);
        int[] temps = new int[1000];
        for (int i = 0; i < 1000; i++) temps[i] = 30 + rng.nextInt(71); // [30, 100]

        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = i + 1; j < 1000; j++) {
                if (temps[j] > temps[i]) {
                    expected[i] = j - i;
                    break;
                }
            }
        }

        assertBoth(expected, temps);
    }

    @Test
    public void testPropertyResultNonNegativeAndLengthMatch() {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
        assertBoth(expected, temps);
        assertProperties(expected, temps);
    }

    @Test
    public void testPropertyWarmerDayCorrectness() {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
        assertBoth(expected, temps);
        assertProperties(expected, temps);
    }

    @Test
    public void testAllSameLonger() {
        int[] temps = new int[10];
        int[] expected = new int[10];
        Arrays.fill(temps, 50);
        assertBoth(expected, temps);
    }

    @Test
    public void testLeetCodeExample() {
        // Explicit LeetCode example verification
        assertBoth(new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }

    private static void assertBoth(int[] expected, int[] temperatures) {
        assertApproach(expected, temperatures, "Stack<Integer>", false);
        assertApproach(expected, temperatures, "primitive int[] stack", true);
    }

    private static void assertApproach(int[] expected, int[] temperatures, String name, boolean arrayStack) {
        int[] input = temperatures == null ? null : temperatures.clone();
        int[] result = arrayStack
                ? new DailyTemperatures_739().dailyTemperaturesWithArrayStack(input)
                : new DailyTemperatures_739().dailyTemperatures(input);
        assertArrayEquals(expected, result, name + " temperatures=" + Arrays.toString(temperatures));
        if (temperatures != null) {
            assertArrayEquals(temperatures, input, name + " modified its input");
        }
    }

    private static void assertProperties(int[] result, int[] temperatures) {
        assertEquals(temperatures.length, result.length);
        for (int i = 0; i < result.length; i++) {
            assertTrue(result[i] >= 0, "result[i] must be >= 0");
            if (result[i] > 0) {
                assertTrue(temperatures[i + result[i]] > temperatures[i],
                        "T[i+result[i]] must be > T[i] when result[i] > 0");
                for (int j = i + 1; j < i + result[i]; j++) {
                    assertTrue(temperatures[j] <= temperatures[i],
                            "No earlier day may be warmer than day " + i);
                }
            }
        }
    }

    private static void runIsolated(String mode) {
        String javaCommand = java.nio.file.Path.of(System.getProperty("java.home"), "bin", "java").toString();
        Process process = null;
        try {
            process = new ProcessBuilder(javaCommand, "-cp", System.getProperty("java.class.path"),
                    Worker.class.getName(), mode).redirectErrorStream(true).start();
            if (!process.waitFor(10, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                assertTrue(process.waitFor(2, TimeUnit.SECONDS), "Timed-out worker did not terminate: " + mode);
                throw new AssertionError("Timed out while checking " + mode);
            }
            String output = new String(process.getInputStream().readAllBytes());
            assertEquals(0, process.exitValue(), "Worker failed for " + mode + ": " + output);
        } catch (InterruptedException e) {
            if (process != null) {
                process.destroyForcibly();
                try {
                    process.waitFor(2, TimeUnit.SECONDS);
                } catch (InterruptedException cleanup) {
                    e.addSuppressed(cleanup);
                }
            }
            Thread.currentThread().interrupt();
            throw new AssertionError("Interrupted while checking " + mode, e);
        } catch (java.io.IOException e) {
            throw new AssertionError("Could not start worker for " + mode, e);
        }
    }

    public static class Worker {
        public static void main(String[] args) {
            if (args[0].equals("exhaustive")) {
                int[] alphabet = {30, 50, 100};
                for (int length = 1; length <= 7; length++) {
                    checkAllArrays(new int[length], 0, alphabet);
                }
            } else if (args[0].equals("giant")) {
                // Retain the original 10,000-day cyclic fixture and verify every answer.
                int[] originalGiant = new int[10000];
                for (int i = 0; i < originalGiant.length; i++) originalGiant[i] = 30 + i % 71;
                assertMatchesBoundedTemperatureOracle(originalGiant);

                int[] temperatures = new int[30000];
                Random random = new Random(739L);
                for (int i = 0; i < temperatures.length; i++) {
                    temperatures[i] = 30 + random.nextInt(71);
                }
                assertMatchesBoundedTemperatureOracle(temperatures);

                // A long nonincreasing input leaves every index unresolved and exercises
                // the maximum stack size; equal temperatures must not resolve one another.
                Arrays.fill(temperatures, 100);
                assertMatchesBoundedTemperatureOracle(temperatures);
            } else {
                throw new IllegalArgumentException("Unknown mode: " + args[0]);
            }
        }

        private static void checkAllArrays(int[] temperatures, int index, int[] alphabet) {
            if (index == temperatures.length) {
                assertMatchesBruteForce(temperatures);
                return;
            }
            for (int temperature : alphabet) {
                temperatures[index] = temperature;
                checkAllArrays(temperatures, index + 1, alphabet);
            }
        }

        private static void assertMatchesBruteForce(int[] temperatures) {
            int[] expected = new int[temperatures.length];
            for (int i = 0; i < temperatures.length; i++) {
                for (int j = i + 1; j < temperatures.length; j++) {
                    if (temperatures[j] > temperatures[i]) {
                        expected[i] = j - i;
                        break;
                    }
                }
            }
            assertBoth(expected, temperatures);
        }

        private static void assertMatchesBoundedTemperatureOracle(int[] temperatures) {
            int[] nextIndex = new int[101];
            Arrays.fill(nextIndex, -1);
            int[] expected = new int[temperatures.length];
            for (int i = temperatures.length - 1; i >= 0; i--) {
                int nearest = Integer.MAX_VALUE;
                for (int warmer = temperatures[i] + 1; warmer <= 100; warmer++) {
                    if (nextIndex[warmer] >= 0) {
                        nearest = Math.min(nearest, nextIndex[warmer]);
                    }
                }
                if (nearest != Integer.MAX_VALUE) expected[i] = nearest - i;
                nextIndex[temperatures[i]] = i;
            }
            assertBoth(expected, temperatures);
        }
    }
}
