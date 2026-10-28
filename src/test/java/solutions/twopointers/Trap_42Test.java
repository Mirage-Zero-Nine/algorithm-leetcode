package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

@Timeout(15)
@ExtendWith(Trap_42Test.ProcessTimeoutExtension.class)
public class Trap_42Test {

    private final Trap_42 test = new Trap_42();

    @Test
    public void testHappyCases() {
        assertTrappedWater(6, new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        assertTrappedWater(9, new int[]{4, 2, 0, 3, 2, 5});
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrappedWater(0, new int[]{1, 2});
        assertTrappedWater(0, new int[]{3, 2, 1});
    }

    @Test
    public void testLargeCase() {
        assertTrappedWater(9, new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 0, 2});
    }

    @Test
    public void testAdditionalHappyCases() {
        assertTrappedWater(2, new int[]{2, 0, 2});
        assertTrappedWater(7, new int[]{3, 0, 2, 0, 4});
        assertTrappedWater(1, new int[]{1, 0, 1});
    }

    @Test
    public void testAdditionalEdgeCases() {
        // Empty input is an explicitly supported extension beyond LeetCode's nonempty arrays.
        assertTrappedWater(0, new int[]{});
        assertTrappedWater(0, new int[]{5});
        assertTrappedWater(0, new int[]{1, 2, 3, 4});
    }

    @Test
    public void testAdditionalGiantCase() {
        assertTrappedWater(43, new int[]{5, 0, 0, 0, 5, 0, 1, 0, 4, 0, 2, 0, 5});
    }

    @Test
    public void testFlat() {
        assertTrappedWater(0, new int[]{3, 3, 3, 3});
    }

    @Test
    public void testValley() {
        assertTrappedWater(8, new int[]{4, 0, 0, 4});
    }

    @Test
    public void testMultipleValleys() {
        assertTrappedWater(6, new int[]{3, 0, 3, 0, 3});
    }

    @Test
    public void testGiantStaircase() {
        int[] height = new int[200];
        for (int i = 0; i < 100; i++) height[i] = i;
        for (int i = 100; i < 200; i++) height[i] = 199 - i;
        // symmetric mountain, no water trapped
        assertTrappedWater(0, height);
    }

    @Test
    public void testAllZeros() {
        assertTrappedWater(0, new int[]{0, 0, 0, 0, 0});
    }

    @Test
    public void testStrictlyDecreasing() {
        assertTrappedWater(0, new int[]{5, 4, 3, 2, 1});
    }

    @Test
    public void testPlateauValley() {
        // [3,0,0,0,3] -> 3*3 = 9
        assertTrappedWater(9, new int[]{3, 0, 0, 0, 3});
    }

    @Test
    public void testVShapeUnequalWalls() {
        // [5,0,3] -> min(5,3)-0 = 3
        assertTrappedWater(3, new int[]{5, 0, 3});
    }

    @Test
    public void testWShape() {
        // The outer walls fill the three interior columns with 4 + 2 + 4 units.
        assertTrappedWater(10, new int[]{4, 0, 2, 0, 4});
    }

    @Test
    public void testLargeInputKnownTotal() {
        // [10, 0,0,...,0 (998 zeros), 10] -> 10 * 998 = 9980
        int[] height = new int[1000];
        height[0] = 10;
        height[999] = 10;
        assertTrappedWater(9980, height);
    }

    @Test
    public void testMixedTerrainKnownTotal() {
        int[] height = {4, 2, 0, 3, 2, 5, 1, 0, 3, 2, 1};
        // Water need not be bounded by the sum of bar heights; validate the actual total.
        assertTrappedWater(14, height);
    }

    @Test
    public void testReversalInvariance() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] reversed = new int[height.length];
        for (int i = 0; i < height.length; i++) reversed[i] = height[height.length - 1 - i];
        assertTrappedWater(6, height);
        assertTrappedWater(6, reversed);
    }

    @Test
    public void testRandomReversalInvariance() {
        Random rng = new Random(42L);
        for (int trial = 0; trial < 50; trial++) {
            int n = rng.nextInt(100) + 3;
            int[] height = new int[n];
            for (int i = 0; i < n; i++) height[i] = rng.nextInt(100);
            int[] reversed = new int[n];
            for (int i = 0; i < n; i++) reversed[i] = height[n - 1 - i];
            int expected = referenceWater(height);
            assertTrappedWater(expected, height, "seed=42, trial=" + trial);
            assertTrappedWater(expected, reversed, "seed=42, reversed trial=" + trial);
        }
    }

    @Test
    public void testSingleInteriorPeak() {
        assertTrappedWater(1, new int[]{2, 0, 1});
    }

    @Test
    public void testNullExtensionAndMinimumSizes() {
        // Both methods intentionally accept null and empty input in addition to the official domain.
        assertTrappedWater(0, null);
        assertTrappedWater(0, new int[]{0});
        assertTrappedWater(0, new int[]{100_000});
        assertTrappedWater(0, new int[]{100_000, 0});
        assertTrappedWater(0, new int[]{0, 100_000});
    }

    @Test
    public void testUnequalWallsFromBothDirections() {
        assertTrappedWater(3, new int[]{3, 0, 5});
        assertTrappedWater(6, new int[]{8, 0, 2, 1, 3});
        assertTrappedWater(6, new int[]{3, 1, 2, 0, 8});
    }

    @Test
    public void testEqualHeightWallsAndRaisedFloors() {
        assertTrappedWater(6, new int[]{5, 5, 2, 2, 5, 5});
        assertTrappedWater(3, new int[]{3, 1, 1, 1, 2});
        assertTrappedWater(2, new int[]{3, 2, 2, 3});
    }

    @Test
    public void testPopLayersAndMissingLeftWall() {
        assertTrappedWater(6, new int[]{4, 3, 2, 1, 4});
        assertTrappedWater(4, new int[]{2, 0, 2, 0, 3});
        assertTrappedWater(0, new int[]{0, 1, 2, 3, 0});
        assertTrappedWater(5, new int[]{0, 3, 0, 1, 3, 0});
    }

    @Test
    public void testNewMaximaAndSeparateBasins() {
        assertTrappedWater(6, new int[]{2, 0, 4, 1, 3, 0, 2});
        assertTrappedWater(6, new int[]{2, 0, 3, 1, 4, 0, 2});
        assertTrappedWater(5, new int[]{3, 0, 5, 0, 2});
    }

    @Test
    public void testRepeatedCallsDoNotRetainWater() {
        int[] height = {4, 1, 4};
        assertTrappedWater(3, height);
        assertTrappedWater(0, null);
        assertTrappedWater(0, new int[]{0, 0, 0});
        assertTrappedWater(3, height);
        assertTrappedWater(2, new int[]{2, 0, 2});
    }

    @Test
    public void testMaximumSizeAndMaximumWaterTotal() {
        int[] height = new int[20_000];
        height[0] = height[height.length - 1] = 100_000;
        // This is the largest possible total under the published size/height bounds, close to int's limit.
        assertTrappedWater(1_999_800_000, height);
    }

    @Test
    public void testMaximumSizeStackGrowthAndNoWater() {
        int[] height = new int[20_000];
        for (int i = 0; i < height.length; i++) height[i] = height.length - i;
        assertTrappedWater(0, height);
        for (int i = 0; i < height.length; i++) height[i] = i;
        assertTrappedWater(0, height);
        Arrays.fill(height, 100_000);
        assertTrappedWater(0, height);
    }

    @Test
    public void testMaximumSizePopCascade() {
        int[] height = new int[20_000];
        for (int i = 0; i < height.length - 1; i++) height[i] = height.length - i;
        height[height.length - 1] = 100_000;
        // Interior depths are 1..19,998; the last bar pops the entire decreasing stack.
        assertTrappedWater(19_998 * 19_999 / 2, height);
    }

    @Test
    public void testMaximumSizeAlternatingBasins() {
        int[] height = new int[20_000];
        for (int i = 0; i < height.length; i += 2) height[i] = 100_000;
        // The last zero has no right wall; the other 9,999 zeros are fully enclosed.
        assertTrappedWater(999_900_000, height);
    }

    @Test
    public void testExhaustiveSmallHeightsAgainstIndependentOracle() {
        // Enumerate all 87,381 arrays of lengths 0..8 over heights 0..3, including the empty extension.
        // Longer arrays and larger heights are covered by explicit maximum-size and seeded cases.
        int scenarioCount = 0;
        int arraysAtLength = 1;
        for (int length = 0; length <= 8; length++) {
            for (int encoded = 0; encoded < arraysAtLength; encoded++) {
                int[] height = new int[length];
                int remaining = encoded;
                for (int i = 0; i < length; i++) {
                    height[i] = remaining % 4;
                    remaining /= 4;
                }
                assertTrappedWater(referenceWater(height), height);
                scenarioCount++;
            }
            arraysAtLength *= 4;
        }
        assertEquals(87_381, scenarioCount);
    }

    @Test
    public void testSeededFullHeightRangeAgainstIndependentOracle() {
        Random random = new Random(420_042L);
        for (int trial = 0; trial < 300; trial++) {
            int[] height = new int[1 + random.nextInt(128)];
            for (int i = 0; i < height.length; i++) height[i] = random.nextInt(100_001);
            assertTrappedWater(referenceWater(height), height, "seed=420042, trial=" + trial);
        }
    }

    private void assertTrappedWater(int expected, int[] height) {
        assertTrappedWater(expected, height, "shared contract");
    }

    private void assertTrappedWater(int expected, int[] height, String context) {
        assertApproach(expected, height, context + ", trap", test::trap);
        assertApproach(expected, height, context + ", trapTwoPointers", test::trapTwoPointers);
    }

    private void assertApproach(int expected, int[] height, String context, ToIntFunction<int[]> approach) {
        int[] input = height == null ? null : height.clone();
        Supplier<String> message = () -> context + ", height=" + Arrays.toString(height);
        assertEquals(expected, approach.applyAsInt(input), message);
        assertArrayEquals(height, input, message);
    }

    private static int referenceWater(int[] height) {
        // Independently scan both sides of each column: O(n^2), used only for small generated cases.
        int water = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftWall = 0;
            int rightWall = 0;
            for (int left = 0; left < i; left++) leftWall = Math.max(leftWall, height[left]);
            for (int right = i + 1; right < height.length; right++) rightWall = Math.max(rightWall, height[right]);
            water += Math.max(0, Math.min(leftWall, rightWall) - height[i]);
        }
        return water;
    }

    // Both implementations ignore interruption, so each test runs in a child JVM that can be killed.
    // The 10-second process limit detects hangs; the class timeout also bounds the parent's wait.
    static final class ProcessTimeoutExtension implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> context, ExtensionContext extensionContext)
                throws Throwable {
            Path log = Files.createTempFile("trap-42-test-", ".log");
            Process process = null;
            try {
                process = new ProcessBuilder(
                        System.getProperty("java.home") + "/bin/java", "-cp",
                        System.getProperty("surefire.test.class.path", System.getProperty("java.class.path")),
                        Trap_42Test.class.getName(), context.getExecutable().getName())
                        .redirectErrorStream(true)
                        .redirectOutput(log.toFile())
                        .start();
                if (!process.waitFor(10, TimeUnit.SECONDS)) {
                    fail("test method exceeded enforceable 10-second process limit: "
                            + context.getExecutable().getName());
                }
                if (process.exitValue() != 0) {
                    fail("child test failed: " + context.getExecutable().getName()
                            + System.lineSeparator() + Files.readString(log));
                }
                invocation.skip();
            } finally {
                try {
                    if (process != null && process.isAlive()) {
                        process.destroyForcibly();
                        if (!process.waitFor(2, TimeUnit.SECONDS)) {
                            fail("child test could not be terminated: " + context.getExecutable().getName());
                        }
                    }
                } finally {
                    Files.deleteIfExists(log);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("missing test method");
        try {
            Trap_42Test.class.getDeclaredMethod(args[0]).invoke(new Trap_42Test());
        } catch (InvocationTargetException exception) {
            throw new RuntimeException(exception.getCause());
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
